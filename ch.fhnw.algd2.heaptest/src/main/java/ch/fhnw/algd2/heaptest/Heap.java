package ch.fhnw.algd2.heaptest;

import java.util.Arrays;

/* Heap implementing a Priority Queue */
class Heap<K> implements PriorityQueue<K> {

    private HeapNode<K>[] heap; // Array to store the heap elements
    private int size; // Number of elements currently stored in heap

    /**
     * Construct the binary heap.
     *
     * @param capacity how many items the heap can store
     */
    @SuppressWarnings("unchecked")
    Heap(int capacity) {
        heap = new HeapNode[capacity];
    }

    /**
     * Returns the number of elements in this priority queue.
     *
     * @return the number of elements in this priority queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Check whether the heap is empty.
     *
     * @return true if there are no items in the heap.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check whether the heap is full.
     *
     * @return true if no further elements can be inserted into the heap.
     */
    @Override
    public boolean isFull() {
        return size == heap.length;
    }

    /**
     * Make the heap (logically) empty.
     */
    @Override
    public void clear() {
        heap = new HeapNode[heap.length];
        size = 0;
    }

    /**
     * Add to the priority queue, maintaining heap order. Duplicates and null values are allowed.
     * Small values of the argument priority means high priority, Large values means low priority.
     *
     * @param element  the item to insert
     * @param priority the priority to be assigned to the item element
     * @throws QueueFullException if the heap is full
     */
    @Override
    public void add(K element, long priority) throws QueueFullException {
        if (isFull()) {
            throw new QueueFullException();
        }

        heap[size] = new HeapNode<>(element, priority);
        siftUp(size);
        size++;
    }

    /**
     * Removes and returns the item with highest priority (smallest priority value) from the queue,
     * maintaining heap order.
     *
     * @return the item with highest priority (smallest priority value)
     * @throws QueueEmptyException if the queue is empty
     */
    @Override
    public K removeMin() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }

        K min = heap[0].element;
        size--;
        swap(0, size);
        heap[size] = null;
        siftDown(0);

        return min;
    }

    /**
     * Internal method to let an element sift up in the heap.
     *
     * @param start the index at which the percolate begins
     */
    private void siftUp(int start) {
        int i = start;
        while (i > 0 && heap[i].priority < heap[(i - 1) / 2].priority) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    /**
     * Internal method to let an element sift down in the heap.
     *
     * @param start the index at which the percolate begins
     */
    private void siftDown(int start) {
        int i = start;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        while (left < size && heap[right].priority < heap[i].priority
            || right < size && heap[right].priority < heap[i].priority) {
            if (right >= size || heap[left].priority < heap[right].priority) {
                swap(left, i);
                i = left;
            } else {
                swap(right, i);
                i = right;
            }
            left = 2 * i + 1;
            right = 2 * i + 2;
        }
    }

    /**
     * Internal method to swap to items in the heap.
     *
     * @param a the index of the first item
     * @param b the index of the second item
     */
    private void swap(int a, int b) {
        HeapNode<K> t = heap[a];
        heap[a] = heap[b];
        heap[b] = t;
    }

    /**
     * Allocates a long[] Array and copies the priority values from the heap array. The length of
     * the returned array shall be equal to the number of elements in the heap (i.e. size()). The
     * smallest element (root) shall be placed at index position 0.
     *
     * @return Array with all priority values
     */
    @Override
    public long[] toLongArray() {
        return Arrays.stream(heap).limit(size).mapToLong(x -> x.priority).toArray();
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
