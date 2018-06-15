package AlKharidSmelter.Tasks;

import AlKharidSmelter.Constants;
import org.powerbot.script.rt4.ClientContext;

public class WalkToBank extends Task<ClientContext> {

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !Constants.hasOres(ctx) &&
                !ctx.players.local().inMotion() &&
                !Constants.inBank(ctx);
    }

    @Override
    public void execute() {
        ctx.movement.newTilePath(Constants.TILE_PATH).reverse().traverse();
    }

    @Override
    public String getName() {
        return "Walking to bank";
    }
}
