//12:15 PM
//12:32 PM

public class TreeReplceNodeWithSum {

	public static void main(String[] args){
		BTree tree = new BTree(100);
		System.out.println(replaceNodewithSum(tree));
		tree.left = new BTree(50);
		tree.right = new BTree(200);
		System.out.println(replaceNodewithSum(tree));
		tree.left.left = new BTree(10);
		tree.left.right = new BTree(60);
		tree.right.left = new BTree(150);
		tree.right.right = new BTree(300);
		System.out.println(replaceNodewithSum(tree));
		
	}

	public static int replaceNodewithSum(BTree root){

		if ( root == null){
			return 0;
		}

		if(root.left == null && root.right != null){

			int rootRightData=replaceNodewithSum(root.right);
			int tmp=root.data +rootRightData; 
			root.data=rootRightData;
			return tmp;
		}else if (root.left != null && root.right == null){
			int rootLeftData=replaceNodewithSum(root.left);
			int tmp=root.data + rootLeftData;
			root.data=rootLeftData;
			return tmp;

		}else if (root.left == null && root.right == null){
			return root.data;
		}else{
			int rootRightData=replaceNodewithSum(root.right);
			int rootLeftData=replaceNodewithSum(root.left);
			int tmpSum=rootRightData + rootLeftData  ;
			int tmp = tmpSum+root.data;
			root.data=tmpSum;
			return tmp;
		}

	}
}
