package com.example.heprtimemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewSelectConfig extends AppCompatActivity {

    RiderRepository riderRepository;
    Disposable disposable;
    private static List<TimeEvent> timeEvents_;
    TextView day,check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_select_config);

        riderRepository = new RiderRepository(this.getApplication());
        day = findViewById(R.id.day_view);
        check = findViewById(R.id.check_view);

        new CountDownTimer(500, 500) {

            public void onTick(long millisUntilFinished) {
                riderRepository.getAllTimeEvents().subscribeOn(Schedulers.computation()).
                        observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<TimeEvent>>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                disposable=d;
                            }

                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<TimeEvent> timeEvents) {
                                timeEvents_=timeEvents;


                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }

            public void onFinish() {

                if(timeEvents_.size()>0) {
                    day.setText("Day Selected: " + timeEvents_.get(0).getDAY());
                    check.setText("CP Selected: " + timeEvents_.get(0).getCP());
                }
                else{
                    day.setText("Please Select Day");
                    check.setText("Please Select CP");
                }

                disposable.dispose();


            }

        }.start();

    }
}