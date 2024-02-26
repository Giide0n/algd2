package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Set;

public class SortedSet<E extends Comparable<? super E>> extends AbstractArrayCollection<E> implements Set<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private int currentSize = 0;
	private E[] data;

	public SortedSet() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SortedSet(int capacity) {
		data = (E[])new Comparable[capacity];
	}

	@Override
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}

		int i = getFirstIndex(e);
		if(i < currentSize && e.equals(data[i])) {
			return false;
		}

		if (currentSize >= data.length) {
			throw new IllegalStateException();
		}

		E a = e;
		while(i < currentSize) {
			E t = data[i];
			data[i] = a;
			a = t;
			i++;
		}

		data[currentSize++] = a;
		return true;
	}

	@Override
	public boolean remove(Object o) throws ClassCastException {
		if (o == null) {
			throw new NullPointerException();
		}

		int i = getFirstIndex(o);
		i = o.equals(data[i]) ? i : currentSize;

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
	public boolean contains(Object o) throws ClassCastException {
		if (o == null) {
			throw new NullPointerException();
		}

		int i = getFirstIndex(o);
		return i < currentSize && o.equals(data[i]);
	}

	private int getFirstIndex(Object o) {
		E e = (E) o;

		int i = 0;
		while(i < currentSize && e.compareTo(data[i]) > 0) {
			i++;
		}

		return i;
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
		SortedSet<Integer> bag = new SortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
