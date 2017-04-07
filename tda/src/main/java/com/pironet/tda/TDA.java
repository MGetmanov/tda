/**
 * Thread Dump Analysis Tool, parses Thread Dump input and displays it as tree
 * <p>
 * This file is part of TDA - Thread Dump Analysis Tool.
 * <p>
 * TDA is free software; you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 * <p>
 * TDA is distributed in the hope that it will be useful,h
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 * <p>
 * TDA should have received a copy of the Lesser GNU General Public License
 * along with Foobar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.pironet.tda;

import com.pironet.tda.ui.TDAMainPanel;

/**
 * main class of the Thread Dump Analyzer. Start using static main method.
 *
 * @author irockel
 */
public class TDA {

    /**
     * main startup method for TDA
     */
    public static void main(final String[] args) {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (args.length > 0) {
                    TDAMainPanel.createAndShowGUI(args[0]);
                } else {
                    TDAMainPanel.createAndShowGUI(null);
                }
            }
        });
    }


}
