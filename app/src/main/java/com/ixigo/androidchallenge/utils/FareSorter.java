package com.ixigo.androidchallenge.utils;

import com.ixigo.androidchallenge.model.FlightDetailData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

/**
 * <h1>FareSorter</h1>
 * This class is a comparator class which is used to list flights on basis of fare lowest to highest form
 *
 * @author  Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since   Sept 5th 2019
 */
public class FareSorter implements Comparator<FlightDetailData.FlightListData> {

    @Override
    public int compare(FlightDetailData.FlightListData lhs, FlightDetailData.FlightListData rhs) {

            return  Integer.parseInt(lhs.getAirlineFares().get(0).getAirlineFare()) >  Integer.parseInt(rhs.getAirlineFares().get(0).getAirlineFare()) ? 1 : (
                    Integer.parseInt(lhs.getAirlineFares().get(0).getAirlineFare()) <  Integer.parseInt(rhs.getAirlineFares().get(0).getAirlineFare()) ? -1 : 0);
    }
}