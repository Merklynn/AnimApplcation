package com.osmobile.capita.animapplcation;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by JKidd on 15/02/2017.
 */

public class AnimIntentService extends IntentService {

    public String getTag() {
        return "com.osmobile.capita.animapplication";//getApplicationContext().getApplicationInfo().packageName.toString();
    }

    public AnimIntentService() {
        super("Anim Intent Service");
        Log.i(getTag(), "Instantiated service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(getTag(), "Created intent service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(getTag(), "Destroyed intent service");
    }

    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               Context#startService(Intent)}.
     *               This may be null if the service is being restarted after
     *               its process has gone away; see
     *               {@link Service#onStartCommand}
     *               for details.
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(getTag(), "Started intent service");

    }
}

