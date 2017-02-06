package com.example.vaibhav.upworkfirstproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.vaibhav.upworkfirstproject.Related_to_card_data.Following;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.ProfessionEntry;
import com.example.vaibhav.upworkfirstproject.Related_to_card_data.TutorDummy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Register extends Activity {

    public static final String LOG_TAG=Register.class.getSimpleName();

    android.app.ActionBar actionBar;

    ImageButton profilepic;
    EditText ename,eemail,epasword,erepass,estudentid;
    RadioButton rfemale,rmale;
    Switch sprofesion;
    Button bsub;
    RadioGroup rggender;

    public static final int Intent_profile=1;

    String valname,valemail,valprofesion,valgender,valpas,valrepass,valstudentid;
    int radiocheckedid;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //Storage
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    //For firebase authication
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateLIstener;

    private SharedPreferences preferences;

    boolean Sharedstate=false;

    ArrayList<String> name;
    ArrayList<String> into;
    ArrayList<String> sub;
    DatabaseReference two,three;
    List<String> lname=new ArrayList<String>();
    List<String> lintro=new ArrayList<String>();
    List<String> lsub=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        //Datbase and storage refernces
        databaseReference=firebaseDatabase.getReference().child("detail");
        two=FirebaseDatabase.getInstance().getReference().child("dummy");
        three=FirebaseDatabase.getInstance().getReference().child("follow");
        mFirebaseAuth=FirebaseAuth.getInstance();

//        firebaseDatabase.setPersistenceEnabled(true);


        actionBar=getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        profilepic=(ImageButton) findViewById(R.id.imagebutton_profile);
        ename=(EditText) findViewById(R.id.input_name);
        eemail=(EditText) findViewById(R.id.input_email);
        epasword=(EditText) findViewById(R.id.input_password);
        erepass=(EditText) findViewById(R.id.input_repass);
        estudentid=(EditText) findViewById(R.id.input_studentid);
        rggender=(RadioGroup) findViewById(R.id.radiogroup1_gender);
        rfemale=(RadioButton) findViewById(R.id.radio_female);
        rmale=(RadioButton) findViewById(R.id.radio_male);
        sprofesion=(Switch) findViewById(R.id.switch_pro);
        bsub=(Button) findViewById(R.id.button_submit);

        //Intent to get image content
        final Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(Intent.createChooser(intent,"Upload Profile Pic"),Intent_profile);
            }
        });
