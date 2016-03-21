package com.jijjy.grace.mycam;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CamApi extends AppCompatActivity implements SurfaceHolder.Callback {

    //Declare camera and Surface
    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Button tkpic;

    Camera.PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    Camera.PictureCallback jpegCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_api);

        tkpic = (Button)findViewById(R.id.tkpic);
        tkpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    captureImage(surfaceView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        jpegCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(String.format("/sdcard/%d.jpg", System.currentTimeMillis()));

                    outputStream.write(data);
                    outputStream.close();
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {}
                Toast.makeText(getApplicationContext(), "Picture saved", Toast.LENGTH_SHORT).show();
                refreshCamera();

            }
        };

    }
    public void captureImage(View v)throws IOException {
        camera.takePicture(null, null, jpegCallback);
    }
    public void refreshCamera(){
        if(surfaceHolder.getSurface()==null){
            return;
        }
        try{
            camera.stopPreview();
        }
        catch (Exception e){

        }
        try{
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        }
        catch (Exception e){

        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }





    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try{
            camera = Camera.open();
        }
        catch (RuntimeException e){
            System.err.println(e);
            return;
        }
        Camera.Parameters param;
        if(camera!=null) {
            param = camera.getParameters();

        param.setPreviewSize(352, 288);
        camera.setParameters(param);
        }

        try{
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        }
        catch (Exception e){
            System.err.println(e);
            return;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        refreshCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }
}
