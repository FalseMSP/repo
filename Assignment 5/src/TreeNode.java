public class TreeNode<T> {
	private T val;
	public TreeNode<T> left;
	public TreeNode<T> right;
	
	public TreeNode(T dataNode) {
		val = dataNode;
	}
	public TreeNode(TreeNode<T> node) {
		val = node.getData();
	}
	public T getData() {
		return val;
	}
	public void setData(T letter) {
		val = letter;
	}
	public void addLeftChild(TreeNode<T> child) {
        this.left = child;
    }
	public void addRightChild(TreeNode<T> child) {
        this.right = child;
    }
}