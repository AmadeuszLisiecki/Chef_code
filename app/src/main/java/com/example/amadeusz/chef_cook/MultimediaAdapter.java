package com.example.amadeusz.chef_cook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class MultimediaAdapter extends ArrayAdapter<Multimedia> {

    private ArrayList<Multimedia> multimedia;

    public MultimediaAdapter(Context context,
                             ArrayList<Multimedia> multimedia) {
        super(context, 0, multimedia);
        this.multimedia = multimedia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Multimedia currentMultimedia = multimedia.get(position);
        //int currentLayput = currentMultimedia.getType().equals("Zdjęcie") ? R.layout.multimedia : R.layout.video;
        // Check if an existing view is being reused, otherwise inflate the view
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.multimedia, parent, false);
        ImageView photo = (ImageView) convertView.findViewById(R.id.multimedia_photo);
        photo.setImageResource(currentMultimedia.getPhotoId());
        // Lookup view for data population
        // Populate the data into the templat
        // Return the completed view to render on screen
        return convertView;
    }
}

