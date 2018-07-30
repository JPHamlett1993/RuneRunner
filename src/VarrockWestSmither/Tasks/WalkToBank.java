package VarrockWestSmither.Tasks;

import VarrockWestSmither.Constants;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class WalkToBank extends Task<ClientContext>  {

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() &&
                ctx.movement.distance(Constants.getBankerTile()) > 5 &&
                ctx.inventory.select().id(Constants.getBronzeBarId()).count() == 0;
    }

    @Override
    public void execute() {
        GameObject bankBooth = ctx.objects.select().name("Bank booth").nearest().poll();
        ctx.movement.findPath(new Tile(bankBooth.tile().x() + Random.nextInt(-2, 2), bankBooth.tile().y() + Random.nextInt(-2, 2))).traverse();
    }

    @Override
    public String getName() {
        return "Walking to bank";
    }
}
