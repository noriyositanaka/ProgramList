package com.example.weatherreport;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
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

import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.concurrent.TimeoutException;

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
                TextView t = v.findViewById(R.id.id);
                String s = t.getText().toString();



                AsyncHTTPConnection asyncHTTPConnection = new AsyncHTTPConnection();
                asyncHTTPConnection.setHttpResultListener(new HTTPResultListener() {
                    @Override
                    public void getHTTPResult(Object o) {

                        try {
                            MainActivity.programInfoJSON = new JSONObject(o.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        System.out.println(o);
                        PostOffice postOffice = new PostOffice();
                        Message msg = new Message();
                        msg.arg1 = postOffice.RECEIVED_PROGRAM_INFO;
                        postOffice.sendMessage(msg);

                    }
                });

                asyncHTTPConnection.execute(AsyncHTTPConnection.COMMAND_GET_PROGRAM_INFO,s);

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

        Date Start = new Date(), End = new Date();
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
        TextView id = viewHolder.itemView.findViewById(R.id.id);
        Date startTime = (Date)viewHolder.itemView.getTag(0);
        Date endTime = (Date)viewHolder.itemView.getTag(1);


        title.setText(arrayList.get(i).get("title"));
        start_time.setText(s);
        end_time.setText(ee);
        id.setText(arrayList.get(i).get("id"));
        viewHolder.itemView.setTag(R.id.program,Start);
 //       viewHolder.itemView.setTag(1,End);

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

