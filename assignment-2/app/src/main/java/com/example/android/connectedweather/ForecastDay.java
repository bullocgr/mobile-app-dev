package com.example.android.connectedweather;

import java.io.Serializable;
import java.util.ArrayList;

public class ForecastDay implements Serializable {
    public String dt_txt;
    public Main main;
    public ArrayList<Weather> weather;

    public class Main implements Serializable {
        public float temp;
        public float temp_min;
        public float temp_max;
    }

    public class Weather implements Serializable {
        public String main;
        public String description;
    }
}
