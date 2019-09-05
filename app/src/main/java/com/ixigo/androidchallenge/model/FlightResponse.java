package com.ixigo.androidchallenge.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 *
 * <h1>FlightResponse</h1>
 * This is response class of ixigo challenge api.
 *
 * @author Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since Sept 5th 2019
 */
public class FlightResponse {

    @SerializedName("appendix") private FlightDetailData.AirLineData airLineData;
    @SerializedName("flights") private ArrayList<FlightDetailData.FlightListData> flightListData;

    public FlightDetailData.AirLineData getAirLineData() {
        return airLineData;
    }

    public ArrayList<FlightDetailData.FlightListData> getFlightListData() {
        return flightListData;
    }

}
