package com.example.vaibhav.upworkfirstproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPass extends Activity {
    TextView tforgetext;
    EditText eemail;
    Button bretrieve;
    //For firebase authication
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateLIstener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
//        getActionBar().setHomeButtonEnabled(true);
        tforgetext=(TextView) findViewById(R.id.textview_forget);
        eemail=(EditText) findViewById(R.id.input_email);
        bretrieve=(Button) findViewById(R.id.button_retieve);
        bretrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.sendPasswordResetEmail(eemail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Email has been sent. Please check.",Toast.LENGTH_LONG).show();
                                }

                            }
                        });

            }
        });

    }
}
