package com.example.heprtimemanager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class RiderViewModel extends AndroidViewModel {

    private RiderRepository riderRepository;

    public RiderViewModel(Application application) {
        super(application);
        riderRepository = new RiderRepository(application);
    }

    public void insert(Rider rider){
        riderRepository.insert(rider);
    }
    public void update(Rider rider){
        riderRepository.update(rider);
    }
    public void delete(Rider rider){
        riderRepository.delete(rider);
    }
    public void deleteAllRiders(){
        riderRepository.deleteAllRiders();
    }
    public Observable<List<Rider>> getAllRiders_list(){ return riderRepository.getAllRiders_list(); }
    public Observable<List<Rider>> getAllRiders_list_by_clas(String clas){ return riderRepository.getAllRiders_list_by_clas(clas); }


}
