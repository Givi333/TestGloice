package com.example.test_khevoyan.db;

import android.app.Application;

import androidx.room.Room;


public class TestApp extends Application {

    public static TestApp instance;
    private TestDatabase database;
    @Override
        public void onCreate() {
            super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, TestDatabase.class, "database")
                .build();

        }
    public static TestApp getInstance() {
        return instance;
    }

    public TestDatabase getDatabase() {
        return database;
    }



}
