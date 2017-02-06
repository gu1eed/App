package com.example.vaibhav.upworkfirstproject.Fragment.BottomNavigation;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibhav.upworkfirstproject.Login;
import com.example.vaibhav.upworkfirstproject.R;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.ProfessionEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //Storage
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    //For firebase authication
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateLIstener;

    private ValueEventListener valueEventListener;
    private ChildEventListener childEventListener;


    TextView tname,tgender,tstudentid,tprofession;
    Button logout;

    private static final String LOG_TAG=Profile.class.getSimpleName();

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment

        tname=(TextView) view.findViewById(R.id.text_name);
        tstudentid=(TextView) view.findViewById(R.id.text_id);
        tgender=(TextView) view.findViewById(R.id.text_gender);
        tprofession=(TextView) view.findViewById(R.id.text_profession);
        logout=(Button)view.findViewById(R.id.button_logout);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        //Datbase and storage refernces
        databaseReference=firebaseDatabase.getReference().child("detail");
        Log.v(LOG_TAG,"database"+databaseReference.toString());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(LOG_TAG,"Count="+dataSnapshot.getChildrenCount());
                ProfessionEntry professionEntry=dataSnapshot.getValue(ProfessionEntry.class);
                Log.v(LOG_TAG,"value="+professionEntry.getName()+" "+professionEntry.getStudentid()
                        +" "+professionEntry.getProfession()+" "+professionEntry.getGender());
                tname.setText("Name:                     "+professionEntry.getName());
                tstudentid.setText("Student Id:         "+professionEntry.getStudentid());
                tgender.setText("Gender:              "+professionEntry.getGender());
                tprofession.setText("Profession:        "+professionEntry.getProfession());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Database query got cancelled "+databaseError.getMessage(),Toast.LENGTH_LONG).show();


            }
        });

//        childEventListener=new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Log.v(LOG_TAG,"Count="+dataSnapshot.getChildrenCount());
//                ProfessionEntry professionEntry=dataSnapshot.getValue(ProfessionEntry.class);
//                Log.v(LOG_TAG,"value="+professionEntry.getName()+" "+professionEntry.getStudentid()
//                        +" "+professionEntry.getProfession()+" "+professionEntry.getGender());
//                tname.setText("Name:                     "+professionEntry.getName());
//                tstudentid.setText("Student Id:         "+professionEntry.getStudentid());
//                tgender.setText("Gender:              "+professionEntry.getGender());
//                tprofession.setText("Profession:        "+professionEntry.getProfession());
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getActivity(),"Database query got cancelled "+databaseError.getMessage(),Toast.LENGTH_LONG).show();
//
//
//            }
//        };
//        databaseReference.addChildEventListener(childEventListener);

//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Log.v(LOG_TAG,"Count="+dataSnapshot.getChildrenCount());
//                ProfessionEntry professionEntry=dataSnapshot.getValue(ProfessionEntry.class);
//                Log.v(LOG_TAG,"value="+professionEntry.getName()+" "+professionEntry.getStudentid()
//                        +" "+professionEntry.getProfession()+" "+professionEntry.getGender());
//                tname.setText("Name:                     "+professionEntry.getName());
//                tstudentid.setText("Student Id:         "+professionEntry.getStudentid());
//                tgender.setText("Gender:              "+professionEntry.getGender());
//                tprofession.setText("Profession:        "+professionEntry.getProfession());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getActivity(),"Database query got cancelled "+databaseError.getMessage(),Toast.LENGTH_LONG).show();
//
//            }
//        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor edit=sharedPreferences.edit();
                edit.putBoolean("sharedstate",false).commit();
                startActivity(new Intent(getActivity(), Login.class));
            }
        });
        return view;
    }


    //Attachlistner
//    public void atttachlister(){
//        childEventListener=new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        databaseReference.addChildEventListener(childEventListener);
//
//    }


    //Deattch listener
    public void deattachlistener(){
        if (childEventListener!=null){
            databaseReference.removeEventListener(childEventListener);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        deattachlistener();
    }
}
