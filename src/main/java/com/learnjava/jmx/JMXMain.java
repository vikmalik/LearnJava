package com.learnjava.jmx;

import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 *
 * @author vikmalik
 */
public class JMXMain {

    public static void main(String[] args) throws Exception {
        // Get the Platform MBean Server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

	// Construct the ObjectName for the Hello MBean we will register
	ObjectName mbeanName = new ObjectName(""
                + ":type=Hello");

	// Create the Hello World MBean
	Hello mbean = new Hello();

	// Register the Hello World MBean
	mbs.registerMBean(mbean, mbeanName);

        // Construct the ObjectName for the QueueSampler MXBean we will register
        ObjectName mxbeanName = new ObjectName("com.learnjava.jmx:type=QueueSampler");

        // Create the Queue Sampler MXBean
        Queue<String> queue = new ArrayBlockingQueue<>(10);
        queue.add("Request-1");
        queue.add("Request-2");
        queue.add("Request-3");
        QueueSampler mxbean = new QueueSampler(queue);

        // Register the Queue Sampler MXBean
        mbs.registerMBean(mxbean, mxbeanName);

        // Wait forever
        System.out.println("Waiting for incoming requests...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
