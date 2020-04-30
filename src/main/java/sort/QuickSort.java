package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Quick sort with additional median of medians strategy(slow, but more stable).
 * In comments faster version without MoM.
 *
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {


    public QuickSort(SortOrder order) {
        super(order);
    }

    private T median(T[] list, int start, int end) {
        Arrays.sort(list, start, end);
        return list[(end + start) / 2];
    }

    private T medianOfMedian(T[] list, int start, int end) {
        if (end - start <= 5) return median(list, start, end);
        int size = (end - start) / 5;
        List<T> medians = new ArrayList<>(size);
        int i = start;
        while (i + 5 <= end) {
            medians.add(median(list, i, i + 5));
            i += 5;
        }
        if (i < end) medians.add(median(list, i, list.length));
        // int index = (int) (Math.random() * medians.size());
        // return medians.get(Math.max(index, 0));
        Collections.sort(medians);
        return medians.get(size / 2);
    }


    private void sort(T[] list, int begin, int end) {
        if (begin >= end) return;
        int i = begin - 1;
        int j = end + 1;
        // int piv = (end + begin) / 2;
        // T pivVal = list[piv];
        T pivVal = medianOfMedian(list, begin, end);
        while (true) {
            while (list[++i].compareTo(pivVal) * sign < 0) {
            }
            while (j > 0 && list[--j].compareTo(pivVal) * sign > 0) {
            }
            if (i <= j) {
                T temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            } else break;
        }
        if (j > begin) sort(list, begin, j);
        if (i < end) sort(list, i, end);
    }

    @Override
    public synchronized void sort(T[] list) {
        sort(list, 0, list.length - 1);
    }

}
