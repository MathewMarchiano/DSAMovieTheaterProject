package structs;

public class TreeIterator<T> implements java.util.Iterator<T> {

    private BinaryTreeBasis<T> tree;
    private TreeNode<T> tNode;
    private SLSQueue<TreeNode<T>> queue;

    public TreeIterator(BinaryTreeBasis<T> tree) {
        this.tree = tree;
        this.tNode = null;
        queue = new SLSQueue<>();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() throws UnsupportedOperationException {
        tNode = queue.dequeue();
        return tNode.getItem();
    }

    @Override
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public void setPreOrder() {
        queue.dequeueAll();
        preOrder(tree.root);
    }

    public void setInOrder() {
        queue.dequeueAll();
        inOrder(tree.root);
    }

    public void setPostOrder() {
        queue.dequeueAll();
        postOrder(tree.root);
    }

    private void preOrder(TreeNode<T> tNode) {
        if (tNode != null) {
            queue.enqueue(tNode);
            preOrder(tNode.getLeftChild());
            preOrder(tNode.getRightChild());
        }
    }

    private void inOrder(TreeNode<T> tNode) {
        if (tNode != null) {
            inOrder(tNode.getLeftChild());
            queue.enqueue(tNode);
            inOrder(tNode.getRightChild());
        }
    }

    private void postOrder(TreeNode<T> tNode) {
        if (tNode != null) {
            inOrder(tNode.getLeftChild());
            inOrder(tNode.getRightChild());
            queue.enqueue(tNode);
        }
    }

}
