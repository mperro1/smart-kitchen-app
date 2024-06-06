/*
 * GroceryItem Class
 *
 * This class represents a grocery item, extending the base Item class. It encapsulates all necessary properties of a grocery item,
 * including the quantity needed, which is specific to shopping or inventory requirements. This class implements the Comparable
 * interface to allow grocery items to be sorted based on the quantity needed, which can be useful in various inventory management
 * and optimization contexts.
 *
 * Approach:
 * - Inherits basic item properties such as name, category, and unit from the superclass Item.
 * - Adds a specific field for the quantity needed, which represents the amount required for shopping or stocking purposes.
 * - Implements standard Java interfaces like Comparable for sorting and overrides methods such as equals and hashCode to ensure
 *   grocery items are managed correctly in collections.
 * - Provides a toString method for easy debugging and logging, and a method to export item data in CSV format for data persistence or external use.
 */
import java.util.Objects;  

//ADT Principle: Encapsulates grocery item data and behavior
public class GroceryItem extends Item implements Comparable<GroceryItem>{
    private int quantityNeeded;

    //Constructor
    public GroceryItem(String name, String category, int quantityNeeded, String unit) {
    	super(name, category, unit);
        this.quantityNeeded = quantityNeeded;
    }
    
    // Returns the quantity needed for the grocery item
    public int getQuantityNeeded() {
    	return quantityNeeded;
    }
    
    //setter
    public void setQuantityNeeded(int quantityNeeded) {
        this.quantityNeeded = quantityNeeded;
    }

    // Compares this grocery item with another based on the quantity needed
    @Override
    public int compareTo(GroceryItem other) {
        return Integer.compare(this.quantityNeeded, other.quantityNeeded);
    }

    // Checks if this grocery item is equal to another object, considering quantity needed
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof GroceryItem)) return false;
        GroceryItem other = (GroceryItem) obj;
        return this.quantityNeeded == other.quantityNeeded;
    }
    
    // Returns a hash code for this grocery item
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantityNeeded);
    }

    // Returns a string representation of this grocery item
    @Override
    public String toString() {
        return "GroceryItem{name=" + getName() + ", category=" + getCategory() + ", unit=" + getUnit() + ", quantityNeeded=" + quantityNeeded + "}";
    }
    
    // Returns a CSV format string of this grocery item
    public String toCSVFormat() {
        return getName() + "," + getQuantityNeeded() + "," + getCategory() + "," + getUnit();
    }
}


