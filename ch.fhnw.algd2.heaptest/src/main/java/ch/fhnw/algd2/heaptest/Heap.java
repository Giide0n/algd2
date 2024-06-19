package ch.fhnw.algd2.heaptest;

/* Heap implementing a Priority Queue */
class Heap<K> implements PriorityQueue<K> {
	private HeapNode<K>[] heap; // Array to store the heap elements
	private int size; // Number of elements currently stored in heap

	/**
	 * Construct the binary heap.
	 * 
	 * @param capacity
	 *          how many items the heap can store
	 */
	@SuppressWarnings("unchecked")
	Heap(int capacity) {
		// TODO 01 allocate array of proper size and assign to heap use base type
		// HeapNode without type parameter
	}

	/**
	 * Returns the number of elements in this priority queue.
	 * 
	 * @return the number of elements in this priority queue.
	 */
	@Override
	public int size() {
		// TODO 02 return number of elements currently contained in the heap
		return 0;
	}

	/**
	 * Check whether the heap is empty.
	 * 
	 * @return true if there are no items in the heap.
	 */
	@Override
	public boolean isEmpty() {
		// TODO 03 return true if no element is in the heap
		return false;
	}

	/**
	 * Check whether the heap is full.
	 * 
	 * @return true if no further elements can be inserted into the heap.
	 */
	@Override
	public boolean isFull() {
		// TODO 04 return true if no further element can be inserted to the heap
		return false;
	}

	/**
	 * Make the heap (logically) empty.
	 */
	@Override
	public void clear() {
		// TODO 05 clear the heap from all elements
	}

	/**
	 * Add to the priority queue, maintaining heap order. Duplicates and null
	 * values are allowed. Small values of the argument priority means high
	 * priority, Large values means low priority.
	 * 
	 * @param element
	 *          the item to insert
	 * @param priority
	 *          the priority to be assigned to the item element
	 * @exception QueueFullException
	 *              if the heap is full
	 */
	@Override
	public void add(K element, long priority) throws QueueFullException {
		// TODO 07 add the item element with the priority priority to the heap
	}

	/**
	 * Removes and returns the item with highest priority (smallest priority
	 * value) from the queue, maintaining heap order.
	 * 
	 * @return the item with highest priority (smallest priority value)
	 * @throws QueueEmptyException
	 *           if the queue is empty
	 */
	@Override
	public K removeMin() throws QueueEmptyException {
		// TODO 09 return the element from the heap's root and remove the element
		// from the heap
		return null;
	}

	/**
	 * Internal method to let an element sift up in the heap.
	 * 
	 * @param start
	 *          the index at which the percolate begins
	 */
	private void siftUp(int start) {
		// TODO 08 implement sift up for element at start
	}

	/**
	 * Internal method to let an element sift down in the heap.
	 * 
	 * @param start
	 *          the index at which the percolate begins
	 */
	private void siftDown(int start) {
		// TODO 10 implement sift down for element at start
	}

	/**
	 * Allocates a long[] Array and copies the priority values from the heap
	 * array. The length of the returned array shall be equal to the number of
	 * elements in the heap (i.e. size()). The smallest element (root) shall be
	 * placed at index position 0.
	 * 
	 * @return Array with all priority values
	 */
	@Override
	public long[] toLongArray() {
		// TODO 06 return array with all the priorities currently in the heap. Use
		// order of storage. Put root element at position 0.
		return null;
	}

	private static class HeapNode<K> {
		private final K element;
		private final long priority;

		HeapNode(K element, long priority) {
			this.element = element;
			this.priority = priority;
		}
	}
}
