import java.time.LocalDate;

public class MainTester {

    public static void main(String[] args) {
        GroceryListManager groceryManager = new GroceryListManager();
        PantryManager pantryManager = new PantryManager();
        RecipeManager recipeManager = new RecipeManager();

        // Adding items to the grocery and pantry lists
        groceryManager.addItem(new GroceryItem("Apples", "Fruit", 10, "Pieces"));
        pantryManager.addItem(new PantryItem("Pasta", 5, "Carbs", "Kg", LocalDate.now().plusYears(1)));

        // Displaying all items initially
        System.out.println("Initial Grocery List:");
        groceryManager.printAllItems();
        System.out.println("Initial Pantry Items:");
        pantryManager.printAllItems();

        // Updating an item in the grocery list
        GroceryItem apples = groceryManager.getItem("Apples");
        if (apples != null) {
            apples.setQuantityNeeded(15); // Increasing the quantity needed
            groceryManager.updateItem(apples);
        }

        // Removing an item from the pantry
        pantryManager.removeItem("Pasta");

        // Adding a new recipe
        Recipe omelette = new Recipe("Omelette");
        omelette.addIngredient(new Ingredient("Eggs", "Protein", 4, "Units"));
        recipeManager.addRecipe(omelette);

        // Displaying changes
        System.out.println("\nUpdated Grocery List after adding and updating:");
        groceryManager.printAllItems();
        System.out.println("Updated Pantry List after removal:");
        pantryManager.printAllItems();

        // Printing recipes
        System.out.println("\nCurrent Recipes:");
        Recipe foundRecipe = recipeManager.findRecipe(r -> r.getName().equals("Omelette"));
        if (foundRecipe != null) {
            System.out.println(foundRecipe);
        }

        // Simulate saving to CSV (displaying message only)
        System.out.println("\nSimulating saving to CSV files.");
        groceryManager.saveToCSV("groceries.csv");
        pantryManager.saveToCSV("pantry.csv");
    }
}
