package sort;

/**
 * Simple Hybrid of Quick and Insert sort.
 * Switches to Insert sort when range size is < 10
 */
public class Hybrid<T extends Comparable<T>> extends Sort<T> {
    public Hybrid(SortOrder order) {
        super(order);
    }

    /**
     * Insert sort without binary search.
     * Tests showed that, for small ranges, binary search is little bit slower.
     */
    private void insertSort(T[] list, int begin, int end) {
        int index = 0;
        for (int i = begin + 1; i <= end; i++) {
            T temp = list[i];
            index = i;
            while (--index >= 0 && list[index].compareTo(temp) * sign > 0) list[index + 1] = list[index];
            list[index + 1] = temp;
        }
    }


    private void quickSort(T[] list, int begin, int end) {
        if (end - begin <= 9) {
            insertSort(list, begin, end);
            return;
        }
        if (begin >= end) return;
        int i = begin - 1;
        int j = end + 1;
        int piv = (end + begin) / 2;
        T pivVal = list[piv];
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
        if (j > begin) quickSort(list, begin, j);
        if (i < end) quickSort(list, i, end);
    }

    @Override
    public void sort(T[] list) {
        quickSort(list, 0, list.length - 1);
    }
}
