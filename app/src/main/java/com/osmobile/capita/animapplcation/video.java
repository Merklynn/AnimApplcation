package com.osmobile.capita.animapplcation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

public class video extends AppCompatActivity {
    VideoView vidView;
    EditText txtEd;
    Button okBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        vidView = (VideoView)findViewById(R.id.videoView);
        txtEd= (EditText) findViewById(R.id.txtVideoEditURL);
        okBut = (Button)findViewById(R.id.btnVidOK);
        android.widget.MediaController cont = new android.widget.MediaController(this);
        cont.setAnchorView(vidView);
       vidView.setMediaController(cont);
        okBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = txtEd.getText().toString();
                //vidView.setVideoURI(android.net.Uri.parse(uri));
                vidView.setVideoPath(uri);


                vidView.start();
            }
        });
    }
}
