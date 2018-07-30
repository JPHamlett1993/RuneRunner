package BlastFurnace.Tasks.Dispensery;

import BlastFurnace.Constants;
import BlastFurnace.Furnace;
import BlastFurnace.Tasks.Task;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.Arrays;

public class MoveToDispensery extends Task<ClientContext> {

    public MoveToDispensery(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GameObject distilary = ctx.objects.select().name("Bar dispenser").nearest().poll();
        return !ctx.players.local().inMotion() && !ctx.bank.opened() && Furnace.ORES_TO_SMELT.length > 1 && Furnace.ORES_TO_SMELT[Constants.oreIndex] == null && ctx.movement.distance(distilary) > 1;
    }

    @Override
    public void execute() {
        GameObject dispensery = ctx.objects.select().name("Bar dispenser").nearest().poll();
        new Tile(dispensery.tile().x() + Random.nextInt(-2, 2), dispensery.tile().y() + Random.nextInt(-2, 2)).matrix(ctx).interact("Walk here");
    }

    @Override
    public String getName() {
        return "Walking to dispenser";
    }
}
