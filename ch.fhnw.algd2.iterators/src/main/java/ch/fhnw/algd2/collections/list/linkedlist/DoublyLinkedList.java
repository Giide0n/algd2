package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;

import ch.fhnw.algd2.collections.list.MyAbstractList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<E> extends MyAbstractList<E> {

    // variable int modCount already declared by AbstractList<E>
    private int size = 0;
    private final Node<E> first;

    public DoublyLinkedList() {
        first = new Node<>(null);
        first.next = first;
        first.prev = first;
    }

    @Override
    public boolean add(E e) {
        if (size == 0) {
            Node<E> n = new Node<>(first, e, first);
            first.next = n;
            first.prev = n;
        } else {
            Node<E> n = new Node<>(first.prev, e, first);
            first.prev.next = n;
            first.prev = n;
        }
        size++;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        modCount++;
        if (index == size) {
            add(element);
            modCount--;
            size--;
        } else {
            Node<E> t = first;
            while (index-- > 0) {
                t = t.next;
            }
            Node<E> n = new Node<>(t, element, t.next);
            t.next.prev = n;
            t.next = n;
        }
        size++;
    }

    @Override
    public boolean remove(Object o) {
        E e = (E) o;

        Node<E> t = first.next;
        while (t != first && !Objects.equals(e, t.elem)) {
            t = t.next;
        }

        if (t != first) {
            t.prev.next = t.next;
            t.next.prev = t.prev;

            t.next = null;
            t.prev = null;
            size--;
            modCount++;
            return true;
        }

        return false;
    }

    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> t = first;
        while (index-- >= 0) {
            t = t.next;
        }

        t.prev.next = t.next;
        t.next.prev = t.prev;

        t.next = null;
        t.prev = null;
        size--;
        modCount++;
        return t.elem;
    }

    @Override
    public boolean contains(Object o) {
        E e = (E) o;

        Node<E> t = first.next;
        while (t != first && !Objects.equals(e, t.elem)) {
            t = t.next;
        }
        return t != first;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> t = first;
        while (index-- >= 0) {
            t = t.next;
        }
        return t.elem;
    }

    @Override
    public Object[] toArray() {
        // return arrayForDoublyLinkedList();
        return arrayForCyclicDoublyLinkedList();
    }

    private Object[] arrayForDoublyLinkedList() {
        Object[] array = new Object[size];
        int index = 0;
        Node<E> current = first;
        while (current != null) {
            array[index++] = current.elem;
            current = current.next;
        }
        return array;
    }

    private Object[] arrayForCyclicDoublyLinkedList() {
        Object[] array = new Object[size];
        int index = 0;
        Node<E> current = first.next;
        while (current != first) {
            array[index++] = current.elem;
            current = current.next;
        }
        return array;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    private static class Node<E> {

        private E elem;
        private Node<E> prev, next;

        private Node(E elem) {
            this.elem = elem;
        }

        private Node(Node<E> prev, E elem, Node<E> next) {
            this.prev = prev;
            this.elem = elem;
            this.next = next;
        }
    }

    private class MyListIterator implements ListIterator<E> {

        private Node<E> next = first.next;
        private int i = -1;
        private int lastI = -1;
        private int itModCount = modCount;

        @Override
        public boolean hasNext() {
            return next != first;
        }

        @Override
        public boolean hasPrevious() {
            return next.prev != first;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (itModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            lastI = ++i;
            E e = next.elem;
            next = next.next;
            return e;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (itModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            lastI = i--;
            E e = next.prev.elem;
            next = next.prev;
            return e;
        }

        @Override
        public int nextIndex() {
            return i + 1;
        }

        @Override
        public int previousIndex() {
            return i;
        }

        @Override
        public void remove() {
            if (itModCount != modCount) {
                throw new ConcurrentModificationException();
            }
			if (lastI == -1) {
				throw new IllegalStateException();
			}
			DoublyLinkedList.this.remove(lastI);
			lastI = -1;
            itModCount++;
        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(Arrays.toString(list.toArray()));
    }
}
