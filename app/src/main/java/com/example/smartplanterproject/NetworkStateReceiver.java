package com.example.smartplanterproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * The type Network state receiver.
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    private static final String TAG = "NetworkStateReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                final boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();

                Log.d(TAG, "Network connectivity change: " + isConnected);

                // Show message only if there is no internet connection
                if (!isConnected) {
                    // Use a Handler to show the message on the main thread
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (context instanceof Activity && !((Activity) context).isFinishing()) {
                                showMessage(context);
                            } else {
                                Log.e(TAG, "Context is not an activity or activity is finishing");
                            }
                        }
                    });
                }
            } else {
                Log.e(TAG, "ConnectivityManager is null");
            }
        } else {
            Log.e(TAG, "Received unexpected intent action: " + intent.getAction());
        }
    }

    private void showMessage(Context context) {
        if (context != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Connection status")
                    .setMessage("No data connection available")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
            Log.e(TAG, "Context is null");
        }
    }
}