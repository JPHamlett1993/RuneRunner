package RunecraftingRunner.Task;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

public class WalkToBank extends Task<ClientContext> {

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !Constants.inAlter(ctx) && ctx.inventory.select().id(Constants.notedPureEssId).poll().stackSize() == 25
                && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        ctx.movement.newTilePath(Constants.path).traverse();
    }

    @Override
    public String getName() {
        return "Walk to bank";
    }
}
