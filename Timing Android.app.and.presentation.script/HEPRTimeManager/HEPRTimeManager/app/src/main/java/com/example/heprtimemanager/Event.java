package com.example.heprtimemanager;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="event_table")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String TAG_ID;

    private String timestamp;

    private String CP;

    private String number;

    private int status;

    public Event(String TAG_ID, String timestamp, String CP ,String number) {
        this.TAG_ID = TAG_ID;
        this.timestamp = timestamp;
        this.CP = CP;
        this.number = number;
        status=0;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTAG_ID() {
        return TAG_ID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getCP() {
        return CP;
    }
}
