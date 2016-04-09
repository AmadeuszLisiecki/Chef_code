package com.example.amadeusz.chef_cook;

import java.util.ArrayList;

public class Ingredient {

    private String meassure;
    private String name;
    private ArrayList<Ingredient> substituties;
    private double price;

    public Ingredient(String meassure, String name, double price) {
        this.meassure = meassure;
        this.name = name;
        substituties = new ArrayList<>();
        this.price = price;
    }

    @Override
    public String toString() {
        return  meassure + " " + name;
    }

    public void setSubstituties(ArrayList<Ingredient> substituties) {
        this.substituties = substituties;
    }

    public ArrayList<Ingredient> getSubstituties() {
        return substituties;
    }

    public String showSubstituties() {
        String result = toString();
        for(int i = 0; i < substituties.size(); i++) {
            result += (" - " + substituties.get(i));
        }
        return  result;
    }

    public double getPrice() {
        return price;
    }

    public String showPrice() {
        return toString() + " - " + getPrice() + " zł";
    }

}
