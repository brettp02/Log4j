package nz.ac.wgtn.swen301.assignment2;

public interface MemoryAppenderMBean {
    // Fields
    String[] getLogs();

    long getLogCount();

    long getDiscardedLogCount();

    // MEthod to export logs
    void export (String fileName);

}