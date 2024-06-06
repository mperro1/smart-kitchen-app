/*
 * MyList Interface
 *
 * This generic interface defines a standard set of functionalities for managing a list of elements of type T.
 * It is designed to abstract the list operations that include adding, retrieving, removing, and listing items,
 * as well as specific utility functions like printing all items or expired items, which may be implemented differently
 * depending on the type of list and the type of elements T represents.
 *
 * Responsibilities:
 * - Add elements to the list.
 * - Retrieve elements from the list based on an identifier, which could be an index or a key.
 * - Determine the number of elements in the list.
 * - Remove elements from the list either by an index or a direct object reference.
 * - Provide utility methods to print all elements and specifically expired elements, which assumes that elements of type T
 *   have an 'expired' state which can be evaluated.
 */

public interface MyList<T> {

    // Adds an item to the list
    void add(T item);

    // Retrieves the item by using an object identifier
    T get(Object identifier);

    // Returns the number of elements in the list
    int size();

    // Removes an item from the list, by index or object
    boolean remove(Object identifier);

    //Method to print expired items
    void printExpiredItems();
    
    //Method to print all items in the list 
    void printAllItems();
    
}

