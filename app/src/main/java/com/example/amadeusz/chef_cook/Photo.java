package com.example.amadeusz.chef_cook;

public class Photo extends Multimedia {

    private int photoId;
    private int biggerId;

    public Photo(int photoId, int biggerId) {
        super("Zdjęcie");
        this.photoId = photoId;
        this.biggerId = biggerId;
    }

    @Override
    public String getReference() {
        return null;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int getBiggerId() {
        return biggerId;
    }

}
