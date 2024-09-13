package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import java.util.Date;
import java.util.TimeZone;
import java.text.*;
import com.google.gson.JsonObject;

/**
 *  This class creates the layout in order to convert log events (i.e., instances of org.apache.log4j.spi.LoggingEvent)
 *  to JSON strings. I.e., the strings produced are valid JSON objects
 *
 * @studentid = 300635306
 */
public class JsonLayout extends Layout {

    /**
     * Creates a new Json Object based on the Logging Event with the (message, name, thread, level, and timestamp) features:
     *
     * @param loggingEvent
     * @return
     */
    public JsonObject makeJsonObject(LoggingEvent loggingEvent) {
        JsonObject logDetails = new JsonObject();

        // Safely handle null values for each field
        String loggerName = loggingEvent.getLoggerName() != null ? loggingEvent.getLoggerName() : "null";
        String message = loggingEvent.getMessage() != null ? loggingEvent.getMessage().toString() : "null";
        String threadName = loggingEvent.getThreadName() != null ? loggingEvent.getThreadName() : "null";
        String level = loggingEvent.getLevel() != null ? loggingEvent.getLevel().toString() : "null";
        String timestamp = formatTime(loggingEvent.getTimeStamp());

        // Add properties to the JSON object
        logDetails.addProperty("name", loggerName);
        logDetails.addProperty("message", message);
        logDetails.addProperty("thread", threadName);
        logDetails.addProperty("level", level);
        logDetails.addProperty("timestamp", timestamp);

        return logDetails;
    }

    /**
     * Used for formatting time when using makeJsonObject(x) into daty-month-year format for the New Zealand timezone (Changed to UTC for tests)
     *
     * @param time
     * @returns
     */
    public String formatTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));  // Changed to UTC for tests can use Pacific/Auckland for NZT
        return sdf.format(new Date(time));
    }

    /**
     * "Pretty" prints JsonObject using GsonBuilder
     *
     * @param jsonObject
     * @return
     */
    public String printJson(JsonObject jsonObject) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }

    @Override
    public String format(LoggingEvent loggingEvent) {
        return makeJsonObject(loggingEvent).toString();
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    @Override
    public void activateOptions() {

    }
}