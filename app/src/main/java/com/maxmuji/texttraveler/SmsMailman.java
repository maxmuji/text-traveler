package com.maxmuji.texttraveler;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Max on 8/1/2015.
 */
public class SmsMailman {

    // TODO: Ideally we want better solution here than google voice number
    private final String defaultNum = "6193560672";

    public void send(String phoneNo, String message, Context context) {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
        } catch (Exception e) {
            // TODO: Handle this situation better. Give option to retry
            Toast.makeText(context,
                    "Failed to send SMS message",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void send(String message, Context context) {
        this.send(defaultNum,message,context);
    }

}
