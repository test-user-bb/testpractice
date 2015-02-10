package ThreadsPractice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import java.util.List;

public class RepeatUsingSet {

		public static  void main(String[] args){
		Integer[] arr1=  {1,2,3,4,5,6,2,3};
		Set<Integer> rep = new HashSet<Integer>();
		List<Integer> lst =  Arrays.asList(arr1);

		for ( Integer a : lst){
			if(!rep.add(a)){
				System.out.println("Repeated element is: " +a );
			}
		}
	}
}
