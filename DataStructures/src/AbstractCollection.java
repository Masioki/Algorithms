import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCollection<T extends Comparable<T>> implements MyCollection<T> {
    @Override
    public void insert(T s) {

    }

    @Override
    public void remove(T s) {

    }

    @Override
    public boolean contains(T s) {
        return false;
    }

    @Override
    public T min() {
        return null;
    }

    @Override
    public T max() {
        return null;
    }

    @Override
    public T successor(T s) {
        return null;
    }

    @Override
    public List<T> inorder() {
        return new ArrayList<>();
    }

    @Override
    public int size() {
        return 0;
    }
}
