package com.example.heprtimemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ReadNFCActivity extends AppCompatActivity {

    private final String[][] techList = new String[][] {
            new String[] {
                    NfcA.class.getName(),
                    NfcB.class.getName(),
                    NfcF.class.getName(),
                    NfcV.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
                    MifareUltralight.class.getName(), Ndef.class.getName()
            }
    };

    TextView timeStamp;
    TextView timeStampMili;
    private ImageView imageView;
    private static RiderRepository riderRepository;
    private static String riderName;
    private static List<Event> eventList;
    private static Disposable disposable;
    private static List<TimeEvent> timeEventList;
    private static TimeEvent timeEvent;
    private static int only_read;
    protected PowerManager.WakeLock mWakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_nfcactivity);
        riderRepository = new RiderRepository(this.getApplication());
        imageView = findViewById(R.id.hepr_logo);

        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "myapp:mywakelocktag");
        this.mWakeLock.acquire();


        riderRepository.getAllEvents().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Event>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onNext(List<Event> eventsList) {

                        eventList=eventsList;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("OnComplete:","Subscription Ended..");
                    }
                });


        riderRepository.getAllTimeEvents().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TimeEvent>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onNext(List<TimeEvent> eventsList) {

                        timeEventList=eventsList;

                        if(timeEventList.size()>0) {
                            timeEvent = timeEventList.get(0);
                            only_read=0;
                        }
                        else{
                            only_read=1;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Time Event Error",e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        Log.d("OnComplete:","Subscription Ended..");
                    }
                });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.readnfc_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // creating pending intent:
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // creating intent receiver for NFC events:
        IntentFilter filter = new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        // enabling foreground dispatch for getting intent from NFC event:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, this.techList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // disabling foreground dispatch:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public void onDestroy() {
        this.mWakeLock.release();
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {


            String id = ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));


            new CountDownTimer(500, 500) {

                public void onTick(long millisUntilFinished) {
                    riderRepository.getRiderName(id);
                    riderRepository.getRiderCAT(id);
                    riderRepository.getRiderNumber(id);
                }

                public void onFinish() {

                    riderName = riderRepository.RiderName;

                    if (riderRepository.RiderName == null) {
                        setContentView(R.layout.activity_read_not_assigned);
                    } else {

                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                       // Vibrate for 1000 milliseconds
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(700, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            v.vibrate(700);
                        }

                        setContentView(R.layout.activity_read_pass);
                        timeStamp = (TextView) findViewById(R.id.text_view_timestamp);
                        timeStampMili = (TextView) findViewById(R.id.text_view_timestamp_mili);

                        ((TextView) findViewById(R.id.text_view_id_read)).setText(riderName);


                        Long tsLong = System.currentTimeMillis();
                        String ts = tsLong.toString();

                        timeStamp.setText(getDate(tsLong, "dd/MM/yyyy HH:mm:ss"));
                        timeStampMili.setText("Clasa " + riderRepository.RiderCateg);
                        //------------------------------------------Event add----------------------------------------------//

                        if(only_read == 0){
                        if (riderRepository.RiderName != null) {


                            int inserted = 0;
                            int id_event = 0;


                            if (eventList.size() == 0) {

                                Event event = new Event(id, ts, timeEvent.getCP(),riderRepository.RiderNumber);
                                riderRepository.insertEvent(event);
                                Toast.makeText(ReadNFCActivity.this, "First Rider Saved", Toast.LENGTH_SHORT).show();

                            } else {
                                for (Event event : eventList) {

                                    if (event.getTAG_ID().equals(id)) {
                                        inserted = 1;
                                        id_event = event.getId();
                                    }

                                }

                                if (inserted == 1) {
                                    Event event1 = new Event(id, ts, timeEvent.getCP(),riderRepository.RiderNumber);
                                    event1.setId(id_event);
                                    riderRepository.updateEvent(event1);
                                    Toast.makeText(ReadNFCActivity.this, "Rider Updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Event event1 = new Event(id, ts, timeEvent.getCP(),riderRepository.RiderNumber);
                                    riderRepository.insertEvent(event1);
                                    Toast.makeText(ReadNFCActivity.this, "Rider Saved", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    }
                    }

                }

            }.start();


            new CountDownTimer(1800, 1800) {

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {

                    setContentView(R.layout.activity_read_nfcactivity);
                }

            }.start();


        }
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        DateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String [] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String out= "";

        for(j = 0 ; j < inarray.length ; ++j)
        {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }
}