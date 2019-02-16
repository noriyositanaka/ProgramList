package com.example.weatherreport;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ProgramList {


    private static JSONObject programListJSON;
    private static JSONObject nowPlayingJSON;
    private static JSONObject programInfoJSON;
    private static ArrayList arrayListProgramList;
    private static ArrayList arrayListProgramInfo  ;
    private static Date startTime;
    private static Date endTime;


    public static void setNowPlayingJSON(JSONObject nowPlayingJSON) {
        ProgramList.nowPlayingJSON = nowPlayingJSON;
    }

    public static void setProgramInfoJSON(JSONObject programInfoJSON) {
        ProgramList.programInfoJSON = programInfoJSON;
    }

    public static void setProgramListJSON(JSONObject programListJSON) {
        ProgramList.programListJSON = programListJSON;
    }

    public static void setArrayListProgramList(ArrayList arrayListProgramList) {
        ProgramList.arrayListProgramList = arrayListProgramList;
    }

    public static void setArrayListProgramInfo(ArrayList arrayListProgramInfo) {
        ProgramList.arrayListProgramInfo = arrayListProgramInfo;
    }

    public static JSONObject getNowPlayingJSON() {
        return nowPlayingJSON;
    }

    public static JSONObject getProgramInfoJSON() {
        return programInfoJSON;
    }

    public static JSONObject getProgramListJSON() {
        return programListJSON;
    }

    public static ArrayList getArrayListProgramList() {
        return arrayListProgramList;
    }

    public static ArrayList getArrayListProgramInfo() {
        return arrayListProgramInfo;
    }
}

