package com.example.wifirecons.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.LocationManager;
import android.provider.Settings;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;

public class GPSHelper {

    private final Context context;
    private static int REQUEST_ENABLE_LOCATION = 100012;

    public GPSHelper(Context context) {
        this.context = context;
    }

    public boolean isGPSEnabled() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public void requestGPSEnabling() {
        if (!isGPSEnabled()) {
            // GPS is not enabled, prompt the user to enable it
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
    }

    public static void enableLocation(Context context, Activity activity) {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        LocationServices.getSettingsClient(context)
                .checkLocationSettings(builder.build())
                .addOnSuccessListener(locationSettingsResponse -> {
                    // Location settings are satisfied.
                    // You can proceed with location-related operations.
                    // Set your flag or perform necessary actions.
                })
                .addOnFailureListener(exception -> {
                    // Location settings are not satisfied.
                    // Handle the failure, show a dialog, or prompt the user to enable location.
                    // In this case, we are starting an intent to resolve the issue.
                    if (exception instanceof ResolvableApiException) {
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) exception;
                            resolvable.startResolutionForResult(activity, REQUEST_ENABLE_LOCATION);
                        } catch (IntentSender.SendIntentException sendEx) {
                            // Ignore or handle the error
                        }
                    }
                });
    }

    public static void enableLocation(Context context) {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        LocationServices.getSettingsClient(context)
                .checkLocationSettings(builder.build())
                .addOnSuccessListener(locationSettingsResponse -> {
                    // Location settings are satisfied.
                    // You can proceed with location-related operations.
                    // Set your flag or perform necessary actions.
                })
                .addOnFailureListener(exception -> {
                    // Location settings are not satisfied.
                    // Handle the failure, show a dialog, or prompt the user to enable location.
                    // In this case, we are starting an intent to resolve the issue.
                    if (exception instanceof ResolvableApiException) {
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) exception;
                            resolvable.startResolutionForResult((Activity) context, REQUEST_ENABLE_LOCATION);
                        } catch (IntentSender.SendIntentException sendEx) {
                             //Ignore or handle the error
                        }
                    }
                });
    }

    public void openLocationSettings() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }
}