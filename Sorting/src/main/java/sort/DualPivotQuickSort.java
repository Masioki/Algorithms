package sort;

/**
 * Dual Pivot Quick Sort with Count strategy
 */
public class DualPivotQuickSort<T extends Comparable<T>> extends Sort<T> {

    public DualPivotQuickSort(SortOrder order) {
        super(order);
    }

    private void swap(T[] list, int fIndex, int sIndex) {
        T temp = list[fIndex];
        list[fIndex] = list[sIndex];
        list[sIndex] = temp;
    }

    private void sort(T[] list, int begin, int end) {
        if (begin >= end) return;
        int s = 0, l = 0;
        int i = begin + 1;

        int b = (end - begin) / 3;
        int e = begin + 2 * b;
        b = begin + b;
        if (b == e) e++;
        T fPivVal = list[b];
        T sPivVal = list[e];

        swap(list, begin, b);
        swap(list, end, e);

        if (fPivVal.compareTo(sPivVal) * sign > 0) {
            list[begin] = sPivVal;
            list[end] = fPivVal;
            T temp = fPivVal;
            fPivVal = sPivVal;
            sPivVal = temp;
        }

        while (i + l < end) {
            if (s >= l) {
                if (list[i].compareTo(fPivVal) * sign < 0) swap(list, i, begin + (++s));
                else if (list[i].compareTo(sPivVal) * sign > 0) swap(list, i--, end - (++l));
            } else {
                if (list[i].compareTo(sPivVal) * sign > 0) swap(list, i--, end - (++l));
                else if (list[i].compareTo(fPivVal) * sign < 0) swap(list, i, begin + (++s));
            }
            i++;
        }

        if (l > 0) sort(list, end - l, end);
        if (s > 0) sort(list, begin, begin + s);
        sort(list, begin + s + 1, end - l - 1);
    }

    @Override
    public void sort(T[] list) {
        sort(list, 0, list.length - 1);
    }
}
