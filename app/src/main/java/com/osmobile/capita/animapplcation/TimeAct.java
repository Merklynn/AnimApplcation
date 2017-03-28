package com.osmobile.capita.animapplcation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.osmobile.capita.animapplcation.TimeService.LocalBinder;

public class TimeAct extends AppCompatActivity {
    private TimeService timeService;
    boolean isBound = false;
    TextView txt;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        txt = (TextView)findViewById(R.id.txtTime);
        btn = (Button)findViewById(R.id.btnTmOK) ;
        btn.setOnClickListener(GetTimeClick());
        Intent intent = new Intent(this, TimeService.class);
        bindService(intent, timeServiceConnection, Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    @NonNull
    private View.OnClickListener GetTimeClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = timeService.getCurrentTime();

                if(txt!= null)
                txt.setText(time);
            }
        };
    }


    private ServiceConnection timeServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalBinder binder = (LocalBinder)service;
            timeService =  binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };
}
