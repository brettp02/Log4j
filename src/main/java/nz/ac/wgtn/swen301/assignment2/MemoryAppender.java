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
    public List<LoggingEvent> getLogEvents() {
        return Collections.unmodifiableList(logEvents);
    }



    @Override
    protected void append(LoggingEvent loggingEvent) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
