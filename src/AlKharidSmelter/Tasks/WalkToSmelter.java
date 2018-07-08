package AlKharidSmelter.Tasks;

import AlKharidSmelter.Constants;
import org.powerbot.script.rt4.ClientContext;

public class WalkToSmelter extends Task<ClientContext> {

    public WalkToSmelter(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28 &&
                !ctx.players.local().inMotion() &&
                !Constants.inSmelter(ctx);
    }

    @Override
    public void execute() {
        ctx.movement.newTilePath(Constants.TILE_PATH).traverse();
    }

    @Override
    public String getName() {
        return "Walking to smelter";
    }
}
