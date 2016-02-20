package com.example.amadeusz.chef_cook;

public abstract class Multimedia {

    protected String type;

    public Multimedia(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract String getReference();

    public abstract int getPhotoId();

    public abstract int getBiggerId();
}
