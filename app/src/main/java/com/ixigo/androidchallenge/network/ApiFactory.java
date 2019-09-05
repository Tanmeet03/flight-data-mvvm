package com.ixigo.androidchallenge.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ixigo.androidchallenge.utils.Constant.BASE_URL;

/**
 * <h1>ApiFactory</h1>
 * This is used to craete Retrofit rest service
 *
 * @author Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since Sept 5th 2019
 */
public class ApiFactory {

    public static ApiService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}
