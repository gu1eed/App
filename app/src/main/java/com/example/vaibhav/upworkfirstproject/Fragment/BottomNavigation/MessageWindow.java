package com.example.vaibhav.upworkfirstproject.Fragment.BottomNavigation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vaibhav.upworkfirstproject.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageWindow extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN=1;
    private static final int RC_PHOTO_PICKER =  2;

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;

    private EditText mMessageEditText;
    private Button mSendButton;

    private String mUsername;

    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private FirebaseStorage mFirebaseStorage;
    private ChildEventListener mchildEventListener;


    //For firebase authication
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateLIstener;
    String child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_window);
        String name=getIntent().getStringExtra("name");
        if (name!=null){
            mUsername=name;
            child=name;
        }else { mUsername = ANONYMOUS;
        child="messages";}
        //Initialise Firebase
        mfirebaseDatabase=FirebaseDatabase.getInstance();
        mFirebaseStorage=FirebaseStorage.getInstance();
        mFirebaseAuth=FirebaseAuth.getInstance();
        mMessageDatabaseReference=mfirebaseDatabase.getReference().child(child);


        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);

        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);

        // Initialize message ListView and its adapter
        final List<ChatMessage> chatMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, chatMessages);
        mMessageListView.setAdapter(mMessageAdapter);

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);



        // Enable Send button when there's text to send
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click
                ChatMessage chatMessage =new ChatMessage(mMessageEditText.getText().toString(),mUsername,null);
                mMessageDatabaseReference.push().setValue(chatMessage);
                // Clear input box
                mMessageEditText.setText("");
            }
        });
        attachReadListener();




    }

    public void attachReadListener(){
        //ChildEventListener to read data from the database when changes occur
        if (mchildEventListener==null){
            mchildEventListener=new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //Reflection. Or as data has exact field as ChatMessage .
                    ChatMessage chatMessage =dataSnapshot.getValue(ChatMessage.class);
                    mMessageAdapter.add(chatMessage);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mMessageDatabaseReference.addChildEventListener(mchildEventListener);
        }
        else Toast.makeText(this,"Not working",Toast.LENGTH_SHORT).show();
    }

    public void deattachReadListener(){
        if (mchildEventListener!=null){
            mMessageDatabaseReference.removeEventListener(mchildEventListener);
            mchildEventListener=null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        deattachReadListener();
        mMessageAdapter.clear();

    }
}
