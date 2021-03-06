package com.jmedinilla.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BoundService extends Service {
    private IBinder mBinder;
    private Chronometer mChronometer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("BoundService", "onCreate");
        mBinder = new MyBinder();
        mChronometer = new Chronometer(this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("BoundService", "onBind");
        mChronometer.start();
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("BoundService", "onRebind");
        mChronometer.start();
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("BoundService", "onUnbind");
        mChronometer.stop();
        //return super.onUnbind(intent);
        //True indica que se desea llamar a onRebind
        return true;
    }

    public String getTimesStamp() {
        return new SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
    }

    public class MyBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}
