package com.example.weatherreport;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class ProgramListViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView start_time;
    public TextView end_time;
    public TextView id;
    View  v;


    public ProgramListViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.text_title);
        start_time = itemView.findViewById(R.id.text_start_time);
        end_time = itemView.findViewById(R.id.text_end_time);
        id = itemView.findViewById(R.id.id);

    }

    public TextView getEnd_time() {
        return end_time;
    }

    public TextView getStart_time() {
        return start_time;
    }

    public TextView getTitle() {
        return title;
    }

}
