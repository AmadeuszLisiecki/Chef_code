package com.example.amadeusz.chef_cook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Base {

    private static ArrayList<Row> categories = new ArrayList<>();
    private static ArrayList<ReceptureInBase> receptures = new ArrayList<>();
    private static ArrayList<Ingredient> ingredients = new ArrayList<>();
    private static ArrayList<Predictor> predictors = new ArrayList<>();

    public static void transformRows(ArrayList<Row> added) {
        for (int i = 0; i < added.size(); i++) {
            if(added.get(i).getType().equals("Kategoria")) {
                categories.add(added.get(i));
            }
            else{
                receptures.add(new ReceptureInBase(added.get(i).getDescription(), added.get(i).getPictureID(), null, null, null,
                        added.get(i).getParent(), null));
            }
        }
    }

    public static void addIngredient(Ingredient added) {
        ingredients.add(added);
    }

    public static void match(String receptureName, Ingredient ingredient) {
        for(int i = 0; i < receptures.size(); i++) {
            if(receptures.get(i).getName().equals(receptureName)) {
                receptures.get(i).getIngredients().add(ingredient);
                return;
            }
        }
    }

    public static void setSubstituteForIngredient(Ingredient ingredient, Ingredient substitute) {
        for (int i = 0; i < ingredients.size(); i++) {
            if(ingredients.get(i).equals(ingredient)) {
                ingredients.get(i).getSubstituties().add(substitute);
                substitute.getSubstituties().add(ingredient);
                return;
            }
        }
    }

    public static ArrayList<Ingredient> getIngredientsForRecepture(String receptureName) {
        for(int i = 0; i < receptures.size(); i++) {
            if(receptures.get(i).getName().equals(receptureName)) {
                return receptures.get(i).getIngredients();
            }
        }
        return null;
    }

    public static double getPriceForRecepture(String receptureName) {
        double sum = 0;
        for(int i = 0; i < receptures.size(); i++) {
            if(receptureName.equals(receptures.get(i).getName())) {
                for(int j = 0; j < receptures.get(i).getIngredients().size(); j++) {
                    sum += receptures.get(i).getIngredients().get(j).getPrice();
                }
            }
        }
        return sum;
    }

    public static void addStep(String[] added) {
        Collections.addAll(Arrays.asList(added));
    }

    public static void match(String receptureName, String[] steps) {
        for(int i = 0; i < receptures.size(); i++) {
            if(receptures.get(i).getName().equals(receptureName)) {
                receptures.get(i).setSteps(steps);
            }
        }
    }

    public static String[] getStepsForRecepture(String receptureName) {
        for(int i = 0; i < receptures.size(); i++) {
            if(receptures.get(i).getName().equals(receptureName)) {
                return receptures.get(i).getSteps();
            }
        }
        return null;
    }

    public static ReceptureInBase getRecepture(String name) {
        for(int i = 0; i < receptures.size(); i++) {
            if(receptures.get(i).getName().equals(name)) {
                return receptures.get(i);
            }
        }
        return null;
    }

    public static ReceptureInBase getRandomRecepture(String timeDay) {
        ArrayList<ReceptureInBase> connectedWithTimeDay = new ArrayList<>();
        for (ReceptureInBase r: receptures) {
            if(r.getDetails().getTimeDay().equals(timeDay)) {
                connectedWithTimeDay.add(r);
            }
        }
        return !connectedWithTimeDay.isEmpty() ? connectedWithTimeDay.get((int)(Math.random() * connectedWithTimeDay.size())) : null;
    }

    public static void addPredictor(Predictor added) {
        predictors.add(added);
    }

    public static ArrayList<Predictor> getPredictors() {
        return predictors;
    }

    public static double[] getPredictorsForIngedient(String ingredientName) {
        double[] result = new double[4];
        for(int i = 0; i < predictors.size(); i++) {
            if(predictors.get(i).getName().equals(ingredientName)) {
                result[0] = predictors.get(i).getGlass();
                result[1] = predictors.get(i).getSpool();
                result[2] = predictors.get(i).getSpoolSmall();
                result[3] = predictors.get(i).getPhotoId();
                return result;
            }
        }
        return result;
    }

}
