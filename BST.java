import java.util.Collection;
import java.lang.Comparable;


public class BST<T extends Comparable> {

    private BSTNode<T> root;

    private int size;
    


    public BST() {
        root = null;
        size = 0;
    }


    public boolean find(T designation) {
        return find(root, designation) != null;
    }


    @SuppressWarnings("unchecked")
    private BSTNode find(BSTNode<T> node, T designation) {
        if (node == null)
            return null;
        if (node.data.compareTo(designation) == 0)
            return node;
        if ((node.data.compareTo(designation) > 0))
            return find(node.left, designation);
        return find(node.right, designation);
    }


    public void insert(Collection<T> list) {
        for (T part: list)
            insert(part);
    }


    @SuppressWarnings("unchecked")
    public void insert(T designation) {
        root = insert(root, designation);
        size++;
    }


    @SuppressWarnings("unchecked")
    private BSTNode<T> insert(BSTNode<T> node, T designation) {
        if (node == null) {
            return new BSTNode(designation);
        }
        if (node.data.compareTo(designation) >= 0) { //could not do alternate method
            node.left = insert(node.left, designation);
        }
        else {
            node.right = insert(node.right, designation);
        }
        return node;
    }



    public boolean delete(T designation) {
        int temp = size;
        root = delete(root, designation);
        return size < temp; //Janky code
    }


    @SuppressWarnings("unchecked")
    private BSTNode<T> delete(BSTNode<T> node, T designation) {
        if (node == null)
            return null;
        if (node.data.compareTo(designation) == 0) {
            size--;
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            BSTNode<T> temp = getLeftmost(node.right);
            node = delete(node, temp.data);
            size++; //replaces the lost size from 2nd call.
            temp.left = node.left;
            temp.right = node.right;

            return temp;
        }
        else if ((node.data.compareTo(designation) > 0))
            node.left = delete(node.left, designation);
        else
            node.right = delete(node.right, designation);
        return node;
    }


    private BSTNode getLeftmost(BSTNode node) {
        if (node.left != null)
            return getLeftmost(node.left);
        return node;
    }


    public void print() {
        System.out.println(toString());
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        generateStringBuilder(root, sb);
        if (root != null) {
            sb.delete(sb.lastIndexOf(", "), sb.length());
        }
        sb.append(']');
        return sb.toString();
    }

    public void generateStringBuilder(BSTNode<T> node, StringBuilder sb) {
        if (node != null) {
            generateStringBuilder(node.left, sb);
            sb.append(node.data)
                    .append(", ");
            generateStringBuilder(node.right, sb);
        }
    }


    public int size() {
        return size;
    }


    private static class BSTNode<T extends Comparable> implements Comparable<BSTNode<T>> {
        BSTNode<T> left, right;
        T data;

        /**
         * Constructs a unit containing Comparable data.
         *
         * @param data
         */
        public BSTNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @SuppressWarnings("unchecked")
        public int compareTo(BSTNode<T> other) throws ClassCastException {
            return data.compareTo(other.data);
        }
    }

}
