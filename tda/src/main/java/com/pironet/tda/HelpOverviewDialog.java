/*
 * HelpOverviewDialog.java
 *
 * This file is part of TDA - Thread Dump Analysis Tool.
 *
 * TDA is free software; you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * TDA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with TDA; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * $Id: HelpOverviewDialog.java,v 1.11 2008-01-20 12:00:40 irockel Exp $
 */

package com.pironet.tda;

import com.pironet.tda.utils.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author irockel
 */
public class HelpOverviewDialog extends JDialog {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpOverviewDialog.class);
    private JEditorPane htmlView;
    private JPanel buttonPanel;
    private JButton closeButton;

    private String file;

    /**
     * Creates a new instance of HelpOverviewDialog
     */
    public HelpOverviewDialog(JFrame owner, String title, String file, Image icon) {
        super(owner, title, true);
        setFile(file);
        if (icon != null) {
            try {
                this.setIconImage(icon);
            } catch (NoSuchMethodError nsme) {
                // ignore, for 1.4 backward compatibility
            }
        }
        getContentPane().setLayout(new BorderLayout());
        initPanel();
        setLocationRelativeTo(owner);
    }

    private void initPanel() {
        try {
            URL tutURL = HelpOverviewDialog.class.getClassLoader().getResource(getFile());
            htmlView = new JEditorPane(tutURL);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        htmlView.addHyperlinkListener(
                new HyperlinkListener() {
                    public void hyperlinkUpdate(HyperlinkEvent evt) {
                        // if a link was clicked
                        if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                            try {
                                if (evt.getURL().toString().indexOf("#") >= 0) {
                                    // show internal anchors in editor pane.
                                    htmlView.setPage(evt.getURL());
                                } else {
                                    // launch a browser with the appropriate URL
                                    Browser.open(evt.getURL().toString());
                                }
                            } catch (InterruptedException e) {
                                LOGGER.info("Error launching external browser.");
                                LOGGER.debug(e.getMessage(), e);
                            } catch (IOException e) {
                                LOGGER.error("I/O error launching external browser:" + e.getMessage(), e);
                            }
                        }
                    }
                });

        JScrollPane scrollPane = new JScrollPane(htmlView);
        htmlView.setEditable(false);
        htmlView.setPreferredSize(new Dimension(780, 600));
        htmlView.setCaretPosition(0);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        closeButton = new JButton("Close");
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getRootPane().setDefaultButton(closeButton);
    }

    //Must be called from the event-dispatching thread.
    public void resetFocus() {
        //searchField.requestFocusInWindow();
    }

    private String getFile() {
        return (file);
    }

    private void setFile(String value) {
        file = value;
    }
}
