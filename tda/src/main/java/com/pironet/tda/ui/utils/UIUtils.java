package com.pironet.tda.ui.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * Common utils for UI
 * Created by Mikhail Getmanov on 07.04.2017.
 */
public class UIUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(UIUtils.class);
    private final static int FONT_SIZE_MODIFIER = 0;

    private UIUtils() {
    }

    /**
     * Return result modifier of basic font
     *
     * @param value add value
     */
    public static String getFontSizeModifier(int value) {
        String result = String.valueOf(FONT_SIZE_MODIFIER + value);
        if ((FONT_SIZE_MODIFIER + value) > 0) {
            result = "+" + (FONT_SIZE_MODIFIER + value);
        }
        return result;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     *
     * @param iconName file name of icon
     */
    public static ImageIcon createImageIcon(String iconName) {
        java.net.URL imgURL = UIUtils.class.getClassLoader().getResource("icons/" + iconName);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            LOGGER.info("Couldn't find icon: {}", iconName);
            return null;
        }
    }
}
