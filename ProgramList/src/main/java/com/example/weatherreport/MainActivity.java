package com.example.weatherreport;

import android.app.Activity;
import android.net.Uri;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements ProgramListFragment.OnFragmentInteractionListener,ProgramInfoFragment.OnFragmentInteractionListener{

    public static JSONObject programListJSON;
    public static JSONObject programInfoJSON;
    public static JSONObject nowOnAirJSON;

    public static ProgramList programList;
    public static String idNowOnAir;

    static ProgramListPageAdapter programListPageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(programListJSON == null) {


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
                        HashMap<String, String> hashMap;
                        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
                        int i = programListJSON.getJSONObject("list").getJSONArray("g1").length();
                        for (int j = 0; j < i; j++) {
                            JSONObject program = programListJSON.getJSONObject("list").getJSONArray("g1").getJSONObject(j);
                            Iterator iterator = program.keys();
                            String key;
                            hashMap = new HashMap<>();
                            while (iterator.hasNext()) {
                                key = iterator.next().toString();
                                hashMap.put(key, program.getString(key));
                            }
                            arrayList.add(hashMap);
                        }
                        programList.setArrayListProgramList(arrayList);
                    /*
                    ここまで解読ルーチン
                     */

                        Message msg = new Message();

                        PostOffice postOffice = new PostOffice();

                        msg.arg1 = postOffice.RECEIVED_PROGRAM_LIST;
                        postOffice.sendMessage(msg);


                        //プログラム受信が終了したら、PostOfficeにmsgを通知

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
            asyncHTTPConnection.execute(AsyncHTTPConnection.COMMAND_GET_PROGRAM_LIST);

            AsyncHTTPConnection asyncGetNowOnAirConnection = new AsyncHTTPConnection();
            asyncGetNowOnAirConnection.setHttpResultListener(new HTTPResultListener() {
                @Override
                public void getHTTPResult(Object o) {
                    try {
                        nowOnAirJSON = new JSONObject(o.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        idNowOnAir = nowOnAirJSON.getJSONObject("nowonair_list").getJSONObject("g1").getJSONObject("present").getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            });
            asyncGetNowOnAirConnection.execute(AsyncHTTPConnection.COMMAND_GET_NOW_ON_AIR);

        }

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        programListPageAdapter = new ProgramListPageAdapter(fragmentManager);
        viewPager.setAdapter(programListPageAdapter);


        final PostOffice postOffice = new PostOffice();
        postOffice.setPostOfficeMessenger(new PostOfficeMessenger() {





            @Override
            public void onProgramListReceived() {

                programListPageAdapter.replacePage(new ProgramListFragment(),0);

 //               ProgramListFragment programListFragment = new ProgramListFragment();
 //               getSupportFragmentManager().beginTransaction().replace(R.id.containerForFragment, programListFragment).commit();

            }

            @Override
            public void onProgramInfoReceived() {
                programListPageAdapter.removeInfoPage();
                programListPageAdapter.replacePage(new ProgramInfoFragment(),1);
                viewPager.arrowScroll(View.FOCUS_RIGHT);

  //              ProgramInfoFragment programInfoFragment = new ProgramInfoFragment();
  //              getSupportFragmentManager().beginTransaction().replace(R.id.containerForFragment,programInfoFragment).commit();

            }

        });


    }

    @Override
    protected void onResume() {
        super.onResume();


    }



    @Override
    public void onFragmentInteraction(Uri uri) {


    }
/*
    @Override
    public void onBackPressed() {

        Bundle bundle =  new Bundle();
        bundle.putCharArray("ProgramList",programListJSON.toString().toCharArray());
        onSaveInstanceState(bundle);

    }
*/

    @Override
    protected void onPause() {
        super.onPause();

        Activity activity = this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        programListJSON = null;
        ProgramListPageAdapter.arrayListFragment = new ArrayList<>();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}


