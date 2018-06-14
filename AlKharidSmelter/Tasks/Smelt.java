package AlKharidSmelter.Tasks;

import AlKharidSmelter.Constants;
import org.powerbot.Con;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Widget;
import org.powerbot.script.rt4.Widgets;

import java.util.concurrent.Callable;

public class Smelt extends Task<ClientContext> {

    public Smelt(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
         return ctx.players.local().animation() == -1
                && !ctx.players.local().inMotion()
                && ctx.inventory.select().id(Constants.getTinId()).count() > 0 &&
                ctx.movement.distance(Constants.getFurnaceTile()) < 5;
    }

    @Override
    public void execute() {
        GameObject furnace = ctx.objects.select().id(Constants.getFurnaceId()).nearest().poll();
        furnace.interact("Smelt");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {return ctx.widgets.widget(270).component(14).valid();
            }
        }, 100, 30);
        System.out.println();
        ctx.widgets.widget(270).component(14).interact("Smelt");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().id(Constants.getTinId()).count() == 0
                        || ctx.widgets.widget(233).component(1).valid();
            }
        }, 100, 600);
    }

    @Override
    public String getName() {
        return "Smelting";
    }
}
