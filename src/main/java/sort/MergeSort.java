package sort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    public MergeSort(SortOrder order) {
        super(order);
    }

    private void mergeParts(T[] list, int begin, int middle, int end) {
        int fIndex = begin;
        int sIndex = middle + 1;
        List<T> result = new ArrayList<>(end - begin + 1);
        while (fIndex <= middle && sIndex <= end) {
            T fTemp = list[fIndex];
            T sTemp = list[sIndex];
            if (fTemp.compareTo(sTemp) * sign > 0) {
                result.add(sTemp);
                sIndex++;
            } else {
                result.add(fTemp);
                fIndex++;
            }
        }
        for (int i = 0; fIndex + i <= middle; i++) list[end - i] = list[middle - i];
        for (int i = 0; i < result.size(); i++) list[begin + i] = result.get(i);
    }

    private void mergeSort(T[] list, int begin, int end) {
        if (begin >= end) return;
        int middle = (begin + end) / 2;
        mergeSort(list, begin, middle);
        mergeSort(list, middle + 1, end);
        mergeParts(list, begin, middle, end);
    }

    @Override
    public void sort(T[] list) {
        mergeSort(list, 0, list.length - 1);
    }

}
