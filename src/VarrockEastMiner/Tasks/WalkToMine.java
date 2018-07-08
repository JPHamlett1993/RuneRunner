package VarrockEastMiner.Tasks;

import VarrockEastMiner.Constants;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Movement;

public class WalkToMine extends Task<ClientContext> {

    Movement movement;

    public WalkToMine(ClientContext ctx) {
        super(ctx);
        movement = new Movement(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() < 28 &&
                !Constants.inMine(ctx)
                && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        ctx.movement.newTilePath(Constants.TILE_PATH).reverse().traverse();
    }

    @Override
    public String getName() {
        return "Walking To Mine";
    }
}
