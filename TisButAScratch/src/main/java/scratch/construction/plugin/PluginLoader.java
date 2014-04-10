/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.construction.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import scratch.utils.FileScanner;

/**
 *
 * @author Ivar
 */
public class PluginLoader {
    public static final String pluginPath = "";
    
    private static List<Class<?>> getPluginClasses (Class annotationType) throws ClassNotFoundException{
        List<File> files = FileScanner.getFiles(new File(pluginPath));
        PluginClassLoader pluginClassLoader = new PluginClassLoader();
        List<Class<?>> classList = new ArrayList<Class<?>>();
        for(File file: files){
            String fileName = file.getName();
            String strippedName = fileName.substring(0, fileName.indexOf(".class"));
            try{
                Class loadedClass = pluginClassLoader.loadClass(strippedName);
                if(loadedClass.getAnnotation(annotationType) != null){
                    classList.add(loadedClass);
                }
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        return classList;
    }
    
    private static Map<Integer, Pluggable<?>> getPluginsFromPluginClasses(List<Class<?>> classList) throws InstantiationException{
        Map<Integer, Pluggable<?>> map =  new HashMap<Integer, Pluggable<?>>();
        for(Class<?> aClass : classList ){
            Object newInstance = null;            
            
            try {
                newInstance = aClass.newInstance();
            } catch (IllegalAccessException ex) {
                Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch(InstantiationException exc){
                exc.printStackTrace();
            }
            
            if(newInstance != null){
                map.put(aClass.getAnnotation(AIPlugin.class).id(),(Pluggable<?>) newInstance);
            }
        }
        return map;
    }
}
