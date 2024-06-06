/*
 * Ingredient Class
 *
 * This class represents an ingredient used in recipes. It extends the Item class, inheriting fields like name,
 * category, and unit, and adds a specific field for quantity. This class is designed to be used as part of culinary
 * recipes where quantities of ingredients are crucial.
 *
 * Approach:
 * - The Ingredient class extends Item to utilize common item properties while focusing on the specific needs of
 *   culinary ingredients such as quantity measurement.
 * - It implements the Comparable interface to allow ingredients to be sorted based on their quantity. This can
 *   be useful in inventory management systems where ingredients need to be organized by quantity.
 * - Overrides equals and hashCode methods to ensure correct behavior in collections that rely on Java's Object
 *   equality mechanisms.
 * - The toString method provides a string representation that includes all inherited fields plus the ingredient's
 *   quantity, aiding in debugging and logging.
 */
import java.util.Objects;

//ADT Principle: Encapsulates ingredient data
public class Ingredient extends Item implements Comparable<Ingredient>{
    private int quantity;

    // Constructor initializes the ingredient with its properties
    public Ingredient(String name, String category, int quantity, String unit) {
        super(name, category, unit);
        this.quantity = quantity;
    }
    
    // Returns the quantity of the ingredient
    public int getQuantity() {
    	return quantity;
    }
	
    // Compares this ingredient with another based on quantity
	@Override 
	public int compareTo(Ingredient other) {
		return Double.compare(this.quantity, other.quantity);
	}
	
    // Checks if this ingredient is equal to another object
	@Override 
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Ingredient)) return false; 
		if(!super.equals(obj)) return false;
		Ingredient other = (Ingredient) obj;
		return Double.compare(quantity,other.quantity) == 0;
	}
	
    // Generates a hash code for this ingredient
	@Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity);
    }

    // Provides a string representation of the ingredient
    @Override
    public String toString() {
        return super.toString() + ", amount=" + quantity;
    }
}