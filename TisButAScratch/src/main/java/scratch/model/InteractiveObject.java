package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class InteractiveObject implements KryoSerializable {

    private String name, type;
    private Rectangle2D.Double unitTile;
    private Map<String, String> properties;

    public InteractiveObject(String name, String type, int x, int y, int width, int height, Map<String, String> properties) {
        this.name = name;
        this.type = type;
        this.unitTile = new Rectangle2D.Double(x, y, width, height);
        this.properties = properties;

    }

    public InteractiveObject() {

    }

    public void setObject(InteractiveObject interactiveObject) {
        this.properties = interactiveObject.getProperties();
        this.name = interactiveObject.getName();
        this.type = interactiveObject.getType();
        this.unitTile = interactiveObject.getUnitTile();
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void update() {

    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object == null || !(object instanceof InteractiveObject)) {
            return false;
        }
        final InteractiveObject recievedObject = (InteractiveObject) object;
        if (this.getName().equals(recievedObject.getName())
                && this.getPosition().equals(recievedObject.getPosition())
                && this.getProperties().equals(recievedObject.getProperties())
                && this.getType().equals(recievedObject.getType())
                && this.getUnitTile().equals(recievedObject.getUnitTile())) {
            return true;
        }
        return false;
    }

    public void setPosition(Vector2D newPos) {
        unitTile.setRect(newPos.getX(), newPos.getY(), unitTile.getWidth(), unitTile.getHeight());
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Vector2D getPosition() {
        return new Vector2D(unitTile.getX(), unitTile.getY());
    }

    public Rectangle2D.Double getUnitTile() {
        return unitTile;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (unitTile != null ? unitTile.hashCode() : 0);
        return 31 * result + (properties != null ? properties.hashCode() : 0);
        
    }

    @Override
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, name);
        kryo.writeObject(output, type);
        kryo.writeObject(output, unitTile);
        kryo.writeObject(output, properties);
    }

    @Override
    public void read(Kryo kryo, Input input) {
        name = kryo.readObject(input, String.class);
        type = kryo.readObject(input, String.class);
        unitTile = kryo.readObject(input, Rectangle2D.Double.class);
        properties = kryo.readObject(input, HashMap.class);
    }
}
