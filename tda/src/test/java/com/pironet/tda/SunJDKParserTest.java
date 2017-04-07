/*
 * SunJDKParserTest.java
 *
 * This file is part of TDA - Thread Dump Analysis Tool.
 *
 * Foobar is free software; you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with Foobar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * $Id: SunJDKParserTest.java,v 1.9 2008-11-21 09:20:19 irockel Exp $
 */
package com.pironet.tda;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * test parsing of log files from sun vms.
 * @author irockel
 */
public class SunJDKParserTest {

    /**
     * Test of hasMoreDumps method, of class com.pironet.tda.SunJDKParser.
     */
    @Test
    public void testDumpLoad() throws IOException {
        InputStream dumpFileStream = null;
        DumpParser instance = null;

        try {
            dumpFileStream = getClass().getClassLoader().getResourceAsStream("test.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(dumpFileStream, dumpMap, false, 0);

            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if three dumps are in it.
            assertEquals(3, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(dumpFileStream != null) {
                dumpFileStream.close();
            }
        }
    }

    /**
     * Test of isFoundClassHistograms method, of class com.pironet.tda.SunJDKParser.
     */
    @Test
    public void testIsFoundClassHistograms() throws IOException {
        DumpParser instance = null;
        InputStream dumpFileStream = null;
        try {
            dumpFileStream =  getClass().getClassLoader().getResourceAsStream("testwithhistogram.log");
            Map dumpMap = new HashMap();
            instance = DumpParserFactory.get().getDumpParserForLogfile(dumpFileStream, dumpMap, false, 0);

            Vector topNodes = new Vector();
            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            boolean expResult = true;
            boolean result = instance.isFoundClassHistograms();
            assertEquals(expResult, result);
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(dumpFileStream != null) {
                dumpFileStream.close();
            }
        }
    }

    @Test
    public void test64BitDumpLoad() throws IOException {
        InputStream dumpFileStream = null;
        DumpParser instance = null;

        try {
            dumpFileStream = getClass().getClassLoader().getResourceAsStream("test64bit.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(dumpFileStream, dumpMap, false, 0);

            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if one dump was found.
            assertEquals(1, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(dumpFileStream != null) {
                dumpFileStream.close();
            }
        }
    }

    @Test
    public void testJava8DumpLoad() throws IOException {
        InputStream dumpFileStream = null;
        DumpParser instance = null;

        try {
            dumpFileStream =  getClass().getClassLoader().getResourceAsStream("java8dump.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(dumpFileStream, dumpMap, false, 0);

            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if one dump was found.
            assertEquals(1, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(dumpFileStream != null) {
                dumpFileStream.close();
            }
        }
    }

    @Test
    public void testSAPDumps()  throws IOException {
        InputStream dumpFileStream = null;
        DumpParser instance = null;

        try {
            dumpFileStream =  getClass().getClassLoader().getResourceAsStream("sapdump.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(dumpFileStream, dumpMap, false, 0);

            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if two dump were found.
            assertEquals(2, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(dumpFileStream != null) {
                dumpFileStream.close();
            }
        }
    }

    @Test
    public void testHPDumps()  throws IOException {
        InputStream dumpFileStream = null;
        DumpParser instance = null;

        try {
            dumpFileStream =  getClass().getClassLoader().getResourceAsStream("hpdump.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(dumpFileStream, dumpMap, false, 0);

            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if two dump were found.
            assertEquals(2, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(dumpFileStream != null) {
                dumpFileStream.close();
            }
        }
    }

    @Test
    public void testRemoteVisualVMDumps()  throws IOException {
        InputStream dumpFileStream = null;
        DumpParser instance = null;

        try {
            dumpFileStream =  getClass().getClassLoader().getResourceAsStream("visualvmremote.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(dumpFileStream, dumpMap, false, 0);

            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if two dump were found.
            assertEquals(1, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(dumpFileStream != null) {
                dumpFileStream.close();
            }
        }
    }

    @Test
    public void testURLThreadNameDumps()  throws IOException {
        InputStream dumpFileStream = null;
        DumpParser instance = null;

        try {
            dumpFileStream =  getClass().getClassLoader().getResourceAsStream("urlthread.log");
            Map dumpMap = new HashMap();
            Vector topNodes = new Vector();
            instance = DumpParserFactory.get().getDumpParserForLogfile(dumpFileStream, dumpMap, false, 0);

            assertTrue(instance instanceof SunJDKParser);

            while (instance.hasMoreDumps()) {
                topNodes.add(instance.parseNext());
            }

            // check if two dump were found.
            assertEquals(1, topNodes.size());
        } finally {
            if(instance != null) {
                instance.close();
            }
            if(dumpFileStream != null) {
                dumpFileStream.close();
            }
        }
    }
}
