package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;

public class UnsortedBag<E> extends AbstractArrayCollection<E> {

    public static final int DEFAULT_CAPACITY = 100;
    private E[] data;
    private int currentSize = 0;

    public UnsortedBag() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public UnsortedBag(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (currentSize >= data.length) {
            throw new IllegalStateException();
        }

        data[currentSize++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        int i = 0;
        while (i < currentSize && !o.equals(data[i])) {
            i++;
        }

        int j = i;
        while (j < currentSize-1) {
            data[j] = data[j+1];
            j++;
        }

        if(i < currentSize) {
            data[currentSize-1] = null;
            currentSize--;
            i--;
        }

        return i < currentSize;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        int i = 0;
        while (i < currentSize && !o.equals(data[i])) {
            i++;
        }

        return i < currentSize;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size());
    }

    @Override
    public int size() {
        return currentSize;
    }

    public static void main(String[] args) {
        UnsortedBag<Integer> bag = new UnsortedBag<Integer>();
        bag.add(2);
        bag.add(1);
        System.out.println(Arrays.toString(bag.toArray()));
    }
}
