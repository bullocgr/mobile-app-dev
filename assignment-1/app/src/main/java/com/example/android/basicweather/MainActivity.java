package com.example.android.basicweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements WeatherAdapter.OnWeatherChangeListener {

    private RecyclerView mWeatherListRV;
    private WeatherAdapter mWeatherAdapter;
    private Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeatherListRV = findViewById(R.id.rv_weather_list);
        mWeatherListRV.setLayoutManager(new LinearLayoutManager(this));
        mWeatherListRV.setHasFixedSize(true);

        mWeatherAdapter = new WeatherAdapter(this);
        mWeatherListRV.setAdapter(mWeatherAdapter);

        mWeatherListRV.setAdapter(mWeatherAdapter);

        ArrayList<String> displayWeather = initializeWeather();
        displayWeather(displayWeather);

        ArrayList<String> toastWeather = initializeToast();
        displayToast(toastWeather);



        mToast = null;
    }

    public ArrayList<String> initializeWeather() {
        ArrayList<String> weatherToday = new ArrayList<>();

        weatherToday.add("Wed Feb 5 - Partly cloudy - 19F\n");
        weatherToday.add("Tues Feb 4 - Snow showers - 11F\n");
        weatherToday.add("Mon Feb 3 - Partly cloudy - 17F\n");
        weatherToday.add("Sun Feb 2 - Sunny - 29F\n");
        weatherToday.add("Sat Feb 1 - Partly cloudy - 26F\n");
        weatherToday.add("Fri Jan 31 - Partly cloudy - 22F\n");
        weatherToday.add("Thur Jan 30 - Partly cloudy - 10F\n");
        weatherToday.add("Wed Jan 29 - Snow showers - 10F\n");
        weatherToday.add("Tues Jan 28 - Partly cloudy - 16F\n");
        weatherToday.add("Mon Jan 27 - Snow showers - 16F\n");

        return weatherToday;

    }

    public ArrayList<String> initializeToast() {
        ArrayList<String> weatherToast = new ArrayList<>();

        weatherToast.add("Partly cloudy with a low of 19F and a high of 27F. \nHopefully some snow on the mountain.\n");
        weatherToast.add("SNOW!!!! with a low of 11F and a high of 25F. \nPray for fresh powder.\n");
        weatherToast.add("Partly cloudy with a low of 17F and a high of 35F. \nHopefully some snow on the mountain.\n");
        weatherToast.add("Sunny with a low of 29F and a high of 45F. \nDefinitely want to shred some powder.\n");
        weatherToast.add("Partly cloudy with a low of 26F and a high of 40F. \nHopefully some snow on the mountain.\n");
        weatherToast.add("Partly cloudy with a low of 22F and a high of 31F. \nHopefully some snow on the mountain.\n");
        weatherToast.add("Partly cloudy with a low of 10F and a high of 26F. \nHopefully some snow on the mountain.\n");
        weatherToast.add("SNOW!!!! with a low of 10F and a high of 28F. \nPray for fresh powder.\n");
        weatherToast.add("Partly cloudy with a low of 16F and a high of 29F. \nHopefully some snow on the mountain.\n");
        weatherToast.add("SNOW!!!! with a low of 16F and a high of 23F. \nPray for fresh powder.\n");

        return weatherToast;

    }

    public void displayWeather(ArrayList<String> weatherToday) {
        for(int i = 0; i < 10; i++) {
            mWeatherAdapter.addWeather(weatherToday.get(i));
        }
    }

    public void displayToast(ArrayList<String> mToast) {
        for(int i = 0; i < 10; i++) {
            mWeatherAdapter.addToast(mToast.get(i));
        }
    }

    @Override
    public void onWeatherChanged(String toastText) {
        if(mToast != null) {
            mToast.cancel();
        }

        mToast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        mToast.show();
    }
}
