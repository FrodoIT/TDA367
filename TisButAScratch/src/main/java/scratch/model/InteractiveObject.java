package scratch.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Properties;

public class InteractiveObject implements IInteractiveObject, KryoSerializable {

    private String name, type;
    private Rectangle2D.Double rect;
    private HashMap<String, String> properties;

    public InteractiveObject(String name, String type, int x, int y, int width, int height, HashMap<String, String> properties) {
        this.name = name;
        this.type = type;
        this.rect = new Rectangle2D.Double(x, y, width, height);
        this.properties = properties;

    }

	public InteractiveObject(){

	}

    @Override
    public void setPosition(Vector2D newPos) {
        rect.setRect(newPos.getX(), newPos.getY(), rect.getWidth(), rect.getHeight());
	    System.out.println("id: " + properties.get("id") + "new Pos: " + rect);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Rectangle2D.Double getUnitTile() {
	    return rect;
    }

	public void setObject(InteractiveObject interactiveObject){
		this.properties=interactiveObject.getProperties();
		this.name=interactiveObject.getName();
		this.type= interactiveObject.getType();
		this.rect= interactiveObject.getUnitTile();
	}

    @Override
    public HashMap<String,String> getProperties() {
        return properties;
    }

    @Override
    public void update() {

    }

	@Override
	public void write(Kryo kryo, Output output) {
		kryo.writeObject(output, name);
		kryo.writeObject(output, type);
		kryo.writeObject(output, rect);
		kryo.writeObject(output, properties);
	}

	@Override
	public void read(Kryo kryo, Input input) {
		name = kryo.readObject(input, String.class);
		type = kryo.readObject(input, String.class);
		rect = kryo.readObject(input, Rectangle2D.Double.class);
		properties = kryo.readObject(input, HashMap.class);
	}
}