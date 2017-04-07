package com.pironet.tda.dumps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * POJO for dumps
 * Created by Mikhail Getmanov on 07.04.2017.
 */
public abstract class DumpFile {
    private final Object parent;
    private static int counter;

    public DumpFile(Object parent) {
        this.parent = parent;
    }

    public static int counter() {
        return counter;
    }

    public boolean isNull() {
        return value() == null;
    }

    /**
     * add the set dumpFileStream to the tree
     */
    public void addDumpFile() {
        addDumpFile(value());
    }

    /**
     * add the set dumpFileStream to the tree
     */
    public void addDumpFile(String filePath) {
        String[] file = new String[1];
        file[0] = filePath;
        addDumpFiles(file);
    }

    /**
     * add the set dumpFileStream to the tree
     */
    public void addDumpFiles(String[] files) {
        for (int i = 0; i < files.length; i++) {
            try {
                counter = 1;
                addDumpStream(new FileInputStream(files[i]), files[i], true);
            } catch (FileNotFoundException ex) {
                exceptionHandle(ex);
            }
        }
    }

    public void addCount() {
        addCount(1);
    }

    private void addCount(int add) {
        counter += add;
    }

    public Object getParent() {
        return parent;
    }

    public abstract String value();

    public abstract void value(String value);

    protected abstract void exceptionHandle(FileNotFoundException ex);

    public abstract void addDumpStream(InputStream inputStream, String file, boolean withLogfile);
}
