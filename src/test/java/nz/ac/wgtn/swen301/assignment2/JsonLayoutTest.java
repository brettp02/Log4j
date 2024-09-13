package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonLayoutTest {

    @Test
    public void testFormat() {
        // Set up a dummy LoggingEvent
        Logger logger = Logger.getLogger("TestLogger");
        String testMessage = "Test log message";
        String testThread = "TestThread";
        long testTimeStamp = System.currentTimeMillis();
        LoggingEvent loggingEvent = new LoggingEvent(
                "TestLogger", logger, testTimeStamp, Level.INFO, testMessage, testThread, null, null, null, null);

        // Create JsonLayout and format the event
        JsonLayout jsonLayout = new JsonLayout();
        String json = jsonLayout.format(loggingEvent);

        // Parse the resulting JSON
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        // Assert that each field in the JSON matches the LoggingEvent
        assertEquals(testMessage, jsonObject.get("message").getAsString());
        assertEquals("TestLogger", jsonObject.get("name").getAsString());
        assertEquals(testThread, jsonObject.get("thread").getAsString());
        assertEquals("INFO", jsonObject.get("level").getAsString());

        // Verify the timestamp formatting (only date, no time)
        String formattedTimeStamp = jsonLayout.formatTime(testTimeStamp);
        assertEquals(formattedTimeStamp, jsonObject.get("timestamp").getAsString());

        System.out.println("Test Format:\n"+jsonLayout.printJson(jsonObject) + "\n");
    }

    @Test
    public void testMakeJsonObject() {
        Logger logger = Logger.getLogger("TestLogger");
        String testMessage = "Test log message for makeJsonObject";
        String testThread = "TestThread";
        long testTimeStamp = System.currentTimeMillis();
        LoggingEvent loggingEvent = new LoggingEvent(
                "TestLogger", logger, testTimeStamp, Level.DEBUG, testMessage, testThread, null, null, null, null);

        JsonLayout jsonLayout = new JsonLayout();
        JsonObject jsonObject = jsonLayout.makeJsonObject(loggingEvent);

        assertEquals(testMessage, jsonObject.get("message").getAsString());
        assertEquals("TestLogger", jsonObject.get("name").getAsString());
        assertEquals(testThread, jsonObject.get("thread").getAsString());
        assertEquals("DEBUG", jsonObject.get("level").getAsString());

        String formattedTimeStamp = jsonLayout.formatTime(testTimeStamp);
        assertEquals(formattedTimeStamp, jsonObject.get("timestamp").getAsString());

        System.out.println("Test Make Json Object:\n" + jsonLayout.printJson(jsonObject) + "\n");
    }


    @Test
    public void testErrorLevel() {
        Logger logger = Logger.getLogger("TestLogger");
        LoggingEvent loggingEvent = new LoggingEvent(
                "TestLogger", logger, System.currentTimeMillis(), Level.ERROR, "Error message", "TestThread", null, null, null, null);
        JsonLayout jsonLayout = new JsonLayout();
        JsonObject jsonObject = JsonParser.parseString(jsonLayout.format(loggingEvent)).getAsJsonObject();
        assertEquals("ERROR", jsonObject.get("level").getAsString());

        System.out.println("Test Error Level:\n"+jsonLayout.printJson(jsonObject) + "\n");
    }

    @Test
    public void testNullMessage() {
        Logger logger = Logger.getLogger("TestLogger");
        LoggingEvent loggingEvent = new LoggingEvent(
                "TestLogger", logger, System.currentTimeMillis(), Level.INFO, null, "TestThread", null, null, null, null);
        JsonLayout jsonLayout = new JsonLayout();
        JsonObject jsonObject = JsonParser.parseString(jsonLayout.format(loggingEvent)).getAsJsonObject();
        assertEquals("null", jsonObject.get("message").getAsString());

        System.out.println("Test Null Message:\n" + jsonLayout.printJson(jsonObject) + "\n");
    }

    @Test
    public void testPrintJson() {
        JsonLayout jsonLayout = new JsonLayout();
        Logger logger = Logger.getLogger("TestLogger");
        LoggingEvent loggingEvent = new LoggingEvent(
                "TestLogger", logger, System.currentTimeMillis(), Level.INFO, "Test message", "TestThread", null, null, null, null);
        JsonObject jsonObject = jsonLayout.makeJsonObject(loggingEvent);
        String prettyJson = jsonLayout.printJson(jsonObject);
        assertNotNull(prettyJson);
        assertTrue(prettyJson.contains("Test message"));

        System.out.println("Test Pretty Print/printJson()\n" + prettyJson + "\n");
    }

    @Test
    public void testFormatTime() {
        JsonLayout jsonLayout = new JsonLayout();

        // Test a specific timestamp
        long testTimeStamp = 0L;
        String formattedTimeStamp = jsonLayout.formatTime(testTimeStamp);
        assertEquals("01-01-1970T00:00:00Z", formattedTimeStamp);
    }

    @Test
    public void testEmptyThreadName() {
        Logger logger = Logger.getLogger("TestLogger");
        LoggingEvent loggingEvent = new LoggingEvent(
                "TestLogger", logger, System.currentTimeMillis(), Level.INFO, "Test message", "", null, null, null, null);
        JsonLayout jsonLayout = new JsonLayout();
        JsonObject jsonObject = JsonParser.parseString(jsonLayout.format(loggingEvent)).getAsJsonObject();
        assertEquals("", jsonObject.get("thread").getAsString());

        System.out.println("Test Empty Thread Name:\n" + jsonLayout.printJson(jsonObject) + "\n");
    }

    @Test
    public void testDebugLevel() {
        Logger logger = Logger.getLogger("TestLogger");
        LoggingEvent loggingEvent = new LoggingEvent(
                "TestLogger", logger, System.currentTimeMillis(), Level.DEBUG, "Debug message", "TestThread", null, null, null, null);
        JsonLayout jsonLayout = new JsonLayout();
        JsonObject jsonObject = JsonParser.parseString(jsonLayout.format(loggingEvent)).getAsJsonObject();
        assertEquals("DEBUG", jsonObject.get("level").getAsString());

        System.out.println("Test Debug Level:\n" + jsonLayout.printJson(jsonObject) + "\n");
    }

}
