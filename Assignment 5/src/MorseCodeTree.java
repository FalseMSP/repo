import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	TreeNode<String> root;
	
	public MorseCodeTree() {
		root = new TreeNode<String>("");
	}
	
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	@Override
	public void setRoot(TreeNode newNode) {
		root = newNode;
	}

	@Override
	public void insert(String code, String letter) {
//		TreeNode<String> currentNode = root;
//		while (!code.equals("")) {
//			String temp = code.substring(0, 1);
//			code = code.substring(1);
//			if (temp.equals(".")) currentNode=currentNode.left;
//			else if (temp.equals("_")) currentNode=currentNode.right;
//		}
		addNode(root,code,letter);
	}

	@Override
	public void addNode(TreeNode root, String code, String letter) {
		String temp = code.substring(0, 1);
		code = code.substring(1);
		if (code.equals("")) {
			if (temp.equals(".")) root.left = new TreeNode<String>(letter);
			if (temp.equals("-")) root.right = new TreeNode<String>(letter);
			return;
		}
		if (temp.equals(".")) addNode(root.left,code,letter);
		else if (temp.equals("-")) addNode(root.right,code,letter);
	}

	@Override
	public String fetch(String code) {
		return fetchNode(root,code);
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if (code.equals("")) {
			return root.getData();
		}
		String temp = code.substring(0, 1);
		code = code.substring(1);
		if (temp.equals(".")) return fetchNode(root.left,code);
		else if (temp.equals("-")) return fetchNode(root.right,code);
		throw new RuntimeException("Invalid Character");
	}

	@Override
	public LinkedConverterTreeInterface delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public LinkedConverterTreeInterface update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void buildTree() {
		insert(".", "e");
        insert("-", "t");

        // Level 2
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");

        // Level 3
        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");

        // Level 4
        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
	}

	@Override
	public ArrayList toArrayList() {
		ArrayList<String> list = new ArrayList<>();
		LNRoutputTraversal(root, list);
        return list;
	}

	@Override
	public void LNRoutputTraversal(TreeNode root, ArrayList list) {
		if (root == null) {
            return; 
        }

		LNRoutputTraversal(root.left, list);

        list.add(root.getData());

        LNRoutputTraversal(root.right, list);
		
	}
	
}
