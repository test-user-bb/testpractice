
//3:30 PM

public class DivisionWithoutOperator {

	public static void main(String[] args) {
		System.out.println("Result : "+getDivision(100, 5));

	}

	public static int getDivision(int divident, int divisor ){
		
		int localdivident = divident;
		int localdivisor = divisor;
		int quotient = 0;
		int divisorDoubleTimes = 1;
		System.out.println(localdivident + "  " + quotient + "   " + localdivisor);
		
		while (localdivident >= divisor){
			if(localdivident >= localdivisor){
				
				localdivident -= localdivisor;
				quotient += divisorDoubleTimes;
				
				// Increase the localdivisor for the next time.
				localdivisor *=2;
				// Increase the base for next time.
				divisorDoubleTimes *=2;
				
			}else{
				localdivisor /=2;
				// Increase the base for next time.
				divisorDoubleTimes /=2;
				
			}
			System.out.println(localdivident + "  " + quotient + "   " + localdivisor);
		}
		
		return quotient;
	}
}
