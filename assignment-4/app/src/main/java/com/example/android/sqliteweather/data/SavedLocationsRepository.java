package com.example.android.sqliteweather.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SavedLocationsRepository {
    private WeatherRepoDao mSavedLocationsDao;

    public SavedLocationsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mSavedLocationsDao = db.weatherRepoDao();
    }

    public void insertSavedLocation(SavedLocationName item) {
        new InsertAsyncTask(mSavedLocationsDao).execute(item);
    }

    public void deleteSavedLocation(SavedLocationName item) {
        new DeleteAsyncTask(mSavedLocationsDao).execute(item);
    }

    public LiveData<List<SavedLocationName>> getAllLocations() {
        return mSavedLocationsDao.getAllLocations();
    }




    private static class InsertAsyncTask extends AsyncTask<SavedLocationName, Void, Void> {
        private WeatherRepoDao mAsyncTaskDAO;

        InsertAsyncTask(WeatherRepoDao dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(SavedLocationName... forecastItems) {
            mAsyncTaskDAO.insert(forecastItems[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<SavedLocationName, Void, Void> {
        private WeatherRepoDao mAsyncTaskDAO;

        DeleteAsyncTask(WeatherRepoDao dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(SavedLocationName... forecastItems) {
            mAsyncTaskDAO.delete(forecastItems[0]);
            return null;
        }
    }
}
