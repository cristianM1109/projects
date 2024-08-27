package com.example.heprtimemanager;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="time_event")
public class TimeEvent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String CP;

    private String DAY;

    public TimeEvent(String CP, String DAY) {
        this.CP = CP;
        this.DAY = DAY;
    }

    public int getId() {
        return id;
    }

    public String getCP() {
        return CP;
    }

    public String getDAY() {
        return DAY;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public void setDAY(String DAY) {
        this.DAY = DAY;
    }
}
