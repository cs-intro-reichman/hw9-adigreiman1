/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node current = first;
		int counter=0;
		while(counter<index){
			current=current.next;
			counter++;
		}
		return current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		Node newNode = new Node(block);
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (index==0){
			if (size==0){
				first = newNode;
            	last = newNode;
		}else{
			newNode.next=first;
			first=newNode;
		}
		}
		else if (index==size){
			last.next=newNode;
			last=newNode;
			
		}
		else{
		Node left = getNode(index-1);
		Node right= left.next;

		left.next = newNode;
		newNode.next= right;
		}
		size++;
		
	}


	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		add(size, block);
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		add(0, block);
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (size==0){
			throw new IllegalArgumentException(
					"ERROR IllegalArgumentException: index must be between 0 and size");
		}else{
		Node chosenNode= getNode(index);
		return chosenNode.block;
	}	
	}
	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {

		Node current = first;
		int counter =0;
	while (current!=null){
		if (current.block.equals(block)){
			return counter;
		}else{
			current=current.next;
			counter++;
		}

	}
		return -1;
	
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		int index = indexOf(node.block);
		if (index==-1){
			throw new IllegalArgumentException(
					"ERROR IllegalArgumentException: index must be between 0 and size");
		
		}else if (index==0){
			Node removed = first;
			first= first.next;
			removed.next=null;
			if(first==null){
				last=null;
		}
	}
		else if (index==size-1){
			Node removed = last;
			Node newLast = getNode(size-2);
			last= newLast;
			last.next=null;
			removed.next = null;

		}
		else{
		Node left = getNode(index-1);
		left.next= node.next;
		node.next=null;
		
		}
		size--;
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index<0 || index>size){
			throw new IllegalArgumentException(
					"ERROR IllegalArgumentException: index must be between 0 and size");

		}else if (index==0){
			Node removed = first;
			first= first.next;
			removed.next=null;
		}
		else if (index==size){
			last= getNode(size-1);
			last.next=null;

		}
		else{
		Node left = getNode(index-1);
		Node removed = getNode(index);
		left.next= removed.next;
		removed.next=null;
		
		}
		size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int index = indexOf(block);
		if (index==-1){
			throw new IllegalArgumentException(
					"ERROR IllegalArgumentException: index must be between 0 and size");

		}else if (index==0){
			Node removed = first;
			first= first.next;
			removed.next=null;
		}
		else if (index==size){
			last= getNode(size-1);
			last.next=null;

		}
		else{
			Node left = getNode(index-1);
			Node removed = getNode(index);
			left.next= removed.next;
			removed.next=null;
		
		}
		size--;
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String output="";
		ListIterator iter = iterator();
		while(iter.current!=null){
			MemoryBlock block = iter.next();
			output+=block.toString();
			if(iter.current != null){
				output += "--->";	
			}
		}
		return output;
	}
	
}