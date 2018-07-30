package JP_AIO_Miner.Task;


import JP_AIO_Miner.Constants;
import JP_AIO_Miner.JP_AIO_Miner;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class WalkToRocks extends Task<ClientContext> {

    public WalkToRocks(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() != 28 &&
                !ctx.objects.select().id(Constants.getIdOfRock(ctx)).nearest().poll().inViewport() &&
                !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        JP_AIO_Miner.state = "Walking to mine";
        JP_AIO_Miner.printStatus("Finding Rock");
        GameObject rock = ctx.objects.select().id(Constants.getIdOfRock(ctx)).nearest().poll();
        JP_AIO_Miner.printStatus("Found Rock: " + rock);
        ctx.movement.findPath(new Tile(rock.tile().x() + Random.nextInt(0, 3), rock.tile().y() + Random.nextInt(0, 3))).traverse();
        JP_AIO_Miner.printStatus("Moving to rock");
    }
}
