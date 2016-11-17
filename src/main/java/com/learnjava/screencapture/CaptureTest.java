package com.learnjava.screencapture;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
 
public class CaptureTest {
       
        // how many fps do you want to record at ...
        final static int FPS = 25;
       
        // how many frames into the past?
        final static int HISTORY_SIZE = 50;
       
        // destination directory...
        final static File BASEDIR = new File( "capture" );
        
       
        // record a small rectangle around the mouse, use RECORD_W=-1 to record the entire screen
        final static int RECORD_W = 500;
        final static int RECORD_H = 500;
       
       
        public static void main(String[] args) throws AWTException, InterruptedException {
                // Make sure directory exists ...
                BASEDIR.mkdirs();
                System.out.println("File Path = " + BASEDIR.getAbsolutePath());
               
                // create a frame to display current fps
                JFrame frame = new JFrame( "Screen Recorder" );
                frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                JLabel fpsLabel = new JLabel();
                fpsLabel.setAlignmentX( 0.5f );
                fpsLabel.setAlignmentY( 0.5f );
                frame.getContentPane().add( fpsLabel, BorderLayout.CENTER );
                frame.setSize( 300, 200 );
                frame.setVisible( true );
               
                // we store our images here ...
                LinkedBlockingQueue<Img> history = new LinkedBlockingQueue<>();
               
                // do the frame capturing party  
                float averageDuration = 0;
                long frameCount = 0;
                Writer writer = new Writer();
                Robot robot = new Robot();
                Rectangle screenRect = GraphicsEnvironment
                                .getLocalGraphicsEnvironment()
                                .getDefaultScreenDevice()
                                .getDefaultConfiguration()
                                .getBounds();
               
                while( true ){
                        // keep a constant # of items in the history
                        if( history.size() > HISTORY_SIZE ){
                                Img old = history.poll();
                                old.image.flush(); // free memory
                        }
                       
                        // record next frame
                        long start = System.currentTimeMillis();
                        Rectangle userRect = new Rectangle( screenRect );
                        if( RECORD_W > 0 ){
                                Point mouse = MouseInfo.getPointerInfo().getLocation();
                                Rectangle mouseRect = new Rectangle( mouse.x - RECORD_W/2, mouse.y - RECORD_H/2, RECORD_W, RECORD_H );
                                Rectangle.intersect( screenRect, mouseRect, userRect );
                        }
                       
                        BufferedImage img = robot.createScreenCapture( userRect );
                        history.add( new Img( start, img ) );
                       
                       
                        // check for some condition (e.g. find green spot or whatnot)
                        if( Math.random() > 0.99 ){
                                // YES!
                                System.out.println( new Date().toString() + " - Adding last " +  HISTORY_SIZE + "frames..." );
                                writer.addAll( history );
                                history.clear();
                        }
                       
                       
                        // compute average duration + show some stats sometimes
                        frameCount ++;
                        Thread.sleep( Math.max( 1, 1000/FPS - System.currentTimeMillis() + start ) );
                        averageDuration += ( System.currentTimeMillis() - start - averageDuration )/10f;
                       
                        if( frameCount % FPS == 0 ){
                                System.out.println( "Recording at " + ((int)(10000/averageDuration))/10f + " fps" );
                                fpsLabel.setText( "Recording at " + ((int)(10000/averageDuration))/10f + " fps" );
                        }
                }
        }
       
       
        static class Img{
                long timestamp;
                BufferedImage image;
               
                public Img( long timestamp, BufferedImage image ){
                        this.timestamp = timestamp;
                        this.image = image;
                }
        }
       
        static class Writer extends Thread{
                LinkedBlockingQueue<Img> writeQueue = new LinkedBlockingQueue<>();
                SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd-HH-mm-ss-SSS" );
               
                public Writer(){
                        start();
                }
               
                public void run(){
                        while( !isInterrupted() ){
                                if( writeQueue.size() > 0 ){
                                        Img img = writeQueue.poll();
                                        long start = System.currentTimeMillis();
                                        try {
                                                ImageIO.write(
                                                        img.image,
                                                        "jpg",
                                                        new File( BASEDIR, format.format( new Date( img.timestamp ) ) + ".jpg" )
                                                );
                                        } catch (IOException e) {
                                                e.printStackTrace();
                                        }
                                       
                                        img.image.flush();
                                       
                                        System.out.println(
                                                "wrote image in " + ( System.currentTimeMillis() - start ) + " ms; " +
                                                writeQueue.size() + " items left in write queue" );
                                }
                               
                                // some pause in between
                                try {
                                        Thread.sleep( 10 );
                                } catch (InterruptedException e) {
                                        System.out.println( "Was interrupted, bye!" );
                                }
                        }
                }
               
                public void addAll( Collection<Img> images ){
                        writeQueue.addAll( images );
                }
        }
}

