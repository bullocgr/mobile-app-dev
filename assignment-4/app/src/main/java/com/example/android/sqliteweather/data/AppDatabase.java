package com.example.android.sqliteweather.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SavedLocationName.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WeatherRepoDao weatherRepoDao();
    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) { //a single instance of this class can exist. Nothing more
        if (INSTANCE == null) { //only a single thread can run this block at once
            //we only want this because we don't want to run too many databases
            synchronized (AppDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "saved_locations_db").build(); //build a database object
                }

            }
        }
        return INSTANCE;
    }
}
