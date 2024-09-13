package nz.ac.wgtn.swen301.assignment2;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryAppenderTest {

    private MemoryAppender memoryAppender;
    private Logger testLogger;


    @BeforeEach
    public void setUp() {
        memoryAppender = new MemoryAppender();
        testLogger = Logger.getLogger("TestLogger");
        testLogger.addAppender(memoryAppender);
    }


    @Test
    public void testAppenderStoresLogs() {
        // Test logging a single message
        testLogger.log(Level.INFO, "Test message");
        List<LoggingEvent> logs = memoryAppender.getCurrentLogs();

        assertEquals(1, logs.size());
        assertEquals("Test message", logs.get(0).getMessage());
        assertEquals(Level.INFO, logs.get(0).getLevel());
    }


    @Test
    public void testAppenderMaxSize() {
        memoryAppender.setMaxSize(5); // Set max size to 5 logs

        for (int i = 1; i <= 7; i++) {
            testLogger.log(Level.INFO, "Log " + i);
        }

        List<LoggingEvent> logs = memoryAppender.getCurrentLogs();

        assertEquals(5, logs.size());
        assertEquals("Log 3", logs.get(0).getMessage()); // Oldest log should be "Log 3"
        assertEquals(2, memoryAppender.getDiscardedLogCount()); // 2 logs discarded
    }


    @Test
    public void testAppenderDifferentLogLevels() {
        // Test different log levels
        testLogger.log(Level.INFO, "Info log");
        testLogger.log(Level.DEBUG, "Debug log");
        testLogger.log(Level.ERROR, "Error log");

        List<LoggingEvent> logs = memoryAppender.getCurrentLogs();

        assertEquals(3, logs.size());
        assertEquals(Level.INFO, logs.get(0).getLevel());
        assertEquals(Level.DEBUG, logs.get(1).getLevel());
        assertEquals(Level.ERROR, logs.get(2).getLevel());
    }


    @Test
    public void testAppenderName() {
        memoryAppender.setAppenderName("MyMemoryAppender");
        assertEquals("MyMemoryAppender", memoryAppender.getAppenderName());
    }


    @Test
    public void testAppendWithDefaultMaxSize() {
        for (int i = 1; i <= 1000; i++) {
            LoggingEvent event = new LoggingEvent(
                    "logger",
                    testLogger,
                    Level.INFO,
                    "Log " + i,
                    null
            );
            memoryAppender.append(event);
        }

        List<LoggingEvent> logs = memoryAppender.getCurrentLogs();

        assertEquals(1000, logs.size());
        assertEquals("Log 1", logs.get(0).getMessage());
        assertEquals("Log 1000", logs.get(999).getMessage());
        assertEquals(0, memoryAppender.getDiscardedLogCount());
    }


    @Test
    public void testAppenderExportToFile() {
        testLogger.log(Level.INFO, "Log for export");
        testLogger.log(Level.WARN, "Warning log for export");

        String fileName = "exported_logs.json";
        memoryAppender.export(fileName);

        // Check if the file exists
        java.io.File file = new java.io.File(fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        // Comment this out to see the Logs
        assertTrue(file.delete());
    }

    @Test
    public void testAppendWithDifferentLogLevels() {
        LoggingEvent event1 = new LoggingEvent(
                "logger",
                testLogger,
                Level.INFO,
                "Info log",
                null
        );
        LoggingEvent event2 = new LoggingEvent(
                "logger",
                testLogger,
                Level.DEBUG,
                "Debug log",
                null
        );
        LoggingEvent event3 = new LoggingEvent(
                "logger",
                testLogger,
                Level.ERROR,
                "Error log",
                null
        );

        memoryAppender.append(event1);
        memoryAppender.append(event2);
        memoryAppender.append(event3);

        List<LoggingEvent> logs = memoryAppender.getCurrentLogs();

        assertEquals(3, logs.size());
        assertEquals(Level.INFO, logs.get(0).getLevel());
        assertEquals(Level.DEBUG, logs.get(1).getLevel());
        assertEquals(Level.ERROR, logs.get(2).getLevel());
    }


    @Test
    public void testAppenderClose() {
        memoryAppender.close();
        assertTrue(true);
    }
}
