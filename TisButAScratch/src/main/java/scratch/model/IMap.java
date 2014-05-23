package scratch.model;

import com.google.inject.ImplementedBy;
import scratch.construction.NpcSpecification;
import scratch.construction.TiledMapPlus;

import java.util.List;

@ImplementedBy(TiledMapPlus.class)
public interface IMap {
    boolean isColliding(Vector2D coordinate);
    boolean hasInteractiveObject();
    boolean hasNpc();
    int getHeight();
    int getWidth();
    int getId();
    List<InteractiveObject> getInteractiveObjects();
    List<NpcSpecification> getNpcSpecifications();
}
