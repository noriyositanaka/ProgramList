package com.example.weatherreport;

import android.content.Context;
import android.icu.text.SymbolTable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProgramListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProgramListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgramListFragment extends Fragment {

    private Parcelable listState;
    private String KEY_LIST_STATE = "LIST_STATE";

    PostOffice postOffice = new PostOffice();
    static ProgramListRecycleAdapter programListRecycleAdapter;

    ProgramList programList = new ProgramList();
    ArrayList<HashMap<String,String>> arrayList = programList.getArrayListProgramList();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProgramListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProgramListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgramListFragment newInstance(String param1, String param2) {
        ProgramListFragment fragment = new ProgramListFragment();
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


        View view = inflater.inflate(R.layout.fragment_program_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        programListRecycleAdapter = new ProgramListRecycleAdapter(getContext(),R.layout.program,arrayList);


        RecyclerView.ViewHolder viewHolder = programListRecycleAdapter.createViewHolder(recyclerView,1);
//        programListRecycleAdapter.bindViewHolder(viewHolder,1);


        recyclerView.setAdapter(programListRecycleAdapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            /*
            savedInstance!=null
            画面回転などで前回の状態が保存されているので、それを再現する
             */
            listState = savedInstanceState.getParcelable(KEY_LIST_STATE);
            ((RecyclerView)getActivity().findViewById(R.id.recycleView)).getLayoutManager().onRestoreInstanceState(listState);

        }else{
            /*
            savedInstance==null
            前回の状態がない＝初回表示なので、現在の時間に合わせたポジションに移動する
             */
/*            RecyclerView v  = getActivity().findViewById(R.id.recycleView);
            View vv = v.getChildAt(1);
            Date Start = (Date)vv.getTag(R.id.program);
            System.out.println(Start.toString());
*/


        ArrayList arrayList = MainActivity.programList.getArrayListProgramList();
        int nowOnAirPosition =0;
            HashMap<String, String> hashMap;

            String id = MainActivity.idNowOnAir;
            String s ;
        int i= 0;
            do{
                hashMap=(HashMap)arrayList.get(i++);
                s=(String)hashMap.get("id");

            }while(MainActivity.idNowOnAir.equals(s) == false);
            System.out.println(Integer.toString(i));
            ((RecyclerView)getActivity().findViewById(R.id.recycleView)).getLayoutManager().scrollToPosition(i-1);


        }
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        RecyclerView recyclerView = getActivity().findViewById(R.id.recycleView);

        listState = recyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(KEY_LIST_STATE,listState);
 //       super.onSaveInstanceState(outState);
    }
}
