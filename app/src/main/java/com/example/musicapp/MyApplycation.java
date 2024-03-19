package com.example.musicapp;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyApplycation extends Application {
    public static final String FIREBASE_URL = "https://musicapp-d2d76-default-rtdb.firebaseio.com";
    private static FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance(FIREBASE_URL);
    }

    public static DatabaseReference referenceSong() {
        return database.getReference("/songs");
    }
}
