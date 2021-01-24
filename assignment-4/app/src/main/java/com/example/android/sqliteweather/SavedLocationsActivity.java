//package com.example.android.sqliteweather;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Parcelable;
//import android.view.View;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.android.sqliteweather.data.ForecastItem;
//import com.example.android.sqliteweather.data.SavedLocationName;
//import com.example.android.sqliteweather.utils.SavedLocationsViewModel;
//
//import java.util.List;
//
//public class SavedLocationsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
//    private SavedLocationsViewModel mSavedLocationsViewModel;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mSavedLocationsViewModel = new ViewModelProvider(
//                this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(SavedLocationsViewModel.class);
//
////this is for the recycler view
//        RecyclerView mSavedLocationsRV = findViewById(R.id.rv_saved_location);
//        mSavedLocationsRV.setLayoutManager(new LinearLayoutManager(this));
//        mSavedLocationsRV.setHasFixedSize(true);
//
//        final GitHubSearchAdapter adapter = new GitHubSearchAdapter(this);
//        savedReposRV.setAdapter(adapter);
//
//
////        LiveData<List<SavedLocationName>> allLocations = mSavedLocationsViewModel.getAllLocations();
////        allLocations.observe(this, new Observer<List<SavedLocationName>>() {
////            @Override
////            public void onChanged(List<SavedLocationName> savedLocationNames) {
////                adapter.up
////            }
////        });
//
//
//    }
//
//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        Intent intent = new Intent(this, ForecastItemDetailActivity.class);
//        intent.putExtra(SettingsActivity.EXTRA_LOCATION_REPO, (Parcelable) sharedPreferences);
//        startActivity(intent);
//    }
//}
