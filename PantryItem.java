/*
 * PantryItem Class
 *
 * This class represents a pantry item with properties specific to food storage such as quantity and expiration date.
 * It extends the Item class, inheriting its basic properties and methods, while adding additional details and behaviors
 * that are specific to pantry items, such as expiration management.
 *
 * Approach:
 * - PantryItem extends the abstract base class Item, inheriting fields like name, category, and unit.
 * - Additional fields specific to pantry items include quantity and expiration date.
 * - Implements Comparable interface to allow sorting of pantry items based on their expiration dates.
 * - Provides a utility method to check if an item has expired, which enhances management capabilities such as removal
 *   of expired items from inventory.
 * - Overrides methods like equals, hashCode, and toString from the Object class to ensure proper behavior when
 *   pantry items are used in collections or output operations.
 * - Provides a method to format the item data into CSV format for data persistence or export.
 */

import java.time.LocalDate; 
import java.util.Objects;  

//ADT Principle: Encapsulates pantry item data and behavior 
//Inheritance: Pantry item extends item inheriting its methods and properties, so its not necessesary to declare those anymore
public class PantryItem extends Item implements Comparable<PantryItem>{
    private LocalDate expirationDate;
    private int quantity;

    //Constructor
    public PantryItem(String name, int quantity, String category, String unit, LocalDate expirationDate) {
        super(name,category, unit);
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    // Getters
    // Returns the quantity of the item
    public int getQuantity() {
    	return quantity;
    }
    
    // Returns the expiration date of the item
    public LocalDate getExpirationDate() {
    	return expirationDate;
    }
    
    //Setters 
    // Sets a new expiration date
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Sets a new quantity of the item
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }
    
    // Checks if the item is expired
    public boolean checkExpiration() {
    	return LocalDate.now().isAfter(expirationDate);
    }

    // Sets a new category (inherited from Item)
	public void setCategory(String category) {
		super.setCategory(category);
		
	}
	
    // Compares this PantryItem with another based on expiration date for sorting
	@Override
    public int compareTo(PantryItem other) {
        return this.expirationDate.compareTo(other.expirationDate);
    }
	
    // Checks if two PantryItems are equal based on inherited and PantryItem-specific fields
	@Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof PantryItem)) return false;
        PantryItem other = (PantryItem) obj;
        return this.expirationDate.equals(other.expirationDate);
    }

    // Returns a hash code for this PantryItem
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), expirationDate);
    }

    // Returns a string representation of the PantryItem
    @Override
    public String toString() {
        return super.toString() + ", quantity=" + quantity + ", expirationDate=" + expirationDate;
    }
    
    // Formats PantryItem data into a CSV format string
    public String toCSVFormat() {
        return getName() + "," + getQuantity() + "," + getCategory() + "," + getUnit() + "," + getExpirationDate();
    }   
}
