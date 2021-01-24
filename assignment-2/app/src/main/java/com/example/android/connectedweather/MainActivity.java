package com.example.android.connectedweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements ForecastAdapter.OnForecastItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mForecastListRV;
    private ForecastAdapter mForecastAdapter;
    private ProgressBar mProgressBar;
    private TextView mErrorMessageTV;


    private static final String[] dummyDetailedForecastData = {
            "Not a cloud in the sky today, with lows around 52F and highs near 75F.",
            "Clouds gathering in the late afternoon and slightly cooler than the day before, with lows around 49F and highs around 72F",
            "Scattered clouds all day with lows near 52F and highs near 73F",
            "Increasing cloudiness as the day goes on with some cooling; lows near 48F and highs near 70F",
            "Showers beginning in the morning and popping up intermittently throughout the day; lows near 46F and highs near 65F",
            "Showers scattered throughout the day, with lows near 46F and highs of 63F",
            "Showers increasing in intensity towards evening, with lows near 46F and highs near 64F",
            "Steady rain all day; lows near 47F and highs near 62F",
            "More steady rain, building in intensity towards evening; lows near 47F and highs near 61F",
            "Very, very strong winds and heavy rain; make sure you're wearing your raincoat today; lows near 50F and highs near 65F",
            "Rain ending in the very early morning, then clearing, with residual strong winds; lows near 61F and highs around 70F",
            "Beautiful day, with nothing but sunshine; lows near 55F and highs around 77F",
            "Another gorgeous day; lows near 56F and highs around 81F"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mForecastListRV = findViewById(R.id.rv_forecast_list);

        mForecastListRV.setLayoutManager(new LinearLayoutManager(this));
        mForecastListRV.setHasFixedSize(true);

        mForecastAdapter = new ForecastAdapter(this);
        mForecastListRV.setAdapter(mForecastAdapter);

        mProgressBar = findViewById(R.id.pb_loading_indicator);
        mErrorMessageTV = findViewById(R.id.tv_error_message);


        doForecast();
//        onForecastItemClick();
    }

    @Override
    public void onForecastItemClick(ForecastDay detailedForecast) {
        Intent ForecastActivityIntent = new Intent(this, ForecastDetailActivity.class);
        ForecastActivityIntent.putExtra(ForecastDetailActivity.EXTRA_FORECAST, detailedForecast);
        startActivity(ForecastActivityIntent);
    }

    private void doForecast() {
        String url = ForecastUtils.buildForecastSearchURL();
        Log.d(TAG, "query URL: " + url);
        new ForecastSearchTask().execute(url);
    }

    public class ForecastSearchTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            String results = null;
            try {
                results = NetworkUtils.doHTTPGet(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mProgressBar.setVisibility(View.INVISIBLE);
            if(s != null) {
                mErrorMessageTV.setVisibility(View.INVISIBLE);
                mForecastListRV.setVisibility(View.VISIBLE);
                ArrayList<ForecastDay> searchResultsList = ForecastUtils.parseForecastSearchResults(s);
                mForecastAdapter.updateForecastData(searchResultsList,new ArrayList<String>(Arrays.asList(dummyDetailedForecastData)));
            } else {
                mErrorMessageTV.setVisibility(View.VISIBLE);
                mForecastListRV.setVisibility(View.INVISIBLE);
            }
        }
    }
}
