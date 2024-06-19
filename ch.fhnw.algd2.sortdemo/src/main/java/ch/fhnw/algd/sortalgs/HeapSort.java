/*
 * Created on 21.03.2014
 */
package ch.fhnw.algd.sortalgs;

import ch.fhnw.algd.sortdemo.framework.SortAlg;
import ch.fhnw.algd.sortdemo.framework.SortData;

public class HeapSort implements SortAlg {
	private SortData data;

	@Override
	public void run(SortData data) {
		createFloydMaxHeap(data);
		heapSort(data);
	}

	private void createFloydMaxHeap(SortData data) {
		int size = data.size();
		for (int i = size / 2 - 1; i >= 0; i--) {
			siftDown(data, i, size);
		}
	}

	private void heapSort(SortData data) {
		int size = data.size();
		for (int i = size - 1; i > 0; i--) {
			data.swap(0, i);
			siftDown(data, 0, i);
		}
	}

	private void siftDown(SortData data, int parent, int size) {
		int child = indexOfLargerChild(data, parent, size);
		while (child < size && data.less(parent, child)) {
			data.swap(parent, child);
			parent = child;
			child = indexOfLargerChild(data, child, size);
		}

	}

	private int indexOfLargerChild(SortData data, int parent, int size) {
		int child = 2 * parent + 1;
		if (child + 1 < size && data.less(child, child + 1)) {
			++child;
		}
		return child;
	}
}
