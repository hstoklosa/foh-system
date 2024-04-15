package main.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents a recipe, including details such as the dish name, ingredients, preparation and appearance info,
 * and suggested wines.
 */
public class Recipe {
    /** Unique identifier for the recipe. */
    private String id;

    /** Name of the dish. */
    private String dishName;

    /** Map of Ingredient objects to their quantities. */
    private Map<Ingredient, String> ingredients;

    /** Preparation instructions for the dish. */
    private String preparationInfo;

    /** Description of the dish's appearance. */
    private String appearanceInfo;

    /** Price of the dish. */
    private double price;

    /** List to store suggested wines for the recipe. */
    private List<Wine> suggestedWines;

    /** Indicates whether the dish is currently available. */
    private boolean isAvailable;
    /**
     * Constructs a new Recipe with the specified details.
     *
     * @param id The unique identifier for the recipe.
     * @param dishName The name of the dish.
     * @param ingredients A map of ingredients and their quantities.
     * @param preparationInfo The preparation instructions.
     * @param appearanceInfo The description of the dish's appearance.
     */
    public Recipe(String id, String dishName, Map<Ingredient, String> ingredients, String preparationInfo, String appearanceInfo) {
        this.id = id;
        this.dishName = dishName;
        this.ingredients = ingredients;
        this.preparationInfo = preparationInfo;
        this.appearanceInfo = appearanceInfo;
        this.suggestedWines = new ArrayList<>();
        this.price = 0;
        this.isAvailable = true;
    }

    /**
     * Returns the unique ID of the recipe.
     * @return The unique identifier for the recipe as a String data type.
     */
    public String getId() {
        return id;
    }

    /**
     * Checks if the dish is available.
     * @return true if the dish is available, false otherwise.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability of the dish.
     * @param available The new availability status of the dish.
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Checks the availability of the dish directly from a Recipe instance.
     * @param recipe The dish to check availability for.
     * @return true if the dish specified by the recipe is available, false otherwise.
     */
    public boolean checkDishAvailability(Recipe recipe) {
        return recipe.isAvailable();
    }

    /**
     * Returns the name of the dish.
     * @return The name of the dish as a String data type.
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * Returns the map of ingredients and their quantities.
     * @return A map representing the ingredients and their quantities.
     */
    public Map<Ingredient, String> getIngredients() {
        return ingredients;
    }

    /**
     * Returns the preparation instructions.
     * @return The preparation instructions as a String data type.
     */
    public String getPreparationInfo() {
        return preparationInfo;
    }

    /**
     * Returns the description of the dish's appearance.
     * @return The description of the dish's appearance as a String data type.
     */
    public String getAppearanceInfo() {
        return appearanceInfo;
    }

    /**
     * Adds a list of suggested wines to the recipe.
     * @param wines A list of wines to be suggested with the recipe.
     */
    public void addSuggestedWines(List<Wine> wines) {
        if (wines != null) {
            suggestedWines.addAll(wines);
        }
    }

    /**
     * Returns a string representation of the recipe, including dish name, ingredients, preparation info, and appearance
     * description.
     * @return A string detailing the recipe.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe: ").append("\n");
        sb.append("Dish Name: ").append(dishName).append("\n");
        sb.append("Ingredients:").append("\n");
        ingredients.forEach((ingredient, quantity) -> sb.append(ingredient).append(": ").append(quantity).append("\n"));
        sb.append("\n").append("Preparation Description: ").append(preparationInfo).append("\n");
        sb.append("Appearance Description: ").append(appearanceInfo).append("\n");
        return sb.toString();
    }
}
