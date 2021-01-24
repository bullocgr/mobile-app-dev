package com.example.android.connectedweather;

import android.net.Uri;


import com.google.gson.Gson;

import java.util.ArrayList;

public class ForecastUtils {
    final static String FORECAST_SEARCH_BASE_URL =
            "https://api.openweathermap.org/data/2.5/forecast";
    final static String FORECAST_SEARCH_QUERY_PARAM = "q";
    final static String FORECAST_SEARCH_SORT_PARAM = "Denver,US";
    final static String FORECAST_SEARCH_APP_ID = "appid";
    final static String FORECAST_SEARCH_API_KEY= "00f212cd2ae8424466d2f31d0a880f97";


    public static class ForecastSearchResults {
        ArrayList<ForecastDay> list;
    }

    public static String buildForecastSearchURL() {
        return Uri.parse(FORECAST_SEARCH_BASE_URL).buildUpon()
                .appendQueryParameter(FORECAST_SEARCH_QUERY_PARAM, FORECAST_SEARCH_SORT_PARAM)
                .appendQueryParameter(FORECAST_SEARCH_APP_ID, FORECAST_SEARCH_API_KEY)
                .build()
                .toString();
    }


    public static ArrayList<ForecastDay> parseForecastSearchResults(String json) {
        Gson gson = new Gson();
        ForecastSearchResults results = gson.fromJson(json, ForecastSearchResults.class);
        if (results != null && results.list != null) {
            return results.list;
        } else {
            return null;
        }
    }
}

