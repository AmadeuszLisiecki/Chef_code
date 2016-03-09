package com.example.amadeusz.chef_cook;

public class Step {

    private int photoId;
    private String description;

    public Step(int photoId, String description) {
        this.photoId = photoId;
        this.description = description;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getDescription() {
        return description;
    }

}
