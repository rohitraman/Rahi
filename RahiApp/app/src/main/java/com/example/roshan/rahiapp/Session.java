package com.example.roshan.rahiapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class Session {
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public Session(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("com.example.roshan.rahiapp", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setLoggedIn(boolean loggedIn)
    {
        editor.putBoolean("loggedIn",loggedIn);
        editor.commit();
    }

    public boolean loggedIn()
    {
        Log.i("loggedIn",Boolean.toString(preferences.getBoolean("loggedIn",false)));
        return preferences.getBoolean("loggedIn",false);
    }
}
