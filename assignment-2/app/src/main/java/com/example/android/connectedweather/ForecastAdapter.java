package com.example.android.connectedweather;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastItemViewHolder> {

    private ArrayList<ForecastDay> mForecastData;
    private ArrayList<String> mDetailedForecastData;
    private OnForecastItemClickListener mOnForecastItemClickListener;

    public ForecastAdapter(OnForecastItemClickListener onForecastItemClickListener) {
        mOnForecastItemClickListener = onForecastItemClickListener;
    }

    public void updateForecastData(ArrayList<ForecastDay> forecastData, ArrayList<String> detailedForecastData) {
        mForecastData = forecastData;
        mDetailedForecastData = detailedForecastData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mForecastData != null && mDetailedForecastData != null) {
            return Math.min(mForecastData.size(), mDetailedForecastData.size());
        } else {
            return 0;
        }
    }

    @Override
    public ForecastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastItemViewHolder holder, int position) {
        holder.bind(mForecastData.get(position));
    }

    interface OnForecastItemClickListener {
        void onForecastItemClick(ForecastDay detailedForecast);
    }

    class ForecastItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mForecastTextView;

        public ForecastItemViewHolder(View itemView) {
            super(itemView);
            mForecastTextView = itemView.findViewById(R.id.tv_forecast_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnForecastItemClickListener.onForecastItemClick(mForecastData.get(getAdapterPosition()));
                }
            });
        }

        public void bind(ForecastDay forecast) {
//            int temp = (int)(((forecast.main.temp - 273) * 1.8) + 32);
//            System.out.println("\n\n\n\n\nDAY TEXT::::::::::::::::::::::::::::::::::::: " + temp +"\n\n\n\n\n");
            String dayText = forecast.dt_txt + " - " + (int)(((forecast.main.temp - 273) * 1.8) + 32) + " - " + forecast.weather.get(0).main;
            mForecastTextView.setText(dayText);        }
    }
}
