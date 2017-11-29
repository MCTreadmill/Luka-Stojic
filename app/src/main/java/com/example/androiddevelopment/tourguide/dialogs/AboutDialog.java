package com.example.androiddevelopment.tourguide.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by androiddevelopment on 29.11.17..
 */

public class AboutDialog extends AlertDialog.Builder {
    public AboutDialog(Context context) {
        super(context);

        setTitle("About");
        setMessage("Luka Stojic");
        setCancelable(false);

        setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
    }


    public AlertDialog prepareDialog() {
        AlertDialog dialog = create();
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

}
