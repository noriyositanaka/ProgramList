package com.example.weatherreport;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProgramInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProgramInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgramInfoFragment extends Fragment {

    private ProgramInfoViewModel programInfoViewModel;
    public ProgramInfoViewModel getProgramInfoViewModel() {
        return programInfoViewModel;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProgramInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProgramInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgramInfoFragment newInstance(String param1, String param2) {
        ProgramInfoFragment fragment = new ProgramInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_program_info, container, false);
                            /*
                    ここから解読ルーチン　コピペ移植可能
                    */
        ProgramList programList = new ProgramList();
        HashMap<String,String> hashMap;
        ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();




        int i = 0;
        if (MainActivity.programInfoJSON !=null) {
            try {

                i = MainActivity.programInfoJSON.getJSONObject("list").getJSONArray("g1").length();
                for (int j = 0; j < i; j++) {
                    JSONObject program = MainActivity.programInfoJSON.getJSONObject("list").getJSONArray("g1").getJSONObject(j);
                    Iterator iterator = program.keys();
                    String key;
                    hashMap = new HashMap<>();
                    while (iterator.hasNext()) {
                        key = iterator.next().toString();
                        hashMap.put(key, program.getString(key));
                    }

                    String startTimeISO = hashMap.get("start_time");
                    String endTimeISO = hashMap.get("end_time");

                    String s = new String();
                    String ee = new String();

                    Date Start, End;
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                        Start = simpleDateFormat.parse(startTimeISO);
                        End = simpleDateFormat.parse(endTimeISO);

                        s = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(Start);
                        ee = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(End);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    TextView id = view.findViewById((R.id.text_info_id));
                    TextView title = view.findViewById(R.id.text_info_title);
                    TextView start_time = view.findViewById(R.id.text_info_start_time);
                    TextView end_time = view.findViewById(R.id.text_info_end_time);
                    TextView subtitle = view.findViewById(R.id.text_info_subtitle);
                    TextView content = view.findViewById(R.id.text_info_content);
                    TextView act = view.findViewById(R.id.text_info_act);


                    id.setText(hashMap.get("id"));
                    title.setText(hashMap.get("title"));
                    subtitle.setText(hashMap.get("subtitle"));
                    content.setText(hashMap.get("content"));
                    act.setText(hashMap.get("act"));

                    start_time.setText(s);
                    end_time.setText(ee);

                    arrayList.add(hashMap);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


                    /*
                    ここまで解読ルーチン
                     */
        }

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP){
                    PostOffice postOffice = new PostOffice();
                    Message msg = new Message();
                    msg.arg1 = postOffice.BACK_KEY_PRESSED;
                    postOffice.sendMessage(msg);

                    return true;
                }
                return false;
            }
        });
            return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
