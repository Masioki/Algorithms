import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListAdapter<T extends Comparable<T>> extends AbstractCollection<T> implements Iterable<T> {
    private final List<T> list;

    public ListAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public void insert(T s) {
        list.add(s);
    }

    @Override
    public void remove(T s) {
        list.remove(s);
    }

    @Override
    public boolean contains(T s) {
        return list.contains(s);
    }

    @Override
    public T min() {
        return list.stream().min(T::compareTo).orElse(null);
    }

    @Override
    public T max() {
        return list.stream().max(T::compareTo).orElse(null);
    }

    @Override
    public T successor(T s) {
        List<T> inorder = inorder();
        int index = inorder.indexOf(s);
        if (index < inorder.size() - 1) return inorder.get(index + 1);
        return null;
    }

    @Override
    public List<T> inorder() {
        List<T> result = new LinkedList<>(list);
        Collections.sort(result);
        return result;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
