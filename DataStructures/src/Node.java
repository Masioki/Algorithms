public class Node<T extends Comparable<T>> {
    int key;
    T value;
    Node<T> parent, left, right;
    boolean red;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> parent, Node<T> left, Node<T> right) {
        this(0, value, parent, left, right, false);
    }

    public Node(int key, T value, Node<T> parent, Node<T> left, Node<T> right, boolean red) {
        this.parent = parent;
        this.key = key;
        this.left = left;
        this.right = right;
        this.red = red;
        this.value = value;
    }


    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

}