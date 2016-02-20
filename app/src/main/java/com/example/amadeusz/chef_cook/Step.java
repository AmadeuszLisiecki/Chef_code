package com.example.amadeusz.chef_cook;

public class Step {

    private int photoId;
    private String description;
    private int nr;

    public Step(int photoId, String description, int nr) {
        this.photoId = photoId;
        this.description = description;
        this.nr = nr;
    }

    public int getNr() {
        return nr;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getDescription() {
        return description;
    }

}
