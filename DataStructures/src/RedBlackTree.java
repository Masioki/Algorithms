public class RedBlackTree<T extends Comparable<T>> extends AbstractTree<T> {

    public RedBlackTree() {
        super();
        root = NIL;
    }

    private Node<T> subtreeMin(Node<T> node) {
        while (node.left != NIL) node = node.left;
        return node;
    }

    private void replace(Node<T> n1, Node<T> n2) {
        if (n1.parent == null) root = n2;
        else if (n1 == n1.parent.left) n1.parent.left = n2;
        else n1.parent.right = n2;
        n2.parent = n1.parent;
    }

    private void rotate(Node<T> node, boolean rotateLeft) {
        Node<T> newNode;
        if (rotateLeft) {
            newNode = node.right;
            node.right = newNode.left;
            if (newNode.left != NIL) newNode.left.parent = node;
            newNode.parent = node.parent;
            if (node.parent == null) this.root = newNode;
            else if (node == node.parent.left) node.parent.left = newNode;
            else node.parent.right = newNode;
            newNode.left = node;
        } else {
            newNode = node.left;
            node.left = newNode.right;
            if (newNode.right != NIL) newNode.right.parent = node;
            newNode.parent = node.parent;
            if (node.parent == null) this.root = newNode;
            else if (node == node.parent.right) node.parent.right = newNode;
            else node.parent.left = newNode;
            newNode.right = node;
        }
        node.parent = newNode;
    }

    private void remove(Node<T> node) {
        Node<T> bro;
        while (node != root && !node.red) {
            if (node == node.parent.left) {
                bro = node.parent.right;
                if (bro.red) {
                    bro.red = false;
                    node.parent.red = true;
                    rotate(node.parent, true);
                    bro = node.parent.right;
                }
                if (!bro.left.red && !bro.right.red) {
                    bro.red = true;
                    node = node.parent;
                } else {
                    if (!bro.right.red) {
                        bro.left.red = false;
                        bro.red = true;
                        rotate(bro, false);
                        bro = node.parent.right;
                    }
                    bro.red = node.parent.red;
                    node.parent.red = false;
                    bro.right.red = false;
                    rotate(node.parent, true);
                    node = root;
                }
            } else {
                bro = node.parent.left;
                if (bro.red) {
                    bro.red = false;
                    node.parent.red = true;
                    rotate(node.parent, false);
                    bro = node.parent.left;
                }
                if (!bro.right.red) {
                    bro.red = true;
                    node = node.parent;
                } else {
                    if (!bro.left.red) {
                        bro.right.red = false;
                        bro.red = true;
                        rotate(bro, true);
                        bro = node.parent.left;
                    }
                    bro.red = node.parent.red;
                    node.parent.red = false;
                    bro.left.red = false;
                    rotate(node.parent, false);
                    node = root;
                }
            }
        }
        node.red = false;
    }

    private void insert(Node<T> node) {
        Node<T> temp;
        while (node.parent.red) {
            if (node.parent == node.parent.parent.right) {
                temp = node.parent.parent.left;
                if (temp.red) {
                    temp.red = false;
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotate(node, false);
                    }
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    rotate(node.parent.parent, true);
                }
            } else {
                temp = node.parent.parent.right;
                if (temp.red) {
                    temp.red = false;
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotate(node, true);
                    }
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    rotate(node.parent.parent, false);
                }
            }
            if (node == root) break;
        }
        root.red = false;
    }


    @Override
    public void insert(T value) {
        Node<T> node = new Node<>(0, value, null, NIL, NIL, true);
        Node<T> prev = null, next = root;

        while (next != NIL) {
            prev = next;
            next = node.value.compareTo(next.value) < 0 ? next.left : next.right;
        }

        node.parent = prev;
        if (prev == null) {
            root = node;
            root.red = false;
            return;
        } else if (node.value.compareTo(prev.value) < 0) prev.left = node;
        else prev.right = node;

        if (node.parent.parent == null) return;
        insert(node);
    }

    @Override
    public void remove(T value) {
        Node<T> node = root, orgNode = NIL, newNode;

        while (node != NIL) {
            if (value.equals(node.value)) orgNode = node;
            if (value.compareTo(node.value) > 0) node = node.right;
            else node = node.left;
        }
        if (orgNode == NIL) return;
        boolean originalRed = orgNode.red;

        if (orgNode.left == NIL) {
            newNode = orgNode.right;
            replace(orgNode, orgNode.right);
        } else if (orgNode.right == NIL) {
            newNode = orgNode.left;
            replace(orgNode, orgNode.left);
        } else {
            Node<T> succ = subtreeMin(orgNode.right);
            originalRed = succ.red;
            newNode = succ.right;
            if (succ.parent == orgNode) newNode.parent = succ;
            else {
                replace(succ, succ.right);
                succ.right = orgNode.right;
                succ.right.parent = succ;
            }
            replace(orgNode, succ);
            succ.left = orgNode.left;
            succ.left.parent = succ;
            succ.red = orgNode.red;
        }
        if (!originalRed) remove(newNode);
    }

}
