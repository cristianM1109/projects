package com.example.heprtimemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncActivity extends AppCompatActivity {

    Button syncBtn,getSyncBtn;
    TextView count,synced,getCount,rider_count;

    private RiderRepository riderRepository;
    private Disposable disposable;
    private static List<Rider> ridersList = new ArrayList<>();
    private int inserted_count;
    private int get_count;
    private ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        progressDoalog = new ProgressDialog(SyncActivity.this);
        syncBtn = (Button) findViewById(R.id.sync_btn);
        getSyncBtn = (Button) findViewById(R.id.get_btn);
        count = findViewById(R.id.unSynced_count);
        synced = findViewById(R.id.synced);
        getCount = findViewById(R.id.getCount);
        rider_count = findViewById(R.id.rider_count);


        riderRepository = new RiderRepository(this.getApplication());

        riderRepository.getAllRiders_list().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Rider>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                        Log.d("OnSubscribe:","Strated..");
                    }

                    @Override
                    public void onNext(@NonNull List<Rider> riderList) {
                        Log.d("OnNext:","Strated..");
                        ridersList=riderList;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("OnError:",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("OnComplete:","Subscription Ended..");
                    }
                });

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                disposable.dispose();
                count.setText("You have "+getUnSynced(ridersList)+" UnSynced riders!");
                if(ridersList.size()==0){
                    rider_count.setText("You have "+ridersList.size()+" riders in memory");
                    rider_count.setTextColor(Color.parseColor("#CE2626"));
                    count.setText("");
                    syncBtn.setEnabled(false);
                }else {
                    if(getUnSynced(ridersList) == 0){
                        syncBtn.setEnabled(false);
                    }
                    rider_count.setText("You have "+ridersList.size()+" riders in memory");
                    rider_count.setTextColor(Color.parseColor("#0EE310"));
                }
            }

        }.start();





        syncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData(ridersList, new RetrofitResponseListener() {
                    @Override
                    public void onSuccess() {
                                inserted_count++;
                                synced.setText(inserted_count+" riders has successfully synced!");


                        if(inserted_count == 1){
                            progressDoalog.dismiss();
                            progressDoalog = new ProgressDialog(SyncActivity.this);
                            progressDoalog.setTitle("Upload Riders List");
                            progressDoalog.setMax(getUnSynced(ridersList));
                            progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressDoalog.show();

                        }

                        progressDoalog.incrementProgressBy(1);

                                if(inserted_count == ridersList.size()){
                                    progressDoalog.setCancelable(true);
                                    progressDoalog.setMessage("Finished");
                                }
                        count.setText("You have "+(getUnSynced(ridersList)-1)+" UnSynced riders!");

                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        });


        getSyncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getRiders(new RetrofitResponseListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        });

    }

    private void getRiders(final RetrofitResponseListener retrofitResponseListener){

        get_count = 0;

        if(get_count== 0){
            progressDoalog.setMessage("Connecting server...");
            progressDoalog.show();
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        Call<List<Rider>> call = MyClient.getInstance().getMyApi().getAll();

        call.enqueue(new Callback<List<Rider>>() {
            @Override
            public void onResponse(Call<List<Rider>> call, Response<List<Rider>> response) {
                if(response.isSuccessful()){
                    List<Rider> riders = response.body();
                    getCount.setText(riders.size()+" riders saved to memory!");
                    retrofitResponseListener.onSuccess();

                    for(Rider r : riders){

                        if(get_count == 0){
                            progressDoalog.dismiss();
                            progressDoalog = new ProgressDialog(SyncActivity.this);
                            progressDoalog.setTitle("Upload Riders List");
                            progressDoalog.setMax(getUnSynced(ridersList));
                            progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressDoalog.show();
                            progressDoalog.setMax(getUnSynced(riders));
                        }
                        r.setStatus(1);
                        riderRepository.insert(r);
                        get_count++;
                        rider_count.setText("You have " + get_count + " riders in memory");
                        progressDoalog.incrementProgressBy(1);
                    }
                    rider_count.setTextColor(Color.parseColor("#0EE310"));
                    count.setText("You have 0 UnSynced riders!");

                    progressDoalog.setMessage("Finished");
                    progressDoalog.setCancelable(true);
                }

            }

            @Override
            public void onFailure(Call<List<Rider>> call, Throwable t) {
                Toast.makeText(SyncActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                retrofitResponseListener.onFailure();
            }
        });
    }

    private int getUnSynced(List<Rider> ridersList){

        int count=0;

        ListIterator<Rider> riderListIterator;
        Rider rider;

        riderListIterator  = ridersList.listIterator();

        while (riderListIterator.hasNext()) {

            rider = riderListIterator.next();

            if(rider.getStatus()==0){
               count++;
            }
        }

       return count;
    }


    private void insertData(List<Rider> ridersList,final RetrofitResponseListener retrofitResponseListener){

        inserted_count=0;


        ListIterator<Rider> riderListIterator;
        Rider rider;

        riderListIterator  = ridersList.listIterator();

        if(inserted_count== 0){
            progressDoalog.setMessage("Connecting server...");
            progressDoalog.show();
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        while (riderListIterator.hasNext()) {

            rider = riderListIterator.next();


        if(rider.getStatus()==0){
        Call<ResponseBody> call=MyClient.getInstance().getMyApi().insertdata(rider.getName(),rider.getTAG_ID(),rider.getCAT(),rider.getNumber());
            Rider finalRider = rider;
            call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    retrofitResponseListener.onSuccess();
                    finalRider.setStatus(1);
                    riderRepository.update(finalRider);
                }
                else{
                    retrofitResponseListener.onFailure();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure", t.toString());
                retrofitResponseListener.onFailure();
                retrofitResponseListener.onFailure();
                progressDoalog.dismiss();
            }
        });
        }
        }

   }
}