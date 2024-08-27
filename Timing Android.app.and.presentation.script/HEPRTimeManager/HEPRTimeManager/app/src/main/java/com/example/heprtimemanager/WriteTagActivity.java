package com.example.heprtimemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WriteTagActivity extends AppCompatActivity {

    public static final int ADD_RIDER_REQUEST = 1;
    public static final int EDIT_RIDER_REQUEST = 2;

    private static String CLASS_TAG_View;



    private RiderViewModel riderViewModel;
    RecyclerView recyclerView;
    ActionBar actionBar ;
    static RiderAdaptor adaptor;
    Disposable disposable;
    private static List<Rider> ridersList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_tag);
        CLASS_TAG_View="A";

        FloatingActionButton buttonAddRider = findViewById(R.id.add_rider_btn);
        buttonAddRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WriteTagActivity.this, AddEditRiderActivity.class);
                startActivityForResult(intent,ADD_RIDER_REQUEST);
            }
        });


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        observe_by_categ(CLASS_TAG_View);



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        riderViewModel.delete(adaptor.getRiderAt(viewHolder.getAdapterPosition()));
                        Toast.makeText(WriteTagActivity.this, "Rider deleted!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void observe_all_riders(String class_sign){

        riderViewModel = ViewModelProviders.of(this).get(RiderViewModel.class);


        adaptor = new RiderAdaptor();

        adaptor.setOnItemClickListener(new RiderAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(Rider rider) {
                Intent intent  = new Intent(WriteTagActivity.this, AddEditRiderActivity.class);
                intent.putExtra(AddEditRiderActivity.EXTRA_NAME,rider.getName());
                intent.putExtra(AddEditRiderActivity.EXTRA_ID,rider.getTAG_ID());
                intent.putExtra(AddEditRiderActivity.EXTRA_id,rider.getId());
                intent.putExtra(AddEditRiderActivity.EXTRA_NUMBER,rider.getNumber());
                intent.putExtra(AddEditRiderActivity.EXTRA_CAT,rider.getCAT());
                startActivityForResult(intent,EDIT_RIDER_REQUEST);
            }
        });

        if(disposable!=null){
            disposable.dispose();
        }


        new CountDownTimer(500, 500) {

            public void onTick(long millisUntilFinished) {
                riderViewModel.getAllRiders_list().subscribeOn(Schedulers.computation()).
                        observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new io.reactivex.rxjava3.core.Observer<List<Rider>>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                disposable=d;
                            }

                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Rider> riders) {
                                ridersList=riders;
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

                adaptor.setRiders(ridersList);

                recyclerView.setAdapter(adaptor);
                updateTitle(class_sign);

            }

        }.start();



    }

    public void observe_by_categ(String CLASS_TAG_View){

        riderViewModel = ViewModelProviders.of(this).get(RiderViewModel.class);


        adaptor = new RiderAdaptor();

        adaptor.setOnItemClickListener(new RiderAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(Rider rider) {
                Intent intent  = new Intent(WriteTagActivity.this, AddEditRiderActivity.class);
                intent.putExtra(AddEditRiderActivity.EXTRA_NAME,rider.getName());
                intent.putExtra(AddEditRiderActivity.EXTRA_ID,rider.getTAG_ID());
                intent.putExtra(AddEditRiderActivity.EXTRA_id,rider.getId());
                intent.putExtra(AddEditRiderActivity.EXTRA_NUMBER,rider.getNumber());
                intent.putExtra(AddEditRiderActivity.EXTRA_CAT,rider.getCAT());
                startActivityForResult(intent,EDIT_RIDER_REQUEST);
            }
        });

        if(disposable!=null){
            disposable.dispose();
        }
        new CountDownTimer(500, 500) {

            public void onTick(long millisUntilFinished) {
                riderViewModel.getAllRiders_list_by_clas(CLASS_TAG_View).subscribeOn(Schedulers.computation())
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

                adaptor.setRiders(ridersList);
                recyclerView.setAdapter(adaptor);
                updateTitle(CLASS_TAG_View);

            }

        }.start();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_RIDER_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddEditRiderActivity.EXTRA_NAME);
            String tag_id = data.getStringExtra(AddEditRiderActivity.EXTRA_ID);
            String CAT = data.getStringExtra(AddEditRiderActivity.EXTRA_CAT);
            String number = data.getStringExtra(AddEditRiderActivity.EXTRA_NUMBER);


            AddEditRiderActivity.SCANNED="";

            Rider rider;

                rider = new Rider(name, tag_id,CAT,number);
                          riderViewModel.insert(rider);

            if(CLASS_TAG_View.equals("All")) {
                observe_all_riders("All");
            }
            else {
                observe_by_categ(CLASS_TAG_View);
            }
            Toast.makeText(this, "Rider saved!", Toast.LENGTH_SHORT).show();
        }else  if(requestCode == EDIT_RIDER_REQUEST && resultCode == RESULT_OK){
              int id = data.getIntExtra(AddEditRiderActivity.EXTRA_id,-1);
              if(id == -1){
                  Toast.makeText(this, "Rider can't be updated!", Toast.LENGTH_SHORT).show();
                  return;
              }

            String name = data.getStringExtra(AddEditRiderActivity.EXTRA_NAME);
            String tag_id = data.getStringExtra(AddEditRiderActivity.EXTRA_ID);
            String CAT = data.getStringExtra(AddEditRiderActivity.EXTRA_CAT);
            String number = data.getStringExtra(AddEditRiderActivity.EXTRA_NUMBER);
            AddEditRiderActivity.SCANNED=data.getStringExtra(AddEditRiderActivity.EXTRA_CAT);


            Rider rider;
            rider = new Rider(name,tag_id,CAT,number);
            rider.setId(id);
            riderViewModel.update(rider);

            if(CLASS_TAG_View.equals("All")) {
                observe_all_riders("All");
            }
            else {
                observe_by_categ(CLASS_TAG_View);
            }
            Toast.makeText(this, "Rider "+name+" Updated!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Rider not saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateTitle(String CLASS_TAG){

        switch (CLASS_TAG){
            case "A":
                setTitle("Clasa A");
                break;
            case "B":
                setTitle("Clasa B");
                break;
            case "C":
                setTitle("Clasa C");
                break;
            case "Fete":
                setTitle("Clasa Fete");
                break;
            case "Veterani":
                setTitle("Clasa Veterani");
                break;
            case "All":
                setTitle("All riders");
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        switch (item.getItemId()){

            case R.id.delete_all_riders:
                riderViewModel.deleteAllRiders();
                Toast.makeText(this, "All riders deleted!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.all_btn:
                CLASS_TAG_View="All";
                observe_all_riders("All");
                return false;

            case R.id.riderB:
                CLASS_TAG_View="B";
                observe_by_categ(CLASS_TAG_View);
                return false;

            case R.id.riderA:
                CLASS_TAG_View="A";
                observe_by_categ(CLASS_TAG_View);
                return false;

            case R.id.riderC:
                CLASS_TAG_View="C";
                observe_by_categ(CLASS_TAG_View);
                return false;

            case R.id.riderFete:
                CLASS_TAG_View="Fete";
                observe_by_categ(CLASS_TAG_View);
                return false;

            case R.id.riderVeterani:
                CLASS_TAG_View="Veterani";
                observe_by_categ(CLASS_TAG_View);
                return false;

            default:
                startActivity(new Intent(WriteTagActivity.this,MainActivity.class));
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.write_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }




}