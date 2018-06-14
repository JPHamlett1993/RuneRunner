package AlKharidSmelter.Tasks;

import AlKharidSmelter.Constants;
import org.powerbot.script.rt4.ClientContext;

public class WalkToBank extends Task<ClientContext> {

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(Constants.getTinId()).count() == 0 &&
                !ctx.players.local().inMotion() &&
                ctx.movement.distance(Constants.getBankTile()) > 5;
    }

    @Override
    public void execute() {
        ctx.movement.newTilePath(Constants.getTilePath()).reverse().traverse();
    }

    @Override
    public String getName() {
        return "Walking to bank";
    }
}
