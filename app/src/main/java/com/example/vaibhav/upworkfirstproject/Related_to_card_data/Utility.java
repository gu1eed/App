package com.example.vaibhav.upworkfirstproject.Related_to_card_data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.twitter.sdk.android.core.models.TwitterCollection;

/**
 * Created by VAIBHAV on 02-Feb-17.
 */

public class Utility {

    public  boolean checknet(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo aa=connectivityManager.getActiveNetworkInfo();
        return  aa.isConnectedOrConnecting();
    }
}
