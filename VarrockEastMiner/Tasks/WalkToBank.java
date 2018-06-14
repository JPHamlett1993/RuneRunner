package VarrockEastMiner.Tasks;

import VarrockEastMiner.Constants;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Movement;

public class WalkToBank extends Task<ClientContext> {

    Movement movement;

    public WalkToBank(ClientContext ctx) {
        super(ctx);
        movement = new Movement(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28 &&
                !Constants.inBank(ctx) &&
                !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
       ctx.movement.newTilePath(Constants.TILE_PATH).traverse();
    }

    @Override
    public String getName() {
        return "Walking to bank";
    }
}
