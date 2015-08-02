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
    private final int MIN_TIME_BW_UPDATES = 100;
    private final int MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;

    public boolean refresh(Context context) {
        try {
            // Get the location manager
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            // Define the criteria how to select the locatioin provider -> use
            // default

            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, false);
            Location loc = locationManager.getLastKnownLocation(provider);

            if (loc == null) {
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                loc = locationManager.getLastKnownLocation(provider);
            }
            // TODO: When gps is disabled and reenabled, app has to be recompiled to work
            latitude = loc.getLatitude();
            longitude = loc.getLongitude();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
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
