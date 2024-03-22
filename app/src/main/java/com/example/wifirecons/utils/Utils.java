package com.example.wifirecons.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static String convertTimestampToFormattedString(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(new Date(timestamp)) + " GMT";
    }


    public static long convertFormattedStringToTimestamp(String formattedDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            return simpleDateFormat.parse(formattedDate).getTime() / 1000L;
        } catch (ParseException e) {
            return -1L;
        }
    }

    public static long calculateTimeDifferenceFromNow(String dateStr) {
        return convertFormattedStringToTimestamp(dateStr) - getCurrentTimeInSeconds();
    }

    public static long getCurrentTimeInSeconds() {
        return System.currentTimeMillis() / 1000L;
    }
}
