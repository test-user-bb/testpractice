import java.text.DecimalFormat;

//12:10 PM to 12:34 PM

public class DoubleToRational {

	public static void main(String[] args){
		//getRationalFromDoubel(0.125);
		getRationalFromDoubel(2.5);
	}
	public static String getRationalFromDoubel(double dvaue){
		DecimalFormat df = new DecimalFormat("#.##");
		long tenMultiply=1;
		System.out.println(String.format( "%.3f", dvaue ));
		
		while( dvaue*tenMultiply - (int) (dvaue*tenMultiply)  !=  0.0){
			tenMultiply*=10;
		}
		System.out.println(String.format( "%.3f", dvaue*tenMultiply ));
		
		//Now find out gcd for both.
		
		int num=(int)(dvaue*tenMultiply);
		int gcd = getGcd((int)tenMultiply,num );
		
		System.out.println(gcd);
		
		System.out.println("Rational is : "+(num/gcd)+"/"+tenMultiply/gcd);
		return null;
	}
	
	static int getGcd(int small,int big){
		
		if (big % small == 0 ) {
			return small;
		}else{
			return getGcd(big%small,small);
		}
	}
}
