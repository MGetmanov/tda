/*
 * DumpParserFactoryTest.java
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
 * $Id: DumpParserFactoryTest.java,v 1.5 2008-02-15 09:05:04 irockel Exp $
 */
package com.pironet.tda;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author irockel
 */
public class DumpParserFactoryTest {

    /**
     * Test of get method, of class com.pironet.tda.DumpParserFactory.
     */
    @Test
    public void testGet() {
        DumpParserFactory result = DumpParserFactory.get();
        assertNotNull(result);
    }

    /**
     * Test of getDumpParserForVersion method, of class com.pironet.tda.DumpParserFactory.
     */
    @Test
    public void testGetDumpParserForSunLogfile() throws FileNotFoundException {
        InputStream dumpFileStream = getClass().getClassLoader().getResourceAsStream("test.log");
        Map threadStore = null;
        DumpParserFactory instance = DumpParserFactory.get();

        DumpParser result = instance.getDumpParserForLogfile(dumpFileStream, threadStore, false, 0);
        assertNotNull(result);

        assertTrue(result instanceof com.pironet.tda.SunJDKParser);
    }

    /**
     * Test of getDumpParserForVersion method, of class com.pironet.tda.DumpParserFactory.
     */
    @Test
    public void testGetDumpParserForBeaLogfile() throws FileNotFoundException {
        InputStream dumpFileStream = getClass().getClassLoader().getResourceAsStream("jrockit_15_dump.txt");
        Map threadStore = null;
        DumpParserFactory instance = DumpParserFactory.get();

        DumpParser result = instance.getDumpParserForLogfile(dumpFileStream, threadStore, false, 0);
        assertNotNull(result);

        assertTrue(result instanceof com.pironet.tda.BeaJDKParser);
    }
}
