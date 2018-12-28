package com.example.sagar.boundservices;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    boolean mBound = false;
    private LocalService mService;
    public ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalService.LocalBinder localBinder = (LocalService.LocalBinder) service;

            mService = localBinder.getService();

            mBound = true;

            Log.i(TAG, "onServiceConnected");
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.i(TAG, "serviceDis-Connected");
        }

    };
    private TextView textView_Response;
    private TextInputEditText edit_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_Response = findViewById(R.id.tv_output);
        edit_query = findViewById(R.id.edit_client_query);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Log.i(TAG, "onStart()");
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
        mBound = false;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onButtonClick(View view) {
        if (mBound) {
            String textQuery = Objects.requireNonNull(edit_query.getText()).toString().trim();

            if (textQuery.isEmpty()) {
                Toast.makeText(mService, "Oh, Something is missing!", Toast.LENGTH_SHORT).show();
                textView_Response.setText(getString(R.string.tv_output));
            } else {
                String response = mService.getQueryResponse(textQuery);
                textView_Response.setText(response);
            }
        }
    }

    // END
}
