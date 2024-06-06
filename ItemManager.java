/*
 * ItemManager Interface
 *
 * This generic interface defines the contract for managing a collection of items of type T.
 * It provides a framework for adding, removing, retrieving, and updating items, as well as filtering them based on specified criteria.
 * This interface is intended to be implemented by classes that manage specific types of items, such as grocery items or pantry items.
 *
 * Responsibilities:
 * - Add an item to the collection.
 * - Remove an item from the collection by an identifier.
 * - Retrieve an item by its identifier.
 * - Get a complete list of all items.
 * - Filter the list of items based on custom conditions.
 * - Update an existing item in the collection.
 *
 * Each method supports essential operations typically required in inventory management systems or similar applications
 * where collections of objects need to be maintained dynamically.
 */
import java.util.function.Predicate;

//Generic interface capable of managing items of any type "T" 
public interface ItemManager<T> {
	//A method for classes implementing this interface in their item type manager (grocery/pantry)
    void addItem(T item);
    //A method to remove an item by its id. Ids are defined/implemented by each class 
    void removeItem(Object identifier);
    // A method to retrive an item by its identifier
    T getItem(Object identifier);
    // A method that returns a list of items type T 
    MyList<T> getItems();
    
    //Get a list of items that match a specific predicate filter 
    MyList<T> getItems(Predicate<T> filter);
    
    //Updates an existing item in the management system.
    void updateItem(T updatedItem);
    
}
