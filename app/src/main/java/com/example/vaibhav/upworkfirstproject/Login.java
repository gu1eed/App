package com.example.vaibhav.upworkfirstproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    //For firebase authication
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateLIstener;

    EditText eemail,epass;
    TextView tregister,tforgotpass;
    Button bsub;
    String valemail,valpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eemail=(EditText) findViewById(R.id.input_email);
        epass=(EditText) findViewById(R.id.input_password);
        bsub=(Button) findViewById(R.id.button_submit);
        tregister=(TextView) findViewById(R.id.textview_register);
        tforgotpass=(TextView) findViewById(R.id.textview_forget);
        valemail=eemail.getText().toString();
        valpass=epass.getText().toString();

        //Register Textview Function
        tregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);

            }
        });

        //Forgot Textview Password
        tforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,ForgetPass.class);
                startActivity(intent);
            }
        });
    }

    //Submit button function
    public void SubmitData(View view){
        mFirebaseAuth.signInWithEmailAndPassword(valemail,valpass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor edit=sharedPreferences.edit();
                            edit.putBoolean("sharedstate",true).commit();
                            Intent intent=new Intent(getApplication(),MainNavi.class);
                            startActivity(intent);
                        }
                        else Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
