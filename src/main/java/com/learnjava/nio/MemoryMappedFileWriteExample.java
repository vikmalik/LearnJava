/*
 * http://howtodoinjava.com/java-7/nio/java-nio-2-0-memory-mapped-files-mappedbytebuffer-tutorial/
 */
package com.learnjava.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
 
public class MemoryMappedFileWriteExample {
    private static String bigExcelFile = "src//main//resources//com//learnjava//nio//bigFile.txt";
 
    public static void main(String[] args) throws Exception {
        // Create file object
        File file = new File(bigExcelFile);
        
        System.out.println("Writing to file: " + file.getAbsolutePath());
         
        //Delete the file; we will create a new file
        file.delete();
 
        // Get file channel in readonly mode
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
 
        // Get direct byte buffer access using channel.map() operation
        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 4096 * 8 * 8);
 
        //Write the content using put methods
        buffer.put("howtodoinjava.com".getBytes());
    }
}