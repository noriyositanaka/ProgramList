package com.example.weatherreport;

import org.json.JSONObject;

import java.util.ArrayList;

public class ProgramList {


    private static JSONObject programListJSON;
    private static JSONObject nowPlayingJSON;
    private static JSONObject programInfoJSON;
    private static ArrayList arrayList;
    private static ArrayList testArrayList;

    public static void setNowPlayingJSON(JSONObject nowPlayingJSON) {
        ProgramList.nowPlayingJSON = nowPlayingJSON;
    }

    public static void setProgramInfoJSON(JSONObject programInfoJSON) {
        ProgramList.programInfoJSON = programInfoJSON;
    }

    public static void setProgramListJSON(JSONObject programListJSON) {
        ProgramList.programListJSON = programListJSON;
    }

    public static void setArrayList(ArrayList arrayList) {
        ProgramList.arrayList = arrayList;
    }

    public static void setTestArrayList(ArrayList testArrayList) {
        ProgramList.testArrayList = testArrayList;
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

    public static ArrayList getArrayList() {
        return arrayList;
    }

    public static ArrayList getTestArrayList() {
        return testArrayList;
    }
}
