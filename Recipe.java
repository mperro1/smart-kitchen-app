/*
 * Recipe Class
 *
 * This class represents a recipe which includes a name and a list of ingredients. It encapsulates all the data and
 * behaviors associated with a culinary recipe, such as adding or removing ingredients.
 *
 * Approach:
 * - The Recipe class maintains a list of Ingredient objects, reflecting the ingredients needed to prepare the recipe.
 * - It provides methods for adding and removing ingredients to and from the recipe, supporting dynamic recipe management.
 * - The toString method is overridden to provide a detailed string representation of the recipe, which includes
 *   the recipe name and a list of all its ingredients, facilitating easy printing and viewing of recipe details.
 */
import java.util.ArrayList;
import java.util.List;

//ADT Principle: Represents recipe data and behaviors
public class Recipe {
    private String name;
    private List<Ingredient> ingredients;

    // Constructor to initialize the recipe with a name
    public Recipe(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }
   
    // Returns the name of the recipe
    public String getName() {
        return name;
    }

    // Returns a list of all ingredients in the recipe
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    // Adds an ingredient to the recipe
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    // Removes an ingredient from the recipe by name
    public void removeIngredient() {
        ingredients.removeIf(ingredient -> ingredient.getName() == name);
    }

    // Provides a string representation of the recipe, including its name and ingredients
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Recipe Name:").append(name).append("\nIngredients:\n");
        for(Ingredient ingredient : ingredients) {
        	builder.append(ingredient).append("\n");
        }
        return builder.toString();
    }
}

