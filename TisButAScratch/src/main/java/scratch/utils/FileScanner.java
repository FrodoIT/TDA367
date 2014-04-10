/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivar
 */
public class FileScanner {
    public static List<File> getFiles(File path) {
        List<File> files = new ArrayList<File>();
        File[] entries = path.listFiles();
        
        if (entries != null) {
            for (File f : entries) {
                if (!f.isDirectory()){
                    files.add(f);
                }
            }
        }
        return files;
    }
}
