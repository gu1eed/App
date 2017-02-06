package com.example.vaibhav.upworkfirstproject.Fragment.Navigation;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibhav.upworkfirstproject.Fragment.BottomNavigation.Home;
import com.example.vaibhav.upworkfirstproject.R;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.CardviewAdapter;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.Following;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.TutorDummy;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherData extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tt;
    String startfilter,endfilter;
    RecyclerView.Adapter adapter;
    ValueEventListener valueEventListener;
    RecyclerView.LayoutManager layoutManager;


    List<String> name=new ArrayList<String>();
    List<String> intro=new ArrayList<String>();
    List<String> sub=new ArrayList<String>();
    DatabaseReference databaseReference;
    String purpose="nm";

    private static final String LOG_TAG=TeacherData.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        startfilter=getIntent().getStringExtra("start");
        endfilter=getIntent().getStringExtra("end");

        Log.v(LOG_TAG,"startfilter="+startfilter+"end filter="+endfilter);

        databaseReference=FirebaseDatabase.getInstance().getReference().child("dummy");
        layoutManager=new LinearLayoutManager(this);
        Query q=databaseReference.child("coursename").orderByKey().startAt(startfilter).endAt(endfilter);
        Log.v(LOG_TAG,"Query ="+q.toString());
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(LOG_TAG,"Value of count ="+dataSnapshot.getChildrenCount());

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Log.v(LOG_TAG,"value="+dataSnapshot1.getValue());
                    name.add(dataSnapshot1.getValue().toString());
                    Log.v(LOG_TAG,"name="+dataSnapshot1.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Query of data got cancelled",Toast.LENGTH_LONG).show();
            }
        });

        //Intro data from database
        q=databaseReference.child("introtutor").orderByKey().startAt(startfilter).endAt(endfilter);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(LOG_TAG,"Value of count into"+dataSnapshot.getChildrenCount());

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Log.v(LOG_TAG,"Value="+dataSnapshot1.getValue());
                    intro.add(dataSnapshot1.getValue().toString());
                    Log.v(LOG_TAG,"intro="+dataSnapshot1.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Query of data got cancelled",Toast.LENGTH_LONG).show();
            }
        });

        //Subject data from database
        q=databaseReference.child("subject").orderByKey().startAt(startfilter).endAt(endfilter);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(LOG_TAG,"value of count sub"+dataSnapshot.getChildrenCount());
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Log.v(LOG_TAG,"Value ="+dataSnapshot1.getValue());
                    sub.add(dataSnapshot1.getValue().toString());
                    Log.v(LOG_TAG,"Subject="+dataSnapshot1.getValue());
                }

                adapter=new CardviewAdapter(name,intro,sub,purpose);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Query of data got cancelled",Toast.LENGTH_LONG).show();
            }
        });

        Log.v(LOG_TAG,"name: "+name.size()+"intro: "+intro.size()+"subject: "+sub.size());

        recyclerView=(RecyclerView) findViewById(R.id.recycleview);
        tt=(TextView) findViewById(R.id.textview_empty);

    }

}
