package com.example.weatherreport;

import android.content.Context;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements ProgramtListFragment.OnFragmentInteractionListener {

    private static JSONObject programListJSON;
    private PostOffice postOffice = new PostOffice();
    ProgramList programList = new ProgramList();

    static ArrayList<HashMap<String,String >> testArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String,String> testHashMap = new HashMap<>();

            testHashMap.put("id","1");
            testHashMap.put("start_time","2");
            testHashMap.put("end_time","3");
            testArrayList.add(testHashMap);

        testHashMap = new HashMap<>();
        testHashMap.put("id","11");
        testHashMap.put("start_time","22");
        testHashMap.put("end_time","33");
        testArrayList.add(testHashMap);

        testHashMap = new HashMap<>();
        testHashMap.put("id","111");
        testHashMap.put("start_time","222");
        testHashMap.put("end_time","333");
        testArrayList.add(testHashMap);

        String s =        testArrayList.get(0).get("id");

        programList.setTestArrayList(testArrayList);


        AsyncHTTPConnection asyncHTTPConnection = new AsyncHTTPConnection();
        asyncHTTPConnection.setHttpResultListener(new HTTPResultListener() {
            @Override
            public void getHTTPResult(Object o) {
                try {
                    programListJSON = new JSONObject(o.toString());
                    programList.setProgramListJSON(programListJSON);



                    /*
                    ここから解読ルーチン　コピペ移植可能
                    */
                    HashMap<String,String> hashMap;
                    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
                    int i = programListJSON.getJSONObject("list").getJSONArray("g1").length();
                    for (int j = 0;j<i ; j++ ){
                        JSONObject program = programListJSON.getJSONObject("list").getJSONArray("g1").getJSONObject(j);
 //                       System.out.println(program.toString(4));
                        Iterator iterator = program.keys();
                        String key;
                        hashMap = new HashMap<>();
                        while(iterator.hasNext()){
                            key = iterator.next().toString();
                            hashMap.put(key,program.getString(key));
                        }
                        arrayList.add(hashMap);
                    }
                    programList.setArrayList(arrayList);
                    /*
                    ここまで解読ルーチン
                     */

                    Message msg = new Message();

                    msg.arg1=postOffice.RECEIVED_PROGRAM_LIST;
                    postOffice.sendMessage(msg);



                    //プログラム受信が終了したら、PostOfficeにmsgを通知
                    final ProgramtListFragment programtListFragment = new ProgramtListFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.containerForFragment, programtListFragment).commit();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        asyncHTTPConnection.execute();




    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {


    }

}
