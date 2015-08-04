package com.maxmuji.texttraveler;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Max on 8/3/2015.
 */
public class SmsReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private Activity callerAct;
    private String TAG = "TextTraveler_SmsReceiver";

    public SmsReceiver() {

    }

    public SmsReceiver(Activity callerAct) {
        super();
        this.callerAct = callerAct;
        Log.v(TAG, "SMS Receiver Constructed");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "onReceive called");
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Log.v(TAG, "SMS_RECEIVED");
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();
                Log.v(TAG, "SMS Message: " + message);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                //TODO: BUG - Why isn't this executing
                if (callerAct instanceof SplashActivity) {
                    Log.v(TAG, "Signalling to SplashActivity: Message received");
                    ((SplashActivity) callerAct).setResponse(message);
                }

                //TODO: BUG - This isn't prevening sms app from getting text
                // prevent any other broadcast receivers from receiving broadcast
                Log.v(TAG, "Hogging the broadcast for myself");
                abortBroadcast();
            }
        }
    }

}

