package com.example.weatherreport;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

public class ProgramListRecycleAdapter extends RecyclerView.Adapter {

    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private int layoutResourceId;

    public ProgramListRecycleAdapter(Context context, int layoutResourceId, ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
        ;
        this.layoutInflater = LayoutInflater.from(context);
        this.layoutResourceId = layoutResourceId;
        this.arrayList = arrayList;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(layoutResourceId, viewGroup, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new ProgramListViewHolder(view) {
            @Override
            public String toString() {
                return super.toString();
            }
        };

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        String startTimeISO = arrayList.get(i).get("start_time");
        String endTimeISO = arrayList.get(i).get("end_time");

        String s = new String();
        String ee = new String();

        Date Start, End;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            Start = simpleDateFormat.parse(startTimeISO);
            End = simpleDateFormat.parse(endTimeISO);

            s = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG).format(Start);
            ee = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG).format(End);
        } catch (ParseException e) {
            e.printStackTrace();
        }




        TextView title = viewHolder.itemView.findViewById(R.id.text_title);
        TextView start_time = viewHolder.itemView.findViewById(R.id.text_start_time);
        TextView end_time = viewHolder.itemView.findViewById(R.id.text_end_time);

        title.setText(arrayList.get(i).get("title"));
        start_time.setText(s);
        end_time.setText(ee);


    }

    @Override
    public int getItemCount() {
        if (arrayList == null) {
            return 0;

        } else {
            return arrayList.size();

        }
    }
}
