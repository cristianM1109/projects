package com.example.heprtimemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewEvents extends AppCompatActivity {


    private EventsViewModel eventsViewModel;
    RecyclerView recyclerView;
    private static EventAdaptor eventAdaptor;
    Disposable disposable;
    private static List<Event> eventList = new ArrayList<>();
    private static List<Rider> ridersList = new ArrayList<>();
    private static List<Event> eventList_new = new ArrayList<>();

    private static String CLASS_TAG_View;
    private RiderRepository riderRepository;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        riderRepository = new RiderRepository(this.getApplication());
        CLASS_TAG_View="A";


        recyclerView = findViewById(R.id.recycler_view_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        eventsViewModel = ViewModelProviders.of(this).get(EventsViewModel.class);

        eventAdaptor = new EventAdaptor();

        new CountDownTimer(500, 500) {

        public void onTick(long millisUntilFinished) {
            eventsViewModel.getAllEvents().subscribeOn(Schedulers.computation())
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

            calculateEvents(CLASS_TAG_View);

        }

    }.start();

    }

    public void calculateEvents(String CLASS_TAG_View){


            if(disposable!=null){
                disposable.dispose();
            }
            new CountDownTimer(1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    riderRepository.getAllRiders_list_by_clas(CLASS_TAG_View).subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<List<Rider>>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposable = d;

                                }

                                @Override
                                public void onNext(List<Rider> riderList) {

                                    ridersList=riderList;
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
                    dispEvents(CLASS_TAG_View);
                    eventAdaptor.setEvents(eventList_new);
                    recyclerView.setAdapter(eventAdaptor);
                }

            }.start();


    }

    public void calculateAllEvents(){


        if(disposable!=null){
            disposable.dispose();
        }
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
                riderRepository.getAllRiders_list().subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<Rider>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable = d;

                            }

                            @Override
                            public void onNext(List<Rider> riderList) {

                                ridersList=riderList;
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
                dispAllEvents();
                eventAdaptor.setEvents(eventList_new);
                recyclerView.setAdapter(eventAdaptor);
            }

        }.start();


    }

    public void dispAllEvents(){


        eventList_new=new ArrayList<>();
        ListIterator<Event> iteratorEvent = eventList.listIterator();
        Event event;


        while(iteratorEvent.hasNext()){

            event = iteratorEvent.next();

            for(Rider rider : ridersList){
                if(rider.getTAG_ID().equals(event.getTAG_ID())){
                    eventList_new.add(new Event(rider.getName(),event.getTimestamp(),event.getCP(),rider.getNumber()));
                }
            }

        }


    }


    public void dispEvents(String CLASS_TAG_View){


        eventList_new=new ArrayList<>();
        ListIterator<Event> iteratorEvent = eventList.listIterator();
        Event event;


        while(iteratorEvent.hasNext()){

            event = iteratorEvent.next();

            for(Rider rider : ridersList){
                if(rider.getTAG_ID().equals(event.getTAG_ID())){
                    eventList_new.add(new Event(rider.getName(),event.getTimestamp(),event.getCP(), rider.getNumber()));
                }
            }

        }

        if(eventList.size() == 0){
            Toast.makeText(ViewEvents.this, "No riders scanned", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        switch (item.getItemId()){

            case R.id.a:
                CLASS_TAG_View="A";
                eventList_new = new ArrayList<>();
                calculateEvents(CLASS_TAG_View);
                return false;

            case R.id.b:
                CLASS_TAG_View="B";
                eventList_new = new ArrayList<>();
                calculateEvents(CLASS_TAG_View);
                return false;

            case R.id.c:
                CLASS_TAG_View="C";
                eventList_new = new ArrayList<>();
                calculateEvents(CLASS_TAG_View);
                return false;

            case R.id.fete:
                CLASS_TAG_View="Fete";
                eventList_new = new ArrayList<>();
                calculateEvents(CLASS_TAG_View);
                return false;

            case R.id.veterani:
                CLASS_TAG_View="Veterani";
                eventList_new = new ArrayList<>();
                calculateEvents(CLASS_TAG_View);
                return false;

            case R.id.all:
                calculateAllEvents();
                return false;

            default:
                startActivity(new Intent(ViewEvents.this,MainActivity.class));
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.event_class,menu);
        return super.onCreateOptionsMenu(menu);
    }

}