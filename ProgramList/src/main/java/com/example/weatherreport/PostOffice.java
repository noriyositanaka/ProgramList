package com.example.weatherreport;

import android.os.Handler;
import android.os.Message;


public class PostOffice extends Handler {

    static ProgramListRecycleAdapter programListRecycleAdapter;


    private PostOfficeMessenger postOfficeMessenger;

    final public int RECEIVED_PROGRAM_LIST = 1 ;


    public void setPostOfficeMessenger(PostOfficeMessenger postOfficeMessenger) {
        this.postOfficeMessenger = postOfficeMessenger;
    }

    @Override
    public void handleMessage(Message msg) {
        switch(msg.arg1){
            case RECEIVED_PROGRAM_LIST:

                ProgramtListFragment programtListFragment=new ProgramtListFragment();
//                programListRecycleAdapter.notifyDataSetChanged();
                break;


        }
        super.handleMessage(msg);
    }



}
