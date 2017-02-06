package com.example.vaibhav.upworkfirstproject.Fragment.BottomNavigation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.vaibhav.upworkfirstproject.R;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.CardviewAdapter;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.Following;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.Objectarraylist;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.TutorDummy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private ImageView imag_cover;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;



    //Database refernce
    private DatabaseReference databaseReference;

    RecyclerView.Adapter adaptera;

    List<String> name=new ArrayList<>();
    List<String> intro=new ArrayList<>();
    List<String> sub=new ArrayList<>();
    TextView t;
    String purpose="nm";

    private static final String LOG_TAG=Home.class.getSimpleName();



    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("follow");

        imag_cover=(ImageView)view.findViewById(R.id.imageView_cover);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleview);
        t=(TextView)view.findViewById(R.id.textview_empty);
        layoutManager=new LinearLayoutManager(getActivity());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(LOG_TAG,"count of without calling following="+dataSnapshot.getChildrenCount());
                Log.v(LOG_TAG,"Value="+dataSnapshot.getValue()+"boolean=="+dataSnapshot.hasChildren());

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Log.v(LOG_TAG,"now children name"+dataSnapshot1.getValue());
                    switch (dataSnapshot1.getKey().toString()){
                        case "coursename":
                            for (DataSnapshot array:dataSnapshot1.getChildren()){
                                Log.v(LOG_TAG,"Now children's name="+array.getValue());
                                name.add(array.getValue().toString());
                            }
                            break;
                        case "introtutor":
                            for (DataSnapshot array:dataSnapshot1.getChildren()){
                                Log.v(LOG_TAG,"Now children's intro="+array.getValue());
                                intro.add(array.getValue().toString());
                            }
                            break;
                        case "subject":
                            for (DataSnapshot array:dataSnapshot1.getChildren()){
                                Log.v(LOG_TAG,"Now children's subject="+array.getValue());
                                sub.add(array.getValue().toString());
                            }
                            break;
                    }
                }
                adaptera=new CardviewAdapter(name,intro,sub,purpose);
                Log.v(LOG_TAG,"Item added to adapter ="+adaptera.getItemCount());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adaptera);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Cancelled due to "+databaseError.getMessage(),Toast.LENGTH_LONG).show();

            }
        });



        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
}
