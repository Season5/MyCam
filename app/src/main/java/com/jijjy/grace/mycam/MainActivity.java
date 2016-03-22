package com.jijjy.grace.mycam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    //Declare button and gridview
    Button buttonpic;
    private GridView gv;
    ImageView img;
//    private GridViewAdapter gridAdapter;
    private static final String FIREBASE_URL = "https://msshortstories.firebaseio.com/";

    private Firebase firebaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase(FIREBASE_URL);
//        //declare grid and gridAdapter and set adapter to g
//        gv = (GridView)findViewById(R.id.gridView);
//        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
//        gv.setAdapter(gridAdapter);



        //link them to xml elements
        buttonpic = (Button)findViewById(R.id.takepic);
//        gv = (GridView)findViewById(R.id.gridView);
//        img = (ImageView)findViewById(R.id.pic);

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
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap)data.getExtras().get("data");
        img.setImageBitmap(bp);

        firebaseRef.push().setValue(bp);
    }
}
