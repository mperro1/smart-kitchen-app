/*
 * LinkedList Class
 *
 * This class implements the MyList interface, providing a generic linked list data structure to manage a collection of elements
 * of type T, where T extends Item. It supports operations such as add, remove, get, and various utility methods including filtering
 * and updating items. The class is designed to offer flexibility and efficient management of elements in scenarios where dynamic
 * modification of data is frequent.
 *
 * Key Features:
 * - Dynamic size adjustments with operations to add and remove items.
 * - Access items by index or by a specific identifier which can be customized per the item's properties.
 * - Filter functionality using predicates, allowing for complex queries such as retrieving all expired items.
 * - Support for updating items based on their unique identifiers.
 * - Implementation of print methods to display item information directly, facilitating debugging and verification.
 */
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T extends Item> implements MyList<T>{
	private class Node{
		T data; // Data element stored in the node 
		Node next; // Pointer to the next node in the list
		
		Node(T data){
			this.data = data; 
			this.next = null; 
		}
	}

	private Node head; // Head of the list 
	private int size;  // Number of elements in the list 
	
	public LinkedList() {
		head = null;
		size = 0;
	}
	
    // Adds a new item to the end of the list
	@Override 
	public void add(T item) {
		Node newNode = new Node(item);
		if(head == null) {
			head = newNode;
		} else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}
		size ++;
	}
	
    // Returns the number of items in the list
	@Override
	public int size() {
		return size;
	}

    // Removes an item by identifier, either index or object name
	@Override
	public boolean remove(Object identifier) {
	    if (head == null) {
	        return false;
	    }

	    // If the head is the node to be removed
	    if (identifier instanceof String && identifier.equals(head.data.getName())) {
	        head = head.next;
	        size--;
	        return true;
	    }

	    Node current = head;
	    while (current.next != null) {
	        // Check if the next node's data matches the identifier
	        if (identifier instanceof String && identifier.equals(current.next.data.getName())) {
	            current.next = current.next.next;  // Skip the node to remove it
	            size--;
	            return true;
	        }
	        current = current.next;
	    }
	    return false;
	}
	//getters 
    public Node getHead() {
        return head;
    }
    
    // allowing to get using object identifier
	@Override
    public T get(Object identifier) {
        if (identifier instanceof Integer) {
            int index = (Integer) identifier;
            return getByIndex(index);
        } else {
            return getByObject(identifier);
        }
    }
	
    // Private helper to get an item by index
    private T getByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Private helper to get an item by object identifier
    private T getByObject(Object identifier) {
        Node current = head;
        while (current != null) {
            if (identifier instanceof String && identifier.equals(current.data.getName())) {
                return current.data;
            }
            current = current.next;
        }
        throw new NoSuchElementException("No element with identifier: " + identifier);
    } 
    
    // Returns a list of all items
    public MyList<T> getAllItems() {
        LinkedList<T> allItems = new LinkedList<>();
        Node currentNode = head;
        while (currentNode != null) {
            allItems.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return allItems;
    }

    // Method to filter items based on a predicate
    public LinkedList<T> filter(Predicate<T> predicate) {
        LinkedList<T> filteredList = new LinkedList<>();
        Node current = head;
        while (current != null) {
            if (predicate.test(current.data)) {
                filteredList.add(current.data);
            }
            current = current.next;
        }
        return filteredList;
    }
    
    // Recursive method to update an item
    public Node updateItemRecursive(Node current, T updatedItem) {
        if (current == null) {
            // Item not found, add new item at the end of the list
            return new Node(updatedItem);
        } else if (current.data.getName().equals(updatedItem.getName())) {
            // Update the current node's data
            current.data = updatedItem;
            return current;
        } else {
            // Proceed to the next node
            current.next = updateItemRecursive(current.next, updatedItem);
            return current;
        }
    }

    // Public method to initiate the recursive update
    public void updateItem(T updatedItem) {
        head = updateItemRecursive(head, updatedItem);
    }
    
    @Override
    public void printAllItems() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data); // Assuming your data class has a meaningful toString() method
            current = current.next;
        }
    }

    @Override
    public void printExpiredItems() {
        Node current = head;
        while (current != null) {
            if (current.data instanceof PantryItem) { // Assuming only pantry items can expire
                PantryItem item = (PantryItem) current.data;
                if (item.checkExpiration()) { 
                    System.out.println(item);
                }
            }
            current = current.next;
        }
    }
}
