package com.osmobile.capita.animapplcation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {

    EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        txt = (EditText)findViewById(R.id.etNext);

        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            String mess = data.getString("txt");
            if(txt != null)
            txt.setText(mess);
        }
    }

    public void btnOnClick(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        String txtMess = txt.getText().toString();
        intent.putExtra("txt",txtMess);
        startActivity(intent);
    }

    public void sendBroad(View view)
    {
        Intent intent = new Intent();
        intent.setAction("com.osmobile.capita.animapplcation");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra("txt", "A receiver message");
        sendBroadcast(intent);
        Toast.makeText(view.getContext(), "Intent sent", Toast.LENGTH_LONG).show();
    }

    public void doWait(View view)
    {
        Toast.makeText(view.getContext(),"Waiting", Toast.LENGTH_LONG).show();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                long future = System.currentTimeMillis()+ 10000;

                while(System.currentTimeMillis() < future)
                {

                    synchronized (this){
                        try {
                            wait(future - System.currentTimeMillis());
                        }
                        catch (InterruptedException e)
                        {
                            break;
                        }
                        catch (Exception e)
                        {

                            break;
                        }
                    }

                }
                handler.sendEmptyMessage(0);


            }
        };
        Thread thrd = new Thread(run);
        thrd.start();

    }

    Handler handler = new Handler(){
        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "The wait is over!", Toast.LENGTH_SHORT).show();
        }
    };
}
