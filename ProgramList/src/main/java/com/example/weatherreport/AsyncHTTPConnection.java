package com.example.weatherreport;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;



public class AsyncHTTPConnection extends AsyncTask {
    HTTPResultListener httpResultListener;

    static final String COMMAND_GET_PROGRAM_LIST = "GET_PROGRAM_LIST";
    static final String COMMAND_GET_PROGRAM_INFO = "GET_PROGRAM_INFO";

    static String FILE_PATH_LIST_BASE = "/v2/pg/list/130/g1/";
    static String FILE_PATH_INFO_BASE = "/v2/pg/info/130/g1/";


    static String FILE_PATH_JSON_KEY = ".json?key=SAOeQx26aasTAPfEHnWANAY9yYbyTBkQ";

    public void setHttpResultListener(HTTPResultListener httpResultListener) {
        this.httpResultListener = httpResultListener;
    }

    @Override
    protected Object doInBackground(Object[] objects) {


        Date today = new Date();
        String stringToday;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        stringToday = simpleDateFormat.format(today);

        URL url;

        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder = new StringBuilder();

        String FILE_PATH=null;

        switch((String)objects[0]){
            case COMMAND_GET_PROGRAM_LIST:
                FILE_PATH= FILE_PATH_LIST_BASE + stringToday + FILE_PATH_JSON_KEY;
                break;
            case COMMAND_GET_PROGRAM_INFO:
                FILE_PATH= FILE_PATH_INFO_BASE + objects[1]+ FILE_PATH_JSON_KEY;
                break;


        }

        try {
            url = new URL("HTTP","api.nhk.or.jp",FILE_PATH);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String line = null;
            while((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(Object o) {
        httpResultListener.getHTTPResult(o);
        super.onPostExecute(o);
    }
}
