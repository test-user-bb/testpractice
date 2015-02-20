class Tree{
	Tree treeLeft,treeright;
	int data;

	Tree(int data){
		this.data=data;
		treeLeft=null;
		treeright=null;

	}
	Tree(int data, Tree left,Tree right){
		this.data=data;
		treeLeft=left;
		treeright=right;

	}

};

public class ThressProblems {

	public static void main(String[] args){

		Tree t = new Tree(100);

		Tree t1 = new Tree(200);
		Tree t2 = new Tree(50);
		t.treeLeft=t2;
		t.treeright=t1;

		System.out.println(checkBST(t, Integer.MIN_VALUE,Integer.MAX_VALUE));
	}
	public static boolean checkBST(Tree tree, int smallLimit,int  bigLimit){

		if( tree == null) return true;
		
		if( tree.data < smallLimit && tree.data > bigLimit){
			return false;
		}
		
		return checkBST(tree.treeLeft, smallLimit, tree.data) && checkBST(tree.treeright, tree.data, bigLimit);
		

	}
}
