package com.example.heprtimemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LogInActivity logIn;
    private boolean isLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logIn = new LogInActivity();
        isLogged = logIn.isLogged;
        ConstraintLayout app_layer1 = (ConstraintLayout) findViewById (R.id.keyboard_touch_1);
        app_layer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogged){
                open_write_activity();
                }
                else{
                    Toast.makeText(MainActivity.this, "Permission denied! Please Log In!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        ConstraintLayout app_layer2 = (ConstraintLayout) findViewById (R.id.keyboard_touch_2);
        app_layer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    open_select_activity();

            }
        });

        ConstraintLayout app_layer3 = (ConstraintLayout) findViewById (R.id.keyboard_touch_3);
        app_layer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_read_activity();
            }
        });

        ConstraintLayout app_layer4 = (ConstraintLayout) findViewById (R.id.keyboard_touch_4);
        app_layer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogged){
                    open_sync_activity();
                }
                else{
                    Toast.makeText(MainActivity.this, "Permission denied! Please Log In!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ConstraintLayout app_layer5 = (ConstraintLayout) findViewById (R.id.keyboard_touch_5);
        app_layer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_events_activity();
            }
        });

        ConstraintLayout app_layer6 = (ConstraintLayout) findViewById (R.id.keyboard_touch_6);
        app_layer6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_sync_events_activity();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        switch (item.getItemId()) {

            case R.id.log_out:
                isLogged=false;
                logIn.isLogged =false;
                Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                invalidateOptionsMenu();
                return false;

            default:
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        if(isLogged){
            menuInflater.inflate(R.menu.log_out,menu);
        }else{
            menuInflater.inflate(R.menu.main_menu,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void open_select_activity() {
        if(!isLogged){
            Intent intent = new Intent(this, ViewSelectConfig.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, SelectCPDay.class);
            startActivity(intent);
        }
    }

    public void open_write_activity() {
        Intent intent = new Intent(this,WriteTagActivity.class);
        startActivity(intent);
    }

    public void open_sync_activity() {
        Intent intent = new Intent(this,SyncActivity.class);
        startActivity(intent);
    }

    public void open_read_activity() {
        Intent intent = new Intent(this, ReadNFCActivity.class);
        startActivity(intent);
    }

    public void open_events_activity() {

        if(isLogged){
            Intent intent = new Intent(this,DeleteAllEvents.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this,ViewEvents.class);
            startActivity(intent);
        }
        }

    public void open_sync_events_activity() {
        Intent intent = new Intent(this,SyncEvents.class);
        startActivity(intent);
    }

}