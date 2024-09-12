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

        logDetails.addProperty("message", loggingEvent.getMessage().toString());
        logDetails.addProperty("name", loggingEvent.getLoggerName());
        logDetails.addProperty("thread", loggingEvent.getThreadName());
        logDetails.addProperty("level", loggingEvent.getLevel().toString());
        logDetails.addProperty("timestamp", formatTime(loggingEvent.getTimeStamp()));

        return logDetails;
    }

    /**
     * Used for formatting time when using makeJsonObject(x) into daty-month-year format for the New Zealand timezone
     *
     * @param time
     * @returns
     */
    public String formatTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));  // can change to UTC for universal time
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