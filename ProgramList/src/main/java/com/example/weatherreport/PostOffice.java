package com.example.weatherreport;

import android.content.SyncAdapterType;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;


public class PostOffice extends Handler {

    static ProgramListRecycleAdapter programListRecycleAdapter;


    private static PostOfficeMessenger postOfficeMessenger;


    final public int RECEIVED_PROGRAM_LIST = 1 ;
    final public int RECEIVED_PROGRAM_INFO = 2;
    final public int PROGRAM_CLICKED = 3;

    public void setPostOfficeMessenger(PostOfficeMessenger postOfficeMessenger) {
        this.postOfficeMessenger = postOfficeMessenger;
    }

    @Override
    public void handleMessage(Message msg) {
        switch(msg.arg1){
            case RECEIVED_PROGRAM_LIST:
                postOfficeMessenger.onProgramListReceived();
                break;
            case RECEIVED_PROGRAM_INFO:
                postOfficeMessenger.onProgramInfoReceived();
                break;
        }
        super.handleMessage(msg);
    }



}
