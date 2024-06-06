/*
 * GroceryListManager Class
 *
 * This class manages a collection of GroceryItem objects. It implements the ItemManager interface to provide
 * standardized operations for adding, removing, retrieving, updating, and filtering grocery items. Additionally,
 * it includes functionality to print all items and save them to a CSV file for data persistence.
 *
 * Approach:
 * - Uses a LinkedList to manage GroceryItem objects dynamically, allowing for efficient manipulation of the list.
 * - Provides methods to add items to the list, remove items by identifier, and retrieve items either individually
 *   or as a filtered list based on specified conditions.
 * - Supports updating item details and printing the entire list of grocery items for review.
 * - Implements a method to save the grocery list to a CSV file, facilitating easy data export and storage.
 */
import java.io.PrintWriter;
import java.util.function.Predicate;
import java.io.File;


public class GroceryListManager implements ItemManager<GroceryItem> {
    private LinkedList<GroceryItem> groceryList = new LinkedList<>();

 // Constructor initializes the grocery list
    public GroceryListManager() {
        groceryList = new LinkedList<>();
    }
    
    // Adds a GroceryItem to the list
    @Override
    public void addItem(GroceryItem item) {
        groceryList.add(item);
    }

    // Removes a GroceryItem from the list by identifier
    @Override
    public void removeItem(Object identifier) {
        groceryList.remove(identifier);
    }

    // Retrieves a GroceryItem from the list by identifier
    @Override
    public GroceryItem getItem(Object identifier) {
        return groceryList.get(identifier);
    }

    // Returns all GroceryItems in the list
    @Override
    public MyList<GroceryItem> getItems() {
        return groceryList;
    }
    
    // Returns all GroceryItems as a list
    public MyList<GroceryItem> getAllItems() {
        return groceryList;  // Simply returns the list
    }

    // Retrieves GroceryItems that match a specific predicate
    @Override
    public MyList<GroceryItem> getItems(Predicate<GroceryItem> filter) {
        return groceryList.filter(filter);
    }

    // Updates an existing GroceryItem in the list
    @Override
    public void updateItem(GroceryItem updatedItem) {
        groceryList.updateItem(updatedItem);
    }
    
    // Prints all GroceryItems
    public void printAllItems() {
    	if(groceryList.size() == 0) {
    		System.out.println("There are no items in the list.");
    	}else {
        groceryList.printAllItems();
    }
    }

    // Method to save grocery items to a CSV file
    public void saveToCSV(String filePath) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            MyList<GroceryItem> allItems = getAllItems();
            for (int i = 0; i < allItems.size(); i++) {
                GroceryItem item = allItems.get(i);
                String line = String.format("%s,%s,%d,%s\n",
                    item.getName(),
                    item.getCategory(),
                    item.getQuantityNeeded(),
                    item.getUnit());
                writer.write(line);
            }
            System.out.println("Grocery List items successfully saved to CSV: " + filePath);  // Feedback to user
        } catch (Exception e) {
            System.out.println("Failed to save grocery items: " + e.getMessage());
        }
    }
}




