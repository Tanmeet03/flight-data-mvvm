package com.ixigo.androidchallenge.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ixigo.androidchallenge.R;
import com.ixigo.androidchallenge.databinding.ActivityMainFlightBinding;
import com.ixigo.androidchallenge.view.adapter.FlightAdapter;
import com.ixigo.androidchallenge.viewModel.FlightViewModel;

import java.util.Observable;
import java.util.Observer;

/**
 * <h1>FlightActivity</h1>
 * This is main activity of project.
 * <p>
 * It performs 3 main function
 * 1. initialize data binding
 * 2. set up recycler view
 * 3. set up observer
 * </p>
 *
 * @author Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since Sept 5th 2019
 */
public class FlightActivity extends AppCompatActivity implements Observer {

    /**
     * flightActivityBinding binds model to layout
     */
    private ActivityMainFlightBinding flightActivityBinding;
    private FlightViewModel flightViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpListOfFlightView(flightActivityBinding.rvList);
        setUpObserver(flightViewModel);
    }

    /**
     * binds flightview model with layout view using Databinding
     */
    private void initDataBinding() {
        flightActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_flight);
        flightViewModel = new FlightViewModel(this);
        flightActivityBinding.setFlightViewModel(flightViewModel);
    }

    //

    /**
     * set up the list of flights with recycler view
     *
     * @param flightList- recyclerview
     */
    private void setUpListOfFlightView(RecyclerView flightList) {
        FlightAdapter flightAdapter = new FlightAdapter(getApplicationContext());
        flightList.setAdapter(flightAdapter);
        flightList.setLayoutManager(new LinearLayoutManager(this));
        flightList.addItemDecoration(new DividerItemDecoration(flightList.getContext(), DividerItemDecoration.VERTICAL));
    }

    /**
     * Sets up observer for any change
     */
    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    /**
     * This method is called whenever the observed object is changed.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     *            method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof FlightViewModel) {
            FlightAdapter flightAdapter = (FlightAdapter) flightActivityBinding.rvList.getAdapter();
            FlightViewModel viewModel = (FlightViewModel) o;
            flightAdapter.setFlightDataList(viewModel.getAirLineData(), viewModel.getFlightDataList());
        }
    }

    /**
     * Reset flight view model to prevent leaks
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        flightViewModel.reset();
    }
}
