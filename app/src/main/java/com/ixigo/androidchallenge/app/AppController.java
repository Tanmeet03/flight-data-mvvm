package com.ixigo.androidchallenge.app;

import android.app.Application;
import android.content.Context;

import com.ixigo.androidchallenge.network.ApiFactory;
import com.ixigo.androidchallenge.network.ApiService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * <h1>AppController</h1>
 * This is Application class of of our Project
 *
 * @author  Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since   Sept 5th 2019
 */
public class AppController extends Application {

    private ApiService apiService;
    private Scheduler scheduler;

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    /** Get api service*/
    public ApiService getUserService() {
        if (apiService == null) {
            apiService = ApiFactory.create();
        }

        return apiService;
    }

    /** Network scheduler*/
    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }

}
