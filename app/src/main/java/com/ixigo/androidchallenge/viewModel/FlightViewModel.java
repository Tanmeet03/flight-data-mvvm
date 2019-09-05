package com.ixigo.androidchallenge.viewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.ixigo.androidchallenge.R;
import com.ixigo.androidchallenge.app.AppController;
import com.ixigo.androidchallenge.model.FlightDetailData;
import com.ixigo.androidchallenge.model.FlightResponse;
import com.ixigo.androidchallenge.network.ApiService;
import com.ixigo.androidchallenge.utils.Constant;
import com.ixigo.androidchallenge.utils.FareSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * <h1>FlightViewModel</h1>
 * This is view model class for main layout
 *
 * @author Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since Sept 5th 2019
 */

public class FlightViewModel extends Observable {

    public ObservableInt progressBar;
    public ObservableInt userRecycler;
    public ObservableInt userLabel;
    public ObservableField<String> messageLabel;
    private FlightDetailData.AirLineData airLineData;
    private ArrayList<FlightDetailData.FlightListData> flightListData;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private int sortTechniqueType = 0;

    public FlightViewModel(@NonNull Context context) {
        this.context = context;
        this.airLineData = null;
        this.flightListData = new ArrayList<>();
        progressBar = new ObservableInt(View.GONE);
        userRecycler = new ObservableInt(View.GONE);
        userLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_message_loading_users));
        initializeViews();
        fetchUsersList();
    }

    /** Sorts the list fare wise*/
    public void onClickSortByFare(View view) {
        sortTechniqueType = 1;
        initializeViews();
        fetchUsersList();
    }

    /** Sorts the list depature time wise*/
    public void onClickSortByDeparture(View view) {
        sortTechniqueType = 2;
        initializeViews();
        fetchUsersList();
    }
    /** Sorts the list arrival time wise*/
    public void onClickSortByArrival(View view) {
        sortTechniqueType = 3;
        initializeViews();
        fetchUsersList();
    }

    public void initializeViews() {
        userLabel.set(View.GONE);
        userRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }

    /**makes call to api to fetch data*/
    private void fetchUsersList() {

        AppController appController = AppController.create(context);
        ApiService apiService = appController.getUserService();

        Disposable disposable = apiService.fetchFlightData(Constant.RANDOM_USER_URL)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flightResponse -> {
                    updateUserDataList(flightResponse);
                    progressBar.set(View.GONE);
                    userLabel.set(View.GONE);
                    userRecycler.set(View.VISIBLE);
                }, throwable -> {
                    messageLabel.set(context.getString(R.string.error_message_loading_users));
                    progressBar.set(View.GONE);
                    userLabel.set(View.VISIBLE);
                    userRecycler.set(View.GONE);
                });
        compositeDisposable.add(disposable);
    }

    /** Updates the data as per reponse*/
    private void updateUserDataList(FlightResponse flightResponse) {
        this.airLineData = flightResponse.getAirLineData();
        this.flightListData = flightResponse.getFlightListData();
        sortData();
        setChanged();
        notifyObservers();
    }

    /** various sorting mechanisms*/
    private void sortData() {
        switch (sortTechniqueType){
            case 1:
                Collections.sort(flightListData, new FareSorter());
                break;
            case 2:
                Collections.sort(flightListData, (o1, o2) -> o1.getDepartureTime().compareTo(o2.getDepartureTime()));
                break;
            case 3:
                Collections.sort(flightListData, (o1, o2) -> o1.getArrivalTime().compareTo(o2.getArrivalTime()));
                break;
        }
    }

    public FlightDetailData.AirLineData getAirLineData() {
        return airLineData;
    }

    public ArrayList<FlightDetailData.FlightListData> getFlightDataList() {
        return flightListData;
    }

    /** unsubscribe from observabel on activity destroyed*/
    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}

