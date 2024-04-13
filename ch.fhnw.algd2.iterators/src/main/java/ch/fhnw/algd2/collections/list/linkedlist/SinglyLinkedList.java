package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import ch.fhnw.algd2.collections.list.MyAbstractList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> extends MyAbstractList<E> {

	private int size = 0;
	private Node<E> first, last;

	@Override
	public boolean add(E e) {
		if (last == null) {
			first = new Node<>(e);
			last = first;
		} else {
			last.next = new Node<>(e);
			last = last.next;
		}
		size++;
		modCount++;
		return true;
	}

	@Override
	public boolean contains(Object o) {
		E e = (E) o;

		Node<E> t = first;
		while (t != null && !Objects.equals(e, t.elem)) {
			t = t.next;
		}
		return t != null;
	}

	@Override
	public boolean remove(Object o) {
		E e = (E) o;

		if (first == null) {
			return false;
		}

		Node<E> t = new Node<>(null, first);
		while (t.next != null && !Objects.equals(e, t.next.elem)) {
			t = t.next;
		}

		if (t.next == first && t.next == last) {
			first = null;
			last = null;
		} else if (t.next == first) {
			first = first.next;
		} else if (t.next == last) {
			last = t;
		}

		if (t.next != null && Objects.equals(e, t.next.elem)) {
			t.next = t.next.next;
			size--;
			modCount++;
			return true;
		}
		return false;
	}

	@Override
	public E get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		Node<E> t = first;
		while (index-- > 0) {
			t = t.next;
		}
		return t.elem;
	}

	@Override
	public void add(int index, E element) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		modCount++;
		if (index == 0) {
			first = new Node<>(element, first);
			last = first;
		} else if (index == size) {
			last.next = new Node<>(element);
			last = last.next;
		} else {
			Node<E> t = first;
			while (--index > 0) {
				t = t.next;
			}
			t.next = new Node<>(element, t.next);
		}
		size++;
	}

	@Override
	public E remove(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		modCount++;
		if (index == 0) {
			E e = first.elem;
			if (first == last) {
				last = null;
			}
			first = first.next;
			size--;
			return e;
		} else {
			Node<E> t = first;
			while (--index > 0) {
				t = t.next;
			}

			E e = t.next.elem;
			if (t.next == last) {
				last = t;
			}
			t.next = t.next.next;
			size--;
			return e;
		}
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int index = 0;
		Node<E> current = first;
		while (current != null) {
			array[index++] = current.elem;
			current = current.next;
		}
		return array;
	}

	@Override
	public int size() {
		return size;
	}

	private static class Node<E> {
		private final E elem;
		private Node<E> next = null;

		private Node(E elem) {
			this.elem = elem;
		}

		private Node(E elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator<E> {
		private Node<E> next = first;
		private Node<E> previous = null;
		private int currentIndex = -1;
		private int itModCount = modCount;

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			if(itModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			previous = next;
			currentIndex++;
			next = next.next;
			return previous.elem;
		}

		@Override
		public void remove() {
			if(itModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(previous == null) {
				throw new IllegalStateException();
			}
			SinglyLinkedList.this.remove(currentIndex);
			itModCount++;
			previous = null;
			currentIndex--;
		}
	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(Arrays.toString(list.toArray()));
	}
}
