package com.example.android.sqliteweather.utils;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.sqliteweather.data.ForecastItem;
import com.example.android.sqliteweather.data.SavedLocationName;
import com.example.android.sqliteweather.data.SavedLocationsRepository;

import java.util.List;

public class SavedLocationsViewModel extends AndroidViewModel {
    private SavedLocationsRepository mLocations;

    public SavedLocationsViewModel(Application application) {
        super(application);
        mLocations = new SavedLocationsRepository(application);
    }

    public void insertSavedLocation(SavedLocationName repo) {
        mLocations.insertSavedLocation(repo);
    }

//    public void deleteSavedLocation(SavedLocationName repo) {
//        mLocations.deleteSavedLocation(repo);
//    }

    public LiveData<List<SavedLocationName>> getAllLocations() {
        return mLocations.getAllLocations();
    }
}
