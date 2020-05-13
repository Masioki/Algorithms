import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<T extends Comparable<T>> extends AbstractCollection<T> {
    private final int cellSize, tableSize;
    private final List<MyCollection<T>> table;

    public HashTable(int tableSize, int cellSize) {
        table = new ArrayList<>(tableSize);
        this.cellSize = cellSize;
        this.tableSize = tableSize;
    }


    private void fix(int index) {
        if (table.get(index) == null) table.set(index, new ListAdapter<T>(new LinkedList<>()));
        else if (table.get(index) instanceof ListAdapter && table.get(index).size() >= cellSize) {
            ListAdapter<T> temp = (ListAdapter<T>) table.get(index);
            table.set(index, new RedBlackTree<>());
            for (T s : temp) table.get(index).insert(s);
        }
    }


    @Override
    public void insert(T s) {
        int i = s.hashCode() % tableSize;
        if (i < 0) i -= 2 * i;
        fix(i);
        table.get(i).insert(s);
    }

    @Override
    public void remove(T s) {
        int i = s.hashCode() % tableSize;
        if (i < 0) i -= 2 * i;
        fix(i);
        table.get(i).remove(s);
    }

    @Override
    public boolean contains(T s) {
        int i = s.hashCode() % tableSize;
        if (i < 0) i -= 2 * i;
        fix(i);
        return table.get(i).contains(s);
    }


    @Override
    public int size() {
        return table.stream().mapToInt(c -> c != null ? c.size() : 0).sum();
    }
}

