package JP_AIO_Miner.Task;


import JP_AIO_Miner.Constants;
import JP_AIO_Miner.JP_AIO_Miner;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class Mine extends Task<ClientContext> {

    public Mine(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() != 28 &&
                ctx.objects.select().id(Constants.getIdOfRock(ctx)).nearest().poll().inViewport()
                && !ctx.players.local().inMotion() && ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        JP_AIO_Miner.printStatus("Starting Mining");
        JP_AIO_Miner.state = "Mine";
        GameObject rock = ctx.objects.select().id(Constants.getIdOfRock(ctx)).nearest().poll();
        rock.click();
        Condition.sleep(750);
        int tries = 0;
        do {
            JP_AIO_Miner.printStatus(String.format("Sleeping: %d In Motion: %b Animation: %d", tries, !ctx.players.local().inMotion(), ctx.players.local().animation()));
            Condition.sleep();
            tries++;
        } while (tries < 50 && (ctx.players.local().inMotion() || ctx.players.local().animation() != -1));
        JP_AIO_Miner.printStatus("Done mining");
    }
}
