package com.example.amadeusz.chef_cook;

import android.graphics.Bitmap;

public abstract class Multimedia {

    protected String type;

    public Multimedia(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract String getReference();

    public abstract Bitmap getPhotoBitmap();

    public abstract Bitmap getPhotoBigBitmap();
}
