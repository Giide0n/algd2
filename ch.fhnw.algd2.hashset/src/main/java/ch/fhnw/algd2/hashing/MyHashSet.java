package ch.fhnw.algd2.hashing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Hash Set with Open Addressing collision resolution
 */
public class MyHashSet<E> implements Set<E> {
    private static final Object SENTINEL = new Object();

    private Object[] table;
    private int size = 0;

    public MyHashSet(int minCapacity) {
        if (minCapacity < 4) {
            throw new IllegalArgumentException("At least table size 4 required");
        }
        int capacity = 4;
        while (capacity < minCapacity) {
            capacity <<= 1;
        }
        table = new Object[capacity];
    }

    private int index(Object o) {
        return Math.abs(o.hashCode() % table.length);
    }

    private int step(Object o) {
        int step = 1 + (o.hashCode() & table.length - 1);
        return step % 2 == 0 ? step - 1 : step;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        int i = index(o);
        int step = step(o);
        for (int j = 0; j < table.length; j++) {
            if (table[i] == null) {
                return false;
            }
            if (table[i] != SENTINEL && table[i].hashCode() == o.hashCode() && table[i].equals(o)) {
                return true;
            }
            i = (i + step) % table.length;
        }
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
        if (e == null) {
            throw new NullPointerException("Null are not allowed in this collection.");
        }
        if (contains(e)) {
            return false;
        }
        if (size == table.length) {
            throw new IllegalStateException("Can not add, is full");
        }

        int i = index(e);
        int step = step(e);

        while (table[i] != null && table[i] != SENTINEL) {
            i = (i + step) & table.length - 1;
        }

        table[i] = e;
        size++;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean hasChanged = false;
        for (E elem : c) {
            if (add(elem)) {
                hasChanged = true;
            }
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
            if (remove(o)) {
                changed = true;
            }
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
        if (o == null) {
            throw new NullPointerException("Null not allowed");
        }
        if (!contains(o)) {
            return false;
        }

        int i = index(o);
        int step = step(o);
        while (table[i] == null || table[i].hashCode() != o.hashCode() || !table[i].equals(o)) {
            i = (i + step) & table.length - 1;
        }

        table[i] = SENTINEL;
        size--;
        return true;
    }

    @Override
    public void clear() {
        table = new Object[table.length];
        size = 0;
    }
}
