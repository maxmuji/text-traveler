package com.maxmuji.texttraveler;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends FragmentActivity {

    // Duration of wait
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private final String TAG = "TextTraveler_SplashActivity";
    private SmsReceiver receiver;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //setContentView(R.layout.splashscreen);

        // Warn the user about sms charges
        showAlertDialog();

    }

    // Shows the sms alert
    private void showAlertDialog(){
        DialogFragment smsAlertFragment = new SmsAlertDialogFragment();
        smsAlertFragment.show(getFragmentManager(), "sms_alert");
    }

    // Sends text with location.
    public void sendLocation(){

        // Set up gps object
        GPSInterface gps = new GPSInterface();

        // Handle GPS failure
        //TODO: User should be able to recover from this error
        if (!gps.refresh(this.getApplicationContext())) {
            Toast.makeText(this.getApplicationContext(),
                    "Failed to connect to GPS. Ensure location is enabled in phone settings.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        // Get coordinates
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        // Send the text
        SmsMailman locSender = new SmsMailman();
        String message = "GPS" + latitude + " " + longitude;
        locSender.send(message, this.getApplicationContext());
        Log.v(TAG, "Waiting for response");

        receiver = new SmsReceiver(this);
    }

    public void setResponse(String response) {

        Log.v(TAG, "Response message:" + response);

        //Start the main activity
        Intent i = new Intent(getApplicationContext(), ItemListActivity.class);
        startActivity(i);
    }


    //TODO: Handle rotation

}
