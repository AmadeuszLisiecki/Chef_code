package com.example.amadeusz.chef_cook;

public class Video extends Multimedia {

    private String reference;

    public Video(String reference) {
        super("Wideo");
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    @Override
    public int getPhotoId() {
        return R.drawable.video;
    }

    @Override
    public int getBiggerId() {
        return 0;
    }

}
