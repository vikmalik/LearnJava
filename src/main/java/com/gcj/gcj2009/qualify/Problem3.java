package com.gcj.gcj2009.qualify;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Problem3 {

	private static final String TEST_DATA_DIR = "E:\\Seagate Backup\\VIMALIK2\\C\\workspace_gcj\\GCJ_2009\\test_data\\";
	private static final String DEFAULT_INPUT_FILE = "C-small-attempt0.in";
	
	public static void main(String[] args) {
		try{
			new Problem3().doMain(args);
		}catch(FileNotFoundException e){
			System.out.println("Input/output file not found.");
			e.printStackTrace();
		}catch (Exception e){
			System.out.println("Some unhandeled exception occured.");
			e.printStackTrace();			
		}
	}
	
	private void doMain(String[] args)throws Exception{
		String inputFileName = DEFAULT_INPUT_FILE;
		String outputFileName ;
		
		if(args != null && args.length > 0){
			inputFileName = args[0].trim();
		}

		if(inputFileName.indexOf("in") > -1){
			outputFileName = inputFileName.replace("in", "out");
		}else{
			outputFileName = inputFileName + ".out";
		}
		
		//get input and output files
		Scanner sc = new Scanner(new FileReader(TEST_DATA_DIR + inputFileName));
		PrintWriter pw = new PrintWriter(new FileWriter(TEST_DATA_DIR + outputFileName));
		
		//process input
		if(sc.hasNext()){
			StringBuilder ouput = new StringBuilder();
			String input;
			String queryString = "welcome to code jam";
			int[] multiplicity = new int[queryString.length()];
			
			int caseCnt = sc.nextInt();
			int counter = 0; 
			
			for (int caseNum=1; caseNum <= caseCnt; caseNum++) {
				input = sc.nextLine();
				
				getCount(input, queryString);
				
				System.out.println(input);
				counter = 0;
				/*for(int i=0 ; i < queryString.length(); i++){
					if(input.indexOf(queryString.charAt(i)) < 0){
						counter = 0;
						break;
					}else{
						do{
							
						}while();
					}
				}*/
				ouput.append("Case #").append(caseNum).append(": ").append(counter).append("\n");
			}
		}
		System.out.println("Terminated Successfully");
		pw.flush();
		pw.close();
		sc.close();
	}
	
	private int[] getCount(String input, String queryString){
		int[] counters = new int[queryString.length()];
		int counter = 0;
		int startIndex, endIndex;
		
		if((startIndex = input.indexOf(queryString.charAt(0))) > 0 &&
				(endIndex = input.lastIndexOf(queryString.charAt(queryString.length()-1))) > 0 &&
				endIndex > startIndex){
			input = input.substring(startIndex, endIndex+1);
			
			int index = 0;
			int len = queryString.length();
			for(int i = 0 ; i < len; i++){
				do{
					if(queryString.charAt(i) == input.charAt(index)){
						counters[i]++;
						index++;
					}else if((i+1) < len && queryString.charAt(i+1) == input.charAt(index)){
						break;
					}
				}while(index < input.length());
			}
		}
		
		return counters;
	}
}
