package com.gcj.gcj2014.qualify;

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
		}catch (Exception e){
			System.out.println("Some unhandeled exception occured.");
		}
	}
	
	private void doMain(String[] args)throws Exception{
		String inputFileName = DEFAULT_INPUT_FILE;
		String outputFileName ;
		
		if(args != null && args.length > 0){
			inputFileName = args[0].trim();
		}

		if(inputFileName.contains("in")){
			outputFileName = inputFileName.replace("in", "out");
		}else{
			outputFileName = inputFileName + ".out";
		}
		
            try ( //get input and output files
                    Scanner sc = new Scanner(new FileReader(TEST_DATA_DIR + inputFileName)); 
                    PrintWriter pw = new PrintWriter(new FileWriter(TEST_DATA_DIR + outputFileName))) {
                
                //process input
                if(sc.hasNext()){
                    StringBuilder output = new StringBuilder();
                    int numTestCases = sc.nextInt();
                    
                    for(int i=1; i<= numTestCases; i++){
                        
                        int firstAnswer = sc.nextInt();
                        
                        int[][][] cardArragement = new int[2][4][4];
                        
                        for(int indexX = 0; indexX < 4; indexX++){
                            for(int indexY = 0; indexY < 4; indexY++){
                                cardArragement[0][indexX][indexY] = sc.nextInt();
                            }
                        }
                        
                        int secondAnswer = sc.nextInt();
                        
                        for(int indexX = 0; indexX < 4; indexX++){
                            for(int indexY = 0; indexY < 4; indexY++){
                                cardArragement[1][indexX][indexY] = sc.nextInt();
                            }
                        }
                        
                        int matchingCardCount = 0;
                        int matchingCard = -1;
                        
                        for(int indexY1 = 0; indexY1 < 4; indexY1++){
                            for(int indexY2 = 0; indexY2 < 4; indexY2++){
                                if(cardArragement[0][firstAnswer-1][indexY1] == cardArragement[1][secondAnswer-1][indexY2]){
                                    matchingCardCount++;
                                    matchingCard = cardArragement[0][firstAnswer-1][indexY1];
                                }
                            }
                        }
                        
                        String result;
                        
                        switch(matchingCardCount){
                            case 0: result = "Volunteer cheated!"; break;
                            case 1: result = "" + matchingCard; break;
                            default: result = "Bad magician!"; break;
                        }
                        
                        output.append("Case #").append(i).append(": ").append(result).append("\n");
                    }
                    pw.write(output.toString());
                }
                
                System.out.println("Terminated Successfully");
                pw.flush();
            }
	}
}
