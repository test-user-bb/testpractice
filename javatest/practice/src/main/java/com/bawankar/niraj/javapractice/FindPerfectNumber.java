package com.bawankar.niraj.javapractice;

import java.util.ArrayList;
import java.util.List;

public class FindPerfectNumber {

	static boolean perfectNum(int num){
		if(num == 0 ) return true;
		
		if( num ==1 || num == 2 || num ==3 ) return true;
		
		int newNum=num;
		int devNum=2;
		List<Integer> lst = new ArrayList<Integer>();
		for(int i =0; i < newNum;++i){
			
			if(newNum < devNum) break;
			
			if( newNum % devNum == 0){
				int tmpNum = newNum/devNum; 
				lst.add(tmpNum);
				lst.add(devNum);
				newNum=tmpNum-1;
				devNum++;
				
			}else{
				devNum++;
			}
		}
		
		return false;
		
	}
	
	public static void main(String[] args){
	
	}
}
