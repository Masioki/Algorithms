package sort;

public class InsertionSort<T extends Comparable<T>> extends Sort<T> {

    public InsertionSort(SortOrder order) {
        super(order);
    }


    /**
     * Binary search without recursion.
     */
    public int findIndex(T[] list, T element, int min, int max) {
        int index;
        while (true) {
            if (min >= max) return max;
            if (max == 0) return 0;
            if (max == 1) return list[0].compareTo(element) * sign <= 0 ? 1 : 0;

            index = (max + min) / 2;
            if (list[index].compareTo(element) * sign <= 0) {
                if (list[index + 1].compareTo(element) * sign >= 0) return index + 1;
                else min = index + 1;
            } else max = index;
        }
    }


    @Override
    public void sort(T[] list) {
        int index = 0;
        for (int i = 1; i < list.length; i++) {
            T temp = list[i];
            index = findIndex(list, temp, 0, i);
            for (int j = i - 1; j >= index; j--) list[j + 1] = list[j];
            list[index] = temp;
        }
    }


}
