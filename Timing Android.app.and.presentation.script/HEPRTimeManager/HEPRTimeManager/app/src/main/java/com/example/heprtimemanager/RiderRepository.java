
package com.example.heprtimemanager;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.rxjava3.core.Observable;

public class RiderRepository implements AsyncResponse {

    private RiderDAO riderDAO;
    public static String RiderName ;
    public static String RiderCateg;
    public static String RiderNumber;


    public RiderRepository(Application application){
       RiderDatabase database = RiderDatabase.getInstance(application);
       riderDAO = database.riderDAO();
   }



   public void insert(Rider rider){
        new InsertRiderAsyncTask(riderDAO).execute(rider);
   }

   public void update(Rider rider){
       new UpdateRiderAsyncTask(riderDAO).execute(rider);
   }

    public void delete(Rider rider){
       new DeleteRiderAsyncTask(riderDAO).execute(rider);
    }

    public void deleteAllRiders(){ new DeleteAllRiderAsyncTask(riderDAO).execute(); }

    public Observable<List<Rider>> getAllRiders_list(){ return riderDAO.getAll_List(); }


    public Observable<List<Rider>> getAllRiders_list_by_clas(String clas){ return riderDAO.getAll_List_by_clas(clas); }

    public void getRiderName(String SCANNED_TAG){

        GetRiderNameAsyncTask asyncTask = new GetRiderNameAsyncTask(riderDAO);
        asyncTask.delegate = this;
        asyncTask.execute(SCANNED_TAG);
    }


    public void getRiderCAT(String SCANNED_TAG){

        GetRiderClassAsyncTask asyncTask = new GetRiderClassAsyncTask(riderDAO);
        asyncTask.delegate = this;
        asyncTask.execute(SCANNED_TAG);
    }

    public void getRiderNumber(String SCANNED_TAG){

        GetRiderNumberAsyncTask asyncTask = new GetRiderNumberAsyncTask(riderDAO);
        asyncTask.delegate = this;
        asyncTask.execute(SCANNED_TAG);
    }

    @Override
    public void processFinish(String output) {
        RiderName = output;
    }

    @Override
    public void processFinishCateg(String output) {
        RiderCateg = output;
    }

    @Override
    public void processFinishNumber(String output) {
        RiderNumber = output;
    }



    private static class InsertRiderAsyncTask extends AsyncTask<Rider,Void,Void>{
        private RiderDAO riderDAO;

        private InsertRiderAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected Void doInBackground(Rider... riders) {
             riderDAO.insert(riders[0]);
            return null;
        }
    }

    private static class UpdateRiderAsyncTask extends AsyncTask<Rider,Void,Void>{
        private RiderDAO riderDAO;

        private UpdateRiderAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected Void doInBackground(Rider... riders) {
            riderDAO.update(riders[0]);
            return null;
        }
    }

    private static class DeleteRiderAsyncTask extends AsyncTask<Rider,Void,Void>{
        private RiderDAO riderDAO;

        private DeleteRiderAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected Void doInBackground(Rider... riders) {
            riderDAO.delete(riders[0]);
            return null;
        }
    }

    private static class DeleteAllRiderAsyncTask extends AsyncTask<Void,Void,Void>{
        private RiderDAO riderDAO;

        private DeleteAllRiderAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            riderDAO.deleteAllRiders();
            return null;
        }
    }

    private static class GetRiderNameAsyncTask extends AsyncTask<String,Void,String>{
        private RiderDAO riderDAO;
        public AsyncResponse delegate = null;

        private GetRiderNameAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected String doInBackground(String... strings) {
            return riderDAO.getRiderName(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            delegate.processFinish(s);
        }
    }

    private static class GetRiderClassAsyncTask extends AsyncTask<String,Void,String>{
        private RiderDAO riderDAO;
        public AsyncResponse delegate = null;

        private GetRiderClassAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected String doInBackground(String... strings) {
            return riderDAO.getRiderCAT(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            delegate.processFinishCateg(s);
        }
    }

    private static class GetRiderNumberAsyncTask extends AsyncTask<String,Void,String>{
        private RiderDAO riderDAO;
        public AsyncResponse delegate = null;

        private GetRiderNumberAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected String doInBackground(String... strings) {
            return riderDAO.getRiderNumber(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            delegate.processFinishNumber(s);
        }
    }

    //----------------------------------Event---------------------------------------//


    public void insertEvent(Event event){new InsertEventAsyncTask(riderDAO).execute(event);}

    public void updateEvent(Event event){new UpdateEventAsyncTask(riderDAO).execute(event);}

    public void deleteAllEvents(){new DeleteEventAsyncTask(riderDAO).execute();}

    public Observable<List<Event>> getAllEvents(){ return riderDAO.getAllEvents(); }






    private static class InsertEventAsyncTask extends AsyncTask<Event,Void,Void>{
        private RiderDAO riderDAO;

        private InsertEventAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected Void doInBackground(Event... events) {
            riderDAO.insertEvent(events[0]);
            return null;
        }
    }

    private static class UpdateEventAsyncTask extends AsyncTask<Event,Void,Void>{
        private RiderDAO riderDAO;

        private UpdateEventAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected Void doInBackground(Event... events) {
            riderDAO.updateEvent(events[0]);
            return null;
        }
    }

    private static class DeleteEventAsyncTask extends AsyncTask<Void,Void,Void>{
        private RiderDAO riderDAO;

        private DeleteEventAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            riderDAO.deleteAllEvents();
            return null;
        }
    }



    //-------------------------------------Event-------------------------------------//



    //------------------------------------TimeEvent---------------------------------//


    public void insertTimeEvent(TimeEvent timeEvent){new InsertTimeEventAsyncTask(riderDAO).execute(timeEvent);}

    public void updateTimeEvent(TimeEvent timeEvent){new UpdateTimeEventAsyncTask(riderDAO).execute(timeEvent);}

    public Observable<List<TimeEvent>> getAllTimeEvents(){ return riderDAO.getAllTimeEvents(); }


    private static class InsertTimeEventAsyncTask extends AsyncTask<TimeEvent,Void,Void>{
        private RiderDAO riderDAO;

        private InsertTimeEventAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected Void doInBackground(TimeEvent... timeEvents) {
            riderDAO.insertTimeEvent(timeEvents[0]);
            return null;
        }
    }

    private static class UpdateTimeEventAsyncTask extends AsyncTask<TimeEvent,Void,Void>{
        private RiderDAO riderDAO;

        private UpdateTimeEventAsyncTask(RiderDAO riderDAO){
            this.riderDAO=riderDAO;
        }

        @Override
        protected Void doInBackground(TimeEvent... timeEvents) {
            riderDAO.updateTimeEvent(timeEvents[0]);
            return null;
        }
    }




}
