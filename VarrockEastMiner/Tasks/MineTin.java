package VarrockEastMiner.Tasks;

import VarrockEastMiner.Constants;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class MineTin extends Task<ClientContext> {

    public MineTin(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() < 28 &&
                ctx.inventory.select().id(Constants.getCopperInvId()).count() == 14 &&
                !ctx.players.local().inMotion() &&
                ctx.players.local().animation() == -1 &&
                Constants.inMine(ctx);
    }

    @Override
    public void execute() {
        GameObject rock = ctx.objects.select().id(Constants.getTinIds()).nearest().poll();
        rock.interact("Mine");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1;
            }
        }, 10, 500);
    }

    @Override
    public String getName() {
        return "Mining Copper";
    }
}
