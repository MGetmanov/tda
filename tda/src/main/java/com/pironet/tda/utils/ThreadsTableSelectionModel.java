/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pironet.tda.utils;

import javax.swing.*;

/**
 *
 * @author irockel
 */
public class ThreadsTableSelectionModel extends DefaultListSelectionModel {
    private JTable table = null;
    
    public ThreadsTableSelectionModel(JTable table) {
        this.table = table;
        
    }
    
    public JTable getTable() {
        return(table);
    }

}
