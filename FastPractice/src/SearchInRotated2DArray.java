//4:45 PM  5PM

public class SearchInRotated2DArray {

	public static void main(String[] args){
		
		int[][] arr = new int[][] {{1,2,3},{4,5,6}};
		
		findElement( arr , 6);
	}
	
	public static void findElement(int[][] arr, int value){
		
		//We start with the leftmost bottom element since it is ordered so, it will  be the 
		//highest element in that column and begining element of row
		
		//So for column we can go up and row we can move right.
		
		int row=arr.length-1;
		int col = 0;
		
		while ( row >= 0 && col <= arr[0].length -1)
		if(value > arr[row][col]){
			//move to the right in the same row, so increase  the column
			col++;
		}else if (value < arr[row][col] ){
			// move up in the same column
			--row;
		}else if( value == arr[row][col]){
			
			System.out.println("Index =" + row +"  "+col);
			return ;
		}
		
		System.out.println("Element not found");
	}
}
