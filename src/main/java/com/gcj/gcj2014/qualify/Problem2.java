package com.gcj.gcj2014.qualify;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Problem2 {
    private static final String TEST_DATA_DIR = "C:\\Users\\vikmalik\\Desktop\\test_data\\";
    private static final String DEFAULT_INPUT_FILE = "Problem2.in";
    private static final String DEFAULT_OUTPUT_FILE = "Problem2.out";
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            readInputFile(TEST_DATA_DIR + DEFAULT_INPUT_FILE);
            System.out.println("Success");
        } catch(IOException e) {
            System.out.println("Fail");
        }
    }

    public static void readInputFile(String fileName) throws IOException {
        File objFile = new File(fileName);

        BufferedReader in = new BufferedReader(new FileReader(objFile));
        PrintWriter pw = new PrintWriter(new FileWriter(TEST_DATA_DIR+DEFAULT_OUTPUT_FILE));
        in.readLine();

        int caseNo = 1;
        String line = in.readLine();
        while(line != null){
            pw.write(calculate(caseNo, line.split(" ")));
            caseNo++;
            line = in.readLine();
        }
        pw.flush();

    }

    public static String calculate(int caseNo, String input[]) {
        double C = Double.parseDouble(input[0]);
        double F = Double.parseDouble(input[1]);
        double X = Double.parseDouble(input[2]);

        double oldRate = 2;
        double newRate = 2 + F;
        double timeToFarm = C/oldRate;
        double timeToTarget = X/oldRate;
        double totalTime = 0;

        if(timeToTarget > timeToFarm) {
            while((X/oldRate) > (timeToFarm + (X/newRate))) {
                totalTime += timeToFarm;	
                oldRate = newRate;
                newRate += F;
                timeToFarm = C/oldRate;
            }
            timeToTarget = totalTime + X/oldRate;
        }
        return String.format("Case #"+caseNo+": %.7f", timeToTarget).concat("\n");
    }
}