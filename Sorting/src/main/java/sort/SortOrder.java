package sort;

/**
 * Sorting order.
 */
public enum SortOrder {
    DESCENDING(-1),
    ASCENDING(1);

    private final int val;

    SortOrder(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}