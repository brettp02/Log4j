package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class MemoryAppender extends AppenderSkeleton {

    // Declaring fields, all except name are initialized
    private String name;
    private long maxSize = 1000;
    private List<LoggingEvent> logEvents = new ArrayList<>();
    private long discardedLogCount = 0;


    /**
     * Public zero-argument constructor
     */
    public MemoryAppender() {
        super();
    }


    /**
     * getMaxSize() returns maxSize field
     *
     * @return
     */
    public long getMaxSize() {
        return this.maxSize;
    }

    /**
     * setMaxSize() sets this maxSize to input
     *
     * @param maxSize
     */
    public void setMaxSize(long maxSize){
        this.maxSize = maxSize;
    }

    /**
     * returns name (can't use getName() as can't override superclass getName() method)
     *
     * @return
     */
    public String getAppenderName() {
        return this.name;
    }


    /**
     * sets name for appender instance
     *
     * @param name
     */
    public void setAppenderName(String name) {
        this.name = name;
    }

    /**
     * Returns discaredLogCount field
     *
     * @return
     */
    public long getDiscardedLogCount() {
        return this.discardedLogCount;
    }

    /**
     * Returns immutable List of the current log events
     *
     * @return
     */
    public List<LoggingEvent> getCurrentLogs() {
        return Collections.unmodifiableList(this.logEvents);
    }


    /**
     * Appends a new LoggingEvent instance to the list of logEvents. If list is at maximum capacity, the oldest log is removed and discardedLogCount is increased
     *
     * @param loggingEvent
     */
    @Override
    protected void append(LoggingEvent loggingEvent) {
        if (logEvents.size() >= maxSize) {
            logEvents.remove(0); // oldest log
            discardedLogCount++;       // increment discardedLogCount by 1
        }

        logEvents.add(loggingEvent);  // add new loggingEvent to logEvents List
    }

    /**
     * Exports current log events to a JSON file. Logs are formatted as JSON objects using JsonLayout
     * and saved into a file which is found from the fileName.
     * File is saved relative to the applications working directory
     *
     * @param fileName
     */
    public void export(String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fw = new FileWriter(fileName)) {
            List<JsonObject> jsonLogEvents = new ArrayList<>();
            JsonLayout jsonLayout = new JsonLayout();

            for(LoggingEvent event: getCurrentLogs()) {
                JsonObject jsonObject = jsonLayout.makeJsonObject(event);
                jsonLogEvents.add(jsonObject);
            }
            gson.toJson(jsonLogEvents, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
