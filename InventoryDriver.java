/*
 * InventoryDriver Class
 *
 * This class serves as the main driver for an inventory management system, facilitating user interaction
 * through a console-based menu. It manages pantry items, grocery items, and recipes, providing functions
 * such as adding, removing, updating, and loading items from CSV files. This class orchestrates operations
 * between different manager classes such as PantryManager, GroceryListManager, and RecipeManager.
 *
 * Key Features:
 * - Offers a text-based menu system to perform various inventory-related tasks.
 * - Integrates with manager classes to perform CRUD operations on items and recipes.
 * - Loads and saves data to and from CSV files, providing persistence across sessions.
 *
 * Usage:
 * - The user interacts with the system via a command-line interface, choosing actions related to inventory management.
 * - Each action in the menu triggers corresponding methods in manager classes that handle specific types of items or recipes.
 */
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.File;

public class InventoryDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PantryManager pantryManager = new PantryManager();
        GroceryListManager groceryListManager = new GroceryListManager();
        RecipeManager recipeManager = new RecipeManager();


        boolean exit = false;

        while (!exit) {
            System.out.println("---- Inventory Management Menu ----");
            System.out.println("1. Add item to pantry");
            System.out.println("2. Remove item from pantry");
            System.out.println("3. Update pantry item");
            System.out.println("4. Print all pantry items");
            System.out.println("5. Print expired pantry items");
            System.out.println("6. Add item to grocery list");
            System.out.println("7. Remove item from grocery list");
            System.out.println("8. Print all grocery list items");
            System.out.println("9. Add a new recipe");
            System.out.println("10. Match recipes with pantry");
            System.out.println("11. Load pantry items from CSV");
            System.out.println("12. Load grocery items from CSV");
            System.out.println("13. Save pantry items to CSV");
            System.out.println("14. Save grocery items to CSV");
            System.out.println("15. Exit");
            System.out.print("Enter a number from the menu or type exit to leave: ");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                exit = true;
                System.out.println("Exiting the program.");
                continue;
            }
            
            
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                continue; // Skip the rest of the loop and prompt again
            }

            switch (choice) {
                case 1:
                    addItemToPantry(scanner, pantryManager);
                    break;
                case 2:
                    removeItemFromPantry(scanner, pantryManager);
                    break;
                case 3:
                    updatePantryItem(scanner, pantryManager);
                    break;
                case 4:
                    pantryManager.printAllItems();
                    break;
                case 5:
                    pantryManager.printExpiredItems();
                    break;
                case 6:
                    addItemToGroceryList(scanner, groceryListManager);
                    break;
                case 7:
                    removeItemFromGroceryList(scanner, groceryListManager);
                    break;
                case 8:
                    groceryListManager.printAllItems();
                    break;
                    
                case 9: 
                	addRecipe(scanner, recipeManager, pantryManager, groceryListManager);
                	break;
                case 10: 
                    recipeManager.matchIngredientsWithPantry(pantryManager);
                    break;
                case 11:
                    loadPantryItemsFromCSV(pantryManager, "pantry.csv");
                    break;
                case 12:
                    loadGroceryItemsFromCSV(groceryListManager, "groceryList.csv");
                    break;
                case 13:
                	pantryManager.saveToCSV("pantry.csv");
                    break;
                case 14:
                	groceryListManager.saveToCSV("groceryList.csv");
                	break;
                case 15:
                    exit = true;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 9.");
            }
        }
        scanner.close();
    }

    /**
     * Adds a new item to the pantry.
     * This method prompts the user to enter details for a new pantry item and adds it to the pantry manager.
     * It handles user input for item details such as name, quantity, unit, category, and expiration date.
     * @param scanner The Scanner object for reading user input.
     * @param pantryManager The manager that handles pantry item operations.
     */
    private static void addItemToPantry(Scanner scanner, PantryManager pantryManager) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter unit: ");
        String unit = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter expiration date (YYYY-MM-DD): ");        
        LocalDate expirationDate = null;
        while (expirationDate == null) {
            String dateInput = scanner.nextLine();
            try {
                expirationDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format or value. Please enter a valid date in YYYY-MM-DD format: ");
            }
        }


        PantryItem pantryItem = new PantryItem(name, quantity, category, unit, expirationDate);
        try {
        pantryManager.addItem(pantryItem);
        System.out.println(name + " was added sucessfully to the pantry inventory.");
        } catch (Exception e) {
            System.out.println("Failed to add " + name + " to the pantry. Error: " + e.getMessage());
        }
    }

    /**
     * Removes an item from the pantry.
     * This method asks for the name of the item to be removed and attempts to remove it if it exists.
     * @param scanner The Scanner object for reading user input.
     * @param pantryManager The manager that handles pantry item operations.
     */
    private static void removeItemFromPantry(Scanner scanner, PantryManager pantryManager) {
        System.out.print("Enter the name of the item to remove: ");
        String name = scanner.nextLine();
        //pantryManager.removeItem(name);
        
        //checking if the item is there
        PantryItem item = pantryManager.getItem(name);
        if(item != null) {
        	pantryManager.removeItem(name);//Attempting to remove the item 
        	if(pantryManager.getItem(name) == null) {
        		System.out.println("Item removed successfully: " + name);
        	}else {
        		System.out.println("Failed to remove item, please try again");
        		
        	}
        }else {
        	System.out.println("Item not found: " + name);
        }
    }


    /**
     * Updates the quantity of an existing pantry item.
     * This method prompts the user for the item's name and the new quantity and applies the update.
     * @param scanner The Scanner object for reading user input.
     * @param pantryManager The manager that handles pantry item operations.
     */
    private static void updatePantryItem(Scanner scanner, PantryManager pantryManager) {
        System.out.print("Enter the name of the item to update: ");
        String currentName = scanner.nextLine();

        PantryItem item = pantryManager.getItem(currentName);
        if (item != null) {
            System.out.print("Enter new name (or press enter to skip): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                item.setName(newName);
            }

            System.out.print("Enter new quantity (or press enter to skip): ");
            String quantityStr = scanner.nextLine();
            if (!quantityStr.isEmpty()) {
                int quantity = Integer.parseInt(quantityStr);
                item.setQuantity(quantity);
            }

            System.out.print("Enter new unit (or press enter to skip): ");
            String newUnit = scanner.nextLine();
            if (!newUnit.isEmpty()) {
                item.setUnit(newUnit);
            }

            System.out.print("Enter new expiration date (YYYY-MM-DD, or press enter to skip): ");
            String dateStr = scanner.nextLine();
            if (!dateStr.isEmpty()) {
                LocalDate newDate = LocalDate.parse(dateStr);
                item.setExpirationDate(newDate);
            }

            pantryManager.updateItem(item);
            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }


    /**
     * Adds an item to the grocery list.
     * Prompts the user for item details and adds the item to the grocery list manager.
     * @param scanner The Scanner object for reading user input.
     * @param groceryListManager The manager that handles grocery list operations.
     */
    private static void addItemToGroceryList(Scanner scanner, GroceryListManager groceryListManager) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter quantity needed: ");
        int quantityNeeded = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter unit: ");
        String unit = scanner.nextLine();


        GroceryItem groceryItem = new GroceryItem(name, category, quantityNeeded, unit);
        //groceryListManager.addItem(groceryItem);
        try {
            groceryListManager.addItem(groceryItem);
            System.out.println(name + " was added sucessfully to the grocery list.");
            } catch (Exception e) {
                System.out.println("Failed to add " + name + " to the grocery list. Error: " + e.getMessage());
            }
    }

    /**
     * Removes an item from the grocery list.
     * Asks for the item name and removes it from the grocery list if found.
     * @param scanner The Scanner object for reading user input.
     * @param groceryListManager The manager that handles grocery list operations.
     */
    /*private static void removeItemFromGroceryList(Scanner scanner, GroceryListManager groceryListManager) {
        System.out.print("Enter the name of the item to remove: ");
        String name = scanner.nextLine();
        groceryListManager.removeItem(name);
    }*/
    private static void removeItemFromGroceryList(Scanner scanner, GroceryListManager groceryListManager) {
        System.out.print("Enter the name of the item to remove: ");
        // Trimming the input to remove any trailing whitespace
        String name = scanner.nextLine().trim();
        groceryListManager.removeItem(name);
        System.out.println(name + " was successfully removed.");

    }
    
    /**
     * Adds a new recipe and its ingredients.
     * Prompts the user for recipe details and ingredients, adding each to the recipe manager.
     * Checks pantry for existing ingredients and updates the grocery list if necessary.
     * @param scanner The Scanner object for reading user input.
     * @param recipeManager The manager that handles recipe operations.
     * @param pantryManager The manager for pantry operations.
     * @param groceryListManager The manager for grocery list operations.
     */
    private static void addRecipe(Scanner scanner, RecipeManager recipeManager, PantryManager pantryManager, GroceryListManager groceryListManager) {
        System.out.print("Enter recipe name: ");
        String name = scanner.nextLine();
        Recipe recipe = new Recipe(name);
        //System.out.println("Enter ingredients (type 'end' to stop):");

        while (true) {
            System.out.print("Enter ingredient name (or type 'end'): ");
            String ingredientName = scanner.nextLine();
            if ("end".equalsIgnoreCase(ingredientName)) {
                break;
            }
            System.out.print("Enter quantity: ");
            double quantity = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter unit: ");
            String unit = scanner.nextLine();
            
            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            //Ingredient ingredient = new Ingredient(ingredientName, amount, unit, category);
            Ingredient ingredient = new Ingredient(ingredientName, category, (int) quantity, unit);

            recipe.addIngredient(ingredient);

            // Check if ingredient is sufficient in the pantry
            PantryItem pantryItem = pantryManager.getItem(ingredientName);
            if (pantryItem == null || pantryItem.getQuantity() < quantity) {
                double quantityNeeded = pantryItem == null ? quantity : quantity - pantryItem.getQuantity();
                System.out.println(ingredientName + " is not sufficient or not in pantry. Adding to grocery list.");
                groceryListManager.addItem(new GroceryItem(ingredientName, "Grocery", (int) quantityNeeded, unit));  // Assuming default expiration date for grocery items
            }
        }

        recipeManager.addRecipe(recipe);
        System.out.println("Recipe added successfully and missing ingredients added to grocery list if necessary.");
    }
    
    /**
    * Loads pantry items from a CSV file.
    * Reads pantry items from a specified CSV file and adds them to the pantry manager.
    * @param pantryManager The manager that handles pantry item operations.
    * @param filename The name of the CSV file from which to load items.
    */
    private static void loadPantryItemsFromCSV(PantryManager pantryManager, String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] attributes = line.split(",");
                PantryItem item = new PantryItem(attributes[0], Integer.parseInt(attributes[1]),
                                                 attributes[3], attributes[2],
                                                 LocalDate.parse(attributes[4]));
                pantryManager.addItem(item);
            }
            System.out.println("Pantry items loaded successfully from CSV.");
        } catch (Exception e) {
            System.out.println("Error loading pantry items from CSV: " + e.getMessage());
        }
    }

    /**
     * Loads grocery items from a CSV file.
     * Reads grocery items from a specified CSV file and adds them to the grocery list manager.
     * @param groceryListManager The manager that handles grocery list operations.
     * @param filename The name of the CSV file from which to load items.
     */
    private static void loadGroceryItemsFromCSV(GroceryListManager groceryListManager, String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] attributes = line.split(",");
                if (attributes.length >= 4) {
                    try {
                        String name = attributes[0];
                        String category = attributes[1];
                        int quantityNeeded = Integer.parseInt(attributes[2]);  // Ensure this is the correct index for quantity
                        String unit = attributes[3];  // Ensure this is the correct index for unit

                        GroceryItem item = new GroceryItem(name, category, quantityNeeded, unit);
                        groceryListManager.addItem(item);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing number from line: " + line);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Error accessing index, line format is incorrect: " + line);
                    }
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
            System.out.println("Grocery items loaded successfully from CSV.");
        } catch (Exception e) {
            System.out.println("Error loading grocery items from CSV: " + e.getMessage());
        }
    }
}

