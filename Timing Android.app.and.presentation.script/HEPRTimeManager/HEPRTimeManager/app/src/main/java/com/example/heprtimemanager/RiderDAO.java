package com.example.heprtimemanager;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.sql.Time;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface RiderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Rider rider);

    @Update
    void update(Rider rider);

    @Delete
    void delete(Rider rider);

    @Query("DELETE FROM rider_table")
    void deleteAllRiders();

    @Query("SELECT * FROM rider_table ORDER BY name ASC")
    Observable<List<Rider>> getAll_List();

    @Query("SELECT * FROM rider_table WHERE CAT LIKE :clasa ORDER BY name ASC")
    Observable<List<Rider>> getAll_List_by_clas(String clasa);

    @Query("SELECT name FROM rider_table WHERE TAG_ID LIKE :SCANNED_TAG")
    String getRiderName(String SCANNED_TAG);

    @Query("SELECT CAT FROM rider_table WHERE TAG_ID LIKE :SCANNED_TAG")
    String getRiderCAT(String SCANNED_TAG);

    @Query("SELECT number FROM rider_table WHERE TAG_ID LIKE :SCANNED_TAG")
    String getRiderNumber(String SCANNED_TAG);

    //--------------------------------------Event-----------------------------------------------//


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvent(Event event);

    @Update
    void updateEvent(Event event);

    @Query("DELETE FROM event_table")
    void deleteAllEvents();

    @Query("SELECT * FROM event_table ORDER BY id ASC")
    Observable<List<Event>> getAllEvents();


    //------------------------------------TimeEvent-----------------------------------------------//

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTimeEvent(TimeEvent timeEvent);

    @Update
    void updateTimeEvent(TimeEvent timeEvent);

    @Query("SELECT * FROM time_event")
    Observable<List<TimeEvent>> getAllTimeEvents();



}
