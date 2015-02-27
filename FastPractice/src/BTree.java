
public class BTree {
		BTree left,right;
		int data;

		BTree(int data){
			this.data=data;
			left=null;
			right=null;

		}
		BTree(int data, BTree left,BTree right){
			this.data=data;
			this.left=left;
			this.right=right;

		}
};
