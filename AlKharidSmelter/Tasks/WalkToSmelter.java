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
                ctx.movement.distance(Constants.getFurnaceTile()) > 5;
    }

    @Override
    public void execute() {
        ctx.movement.newTilePath(Constants.getTilePath()).traverse();
    }

    @Override
    public String getName() {
        return "Walking to smelter";
    }
}
