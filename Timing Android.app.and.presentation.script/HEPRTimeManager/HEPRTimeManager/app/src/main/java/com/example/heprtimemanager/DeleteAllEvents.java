package com.example.heprtimemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DeleteAllEvents extends AppCompatActivity {


    private Button deleteEvents ;
    private RiderRepository riderRepository;
    private static Disposable disposable;
    private static List<Event> eventList;
    TextView eventsCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_all_events);

        deleteEvents = findViewById(R.id.deleteEvents);
        eventsCount = findViewById(R.id.eventsCount);

        riderRepository = new RiderRepository(this.getApplication());
        eventList = new ArrayList<>();

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

                eventsCount.setText("Events No: "+ eventList.size());

            }

        }.start();

        deleteEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                riderRepository.deleteAllEvents();
                Toast.makeText(DeleteAllEvents.this, "All Events Deleted!", Toast.LENGTH_SHORT).show();


            }
        });

    }
}