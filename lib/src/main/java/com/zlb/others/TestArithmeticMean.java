package com.zlb.others;
import java.util.Arrays;
import java.util.Scanner;


public class TestArithmeticMean {
	public static void main(String[] args) {
		
		
		
		
//		Scanner mScanner = Scanner
		
		
		
		
		
		
			int createSum[] = new int[]{528,111,80,106,65,117,73,69,92,117};
			int resumeSum[] = new int[]{95,22,42,20,22,26,23,27,21,23};
			
			
			int totalCreate = 0;
			int totalResume = 0;
			
			for(int i=0;i<createSum.length;i++) {
				totalCreate += createSum[i];
				totalResume += resumeSum[i];
			}
			
			System.out.println("The createSum's total number is "+createSum.length+",They are :\n"+Arrays.toString(createSum)+" , their ArithmeticMean is "+totalCreate/createSum.length);
			System.out.println("\nThe resumeSum's total number is "+resumeSum.length+",They are :\n"+Arrays.toString(resumeSum)+" , their ArithmeticMean is "+totalResume/resumeSum.length);
	}
}
