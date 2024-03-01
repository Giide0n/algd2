package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.Arrays;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class MyLinkedList<E> extends MyAbstractList<E> {
	private int size = 0;
	private Node<E> first;

	@Override
	public boolean add(E e) {
		if(first == null) {
			first = new Node<>(e);
		} else {
			Node<E> t = first;
			while(t.next != null) {
				t = t.next;
			}
			t.next = new Node<>(e);
		}
		size++;
		return true;
	}

	@Override
	public boolean contains(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}

		E e = (E) o;

		Node<E> t = first;
		while(t != null && !e.equals(t.elem)) {
			t = t.next;
		}
		return t != null;
	}

	@Override
	public boolean remove(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}

		E e = (E) o;

		if(first != null && e.equals(first.elem)) {
			first = first.next;
			size--;
			return true;
		} else if (first != null){
			Node<E> t = first;
			while (t.next != null && !e.equals(t.next.elem)) {
				t = t.next;
			}
			if (t.next != null && e.equals(t.next.elem)) {
				t.next = t.next.next;
				size--;
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	@Override
	public E get(int index) {
		if(index >= size || index < 0) {
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
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if(index == 0) {
			first = new Node<>(element, first);
		} else {
			Node<E> t = first;
			while(--index > 0) {
				t = t.next;
			}
			t.next = new Node<>(element, t.next);
		}
		size++;
	}

	@Override
	public E remove(int index) {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if(index == 0) {
			E e = first.elem;
			first = first.next;
			size--;
			return e;
		} else {
			Node<E> t = first;
			while (--index > 0) {
				t = t.next;
			}

			E e = t.next.elem;
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
		private Node<E> next;

		private Node(E elem) {
			this.elem = elem;
		}

		private Node(E elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(Arrays.toString(list.toArray()));
	}
}
