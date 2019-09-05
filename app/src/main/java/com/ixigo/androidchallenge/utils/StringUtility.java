package com.ixigo.androidchallenge.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <h1>StringUtility</h1>
 * This is utility class
 *
 * @author  Tanmeet Singh Bhalla
 * @version 1.0.0
 * @since   Sept 5th 2019
 */
public class StringUtility {
    public static String convertToTime(String time, Context context) {
        String formattedDate = "";
        SimpleDateFormat sdf;
        if (!isNullOrEmptyString(time)) {
            try {
                Long timeInMs = Long.parseLong(time);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timeInMs);
                sdf = new SimpleDateFormat("HH:mm");
                try {
                    sdf = (SimpleDateFormat) android.text.format.DateFormat.getTimeFormat(context);
                } catch (Exception e) {
                }
                formattedDate = sdf.format(calendar.getTimeInMillis());
            } catch (Exception e) {
            }
        }
        return formattedDate;
    }

    private static boolean isNullString(String text) {
        return (null == text || ("").equals(text) || "null".equalsIgnoreCase(text));
    }

    private static boolean isEmptyString(String text) {
        return (0 == text.trim().length());
    }

    public static boolean isNullOrEmptyString(String text) {
        return isNullString(text) || isEmptyString(text);
    }

    public static String getTimDifference(long timeDiff) {
        String formattedTime = "";
        try {
            int seconds = (int) timeDiff / 1000;

            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            formattedTime = hours + "h " + minutes+"m";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedTime;
    }
}
