package com.osmobile.capita.animapplcation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Camera extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = (ImageView) findViewById(R.id.imgCamera);
        Button but = (Button)findViewById(R.id.btnCamGoTo);
        if(!HasCamera()) {
            but.setEnabled(false);
            Toast.makeText(this, "You have no camera", Toast.LENGTH_LONG).show();
        }
        else {
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (HasCamera())
                        GotoCamera();
                }
            });
        }

        Button butInverse = (Button)findViewById(R.id.btnInverse);
       butInverse.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               InverseImage();
           }
       });

        Button butFrame = (Button)findViewById(R.id.btnCamFrame);
        butFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameImage();
            }
        });

        Button butSave = (Button)findViewById(R.id.btnCamSave);
        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveImage();
            }


        });

    }
    private void SaveImage() {
        MediaStore.Images.Media.insertImage(getContentResolver(), GetBitmapFromImageView(), "Tile", "Description");
    }
    private void InverseImage() {
        Bitmap bitImage = GetBitmapFromImageView();
        Bitmap framed = InverseImage(bitImage);
        imageView.setImageBitmap(framed);
    }

    private Bitmap GetBitmapFromImageView() {
        Drawable image = imageView.getDrawable();
        return ((BitmapDrawable)image).getBitmap();
    }

    private void FrameImage() {
        Drawable[] drawables = new Drawable[2];
        drawables[0]= imageView.getDrawable();
        drawables[1]= getResources().getDrawable(R.drawable.frame);
        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        imageView.setImageDrawable(layerDrawable);
    }



    /*Alter the image  */
    private Bitmap InverseImage(Bitmap bitImage) {
        int height = bitImage.getHeight();
        int width = bitImage.getWidth();
        /* Blank image */
        Bitmap framed = Bitmap.createBitmap(width, height, bitImage.getConfig());
        /*Alpha, Red, Green, Blue*/
        int A, R,G,B;
        int pixelColour;
        /*for each row*/
        for(int y = 0; y <height; y++) {
            /*for each column*/
            for(int x = 0; x <width; x++) {
                /*get pixel*/
                pixelColour = bitImage.getPixel(x,y);
                A= Color.alpha(pixelColour);
                R= InverseColour(Color.red(pixelColour));
                G=InverseColour(Color.green(pixelColour));
                B=InverseColour(Color.blue(pixelColour));
                framed.setPixel(x,y, Color.argb(A, R,G,B));
            }
        }
        return framed;
    }

    private static int InverseColour(int col) {
        return 255 -col;
    }

    private boolean HasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    private void GotoCamera()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {

            Bundle dat = data.getExtras();
            Bitmap photo = (Bitmap)dat.get("data");
            imageView.setImageBitmap(photo);
        }
    }


}
