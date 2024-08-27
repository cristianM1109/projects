package com.example.heprtimemanager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class EventsViewModel extends AndroidViewModel {

    private RiderRepository riderRepository;

    public EventsViewModel(Application application) {
        super(application);
        riderRepository = new RiderRepository(application);
    }

    public Observable<List<Event>> getAllEvents(){ return riderRepository.getAllEvents(); }

}
