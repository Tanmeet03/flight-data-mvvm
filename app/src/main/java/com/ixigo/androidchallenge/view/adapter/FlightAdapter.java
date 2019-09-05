package com.ixigo.androidchallenge.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ixigo.androidchallenge.R;
import com.ixigo.androidchallenge.databinding.FlightRowBinding;
import com.ixigo.androidchallenge.model.FlightDetailData;
import com.ixigo.androidchallenge.viewModel.ItemFlightViewModel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <h1>FlightAdapter</h1>
 * This is adpater class showing diff flight views using @see {@link ItemFlightViewModel}
 *
 * @author Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since Sept 5th 2019
 */

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightAdapterViewHolder> {
    /**
     * airLineData set in appendix object of response
     */
    private FlightDetailData.AirLineData airLineData;
    /**
     * flightListData- list of flight data
     */
    private ArrayList<FlightDetailData.FlightListData> flightListData;
    private Context mContext;

    public FlightAdapter(Context context) {
        this.airLineData = null;
        mContext = context;
        this.flightListData = new ArrayList<>();
    }

    @Override
    public FlightAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /** bind flight adapter layout to @see {@link FlightRowBinding} @*/
        FlightRowBinding flightRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.flight_row, parent, false);
        return new FlightAdapterViewHolder(flightRowBinding);
    }

    @Override
    public void onBindViewHolder(FlightAdapterViewHolder holder, int position) {
        holder.bindUser(airLineData, flightListData.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return flightListData.size();
    }

    /**
     * whenever the observer changes response is updated this method is called to set the latest data to it
     */
    public void setFlightDataList(FlightDetailData.AirLineData airLineData, ArrayList<FlightDetailData.FlightListData> flightListData) {
        this.airLineData = airLineData;
        this.flightListData = flightListData;
        notifyDataSetChanged();
    }

    static class FlightAdapterViewHolder extends RecyclerView.ViewHolder {

        FlightRowBinding mItemUserBinding;

        FlightAdapterViewHolder(FlightRowBinding itemUserBinding) {
            super(itemUserBinding.flightCard);
            this.mItemUserBinding = itemUserBinding;
        }

        void bindUser(FlightDetailData.AirLineData airLineData, FlightDetailData.FlightListData flightListData, Context mContext) {

            /** get airline fares and sort them in desc order*/
            ArrayList<FlightDetailData.FlightListData.AirlineFare> airlineFares = flightListData.getAirlineFares();
            Collections.sort(airlineFares, (o1, o2) -> o1.getAirlineFare().compareTo(o2.getAirlineFare()));
            if (mItemUserBinding.fareLayout.getChildCount() > 0) {
                mItemUserBinding.fareLayout.removeAllViews();
            }

            Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                    "font/lato_bold.ttf");
            /** Based on num of flight provider we have we create the layouts dynamically*/
            for (FlightDetailData.FlightListData.AirlineFare airlineFaresData :
                    airlineFares) {
                LinearLayout linearLayoutHorizontal = new LinearLayout(mContext);
                linearLayoutHorizontal.setOrientation(LinearLayout.HORIZONTAL);
                TextView tv1 = new TextView(mContext);
                TextView tv2 = new TextView(mContext);

                LinearLayout.LayoutParams childParam1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                childParam1.gravity = Gravity.LEFT;
                childParam1.weight = 1;
                tv1.setPadding(8, 8, 8, 8);
                tv1.setTypeface(face);
                tv1.setLayoutParams(childParam1);
                tv1.setTextSize(16);

                LinearLayout.LayoutParams childParam2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                childParam2.gravity = Gravity.RIGHT;
                childParam2.weight = 1;
                tv2.setLayoutParams(childParam2);
                tv2.setPadding(8, 8, 8, 8);
                tv2.setTextSize(16);
                tv2.setTypeface(face);
                tv2.setGravity(Gravity.RIGHT);
                tv2.setText(String.format("%s%s", mContext.getString(R.string.Rs), airlineFaresData.getAirlineFare()));
                String ticketCompanyName = "";
                if (airLineData.getProvidersCode().containsKey(airlineFaresData.getProviderId())) {
                    ticketCompanyName = airLineData.getProvidersCode().get(airlineFaresData.getProviderId());
                }
                tv1.setText(ticketCompanyName);

                linearLayoutHorizontal.addView(tv1);
                linearLayoutHorizontal.addView(tv2);
                mItemUserBinding.fareLayout.addView(linearLayoutHorizontal);
            }
/** Set data to View Model*/
            if (mItemUserBinding.getFlightViewModel() == null) {
                mItemUserBinding.setFlightViewModel(new ItemFlightViewModel(airLineData, flightListData, itemView.getContext()));
            } else {
                mItemUserBinding.getFlightViewModel().setFlightData(airLineData, flightListData);
            }
        }
    }
}
