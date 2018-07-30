package Wcing.Tasks;

import org.powerbot.Con;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class Fletch extends Task<ClientContext> {

    public Fletch(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28;
    }

    @Override
    public void execute() {
        ctx.inventory.select().id(946).poll().interact("Use");
        ctx.inventory.select().id(1519).poll().interact("Use");
        do {
            Condition.sleep(10);
        } while (!ctx.widgets.widget(270).valid());
        ctx.input.send("1");
        do {
            Condition.sleep(100);
        } while (ctx.inventory.select().id(1519).count() > 0);
    }

    @Override
    public String getName() {
        return "Fletching";
    }
}
