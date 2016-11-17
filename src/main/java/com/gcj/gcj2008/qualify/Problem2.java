package com.gcj.gcj2008.qualify;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Problem2 {
public static void main(String[] args) {
		
		int i, numCase, j, k, l;
		Scanner scanner = null;
		BufferedWriter writer = null;
		
		int n, A, B, C, D, x0, y0, M ;
		double cord[][] , X, Y, count;

		
		try {
			scanner = new Scanner(new File("C:/Users/vikas/Desktop/Google Code JAM/Round 1/problem 1/A-small-attempt3.in"));
			writer = new BufferedWriter(new FileWriter(new File("C:/Users/vikas/Desktop/Google Code JAM/Round 1/problem 1/A-small.out")));
			
			numCase = scanner.nextInt();
			
			for (i = 1; i <= numCase; i++) {
				n = Integer.valueOf(scanner.next()).intValue();
				A = Integer.valueOf(scanner.next()).intValue();
				B = Integer.valueOf(scanner.next()).intValue();
				C = Integer.valueOf(scanner.next()).intValue();
				D = Integer.valueOf(scanner.next()).intValue();
				x0 = Integer.valueOf(scanner.next()).intValue();
				y0 = Integer.valueOf(scanner.next()).intValue();
				M = Integer.valueOf(scanner.next()).intValue();
				
				X = x0;
				Y = y0;
				
				cord = new double[n][2];
				cord[0][0]= x0;
				cord[0][1] = y0;
				
				for(j =1 ; j < n; j++ ){
					cord[j][0]= (A * X + B) % M;
					cord[j][1] = (C * Y + D) % M;
					X = cord[j][0];
					Y = cord[j][1];
				}
				count = 0;
				for(j =0; j < n - 2; j++){
					for(k = j+1; k < n-1; k++){
						for(l = k+1; l < n; l++){
							if((cord[j][0] + cord[k][0] + cord[l][0])% 3 == 0 &&
								(cord[j][1] + cord[k][1] + cord[l][1])% 3 == 0){
								count ++;
							}
						}
					}
					
				}				
				writer.write("Case #" + i + ": "+ (int)count + "\n");				
			}
			System.out.println("Terminated Successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			scanner.close();
		}
	}


}
