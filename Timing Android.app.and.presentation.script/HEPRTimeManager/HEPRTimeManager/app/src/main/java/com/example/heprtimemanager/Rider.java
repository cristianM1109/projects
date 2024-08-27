package com.example.heprtimemanager;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName="rider_table")
public class Rider{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String TAG_ID;

    private String CAT;

    private int status;

    private String number;

    public Rider(String name, String TAG_ID, String CAT,String number) {
        this.name = name;
        this.TAG_ID = TAG_ID;
        this.CAT=CAT;
        this.status=0;
        this.number=number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTAG_ID() {
        return TAG_ID;
    }

    public String getCAT() { return CAT; }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
