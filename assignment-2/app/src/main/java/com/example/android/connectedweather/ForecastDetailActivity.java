package com.example.android.connectedweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class ForecastDetailActivity extends AppCompatActivity {
    public static final String EXTRA_FORECAST = "Forecast Data";
    private ForecastDay mForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_detail);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(EXTRA_FORECAST)) {
            //launch with new intent and grab the repo
            mForecast = (ForecastDay) intent.getSerializableExtra(EXTRA_FORECAST);

            TextView forecastNameTV = findViewById(R.id.tv_repo_name);
            forecastNameTV.setText(mForecast.dt_txt);

            TextView repoDescriptionTV = findViewById(R.id.tv_repo_description);
            int temp = (int)(((mForecast.main.temp - 273) * 1.8) + 32);
            String displayTemp = "Temperature: " + temp + "F";
            repoDescriptionTV.setText(displayTemp);

            TextView feelsLike = findViewById(R.id.tv_feels_like);
            int min = (int)(((mForecast.main.temp_min - 273) * 1.8) + 32);
            int max = (int)(((mForecast.main.temp_max - 273) * 1.8) + 32);
            String minMax = "Minimum temperature: " + min + "F" + "\nMaximum temperature: " + max + "F";
            feelsLike.setText(minMax);


            TextView forecastDescriptionTV = findViewById(R.id.tv_forecast_description);
            forecastDescriptionTV.setText("Conditions: " + mForecast.weather.get(0).description);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.forecast_area, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_area:
                viewAreaOnApp();
                return true;

            case R.id.action_share:
                shareWeather();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void viewAreaOnApp() {
        if (mForecast != null) {
            String uri = String.format(Locale.ENGLISH, "geo:39.742043, -104.991531");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            PackageManager pm = getPackageManager();

            //pass a flag saying we don't want any false matches
            List<ResolveInfo> activities = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

            if(activities.size() > 0) {
                startActivity(intent);
            }
        }
    }

    public void shareWeather() {
        if(mForecast != null) {
            String shareText = "Conditions: " + mForecast.weather.get(0).description; //add the text to the intent
            Intent shareIntent = new Intent(Intent.ACTION_SEND); //we want to send the intent

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            shareIntent.setType("text/plain");

            //share sheet so user can choose what to share on
            Intent chooserIntent = Intent.createChooser(shareIntent, null);
            startActivity(chooserIntent);
        }
    }
}
