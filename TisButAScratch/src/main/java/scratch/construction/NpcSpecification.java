package scratch.construction;

import java.awt.geom.Rectangle2D;

/**
 * Created by tejp on 2014-05-14.
 */
public class NpcSpecification {

    private String pluginName;
    private int id;
    private Rectangle2D.Double area;

    public NpcSpecification(String pluginName, int id, Rectangle2D.Double area) {
        this.pluginName = pluginName;
        this.id = id;
        this.area = area;
    }

    public String getPluginName() {
        return pluginName;
    }

    public Rectangle2D.Double getArea() {
        return area;
    }

    public int getId() {
        return id;
    }
}
