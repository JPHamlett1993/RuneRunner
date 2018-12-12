package RunecraftingRunner.Task;

import RunecraftingRunner.RunecraftingRunner;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

public class WalkToAlter extends Task<ClientContext> {

    public WalkToAlter(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.objects.select().name("Mysterious ruins").nearest().poll().inViewport() &&
                ctx.inventory.select().id(Constants.pureEssId).size() == RunecraftingRunner.amountOfRuneEss
                && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        ctx.movement.newTilePath(Constants.path).reverse().traverse();
    }

    @Override
    public String getName() {
        return "Walking to alter";
    }


}
