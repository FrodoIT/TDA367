package scratch.construction;

import scratch.construction.plugin.Pluggable;
import scratch.construction.plugin.PluginLoader;
import scratch.model.IMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alma on 4/26/14.
 * A generic class for all Factories which use plugins. A means to avoid duplicating
 * code. It contains A list with the type of objects it produces, a map for all plugins,
 * an abstract method for creating this object at a position
 *
 * call
 * pluginMap = PluginLoader.loadPlugins(Class anotationType)
 * in constructor when extending this class
 */
public abstract class PluginUserFactory<T> {
    private final Map<Integer, T> givenTypeMap;
    private Map<Integer, Pluggable<?>> pluginMap;
    private final IMap map;
    public PluginUserFactory(IMap map){
        this.map = map;
        givenTypeMap = new TreeMap<>();
        pluginMap = null;
    }

    public abstract void loadType();
    public Map<Integer, Pluggable<?>> getPluginMap() {
        return pluginMap;
    }

    public Map<Integer, T> getGivenTypeMap() {
        return givenTypeMap;
    }
    
    public void setPluginMap(Map<Integer,Pluggable<?>> pluginMap){
        this.pluginMap = pluginMap;
    }

    public IMap getMap() {
        return map;
    }
}
