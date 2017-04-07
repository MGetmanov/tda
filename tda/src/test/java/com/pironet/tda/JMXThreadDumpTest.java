package com.pironet.tda;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Example for getting statistic of MXBean
 * Created by Mikhail Getmanov on 07.04.2017.
 */
public class JMXThreadDumpTest {
    @Test
    public void t() throws Exception {
        dumpStack();
    }

    private void dumpStack() throws IOException {
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        java.lang.management.ThreadInfo[] threadInfos = mxBean.getThreadInfo(mxBean.getAllThreadIds(), 0);
        Map<Long, java.lang.management.ThreadInfo> threadInfoMap = new HashMap<Long, java.lang.management.ThreadInfo>();
        for (java.lang.management.ThreadInfo threadInfo : threadInfos) {
            threadInfoMap.put(threadInfo.getThreadId(), threadInfo);
        }

//        File dumpFile = new File(".", "stack." + System.currentTimeMillis());
//        Writer writer = new BufferedWriter(new FileWriter(dumpFile));
        dumpTraces(mxBean, threadInfoMap, new PrintWriter(System.out));
    }

    private void dumpTraces(ThreadMXBean mxBean, Map<Long, ThreadInfo> threadInfoMap, Writer writer) throws IOException {
        Map<Thread, StackTraceElement[]> stack = Thread.getAllStackTraces();
        writer.write("Dump of " + stack.size() + " thread at " + new Date() + "\n\n");
        for (Map.Entry<Thread, StackTraceElement[]> entry : stack.entrySet()) {
            Thread thread = entry.getKey();

            writer.write("\"" + thread.getName() + "\" prio=" + thread.getPriority() + " tid=" + thread.getId() + " ");
            writer.write(thread.getState() + " " + (thread.isDaemon() ? "demon" : "worker") + "\n");

            ThreadInfo threadInfo = threadInfoMap.get(thread.getId());
            if (threadInfo != null) {
                writer.write("\tnative=" + threadInfo.isInNative() + ", suspended=" + threadInfo.isSuspended() + ", ");
                writer.write("block=" + threadInfo.getBlockedCount() + ", wait=" + threadInfo.getWaitedCount() + "\n");

                writer.write("\tlock=" + threadInfo.getLockName() + " owned by " + threadInfo.getLockOwnerName() + " ");
                writer.write("(" + threadInfo.getLockOwnerId() + "), ");
                writer.write("cpu=" + (mxBean.getThreadCpuTime(threadInfo.getThreadId()) / 1000000L) + ", ");
                writer.write("user=" + (mxBean.getThreadUserTime(threadInfo.getThreadId()) / 1000000L) + "\n");
            }
            for (StackTraceElement element : entry.getValue()) {
                writer.write("\t" + element.toString() + "\n");
            }
            writer.write("\n");
        }
        writer.flush();
    }
}
