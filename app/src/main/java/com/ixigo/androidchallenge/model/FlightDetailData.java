package com.ixigo.androidchallenge.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * <h1>FlightDetailData</h1>
 * This is enum class to fetch airline logo
 *
 * @author Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since Sept 5th 2019
 */
public class FlightDetailData implements Serializable {

    @SerializedName("appendix")
    AirLineData appendix;

    @SerializedName("flights")
    ArrayList<FlightListData> flightList;

    public class AirLineData implements Serializable {

        @SerializedName("airlines")
        Map<String, String> airlineCode;

        @SerializedName("airports")
        Map<String, String> airportCode;


        @SerializedName("providers")
        Map<String, String> providersCode;

        public Map<String, String> getAirlineCode() {
            return airlineCode;
        }

        public Map<String, String> getAirportCode() {
            return airportCode;
        }

        public Map<String, String> getProvidersCode() {
            return providersCode;
        }
    }

    public class FlightListData implements Serializable {
        @SerializedName("originCode")
        String originCode;

        @SerializedName("destinationCode")
        String destinationCode;

        @SerializedName("departureTime")
        String departureTime;

        @SerializedName("arrivalTime")
        String arrivalTime;

        @SerializedName("airlineCode")
        String airlineCode;

        @SerializedName("class")
        String airlineClass;

        @SerializedName("fares")
        ArrayList<AirlineFare> airlineFares;

        public String getOriginCode() {
            return originCode;
        }

        public String getDestinationCode() {
            return destinationCode;
        }

        public String getDepartureTime() {
            return departureTime;
        }

        public String getArrivalTime() {
            return arrivalTime;
        }

        public String getAirlineCode() {
            return airlineCode;
        }

        public String getAirlineClass() {
            return airlineClass;
        }

        public ArrayList<AirlineFare> getAirlineFares() {
            return airlineFares;
        }

        public class AirlineFare {
            @SerializedName("providerId")
            String providerId;

            @SerializedName("fare")
            String airlineFare;

            public String getProviderId() {
                return providerId;
            }

            public String getAirlineFare() {
                return airlineFare;
            }
        }
    }
}

