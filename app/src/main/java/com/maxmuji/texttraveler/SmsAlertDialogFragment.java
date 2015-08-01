package com.maxmuji.texttraveler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


/**
 * Created by Max on 7/30/2015.
 */
public class SmsAlertDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.sms_warning)
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((SplashActivity)getActivity()).sendLocation();
                    }
                })
                .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
