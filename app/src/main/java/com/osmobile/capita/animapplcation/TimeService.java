package com.osmobile.capita.animapplcation;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeService extends Service {

    private final IBinder binder = new LocalBinder();
    public TimeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getCurrentTime()
    {
       SimpleDateFormat dtF = new SimpleDateFormat("HH:mm:ss", Locale.UK);
        return dtF.format(new Date());
    }
    public class LocalBinder extends Binder
    {

        public LocalBinder() {
        }

        public TimeService getService()
        {
            return TimeService.this;
        }

    }
}
