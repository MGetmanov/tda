package com.pironet.tda.utils;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File utils for TDA
 * Created by Mikhail Getmanov on 07.04.2017.
 */
public class FileUtils {
    /**
     * Return files array for @param transferable
     */
    public static File[] loadFilesJavaFileListFlavor(Transferable transferable) {
        try {
            if (!transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                return null;
            }

            List fileList = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
            List acceptedFiles = new ArrayList();
            File[] files = (File[]) fileList.toArray(new File[0]);
            for (File file : files) {
                if (!file.isDirectory()) {
                    acceptedFiles.add(file);
                }
            }
            return (File[]) acceptedFiles.toArray(new File[0]);
        } catch (IOException ex) {
            return null;
        } catch (UnsupportedFlavorException ex) {
            return null;
        }
    }
}
