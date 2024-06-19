package ch.fhnw.algd2.hashing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Hash Set with Open Addressing collision resolution
 */
public class MyHashSet<E> implements Set<E> {
	private Object[] table;
	private int size = 0;

	public MyHashSet(int minCapacity) {
		if (minCapacity < 4) throw new IllegalArgumentException("At least table size 4 required");
		// TODO: Aufgabe 1
	}

	@Override
	public boolean contains(Object o) {
		// TODO: Aufgabe 3
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	public Object[] copyOfArray() {
		return Arrays.copyOf(table, table.length);
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(E e) {
		if (e == null) throw new NullPointerException("Null are not allowed in this collection.");
		// TODO: Aufgabe 2
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean hasChanged = false;
		for (E elem : c) {
			if (add(elem)) hasChanged = true;
		}
		return hasChanged;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;
		for (Object o : c) {
			if (remove(o)) changed = true;
		}
		return changed;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean remove(Object o) {
		if (o == null) throw new NullPointerException("Null not allowed");
		// TODO: Aufgabe 4
		return false;
	}

	@Override
	public void clear() {
		table = new Object[table.length];
		size = 0;
	}
}