//        dummydata();

    }

    public void getdata(){

        valname=ename.getText().toString();
        Log.v(LOG_TAG,"Value: "+valname);
        valemail=eemail.getText().toString();
        Log.v(LOG_TAG,"Value: "+valemail);
        valstudentid=estudentid.getText().toString();
        Log.v(LOG_TAG,"Value: "+valstudentid);

        valpas=epasword.getText().toString();
        Log.v(LOG_TAG,"Value: "+valpas);
        valrepass=erepass.getText().toString();
        Log.v(LOG_TAG,"Value: "+valrepass);

        //Get switch TeacherData
        if (sprofesion.isChecked()){
            valprofesion="Student";
            Log.v(LOG_TAG,"Value: "+valprofesion);
        }
        else valprofesion="Professional";
        Log.v(LOG_TAG,"Value: "+valprofesion);

        //Get radio group checked id
        radiocheckedid=rggender.getCheckedRadioButtonId();
        switch (radiocheckedid){
            case R.id.radio_female:
                valgender="female";
                Log.v(LOG_TAG,"Value: "+valgender);
                break;
            case R.id.radio_male:
                valgender="male";
                Log.v(LOG_TAG,"Value: "+valgender);
                break;
            default:valgender=null;
                Log.v(LOG_TAG,"Value: "+valgender);

        }

    }

    //Radio function
    public boolean radiodta(){
        //Get radio group checked id
        radiocheckedid=rggender.getCheckedRadioButtonId();
        switch (radiocheckedid){
            case R.id.radio_female:
                valgender="female";
                return true;

            case R.id.radio_male:
                valgender="male";
                return true;

            default:valgender=null;
                return false;
        }

    }

    //Checkcorrect pass.
    public boolean checkpass(){
        if (valpas.equals(valrepass)){
            return true;
        }
        else return false;
    }

    //Add dummy data
    public void dummydata(){
        //Add name
        lname.add("vaibhav Kumar ");
        lname.add("Praksh jha");
        lname.add("Guleed");
        lname.add("John thomas");
        lname.add("Andrew gate");
        lname.add("Einstein ");
        lname.add("Barak obama");
        lname.add("Guleed");
        lname.add("Vinita ");
        lname.add("Shivpal ");
        lname.add("Tisco Kumar");
        lname.add("Mark jobs");
        lname.add("Steve jobs");
        lname.add("Vaibhav Kumar");

        //Add intro
        lintro.add("Hi! I am computer Science teaher. I have 5 year of experience");
        lintro.add("Hi! I am computer Science teaher. I have 7 year of experience");
        lintro.add("Hi! I am Buissness teaher. I have 2 year of experience");
        lintro.add("Hi! I am Buissness teaher. I have 5 year of experience");
        lintro.add("Hi! I am Law and finance. I have 2 year of experience");
        lintro.add("Hi! I am Law and finance. I have 10 year of experience");
        lintro.add("Hi! I am HealthCare. I have 2 year of experience");
        lintro.add("Hi! I am HealthCare. I have 2 year of experience");
        lintro.add("Hi! I am Art and design. I have 2 year of experience");
        lintro.add("Hi! I am Art and design. I have 2 year of experience");
        lintro.add("Hi! I am Physcology. I have 2 year of experience");
        lintro.add("Hi! I am Physcology. I have 2 year of experience");
        lintro.add("Hi! I am Media and performance. I have 2 year of experience");
        lintro.add("Hi! I am Media and performance. I have 2 year of experience");

        //aAdd subject
        lsub.add("Computer Science");
        lsub.add("Computer Science");
        lsub.add("Buissness");
        lsub.add("Buissness");
        lsub.add("Law and finance");
        lsub.add("Law and finance");
        lsub.add("HealthCare");
        lsub.add("HealthCare");
        lsub.add("Art and design");
        lsub.add("Art and design");
        lsub.add("Physcology");
        lsub.add("Physcology");
        lsub.add("Media and performance");
        lsub.add("Media and performance");

//        name.add("vaibhav");
//        into.add("Hi i am vaibhav");
//        sub.add("Computer Science");




    }

    //Check internet connection
    public  boolean checknet(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo aa=connectivityManager.getActiveNetworkInfo();
        return  aa.isConnectedOrConnecting();
    }

    //Submitdata
    public void SubmitData(View view){
        getdata();
        radiodta();
        if (valname!=null && valstudentid!=null && valemail!=null && valprofesion!=null
                && valgender!=null &&valrepass!=null && valrepass!=null ){

            if (checkpass()==true && checknet()==true) {
                preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor edit=preferences.edit();
                edit.putString("sharedemail",valemail);
                edit.putString("sharedpass",valpas);
                edit.putBoolean("sharedstate",true);
                edit.commit();
                mFirebaseAuth.createUserWithEmailAndPassword(valemail,valpas)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    ProfessionEntry professionEntry=new ProfessionEntry(valname,valgender,valstudentid,valprofesion);
//                                    TutorDummy tutorDummy=new TutorDummy(lname,lintro,lsub);
//                                    Following following=new Following(name,into,sub);
                                    databaseReference.setValue(professionEntry);
//                                    two.setValue(tutorDummy);
//                                    three.setValue(following);

                                    Toast.makeText(getApplicationContext(),"Sucessful fign up ",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Register.this,MainNavi.class));
                                    finish();
                                }
                                else Toast.makeText(getApplicationContext(),
                                        "Authentication Fail "+task.getException(),Toast.LENGTH_LONG).show();
                            }
                        });
            }
            else Toast.makeText(getApplicationContext(),"Password Didn't match or You are not connected to net",Toast.LENGTH_LONG).show();

        }
        else Toast.makeText(getApplicationContext(),"Please fill whole form",Toast.LENGTH_LONG).show();
    }
}
