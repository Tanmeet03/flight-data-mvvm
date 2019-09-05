package com.ixigo.androidchallenge.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ixigo.androidchallenge.model.AirlineTypeEnum;
import com.ixigo.androidchallenge.model.FlightDetailData;
import com.ixigo.androidchallenge.utils.StringUtility;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <h1>ItemFlightViewModel</h1>
 * This is view model class for adapter layout
 *
 * @author Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since Sept 5th 2019
 */

public class ItemFlightViewModel extends BaseObservable {

    private Context context;
    private FlightDetailData.FlightListData flightListData;
    private FlightDetailData.AirLineData airLineData;

    public ItemFlightViewModel(FlightDetailData.AirLineData airLineData, FlightDetailData.FlightListData flightListData, Context context) {
        this.airLineData=airLineData;
        this.flightListData = flightListData;
        this.context = context;
    }

    public Drawable getAirlineLogo() {
        return AirlineTypeEnum.fromString(flightListData.getAirlineCode(),context);
    }

    public String flightArrivalTime() {
        return StringUtility.convertToTime(flightListData.getArrivalTime(), context);
    }

    public String getFlightDestinationName() {
        if (airLineData.getAirportCode().containsKey(flightListData.getDestinationCode())) {
            return airLineData.getAirportCode().get(flightListData.getDestinationCode());
        }
        return "";
    }
    public String getFlightOriginName() {
        if (airLineData.getAirportCode().containsKey(flightListData.getOriginCode())) {
            return airLineData.getAirportCode().get(flightListData.getOriginCode());
        }
        return "";
    }
    public String getFlightName() {
        if (airLineData.getAirlineCode().containsKey(flightListData.getAirlineCode())) {
            return airLineData.getAirlineCode().get(flightListData.getAirlineCode());
        }
        return "";
    }

    public String getFlightClassType() {
        return flightListData.getAirlineClass();
    }

    public String flightDepartureTime() {
        return StringUtility.convertToTime(flightListData.getDepartureTime(), context);
    }
    public String flightTime() {
        long timeDiff = Long.parseLong(flightListData.getArrivalTime()) - Long.parseLong(flightListData.getDepartureTime());
        return StringUtility.getTimDifference(timeDiff);
    }
    public void setFlightData(FlightDetailData.AirLineData airLineData, FlightDetailData.FlightListData flightListData) {
        this.flightListData = flightListData;
        this.airLineData=airLineData;
        notifyChange();
    }
}
