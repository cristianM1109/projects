package com.example.heprtimemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
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
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AddEditRiderActivity extends AppCompatActivity {

    public static final String EXTRA_NAME =
            "com.example.heprtimemanager.EXTRA_NAME";
    public static final String EXTRA_ID =
            "com.example.heprtimemanager.EXTRA_ID";
    public static final String EXTRA_id =
            "com.example.heprtimemanager.EXTRA_id";
    public static final String EXTRA_CAT =
            "com.example.heprtimemanager.EXTRA_CAT";
    public static final String EXTRA_NUMBER =
            "com.example.heprtimemanager.EXTRA_NUMBER";

    public static String SCANNED = "";

    private EditText editTextName,riderNumber;
    private TextView TAG_ID;
    private TextView SELECTED;
    private NumberPicker numberPicker;
    private String[] category;
    private RiderRepository riderRepository;
    private String last_Scanned="";

    private static int selected;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rider);
        editTextName = findViewById(R.id.rider_name_insert);
        riderRepository = new RiderRepository(this.getApplication());
        riderNumber = findViewById(R.id.rider_number);

        TAG_ID = (TextView) findViewById(R.id.rider_TAG_ID_SCANNED);
        SELECTED = findViewById(R.id.selected_class);
        numberPicker = findViewById(R.id.catgory_picker);

        category = getResources().getStringArray(R.array.catgory);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(4);
        numberPicker.setDisplayedValues(category);
        SELECTED.setText(String.format("Rider's Category: %s",category[numberPicker.getValue()]));


        selected=0;
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SELECTED.setText(String.format("Rider's Category: %s" , category[newVal]));
                selected=newVal;
            }
        });





        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_id)){
            setTitle("Edit Rider");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            TAG_ID.setText(intent.getStringExtra(EXTRA_ID));
            riderNumber.setText(intent.getStringExtra(EXTRA_NUMBER));
            last_Scanned=intent.getStringExtra(EXTRA_ID);
            SELECTED.setText(String.format("Selected Category: %s",category[decodeCateg(intent.getStringExtra(EXTRA_CAT))]));
            numberPicker.setValue(decodeCateg(intent.getStringExtra(EXTRA_CAT)));
            SCANNED=intent.getStringExtra(EXTRA_ID);
        }else{
            setTitle("Add Rider");
        }

    }

    public int decodeCateg(String CAT){

        switch (CAT){
            case "A":
                selected = 0;
                break;
            case "B":
                selected = 1;
                break;

            case "C":
                selected = 2;
                break;

            case "Fete":
                selected = 3;
                break;

            case "Veterani":
                selected = 4;
                break;
        }
        return selected;
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            TAG_ID.setText(ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));
            SCANNED = ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
        }

        new CountDownTimer(500, 500) {

            public void onTick(long millisUntilFinished) {
                riderRepository.getRiderName(SCANNED);
            }

            public void onFinish() {
                if(riderRepository.RiderName != null){
                    Toast.makeText(AddEditRiderActivity.this, "TAG is alredy assigned to "+" "+riderRepository.RiderName, Toast.LENGTH_SHORT).show();
                    SCANNED=last_Scanned;
                    TAG_ID.setText(SCANNED);
                }
            }

        }.start();

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

    private void saveRider(){
        String name = editTextName.getText().toString();
        String tag_id = SCANNED;
        String number = riderNumber.getText().toString();

        if(name.trim().isEmpty()){
            Toast.makeText(this, "Please insert name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(number.trim().isEmpty()){
            Toast.makeText(this, "Please insert rider number!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(tag_id.trim().isEmpty()){
            Toast.makeText(this, "Please scan NFC TAG!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_ID,tag_id);
        data.putExtra(EXTRA_NUMBER,number);

        data.putExtra(EXTRA_CAT,category[selected]);

        int id = getIntent().getIntExtra(EXTRA_id , -1);
        if(id != -1){
            data.putExtra(EXTRA_id,id);
        }

        setResult(RESULT_OK,data);
        SCANNED="";
        finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_rider_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_rider:
                saveRider();
                return true;
            default:
                SCANNED="";
                return super.onOptionsItemSelected(item);
        }
    }
}