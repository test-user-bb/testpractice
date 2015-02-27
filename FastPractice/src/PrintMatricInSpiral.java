
//5:35 PM

//Print the matric in Spiral order.
//Key check the elements getting included and excluded 
// Withe very rotation your elements getting exlcuded from the column and rows are increasing.
// We need to keep track of  rotation and elements to print out.
// With every rotation, the elemets to exclude will be increasing.


public class PrintMatricInSpiral {

	//Steps : 

	public static void main(String[] args){
		int[][] matrix = { {1,2,3},
						   {4,5,6},
						   {7,8,9},

		};

		printSpiral(matrix);
	}

	private static void printSpiral(int[][] matrix) {

		// we can not do it by two for loops.
		//we always need to get the row and column dynamically 
		// Then based on our rotation from left to right,
		// top to bottom
		// right to left
		// bottom to up.

		//So, get some starting coordinate, then get orientation , and number of times we orient.

		//e.g: for first orientation, the left => right startCol=0, 
		//for first orientation, the top to bottom  =>  startrow will be 1, startcol= arr[0].lenght -1
		//for first orientation, the right to left  =>  startrow will be arr.lenght-1 , start col= arr[0].length-2
		//for first orientation, the bottom to top  =>  startrow will be arr.lenght-2 , start col= 1

		// so parameters  are increasing with rotation.

		// Now to find out, the end condition and the how to stop in a rotation.

		//Assume we use an infinite loop and later generate the exit condition.

		// We use rotation count  to loop into and get the correct increment.

		//L->R  == 0, rotatin% 4
		// T-B = 1 , rotatin % 4
		// R -> L = 2  rotation % 4
		// B -> T = 3 rotation % 4

		int rotation = 0;

		//We are moving in a spiral, so we will keep track of the current element 
		// and based on orientation we will decide the next move.

		//initialized to zero since we are starting spiral from the leftmost top corner as left to right.
		int currentRpos=0;
		int currentCpos=0;
		int incrR=0,incrC=0;
		int i = 0;
		while(true){
			int orientation = rotation % 4;
			
			int noOfElements= 0;

			if(rotation%2 ==0 ){
				noOfElements = matrix.length - rotation/2 ;
			}else{
				noOfElements = (matrix[0].length - 1) - rotation/2 ;
			}
			
			System.out.println("Elements "+noOfElements +" and orientation "+ orientation);

			if(noOfElements == 0 ){
				System.out.println("Elements finished printing");
				return;
			}
			switch (orientation){
			case 0:  //L-R  Same row, increment col
				incrR=0;
				incrC=1;
				break;
			case 1:  //T-B  increment row, keep same col
				incrR=1;
				incrC=0;
				break;
			case 2:  //R-L  decr col, keep, row  same
				incrR=0;
				incrC=-1;
				break;
			case 3:  //B-U  decr  row, keep col same
				incrR=-1;
				incrR=0;
				break;
			}

		
		
			// Now since we got the noOf elements to print and we got the orientation to move on
			// we can simply go into a for loop and access all the elements.

			
			for(i = 0; i <noOfElements;++i){
				
				
				//System.out.print(nums[x+(i+1)*xIncre][y+(i+1)*yIncre]+" ");//pay attention to the index settings
				System.out.print("Cur POS:"+ (currentRpos+ incrR*(i)) +"    " +  (currentCpos + incrC*(i)));
				System.out.println();
			
				System.out.println( matrix[currentRpos+ incrR*(i+1)][currentCpos + incrC*(i+1)]);
			}
			rotation ++;
			currentRpos = currentRpos+ incrR*noOfElements;
			currentCpos =currentCpos + incrC*noOfElements ;
			
		}




	}



}



