package com.gcj.gcj2009.qualify;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Problem2 {

	private static final String TEST_DATA_DIR = "E:\\Seagate Backup\\VIMALIK2\\C\\workspace_gcj\\GCJ_2009\\test_data\\";
	private static final String DEFAULT_INPUT_FILE = "B-small-attempt0.in";
	
	public static void main(String[] args) {
		try{
			new Problem2().doMain(args);
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
			
		int caseCnt = sc.nextInt();
			for (int caseNum=1; caseNum <= caseCnt; caseNum++) {
				
			}
		}
		System.out.println("Terminated Successfully");
		pw.flush();
		pw.close();
		sc.close();
	}
}
