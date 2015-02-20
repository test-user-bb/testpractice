//3:03  3:08



class MyTree{
		MyTree treeLeft,treeright;
		int data;

		MyTree(int data){
			this.data=data;
			treeLeft=null;
			treeright=null;

		}
		MyTree(int data, MyTree left,MyTree right){
			this.data=data;
			treeLeft=left;
			treeright=right;

		}

	};
public class PrintLevelTree {

	


	public static void main(String[] args){

		MyTree t = new MyTree(100);

		MyTree t1 = new MyTree(200);
		MyTree t2 = new MyTree(50);
		t.treeLeft=t2;
		t.treeright=t1;
		printLevelTree(t,1,2);
	}
	public static void printLevelTree(MyTree tree, int actualLevel, int desiredlevel){

		if(tree == null){
			return;
		}

		if(actualLevel == desiredlevel){
			System.out.println("  "+tree.data);
		}
		printLevelTree(tree.treeLeft, actualLevel+1, desiredlevel);
		printLevelTree(tree.treeright, actualLevel+1, desiredlevel);

	}

}

