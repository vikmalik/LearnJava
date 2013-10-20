package com.learnjava.java.general;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vikmalik
 */
public class PossibleWordsFinder {
    public static void main(String... args){
        new PossibleWordsFinder().doMain(args);
    }
    
    private static String queryString = "lensicemrbo";
    
    //private static String queryString = "kwolaclcl"
        
    private void doMain(String... args){
        FileWriter fw = null;
        try {
            Set<String> list = getPossibleWords(queryString);
            fw = new FileWriter(new File("output.txt"));
            for (String str : list) {
                //System.out.println(str);   
                fw.append(str).append("\n");
                fw.flush();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PossibleWordsFinder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(PossibleWordsFinder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    private Set<String> getPossibleWords(String str){
        Set<String> list = new HashSet<>();
        list.add(str);
        
        char[] chars;
        String tempString = str;
        for(int i = 0; i < str.length(); i++){
            chars = str.toCharArray();
            replaceIndexes(chars, 0, i);
            tempString = new String(chars);
            //System.out.println("process : " + tempString);
            list.add(tempString);
            Set<String> tempSet = getPossibleWords(tempString.substring(1,str.length()));
            for (String tempStr : tempSet) {
                list.add(""+ chars[0] + tempStr);
            }

//            for(int j = 1; j < str.length(); j++){
//                for(int k = 2; k < str.length(); k++){
//                    chars = tempString.toCharArray();
//                    replaceIndexes(chars, j, k);
//                    list.add(new String(chars));
//               }
//            }
        }
        
        return list;
    }
    
    private void replaceIndexes(char[] chars, int fromIndex, int toIndex){
        //System.out.println("length = "+chars.length+"fromIndex = " + fromIndex + ", toIndex = "+ toIndex);
        char tempChar = chars[fromIndex];
        chars[fromIndex] = chars[toIndex];
        chars[toIndex] = tempChar;
    }
    
}