package com.example.weatherreport;

import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements ProgramtListFragment.OnFragmentInteractionListener,ProgramInfoFragment.OnFragmentInteractionListener{

    public static JSONObject programListJSON;
    public static JSONObject programInfoJSON;

    ProgramList programList = new ProgramList();

    static ArrayList<HashMap<String,String >> testArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                        Iterator iterator = program.keys();
                        String key;
                        hashMap = new HashMap<>();
                        while(iterator.hasNext()){
                            key = iterator.next().toString();
                            hashMap.put(key,program.getString(key));
                        }
                        arrayList.add(hashMap);
                    }
                    programList.setArrayListProgramList(arrayList);
                    /*
                    ここまで解読ルーチン
                     */

                    Message msg = new Message();

                    PostOffice postOffice = new PostOffice();

                    msg.arg1=postOffice.RECEIVED_PROGRAM_LIST;
                    postOffice.sendMessage(msg);



                    //プログラム受信が終了したら、PostOfficeにmsgを通知

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        asyncHTTPConnection.execute(AsyncHTTPConnection.COMMAND_GET_PROGRAM_LIST);




    }

    @Override
    protected void onResume() {
        super.onResume();

        PostOffice postOffice = new PostOffice();
        postOffice.setPostOfficeMessenger(new PostOfficeMessenger() {


            @Override
            public void onProgramListReceived() {

                ProgramtListFragment programtListFragment = new ProgramtListFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.containerForFragment, programtListFragment).commit();

            }

            @Override
            public void onProgramInfoReceived() {
                ProgramInfoFragment programInfoFragment = new ProgramInfoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.containerForFragment,programInfoFragment).commit();

            }

        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {


    }

}
