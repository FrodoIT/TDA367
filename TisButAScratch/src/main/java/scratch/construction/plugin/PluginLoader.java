/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scratch.construction.plugin;

import scratch.utils.FileScanner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivar
 */
public final class PluginLoader {
    public static final String PLUGIN_PATH = "target/classes/scratch/construction/plugin/exported/";

    private PluginLoader() {
    }

    private static List<Class<?>> getPluginClasses (Class annotationType) {
        final List<File> files = FileScanner.getFiles(new File(PLUGIN_PATH));
        final PluginClassLoader pluginClassLoader = new PluginClassLoader();
        final List<Class<?>> classList = new ArrayList<Class<?>>();
        for(final File file: files) {
            final String fileName = file.getName();
            final String strippedName = fileName.substring(0, fileName.indexOf(".class"));
            try {
                final Class loadedClass = pluginClassLoader.loadClass(strippedName);
                if(loadedClass.getAnnotation(annotationType) != null) {
                    classList.add(loadedClass);
                }
            }catch(ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classList;
    }

    private static Map<Integer, Pluggable<?>> getPluginsFromPluginClasses(List<Class<?>> classList) {
        final Map<Integer, Pluggable<?>> map =  new HashMap<Integer, Pluggable<?>>();
        for(final Class<?> aClass : classList ) {
            Object newInstance = null;

            try {
                newInstance = aClass.newInstance();
            } catch (IllegalAccessException ex) {
                Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch(InstantiationException exc) {
                exc.printStackTrace();
            }

            if(newInstance != null) {
                System.out.println(aClass.toString());
                if(aClass.isAnnotationPresent(InteractionPlugin.class)) {

                    map.put(aClass.getAnnotation(InteractionPlugin.class).id(),(Pluggable<?>) newInstance);
                } else {
                    map.put(aClass.getAnnotation(AIPlugin.class).id(),(Pluggable<?>) newInstance);
                }
            }
        }
        return map;
    }

    public static Map<Integer,Pluggable<?>> loadPlugins(Class anotationType) {
        return getPluginsFromPluginClasses(getPluginClasses(anotationType));
    }
}
