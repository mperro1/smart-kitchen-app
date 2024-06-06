/*
 * Node Class
 *
 * This class represents a node in a linked list structure, encapsulating an item of type Item and a reference to the next node.
 * It serves as the basic building block for linked list implementations, providing methods to manage the item data and the linkage
 * to subsequent nodes in the list.
 *
 * Responsibilities:
 * - Store an item and reference to the next node.
 * - Provide access to and allow modification of the item.
 * - Manage the reference to the next node, enabling the dynamic construction and modification of the linked list.
 * - Offer utility methods to display item details and calculate the length of the list starting from this node.
 *
 * Usage:
 * - Used internally by LinkedList and similar data structures that require a sequential chaining of elements.
 * - Not typically used directly by client code outside of the linked list context but integral to the functionality of list operations.
 */
public class Node {
	private Item item; // The item stored in this node
	private Node next; // Reference to the next node in the list
	
	//Constructor 
	public Node(Item item) {
		this.item = item; 
		this.next = null; 
	}
	
	//Getters and setters 
	public Item getItem() {
		return item;
	}
	
	public Node getNext() {
		return next;
	}
	
	//Method to get the length of the linked list starting from this node 
	public int getLength() {
		if(next == null){
			return 1; //base case
		} else {
			return 1 + next.getLength();
		}
	}
	
	//To change which node comes after the current node 
	public void setNext(Node next) {
		this.next = next;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	//Method to print the details of the item stored in this node 
	public void printItem() {
		System.out.println(item);
	}

}
