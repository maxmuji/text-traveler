package com.maxmuji.texttraveler;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class SplashActivity extends FragmentActivity {

    // Duration of wait
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //setContentView(R.layout.splashscreen);

        // Warn the user about sms charges
        showAlertDialog();

        // New Handler to start the Menu-Activity
        // and close this Splash-Screen after some seconds.
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // Create an Intent that will start the Menu-Activity. */
                //Intent mainIntent = new Intent(SplashActivity.this,Menu.class);
                //SplashActivity.this.startActivity(mainIntent);
                //SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
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

    }

    //TODO: Handle rotation

}
