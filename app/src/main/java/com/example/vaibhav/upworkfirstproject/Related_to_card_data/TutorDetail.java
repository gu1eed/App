package com.example.vaibhav.upworkfirstproject.Related_to_card_data;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibhav.upworkfirstproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TutorDetail extends Activity {

    ImageView iprofile,iadd;
    TextView tcourse,tintro;
    ImageView ibadd;
    DatabaseReference databaseReference;
    List<String> lname=new ArrayList<String>();
    List<String> lintro=new ArrayList<String>();
    List<String> lsub=new ArrayList<String>();

    String name,intro,sub;
    public final static String LOG_TAG=TutorDetail.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_detail);

        iprofile=(ImageView) findViewById(R.id.imageView_profile);
        iadd=(ImageView)findViewById(R.id.imageButton_add);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("follow");
        name=getIntent().getStringExtra("name");
        intro=getIntent().getStringExtra("intro");
        sub=getIntent().getStringExtra("subj");

        tcourse=(TextView) findViewById(R.id.text_course);
        tintro=(TextView) findViewById(R.id.text_tutor_into);
        Log.v(LOG_TAG,"name="+name+" intro="+intro);

        tcourse.setText("Field Of Knowledge:  "+sub);
        tintro.setText("introduction of the Tutor: "+"\n"+intro);



        ibadd=(ImageButton) findViewById(R.id.imageButton_add);
        lname.add(name);
        lintro.add(intro);
        lsub.add(sub);

        ibadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Following following=new Following(lname,lintro,lsub);
                databaseReference.setValue(following);
                Toast.makeText(getApplicationContext(),"Teacher added to your following list",Toast.LENGTH_LONG).show();


            }
        });


    }
}
