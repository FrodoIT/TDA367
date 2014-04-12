/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.construction.plugin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Ivar
 */
public class PluginClassLoader extends ClassLoader{
    public static final String exportedPackage = "scratch.construction.plugin.exported";
    
    @Override
    public Class<?> findClass (String name) {
        byte[] data = loadClassData(name);
        return defineClass(exportedPackage + "." + name, data, 0, data.length);
    }
    
    private byte[] loadClassData(String name){
        File f = new File(PluginLoader.pluginPath+name+".class");

        try {
            return Files.readAllBytes(f.toPath());
        } catch (IOException e){
            e.printStackTrace();
            return new byte[0];
        }
       
        
    }
}
