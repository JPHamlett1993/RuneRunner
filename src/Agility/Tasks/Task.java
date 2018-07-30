package Agility.Tasks;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public abstract class Task<C extends ClientContext> extends ClientAccessor<C> {

    public Task(C ctx) {
        super(ctx);
    }

    public abstract boolean activate();
    public abstract void execute();
    public abstract String getName();
}
