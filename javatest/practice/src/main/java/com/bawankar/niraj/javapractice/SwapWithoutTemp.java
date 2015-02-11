package com.bawankar.niraj.javapractice;

public class SwapWithoutTemp {

	public static void main(String[] args){
		int num1=100,num2=2000;
		num1=num1+num2;
		num2=num1-num2;
		num1=num1-num2;
		System.out.println("The num1 "+num1 +" Num2 "+num2);
	}
}
