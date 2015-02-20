
//9:41 end 10:13 time(28 mins)
/**
 * 
 * Define the precision
 * Define the range initially from 1 to the half of the current number.
 * Then check the binary range and device
 *
 */
public class SquareRoot {

	final static double  PRECISION=0.01;

	public static double getSquareRoot(double number){

		if(number<0) {
			return -1;
		}
		if( number ==0 || number == 1) {
			return number;
		}


		double rangeStart=1;
		double rangeEnd = number/2;

		double squareRoort = rangeEnd;

		while( rangeEnd-rangeStart >  PRECISION  ){

			squareRoort= (rangeStart+rangeEnd)/2;

			Double square = squareRoort*squareRoort;

			if (number == squareRoort ){
				return squareRoort;
			}

			if(number < square){
				rangeEnd=squareRoort;
				//decrease the squareRoot by moving rangeEnd to smaller number
			}else{
				rangeStart=squareRoort;
				//increase  the squareRoot by moving rangeStart to higher number
			}
		}
		return squareRoort;
	}


	public static void main(String[] args){
		System.out.println(getSquareRoot(1));
		//System.out.println(getSquareRoot(4));
		System.out.println(getSquareRoot(9));
		System.out.println(getSquareRoot(36));
	}
}
