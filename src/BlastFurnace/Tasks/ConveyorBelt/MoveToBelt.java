package BlastFurnace.Tasks.ConveyorBelt;

import BlastFurnace.Constants;
import BlastFurnace.Furnace;
import BlastFurnace.Tasks.Task;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class MoveToBelt extends Task<ClientContext> {

    public MoveToBelt(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GameObject belt = ctx.objects.select().id(9100).nearest().poll();
        return Furnace.ORES_TO_SMELT[Constants.oreIndex] != null && !ctx.players.local().inMotion() && Constants.hasOresInInventory(ctx) && !belt.inViewport();
    }

    @Override
    public void execute() {
        Tile belt = new Tile(1938, 4967);
        ctx.movement.findPath(new Tile(belt.x() + Random.nextInt(0, 4), belt.y())).traverse();
    }

    @Override
    public String getName() {
        return "Move to belt";
    }
}
