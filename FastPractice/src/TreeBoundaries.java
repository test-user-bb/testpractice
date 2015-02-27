//:5:30  : 5:50 
public class TreeBoundaries {

	public static void main(String[] args){

		BTree root = new BTree(100);
		root.left = new BTree(50);
		root.right = new BTree(200);

		root.left.left = new BTree(25);
		root.left.left.left = new BTree(5);
		root.left.right = new BTree(60);

		root.right.left = new BTree(150);
		root.right.right = new BTree(300);
		root.right.right.right = new BTree(400);

		printLeftBoundary(root);
		printInorderDownBoundary(root);
		printRightBoundary(root);


	}

	//We need to print 3 boundries:
	//1. Lfet
	//2. Right.
	//3. Down leaves.

	public static void printLeftBoundary(BTree root){
		//Left boundary means all the left children
		while(root != null && root.left != null && root.right != null){
			System.out.print(root.data+"->");
			root=root.left;
		}

	}

	//Print Right boundary
	public static void printRightBoundary(BTree root){
		//Left boundary means all the left children
		while(root != null && root.right != null && root.left != null){
			System.out.print(root.data+"->");
			root=root.right;
		}

	}

	public static void printInorderDownBoundary(BTree root){
		//Left boundary means all the left children
		if(root != null){
			printInorderDownBoundary(root.left);
			if(root.right == null && root.left == null){
				System.out.print(root.data+"->");
			}
			printInorderDownBoundary(root.right);
		}
	}


	public static void printTreeBoundries(BTree root){

	}

}
