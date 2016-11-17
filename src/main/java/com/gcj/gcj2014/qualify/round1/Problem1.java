package com.gcj.gcj2014.qualify.round1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Problem1 {

    private static final String TEST_DATA_DIR = "C:\\Users\\vikmalik\\Desktop\\test-data\\";
    private static final String DEFAULT_INPUT_FILE = "Problem1.in";

    public static void main(String[] args) {
        try {
            new Problem1().doMain(args);
        } catch (FileNotFoundException e) {
            System.out.println("Input/output file not found.");
        } catch (Exception e) {
            System.out.println("Some unhandeled exception occured.");
        }
    }

    private void doMain(String[] args) throws Exception {
        String inputFileName = DEFAULT_INPUT_FILE;
        String outputFileName;

        if (args != null && args.length > 0) {
            inputFileName = args[0].trim();
        }

        if (inputFileName.contains("in")) {
            outputFileName = inputFileName.replace("in", "out");
        } else {
            outputFileName = inputFileName + ".out";
        }

        try ( //get input and output files
                Scanner sc = new Scanner(new FileReader(TEST_DATA_DIR + inputFileName));
                PrintWriter pw = new PrintWriter(new FileWriter(TEST_DATA_DIR + outputFileName))) {

            int numberOfCases = sc.nextInt();

            StringBuilder output = new StringBuilder();

            for (int i = 0; i < numberOfCases; i++) {
                int numberOfStrings = sc.nextInt();
                String[] strings = new String[numberOfStrings];

                int minLenght = Integer.MAX_VALUE;
                int index = 0;
                boolean isAlreadyEqual = true;
                
                //read input
                for (int j = 0; j < numberOfStrings; j++) {
                    strings[j] = sc.next();
                    
                    if (minLenght > strings[j].length()) {
                        minLenght = strings[j].length();
                        index = j;
                    }
                    
                    if(isAlreadyEqual && j >= 1 && ! strings[j].equals(strings[j-1]) ){
                        isAlreadyEqual = false;
                    }
                }

                System.out.println("target string " + strings[index]);

                int numberOfMovies = 0;
                if( ! isAlreadyEqual){
                    String[] stringsWithoutRepetition = new String[numberOfStrings];
                    
                    for (int j = 0; j < numberOfStrings; j++) {
                        StringBuilder sb = new StringBuilder().append(strings[j].charAt(0));
                        
                        for (int k = 1; k < strings[j].length(); k++) {
                            if(strings[j].charAt(k) != strings[j].charAt(k-1)){
                                sb.append(strings[j].charAt(k));
                            }
                        }
                        stringsWithoutRepetition[j] = sb.toString();
                        
                        if(j >= 1 && ! stringsWithoutRepetition[j].equals(stringsWithoutRepetition[j-1]) ){
                            numberOfMovies = -1;
                            break;
                        }
                    }

                }
                output.append("Case #").append(i).append(": ");
                if(numberOfMovies >= 0){
                    output.append(numberOfMovies);
                }else{
                    output.append("Fegla Won");
                }
                output.append("\n");

            }
            pw.write(output.toString());

            System.out.println("Terminated Successfully");
            pw.flush();
        }
    }
}
