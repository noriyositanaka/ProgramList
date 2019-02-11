package com.example.weatherreport;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AsyncHTTPConnection extends AsyncTask {
    HTTPResultListener httpResultListener;

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
        try {
            url = new URL("HTTP","api.nhk.or.jp","/v2/pg/list/130/g1/"+stringToday+".json?key=SAOeQx26aasTAPfEHnWANAY9yYbyTBkQ");
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