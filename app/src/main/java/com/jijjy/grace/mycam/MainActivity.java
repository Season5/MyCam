package com.jijjy.grace.mycam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Declare button and gridview
    Button buttonpic;
    GridView gv;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //link them to xml elements
        buttonpic = (Button)findViewById(R.id.takepic);
//        gv = (GridView)findViewById(R.id.gridView);
        img = (ImageView)findViewById(R.id.pic);

        // set onclick listener on button
        buttonpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent that launches the camera app
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        Bitmap bp = (Bitmap)data.getExtras().get("data");
        img.setImageBitmap(bp);
    }
}
