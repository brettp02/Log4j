package nz.ac.wgtn.swen301.assignment2;

import nz.ac.wgtn.swen301.assignment2.MemoryAppender;
import nz.ac.wgtn.swen301.assignment2.MBeanUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Random;

public class LogRunner {

    public static void main(String[] args) {
        // New Instance of MemoryAppender called "LogRunnerAPpender"
        MemoryAppender memoryAppender = new MemoryAppender();
        memoryAppender.setAppenderName("LogRunnerAppender");

        // Register MBean
        MBeanUtil.registerMemoryAppenderMBean(memoryAppender);

        //Configuring logger
        Logger logger = Logger.getLogger(LogRunner.class);
        logger.addAppender(memoryAppender);

        // Array of Log Levels
        Level[] levels = {Level.TRACE, Level.DEBUG, Level.INFO, Level.WARN, Level.ERROR, Level.FATAL};

        // Sample Messages
        String[] messages = {
                "System starting up",
                "User login successful",
                "Data processing completed",
                "An unexpected error occurred",
                "Connection lost",
                "Operation timed out",
                "Memory usage is high",
                "Disk space is low",
                "File not found",
                "User logged out"
        };

        Random random = new Random();

        long endTime = System.currentTimeMillis() + 2 * 60 * 1000; // 2 minutes in millisecons

        while (System.currentTimeMillis() < endTime) {
            Level randomLevel = levels[random.nextInt(levels.length)];
            String randomMessage = messages[random.nextInt(messages.length)];

            logger.log(randomLevel, randomMessage);

            // wait one second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        memoryAppender.export("logrunner_logs.json");

        try {
            System.out.println("Logging completed press Enter to exit");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
