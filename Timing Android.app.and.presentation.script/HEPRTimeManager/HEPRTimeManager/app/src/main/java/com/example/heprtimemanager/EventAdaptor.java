package com.example.heprtimemanager;

import android.app.Application;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventAdaptor extends RecyclerView.Adapter<EventAdaptor.EventHolder> {


    private List<Event> events = new ArrayList<>();

    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new EventAdaptor.EventHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event currentEvent;
        currentEvent=events.get(position);


        holder.textViewid.setText(String.valueOf(events.indexOf(currentEvent)+1));
        holder.textViewname.setText(currentEvent.getTAG_ID());
        holder.textViewtime.setText( getDate(Long.parseLong(currentEvent.getTimestamp()), "HH:mm:ss"));
        holder.textViewNumber.setText(currentEvent.getNumber());


    }

    public class EventHolder extends RecyclerView.ViewHolder {

        private TextView textViewid;
        private TextView textViewname;
        private TextView textViewtime;
        private TextView textViewNumber;

        public EventHolder(@NonNull View itemView) {
            super(itemView);
            textViewid = itemView.findViewById(R.id.text_view_id);
            textViewname = itemView.findViewById(R.id.text_view_name);
            textViewtime = itemView.findViewById(R.id.text_view_time);
            textViewNumber = itemView.findViewById(R.id.text_view_number);
            textViewNumber.setTextColor(Color.RED);

        }
    }


    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        DateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public void setEvents(List<Event> events) {

        this.events = events;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
