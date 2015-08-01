package com.maxmuji.texttraveler;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by Max on 8/1/2015.
 */
public class GPSInterface implements LocationListener{

    private double latitude;
    private double longitude;
    private LocationManager locationManager;
    private String provider;

    public GPSInterface(Context context) {

        // Get the location manager
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location loc = locationManager.getLastKnownLocation(provider);
        latitude = loc.getLatitude();
        longitude = loc.getLongitude();
    }

    @Override
    public void onLocationChanged(Location loc) {
        latitude = loc.getLatitude();
        longitude = loc.getLongitude();
    }
    @Override
    public void onProviderDisabled(String provider) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
