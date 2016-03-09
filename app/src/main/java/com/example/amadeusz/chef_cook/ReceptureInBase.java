package com.example.amadeusz.chef_cook;

import java.util.ArrayList;

public class ReceptureInBase {

    private String name;
    private int mainPhoto;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Multimedia> multimedia;
    private ReceptureDetails details;
    private Row category;
    private String[] steps;

    public ReceptureInBase(String name, int mainPhoto, ArrayList<Ingredient> ingredients, ArrayList<Multimedia> multimedia,
                           ReceptureDetails details, Row category, String[] steps) {
        this.name = name;
        this.mainPhoto = mainPhoto;
        this.ingredients = (ingredients == null ? new ArrayList<Ingredient>() : ingredients);
        this.multimedia = (multimedia == null ? new ArrayList<Multimedia>() : multimedia);
        this.details = details;
        this.category = category;
        this.steps = (steps == null ? new String[4] : steps);
    }

    public void addIngredient(Ingredient added, ArrayList<Ingredient> substituties) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        added.setSubstituties(substituties);
        ingredients.add(added);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setSteps(String[] added){
        steps = added;
    }

    public String[] getSteps() {
        return steps;
    }

    public void setDetails(String description, int timePrepare, int protein, int fat, int sugar, String difficulty, int weight1portion, int caloric, int portions, String timeDay) {
        details = new ReceptureDetails(description, timePrepare, protein, fat, sugar, difficulty,weight1portion, caloric, portions, timeDay);
    }

    public ReceptureDetails getDetails() {
        return details;
    }

    public void setMultimedia(ArrayList<Multimedia> newValue) {
        multimedia = newValue;
    }

    public ArrayList<Multimedia> getMultimedia() {
        return multimedia;
    }

    public ArrayList<Integer> getBiggerPhotos() {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < multimedia.size(); i++) {
            if(multimedia.get(i).getType().equals("Zdjęcie")) {
                result.add(multimedia.get(i).getBiggerId());
            }
        }
        return result;
    }

    public int getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(int mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
}
