package com.example.heprtimemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SelectCPDay extends AppCompatActivity {

    private TextView select_options,getSelect_cp;
    RiderRepository riderRepository;
    Disposable disposable;
    private static List<TimeEvent> timeEvents_;
    private static TimeEvent currentTimeEvent;
    private static int id_e;

    AutoCompleteTextView autoCompleteTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cpday);

        select_options = findViewById(R.id.select_options);
        getSelect_cp = findViewById(R.id.selected_CP);
        riderRepository = new RiderRepository(this.getApplication());
        timeEvents_ = new ArrayList<>();

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

                if(timeEvents_.size() == 0 ){
                    currentTimeEvent = new TimeEvent("Please Select CP","Please Select Day");
                    riderRepository.insertTimeEvent(currentTimeEvent);
                    id_e=1;
                }
                else{
                    currentTimeEvent = timeEvents_.get(0);
                    for(TimeEvent e : timeEvents_) {
                        id_e=e.getId();
                    }
                }

                disposable.dispose();
                select_options.setText("Race Day: "+currentTimeEvent.getDAY());
                getSelect_cp.setText("Checkpoint :"+currentTimeEvent.getCP());

            }

        }.start();



        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        String[] opions = getResources().getStringArray(R.array.CP);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.dropdwon_item,opions);

        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(),false);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String s = (String) parent.getItemAtPosition(position);
                currentTimeEvent.setCP(s);
                currentTimeEvent.setId(id_e);
                getSelect_cp.setText("Checkpoint :"+currentTimeEvent.getCP());
                riderRepository.updateTimeEvent(currentTimeEvent);


            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.cp_day, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.day_1:
                currentTimeEvent.setDAY("Day 1");
                currentTimeEvent.setId(id_e);
                riderRepository.updateTimeEvent(currentTimeEvent);
                select_options.setText("Race Day: : "+currentTimeEvent.getDAY());

                return true;

            case R.id.day_2:
                currentTimeEvent.setDAY("Day 2");
                currentTimeEvent.setId(id_e);
                riderRepository.updateTimeEvent(currentTimeEvent);
                select_options.setText("Race Day: : "+currentTimeEvent.getDAY());


                return true;

            case R.id.day_3:
                currentTimeEvent.setDAY("Day 3");
                currentTimeEvent.setId(id_e);
                riderRepository.updateTimeEvent(currentTimeEvent);
                select_options.setText("Race Day: : "+currentTimeEvent.getDAY());

                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}