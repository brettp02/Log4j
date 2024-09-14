package nz.ac.wgtn.swen301.assignment2;

import javax.management.*;
import java.lang.management.ManagementFactory;
public class MBeanUtil {
    public static void registerMemoryAppenderMBean(MemoryAppender memoryAppender) {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            String appenderName = memoryAppender.getAppenderName();

            if(appenderName == null || appenderName.isEmpty()) {
                throw new IllegalArgumentException("Appender name must be set before registering MBean.");
            }

            ObjectName mbeanName = new ObjectName(
                    "nz.ac.wgtn.swen301.assignment2:type=MemoryAppender,name=" + ObjectName.quote(appenderName)
            );

            mbs.registerMBean(memoryAppender, mbeanName);

        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | MalformedObjectNameException e) {
            System.out.println(e);
        }
    }
}