package com.gcj.gcj2009.qualify;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Problem1 {

	private static final String TEST_DATA_DIR = "E:\\Seagate Backup\\VIMALIK2\\C\\workspace_gcj\\GCJ_2009\\test_data\\";
	private static final String DEFAULT_INPUT_FILE = "A-small-attempt0.in";
	
	public static void main(String[] args) {
		try{
			new Problem1().doMain(args);
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
			int l = sc.nextInt();
			int d = sc.nextInt();
			int n = sc.nextInt();
		
			String[] words = new String[d];
			String[] patterns = new String[n];
			
			//read words
			for (int i = 0; i < d; i++) {
				words[i] = sc.next();			
			}
			
			//read patterns
			for (int i = 0; i < n; i++) {
				patterns[i] = sc.next();			
			}
			
			String pattern;
			char[] characters;
			StringBuilder patternKeys = new StringBuilder();
			String[] tokens;
			int index;
			int counter;
			boolean matchFound;
			
			
			//check pattern
			for (int i = 0; i < n; i++) {
				
				pattern = patterns[i];
				tokens = new String[l];
				index = 0;
				
				characters = pattern.toCharArray();
				
				for (int j = 0; j < characters.length; j++) {
					if(characters[j] == '('){
						patternKeys = new StringBuilder();
						do{
							j++;
							patternKeys.append(characters[j]);	
						}while(characters[j] != ')');
						tokens[index++] = patternKeys.toString();
						continue;
					}
					tokens[index++] = "" + characters[j];
				}
				
				counter = 0;
				for (int j = 0; j < d; j++) {
					characters = words[j].toCharArray();
					matchFound = true;
					
					for (int k = 0; k < characters.length; k++) {
						if(!tokens[k].contains(""+characters[k])){
							matchFound = false;
							break;
						}
					}
					if(matchFound){
						counter++;
					}					
				}
				ouput.append("Case #").append(i+1).append(": ").append(counter).append("\n");
			}
			pw.write(ouput.toString());
		}
		
		System.out.println("Terminated Successfully");
		pw.flush();
		pw.close();
		sc.close();
	}
}
