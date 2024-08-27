package com.example.heprtimemanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RiderAdaptor extends RecyclerView.Adapter<RiderAdaptor.RiderHolder> {

    private List<Rider> riders = new ArrayList<>();
    private OnItemClickListener listener;


    @Override
    public RiderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rider_item, parent, false);
        return new RiderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RiderHolder holder, int position) {
        Rider currentRider;
        currentRider=riders.get(position);


        holder.textViewtagid.setText(currentRider.getTAG_ID());
        holder.textViewid.setText(String.valueOf(riders.indexOf(currentRider)+1));
        holder.textViewname.setText(currentRider.getName());
    }

    @Override
    public int getItemCount() {
          return riders.size();
    }

    public void setRiders(List<Rider> riders) {

           this.riders = riders;
        notifyDataSetChanged();
    }

    public Rider getRiderAt(int position) {
        return riders.get(position);
    }


    public class RiderHolder extends RecyclerView.ViewHolder {

        private TextView textViewid;
        private TextView textViewname;
        private TextView textViewtagid;

        public RiderHolder(@NonNull View itemView) {
            super(itemView);
            textViewid = itemView.findViewById(R.id.text_view_id);
            textViewname = itemView.findViewById(R.id.text_view_name);
            textViewtagid = itemView.findViewById(R.id.text_view_TAG_ID);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                                listener.onItemClick(riders.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Rider rider);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
