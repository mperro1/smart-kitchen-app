/*
 * Item Class
 *
 * This abstract class serves as a base for different types of items in the system, encapsulating common properties
 * such as name, category, and unit. This class provides a foundation for item-related operations and standardizes
 * basic attributes and behaviors that all items should have, regardless of their specific type.
 *
 * Responsibilities:
 * - Store and manage basic information about an item including its name, category, and measurement unit.
 * - Provide methods to access and modify these properties, ensuring they can be easily managed and integrated
 *   within different parts of the application.
 * - Offer utility methods like printing item details, comparing items, and generating standard hash codes and
 *   string representations, which are crucial for collections and debugging.
 *
 * Usage:
 * - This class is intended to be extended by more specific item classes that may add additional properties
 *   or behaviors. It provides a common structure and set of functionalities that ensure consistency across
 *   various types of items managed within the system.
 */
import java.util.Objects;

public abstract class Item {
	private String name; 
	private String category; //pantry, grocery, recipe ingredient 
	private String unit; // gallons, lbs,etc 
	
	//Constructor
	public Item(String name, String category, String unit) {
		this.name=name; 
		this.category = category; 
		this.unit = unit;
	}
	
	//getters 
	public String getName() {
		return name;
	}

	public String getCategory() {
		return category; 
	}
	
	public String getUnit() {
		return unit;
	}
	
	//Setters 
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

    // Checks if this item is equal to another object
	@Override 
	public boolean equals(Object obj) {
		if (this == obj) return true; 
		if(!(obj instanceof Item)) return false;
		Item item = (Item) obj; 
		return Objects.equals(name, item.name) &&
				Objects.equals(category, item.category) &&
				Objects.equals(unit, item.unit);
	    }

    // Generates a hash code for this item
	@Override
	public int hashCode() {
		return Objects.hash(name, category, unit);
	    }

    // Compares this item with another item based on name
	public int compareTo(Item other) {
		return this.name.compareTo(other.getName());
	    }
	
	//Prints the details of the item
	public void printItem() {
		System.out.println("Name: " + name);
		System.out.println("Category" + category);
		System.out.println("Unit" + unit);
	}

    // Returns a string representation of the item
	@Override 
	public String toString() {
        return String.format("Item[name=%s, category=%s, unit=%s", name, category, unit);
	}
	
	}
