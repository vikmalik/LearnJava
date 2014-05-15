package com.gcj.gcj2012.qualify;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Problem1 {

	private static final String TEST_DATA_DIR = "C:\\Users\\vikmalik\\Desktop\\test_data\\";
	private static final String DEFAULT_INPUT_FILE = "Problem1.in";
	
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
			StringBuilder output = new StringBuilder();
			int numTestCases = sc.nextInt();
			
			for(int i=1; i<= numTestCases; i++){
				int numParticipants = sc.nextInt();
				int numpartGTZero = 0;
				
				int[] judgePoints = new int[numParticipants];
				int totatPoints = 0;
				
				boolean caseZero = false;
				for(int j=0; j< numParticipants; j++){
					judgePoints[j] = sc.nextInt();
					if(judgePoints[j] > 0 ){
						numpartGTZero++;
					}
					totatPoints += judgePoints[j];
				}
				double minPoints = totatPoints * 2 / numParticipants ;
				
				if(numpartGTZero == 1 && numParticipants > 2){
					minPoints  = totatPoints * 2 / (numParticipants - 1);
					caseZero = true;
				}
					
				
				
				double[] audiancepercent = new double[numParticipants];
				
				output.append("Case #").append(i).append(":");
				for(int j=0; j< numParticipants; j++){
					audiancepercent[j] = ((minPoints - judgePoints[j])/totatPoints ) * 100;	
					audiancepercent[j] *= 1000000;
					audiancepercent[j] = Math.round(audiancepercent[j]);
					audiancepercent[j] /= 1000000;
					
					if(caseZero && judgePoints[j] > 0){
						output.append(" 0.0");
					}else{
						output.append(" ").append(audiancepercent[j]);
					}
				}
				output.append("\n");
				
			}
			pw.write(output.toString());
		}
		
		System.out.println("Terminated Successfully");
		pw.flush();
		pw.close();
		sc.close();
	}
}
