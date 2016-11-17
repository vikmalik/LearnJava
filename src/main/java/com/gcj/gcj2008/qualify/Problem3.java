package com.gcj.gcj2008.qualify;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Problem3 {
	
	private void doMain() throws Exception{
		Scanner sc = new Scanner(new FileReader("C:/Google Code JAM/Round 1/problem a/A-small-attempt0.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("C:/Google Code JAM/Round 1/problem A/A-small_0.out"));
		int caseCnt = sc.nextInt();
		for (int caseNum=1; caseNum <= caseCnt; caseNum++) {
			pw.print("Case #" + caseNum + ": ");
			
			long maxPut = sc.nextLong();
			long numKeys = sc.nextLong();
			int numAlpha = sc.nextInt();
			
			int[] freq =new int[numAlpha];
			
			for (int i = 0; i < freq.length; i++) {
				freq[i] = sc.nextInt();
			}
			
			Arrays.sort(freq);
			
			double sum = 0;
			
			int i = numAlpha;
			for (int j = 1; j <= maxPut && i >0; j++) {
				for (int k = 0; k < numKeys && i >0 ; k++) {
					i--;
					sum += j * freq[i];
					System.out.println(i);
				}
			}	
			
			if(i != 0){
				pw.println("Impossible");
			}else{
				pw.println((int)sum);
			}
		}
		System.out.println("Terminated Successfully");
		pw.flush();
		pw.close();
		sc.close();
	}
	public static void main(String[] args) throws Exception{
		try{
			(new Problem3()).doMain();
		}catch(Exception e){
			e.printStackTrace();
		}
			

	}
	
}
