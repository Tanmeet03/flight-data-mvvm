package com.ixigo.androidchallenge.network;

import com.ixigo.androidchallenge.model.FlightResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    Observable<FlightResponse> fetchFlightData(@Url String url);
}
