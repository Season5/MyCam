package com.jijjy.grace.mycam;

import android.graphics.Bitmap;

/**
 * Created by jaykayitare on 3/21/16.
 */
public class ImageItem {
    private Bitmap image;
    private String title;

    public ImageItem(Bitmap image, String title){
        super();
        this.image = image;
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Bitmap getImage(){
        return image;
    }

    public void setImage(Bitmap image){
        this.image = image;
    }
}
