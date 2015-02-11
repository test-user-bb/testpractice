package com.bawankar.niraj.javapractice;

import java.io.ObjectInputStream.GetField;

// Using DP, botttom up.
public class FibSeries {
	static int[] fibd = new int[100];
	
	public static  void main(String[] args){
		fibd[0]=0;
		fibd[1]=1;
		
		for(int i=2;i<100;++i){
			fibd[i]=fibd[i-1]+fibd[i-2];
		}
		
		System.out.println(fibd[6]+","+fibd[7]+","+fibd[8]);
		
	}
}
