package com.example.heprtimemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncEvents extends AppCompatActivity {

    private RiderRepository riderRepository;
    private Disposable disposable;
    private static int unSynced;
    private int inserted_count;
    private ProgressDialog progressDoalog;


    private static List<Event> eventList = new ArrayList<>();
    private static List<TimeEvent> timeEventList = new ArrayList<>();
    private TextView unSyncedView;
    private Button sync;
    private static TimeEvent timeEvent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_events);

        progressDoalog = new ProgressDialog(SyncEvents.this);
        riderRepository = new RiderRepository(this.getApplication());
        unSynced=0;
        unSyncedView = findViewById(R.id.unSyncedEv);
        sync = findViewById(R.id.sync_events);
        sync.setEnabled(false);

        new CountDownTimer(500, 500) {

            public void onTick(long millisUntilFinished) {
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
            }

            public void onFinish() {

                disposable.dispose();
                for(Event e :eventList){
                    if(e.getStatus() == 0){
                        unSynced++;
                    }
                }
            }

        }.start();

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
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
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d("OnComplete:","Subscription Ended..");
                            }
                        });
            }

            public void onFinish() {

                disposable.dispose();

                if(timeEventList.size()>0) {
                    timeEvent = timeEventList.get(0);
                    sync.setEnabled(true);
                    unSyncedView.setText("You have "+unSynced+" new events!");
                }
                else{
                    unSyncedView.setText("Please Select your Day and Checkpoint first!");
                    unSyncedView.setTextColor(Color.RED);
                }
            }

        }.start();

        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                insertData(eventList, new RetrofitResponseListener() {
                    @Override
                    public void onSuccess() {
                        inserted_count++;

                        if(inserted_count == 1){
                            progressDoalog.dismiss();
                            progressDoalog = new ProgressDialog(SyncEvents.this);
                            progressDoalog.setTitle("Upload Event List");
                            progressDoalog.setMax(unSynced);
                            progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressDoalog.show();
                        }

                        progressDoalog.incrementProgressBy(1);

                        if(inserted_count == unSynced){
                            Toast.makeText(SyncEvents.this, inserted_count+" riders sent!", Toast.LENGTH_SHORT).show();
                        }

                        unSyncedView.setText("You have "+(unSynced-inserted_count)+" unsynced events!");
                    }

                    @Override
                    public void onFailure() {

                    }
                });

            }
        });


    }


    public void insertData(List<Event> eventList,final RetrofitResponseListener retrofitResponseListener){

        inserted_count=0;
        progressDoalog.setMessage("Connecting server...");


       for(Event e : eventList){

            if(e.getStatus()==0){
                Call<ResponseBody> call=MyClient.getInstance().getMyApi().insertevents(e.getTAG_ID(),e.getTimestamp(),timeEvent.getCP(),timeEvent.getDAY());
            //    Event finalEvent = e;
                if(inserted_count==0){
                    progressDoalog.show();
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                }
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()) {
                            retrofitResponseListener.onSuccess();
                            Log.d("update finish", response.body().toString());
                    //        finalEvent.setStatus(1);
                    //        riderRepository.updateEvent(finalEvent);
                        }
                        else{
                            retrofitResponseListener.onFailure();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("onFailure", t.toString());
                        retrofitResponseListener.onFailure();
                        progressDoalog.dismiss();
                    }
                });
            }
        }

    }


}