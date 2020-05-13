
public class BinarySearchTree<T extends Comparable<T>> extends AbstractTree<T> {


    private void insert(Node<T> node, T val) {
        boolean isLeft = node.value.compareTo(val) > 0;
        while ((isLeft && node.left != null) || (!isLeft && node.right != null)) {
            node = isLeft ? node.left : node.right;
            isLeft = node.value.compareTo(val) > 0;
        }
        if (isLeft) node.setLeft(new Node<>(val));
        else node.setRight(new Node<>(val));
    }

    private void remove(Node<T> node, T s, Node<T> parent) {
        if (node.value.equals(s)) {
            Node<T> newNode;
            if (node.left != null && node.right != null) {
                T successor = successor(node.value);
                remove(successor);
                newNode = new Node<>(successor);
                newNode.left = node.left;
                newNode.right = node.right;
            } else newNode = node.left != null ? node.left : node.right;

            if (node.equals(parent.left)) parent.setLeft(newNode);
            else parent.setRight(newNode);
        } else remove(node.value.compareTo(s) > 0 ? node.left : node.right, s, node);

    }


    @Override
    public void insert(T s) {
        if (root == null) root = new Node<>(s);
        else insert(root, s);
    }

    @Override
    public void remove(T s) {
        if (root.value.equals(s)) {
            if (root.left != null && root.right != null) {
                T successor = successor(s);
                remove(successor);
                Node<T> newRoot = new Node<>(successor);
                newRoot.setLeft(root.left);
                newRoot.setRight(root.right);
                root = newRoot;
            } else root = root.left == null ? root.right : root.left;
        } else remove(root.value.compareTo(s) > 0 ? root.left : root.right, s, root);
    }

}
