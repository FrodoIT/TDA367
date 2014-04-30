package scratch.construction.plugin.exported;

import scratch.construction.plugin.InteractionPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.model.IInteraction;

@InteractionPlugin(id = 2)
public final class SimpleInteractPlugin implements Pluggable<SimpleInteractPlugin>, IInteraction {


    @Override
    public SimpleInteractPlugin get() {
        return this;
    }

    @Override
    public void performInteraction() {
        System.out.println("Interaction Plugin is working");
    }
}
