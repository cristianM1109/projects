package com.example.heprtimemanager;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Rider.class,Event.class,TimeEvent.class},version = 3,exportSchema = false)
public abstract class RiderDatabase extends RoomDatabase {

    private static RiderDatabase instance;

    public abstract RiderDAO riderDAO();


    public static synchronized RiderDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),RiderDatabase.class,"rider_database")
                       .fallbackToDestructiveMigration()
                       .addCallback(roomCallback)
                       .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void>{
        private RiderDAO riderDAO;
        private PopulateDBAsyncTask(RiderDatabase riderDatabase){
            riderDAO = riderDatabase.riderDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Rider rider = new Rider("Teodor Gorbaciov","FFFC","A","211");
            rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Varga Zolt","FFFB","A","211");
            rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Patrau Raul","FFFA","A","211");
            rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Poxi Murgu","FFFC","C","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Gervis din Vaslui","FFFA","B","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Cyprian Cotet","FFFB","B","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Marius Fabian","FFFC","B","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Zdnenk Cyprin","FFFA","B","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("David Cyprina","FFFB","A","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider =new Rider("Costin Viorel","FFFC","B","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider =new Rider("Denis Lascoiu","FFFA","B","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider =new Rider("Lascus Ovidiu","FFFB","B","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider =new Rider("Norbert Levente","FFFC","A","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Alin Pop","FFFB","C","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Cotovanu Andrei","FFFA","C","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Mares Cosmin","FFFC","C","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Mares Alyssa","FFFB","Fete","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Mares Celine","FFFA","Fete","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Vierme","FFFC","C","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Viorel","FFFB","C","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Meduza Brk","FFFA","C","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Jean","FFFA","Veterani","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Cocrean Adrian","FFFB","Veterani","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);

            rider = new Rider("Claudiu Francu","FFFC","Veterani","211");
                    rider.setStatus(1);
            riderDAO.insert(rider);



            return null;
        }

    }

}
