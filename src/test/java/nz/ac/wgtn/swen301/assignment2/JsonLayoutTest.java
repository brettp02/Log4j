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

        System.out.println(jsonObject.toString());
    }

}
