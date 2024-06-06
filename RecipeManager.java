/*
 * RecipeManager Class
 *
 * This class is responsible for managing a collection of Recipe objects. It provides functionalities
 * to add, remove, and search for recipes based on specific criteria. Additionally, it offers a method
 * to check which recipes can be prepared with the ingredients available in the pantry.
 *
 * Approach:
 * - Recipes are stored in a List, facilitating efficient add, remove, and search operations.
 * - The addRecipe and removeRecipe methods manage the recipes in the list.
 * - The findRecipe method utilizes Java's Stream API to filter recipes based on a given Predicate,
 *   allowing for flexible search queries.
 * - The matchIngredientsWithPantry method checks each recipe against available pantry items to determine
 *   if a recipe can be prepared with the current stock, supporting inventory management and planning.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


//Manages Recipe objects
public class RecipeManager {
    private List<Recipe> recipes;

    //Constructor
    public RecipeManager() {
    	recipes = new ArrayList<>();
    }
    
    // Adds recipe to the list 
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    // Removes a recipe from the list
    public void removeRecipe(Recipe recipe) {
    	recipes.remove(recipe);
    }
    
    // Generic method to find a recipe based on a predicate
    public Recipe findRecipe(Predicate<Recipe> searchCriteria) {
        return recipes.stream()
        		.filter(searchCriteria)
        		.findFirst()
        		.orElse(null);
    }
    
    // Checks which recipes can be fully prepared with the available pantry items
    public void matchIngredientsWithPantry(PantryManager pantryManager) {
        for (Recipe recipe : recipes) {
            System.out.println("Checking ingredients for: " + recipe.getName());
            for (Ingredient ingredient : recipe.getIngredients()) {
                PantryItem pantryItem = pantryManager.getItem(ingredient.getName());
                if (pantryItem != null && pantryItem.getQuantity() >= ingredient.getQuantity()) {
                    System.out.println("Available in pantry: " + ingredient.getName());
                } else {
                    System.out.println("Not available or insufficient quantity in pantry for: " + ingredient.getName());
                }
            }
        }
    }
}


