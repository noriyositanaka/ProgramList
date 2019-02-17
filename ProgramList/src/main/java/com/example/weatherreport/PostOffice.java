package com.example.weatherreport;

import android.os.Handler;
import android.os.Message;


public class PostOffice extends Handler {

    static ProgramListRecycleAdapter programListRecycleAdapter;


    private static PostOfficeMessenger postOfficeMessenger;


    final public int RECEIVED_PROGRAM_LIST = 1 ;
    final public int RECEIVED_PROGRAM_INFO = 2;
    final public int BACK_KEY_PRESSED = 3;
    final public int PROGRAM_CLICKED =4;


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
            case PROGRAM_CLICKED:
                postOfficeMessenger.onProgramClicked();
                break;
            case BACK_KEY_PRESSED:
                postOfficeMessenger.onBackKeyPressed();
                break;
        }
        super.handleMessage(msg);
    }



}
