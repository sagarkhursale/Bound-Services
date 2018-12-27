package com.example.sagar.boundservices;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.util.Random;


@SuppressLint("Registered")
public class LocalService extends Service {

    private final IBinder mBinder=new LocalBinder();
    private final Random mGenerator=new Random();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int getRandomNumber(){
        return mGenerator.nextInt(10);
    }

    public class LocalBinder extends Binder{
        LocalService getService(){
            return LocalService.this;
        }
    }


    // END
}
