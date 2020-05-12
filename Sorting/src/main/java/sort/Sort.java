package sort;


/**
 * Abstract Sort class, collects statistics.
 */
public abstract class Sort<T extends Comparable<T>> {
    protected int sign;

    public Sort(SortOrder order) {
        sign = order.getVal();
    }

    public abstract void sort(T[] list);

}
