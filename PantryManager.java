/*
 * PantryManager Class
 *
 * This class manages pantry items. It provides methods to add, remove, update, and list pantry items
 * and to save and load items from a CSV file. The PantryManager implements the ItemManager interface,
 * ensuring standardized management operations for PantryItem objects.
 *
 * Approach:
 * - The PantryManager uses a LinkedList to store PantryItem objects, allowing dynamic management
 *   of pantry inventory without predefined size constraints.
 * - It provides methods to add and remove items based on unique identifiers, to retrieve single items,
 *   and to filter items based on custom conditions using Predicate.
 * - The class also supports operations to print all items, print only expired items, and save or load
 *   items to and from a CSV file format for persistence.
 * - Each method is designed to handle typical pantry management tasks such as checking item expiration,
 *   updating quantities, and categorizing items, which are essential for effective kitchen inventory management.
 */


import java.io.File;
import java.io.PrintWriter;
import java.util.function.Predicate;

//Interaction: Accesses and modifies PantryItem objects, interacts with data storage. 
public class PantryManager implements ItemManager<PantryItem> {
    private LinkedList<PantryItem> pantryItems = new LinkedList<>();

 // Constructor
    public PantryManager() {
        this.pantryItems = new LinkedList<>();
    }
    
    //Getters
    //Retrieves a pantry item from the LinkedList by identifier
    @Override
    public PantryItem getItem(Object identifier) {
        if (identifier instanceof String) {
            String name = (String) identifier;
            for (int i = 0; i < pantryItems.size(); i++) {
                PantryItem item = pantryItems.get(i);  // Assuming get(i) method returns the item at index i
                if (item.getName().equals(name)) {
                    return item;
                }
            }
        }
        return null;
    }

    //Returns all PantryItems in a linkedlist
    @Override
    public MyList<PantryItem> getItems() {
        return pantryItems;

    }
    
    //Returns all pantry items as a list
    public MyList<PantryItem> getAllItems() {
        return pantryItems;  
    }
    
    // Filters PantryItems in the LinkedList based on a Predicate
    @Override
    public MyList<PantryItem> getItems(Predicate<PantryItem> filter) {
        return pantryItems.filter(filter);
    }
    
    //Adds pantry item to the linked list
    @Override
    public void addItem(PantryItem item) {
        pantryItems.add(item);
    }

    //Removes a pantry item from the linked list by identifier 
    @Override
    public void removeItem(Object identifier) {
        pantryItems.remove(identifier);  // Assuming id can be used as an identifier here
    }
    
    // Updates a PantryItem in the LinkedList
    @Override
    public void updateItem(PantryItem updatedItem) {
        pantryItems.updateItem(updatedItem);
    }

    // Prints all PantryItems
    public void printAllItems() {
    	if(pantryItems.size() == 0) {
    		System.out.println("There are no items in the list");
    	}else {
       pantryItems.printAllItems();
    }
    }
    
    // Prints all expired PantryItems
    public void printExpiredItems() {
        for (int i = 0; i < pantryItems.size(); i++) {
            PantryItem item = pantryItems.get(i);
            if (item.checkExpiration()) {
                System.out.println(item);
            }
        }
    }
    
    // Method to save pantry items to a CSV file
    public void saveToCSV(String filePath) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            MyList<PantryItem> allItems = getAllItems();
            for (int i = 0; i < allItems.size(); i++) {
                PantryItem item = allItems.get(i);
                String line = String.format("%s,%d,%s,%s,%s\n",
                    item.getName(),
                    item.getQuantity(),
                    item.getUnit(),
                    item.getCategory(),
                    item.getExpirationDate());
                writer.write(line);
            }
            System.out.println("Pantry items successfully saved to CSV: " + filePath);  // Feedback to user
        } catch (Exception e) {
            System.out.println("Failed to save pantry items: " + e.getMessage());
        }
    }
 
}


