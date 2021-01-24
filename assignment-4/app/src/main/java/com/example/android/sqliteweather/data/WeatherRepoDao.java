package com.example.android.sqliteweather.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeatherRepoDao {
    @Insert
    void insert(SavedLocationName forecastItem);

    @Delete
    void delete(SavedLocationName forecastItem);

    @Query("SELECT * FROM locations")
    LiveData<List<SavedLocationName>> getAllLocations();
}
