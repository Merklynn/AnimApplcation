package com.osmobile.capita.animapplcation;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ViewGroup layout;
    Button but;
    boolean down = false;
    EditText txt;
    NotificationCompat.Builder notification;
    private static final int UniqueNoteId = 1234;

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        SaveInfo();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (ViewGroup) findViewById(R.id.rlMain);
        but = (Button) layout.findViewById(R.id.btnMN);
        txt = (EditText)findViewById(R.id.etMain);

        StartIntentService();
        StartMainService();
        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            String mess = data.getString("txt");
            txt.setText(mess);
        }
        else
        {
            LoadInfo();
        }


        layout.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            /**
             * Called when a touch event is dispatched to a view. This allows listeners to
             * get a chance to respond before the target view.
             *
             * @param v     The view the touch event has been dispatched to.
             * @param event The MotionEvent object containing full information about
             *              the event.
             * @return True if the listener has consumed the event, false otherwise.
             */
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {


                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
                    );
                    ViewGroup.LayoutParams szParams;
                    TransitionManager.beginDelayedTransition(layout);
                    if (event.getAction() == MotionEvent.ACTION_DOWN)
                        if (!down) {
                            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                            but.setLayoutParams(params);

                            szParams = but.getLayoutParams();
                            szParams.width = 450;
                            szParams.height = 300;
                            but.setLayoutParams(szParams);
                            down = true;
                        } else {
                            params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                            but.setLayoutParams(params);

                            szParams = but.getLayoutParams();
                            szParams.width = 200;
                            szParams.height = 120;
                            but.setLayoutParams(szParams);
                            down = false;
                        }


                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

        Button btnLst = (Button)findViewById(R.id.btnGotoList);
        btnLst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListAct.class);

                startActivity(intent);
            }
        });

        Button cust = (Button)findViewById(R.id.btnCustom);
        cust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomAdap.class);
                startActivity(intent);
            }
        });

        Button dataBut = (Button)findViewById(R.id.btnData);
        dataBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GotToActivity(DataBase.class);
            }
        });
        Button vidBut =(Button)findViewById(R.id.btnMainVideo);
        vidBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GotToActivity(video.class);
            }
        });

        Button camBut  =(Button)findViewById(R.id.btnCam);
        camBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GotToActivity(Camera.class);
            }
        });

        Button notBut = (Button)findViewById(R.id.btnMakeNot);
        notBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeNotification();
            }


        });
    }
    private void MakeNotification() {
        String mess = txt.getText().toString();
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        notification.setSmallIcon(android.R.drawable.ic_dialog_alert);
        notification.setTicker(mess);
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Notification");
        notification.setContentText(mess);

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager man = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        man.notify(UniqueNoteId, notification.build());
    }

    private void GotToActivity(Class act)
    {
        try {
            Intent intent = new Intent(MainActivity.this, act);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG);
        }
    }

    private void StartMainService() {
        Intent intent = new Intent(this, AnimService.class);
        startService(intent);
    }

    private void StartIntentService() {
        Intent intent = new Intent(this, AnimIntentService.class);
        startService(intent);
    }

    public void btnOnClick(View view) {
        Intent intent = new Intent(this, NextActivity.class);

        if (txt != null) {
            String txtMess =  txt.getText().toString();

            intent.putExtra("txt", txtMess);
        }
        startActivity(intent);
    }
    public void btnTimeOnClick(View view) {

        Intent intent = new Intent(this, TimeAct.class);

        startActivity(intent);
    }

    public void SaveInfo()
    {
        SharedPreferences preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("txt", txt.getText().toString());
        editor.apply();
        //Toast.makeText(this, "Saved txt", Toast.LENGTH_SHORT).show();
    }

    public void LoadInfo() {
        SharedPreferences preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String def = "";
        def = preferences.getString("txt", "");
        txt.setText(def);

    }


}
