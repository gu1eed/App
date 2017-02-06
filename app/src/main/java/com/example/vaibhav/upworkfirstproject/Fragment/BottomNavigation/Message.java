package com.example.vaibhav.upworkfirstproject.Fragment.BottomNavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaibhav.upworkfirstproject.R;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.CardviewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Message extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<String> name=new ArrayList<>();
    List<String> intro=new ArrayList<>();
    List<String> sub=new ArrayList<>();
    String purpose="m";


    public Message() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_message, container, false);
        getdata();
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new CardviewAdapter(name,intro,sub,purpose);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void getdata(){
        name.add("vabhav");
        name.add("guleed");
        intro.add("hi! i am vaibhav");
        intro.add("Hi i am gulled");
        sub.add("computer Science");
        sub.add("Computer Science");
    }

}
