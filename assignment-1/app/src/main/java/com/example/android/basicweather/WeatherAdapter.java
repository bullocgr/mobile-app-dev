package com.example.android.basicweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private ArrayList<String> mWeatherList;
    private ArrayList<String> mToastList;
    private OnWeatherChangeListener mListener;

    public interface OnWeatherChangeListener {
        void onWeatherChanged(String todoText);
    }

    public WeatherAdapter(OnWeatherChangeListener listener) {
        mWeatherList = new ArrayList<>();
        mToastList = new ArrayList<>();
        mListener = listener;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weather_list_item, parent, false);

        return new WeatherViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        String weather = mWeatherList.get(position);
        holder.bind(weather);
    }

    public void addWeather(String weather) {
        mWeatherList.add(0, weather);
        notifyItemInserted(0);
    }

    public void addToast(String toast) {
        mToastList.add(0, toast);
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView mWeatherTv;

        private WeatherViewHolder(final View itemView) {
            super(itemView);
            mWeatherTv = itemView.findViewById(R.id.tv_weather_text);


            mWeatherTv.setOnClickListener(new CompoundButton.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String toastText = mToastList.get(getAdapterPosition());
                    mListener.onWeatherChanged(toastText);
                }
            });

        }


        void bind(String newWeatherText) {
            mWeatherTv.setText(newWeatherText);
        }
    }
}


