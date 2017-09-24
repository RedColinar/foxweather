package com.harry.data.common.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

/**
 * Base class for communicating with network.
 */
public abstract class RestfulServiceBase {
    @Inject
    Context context;

    public RestfulServiceBase() {
    }

    public abstract void initializeRetrofitAPIs();

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    protected boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
