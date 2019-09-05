package com.ixigo.androidchallenge.model;


import android.content.Context;
import android.graphics.drawable.Drawable;

import com.ixigo.androidchallenge.R;

/**
 * <h1>AirlineTypeEnum</h1>
 * This is enum class to fetch airline logo
 *
 * @author Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since Sept 5th 2019
 */
public enum AirlineTypeEnum {
    SPICEJET("SG"),
    AIRINDIA("AI"),
    GOAIR("G8"),
    JETAIRWAYS("9W"),
    INDIGO("6E");

    private String value;

    AirlineTypeEnum(String value) {
        this.value = value;
    }

    public static Drawable fromString(String value, Context context) {
        if (SPICEJET.getValue().equalsIgnoreCase(value)) {
            return context.getResources().getDrawable(R.drawable.spicejte);
        } else if (AIRINDIA.getValue().equalsIgnoreCase(value)) {
            return context.getResources().getDrawable(R.drawable.airindia);
        } else if (GOAIR.getValue().equalsIgnoreCase(value)) {
            return context.getResources().getDrawable(R.drawable.goair);
        } else if (JETAIRWAYS.getValue().equalsIgnoreCase(value)) {
            return context.getResources().getDrawable(R.drawable.jetairways);
        } else if (INDIGO.getValue().equalsIgnoreCase(value)) {
            return context.getResources().getDrawable(R.drawable.indigo);
        } else {
            return context.getResources().getDrawable(R.drawable.flight);
        }
    }

    public String getValue() {
        return value;
    }

}
