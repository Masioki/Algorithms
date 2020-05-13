import java.util.ArrayList;
import java.util.List;


public abstract class AbstractTree<T extends Comparable<T>> extends AbstractCollection<T> {
    protected Node<T> root;
    protected final Node<T> NIL;
    protected int size;

    public AbstractTree() {
        NIL = new Node<T>(null, null, null, null);
        size = 0;
    }


    @Override
    public boolean contains(T s) {
        Node<T> temp = root;
        while (temp != null && temp != NIL) {
            if (s.equals(temp.value)) return true;
            temp = temp.value.compareTo(s) > 0 ? temp.left : temp.right;
        }
        return false;
    }

    @Override
    public T min() {
        if (root == null || root == NIL) return null;
        Node<T> temp = root;
        while (temp.left != null && temp.left != NIL) temp = temp.left;
        return temp.value;
    }

    @Override
    public T max() {
        if (root == null) return null;
        Node<T> temp = root;
        while (temp.right != null && temp.right != NIL) temp = temp.right;
        return temp.value;
    }


    @Override
    public T successor(T s) { //TODO:
        if (contains(s)) {
            List<T> inorder = inorder();
            int index = inorder.indexOf(s);
            if (index == inorder.size() - 1) return null;
            else return inorder.get(index + 1);
        }
        return null;
    }


    private void inorder(Node<T> node, List<T> data) {
        if (node.left != null && node.left != NIL) inorder(node.left, data);
        data.add(node.value);
        if (node.right != null && node.right != NIL) inorder(node.right, data);
    }

    @Override
    public List<T> inorder() {
        if (root == null || root == NIL) return new ArrayList<>();
        List<T> data = new ArrayList<>();
        inorder(root, data);
        return data;
    }

    @Override
    public int size() {
        return size;
    }
}
