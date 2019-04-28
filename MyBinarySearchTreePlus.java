/*
 * Purpose: Data Structure and Algorithms Lab 11 Problem 2
 * Status: Complete and thoroughly tested
 * Last update: 04/21/19
 * Submitted:  04/22/19
 * Comment:
 * @author: Scott Zockoll
 * @version: 2019.04.21
 */

package structs;

public class MyBinarySearchTreePlus<T extends KeyedItem<KT>,
        KT extends Comparable<? super KT>> extends MyBinarySearchTree<T,
        KT> implements BSTPInterface<T, KT> {

    @Override
    public int getHeight() {
        return computeHeight(root);
    }

    @Override
    public int getSize() {
        return computeSize(root);
    }

    @Override
    public String toStringInorder() {
        TreeIterator<T> iterator = new TreeIterator<>(this);
        iterator.setInOrder();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next() + " ");
        }
        return sb.toString();
    }

    @Override
    public String toStringPreorder() {
        TreeIterator<T> iterator = new TreeIterator<>(this);
        iterator.setPreOrder();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next() + " ");
        }
        return sb.toString();
    }

    @Override
    public String toStringPostorder() {
        TreeIterator<T> iterator = new TreeIterator<>(this);
        iterator.setPostOrder();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next() + " ");
        }
        return sb.toString();
    }

    @Override
    public MyBinarySearchTreePlus<T, KT> getCopyOfTree() {
        TreeIterator<T> iterator = new TreeIterator<>(this);
        iterator.setPreOrder();
        MyBinarySearchTreePlus<T, KT> treeCopy =
                new MyBinarySearchTreePlus<>();
        while (iterator.hasNext()) {
            treeCopy.insert(iterator.next());
        }

        return treeCopy;
    }

    private int computeHeight(TreeNode<T> tNode) {
        if (tNode == null) {
            return 0;
        } else {
            int leftHeight = computeHeight(tNode.getLeftChild());
            int rightHeight = computeHeight(tNode.getRightChild());
            if (leftHeight > rightHeight) {
                return 1 + leftHeight;
            } else {
                return 1 + rightHeight;
            }
        }
    }

    private int computeSize(TreeNode<T> tNode) {
        if (tNode == null) {
            return 0;
        } else {
            return 1 + computeSize(tNode.getLeftChild()) +
                    computeSize(tNode.getRightChild());
        }
    }

}
