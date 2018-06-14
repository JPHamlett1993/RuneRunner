package VarrockEastMiner.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;


public class Miner {

    public static void mineRock(final ClientContext ctx, int[] rockIds) {
        final int inventoryCount = ctx.inventory.count();
        GameObject rock = ctx.objects.select().id(rockIds).nearest().poll();
        if (!rock.inViewport()) {
            ctx.camera.turnTo(rock);
        }
        rock.interact("Mine", "Rocks");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1;
            }
        }, 500, 10);
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() == -1;
            }
        }, 500, 60);

    }
}
