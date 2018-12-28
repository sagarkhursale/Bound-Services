package com.example.sagar.boundservices;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


@SuppressLint("Registered")
public class LocalService extends Service {

    private static final String FIRST_QUERY = "Service";
    private static final String SECOND_QUERY = "Bound Service";
    private static final String THIRD_QUERY = "Service Types";


    private final IBinder mBinder = new LocalBinder();

    public String getQueryResponse(String query) {
        String response;

        if (query.contains("Hi")) {

            response = getString(R.string.welcom_message);

        } else if (query.equalsIgnoreCase(FIRST_QUERY)) {

            response = getString(R.string.service);

        } else if (query.equalsIgnoreCase(SECOND_QUERY)) {

            response = getString(R.string.bound_service);

        } else if (query.equalsIgnoreCase(THIRD_QUERY)) {

            response = getString(R.string.service_type);

        } else {

            response = getString(R.string.invalid_query);

        }

        return response;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }


    // END
}
