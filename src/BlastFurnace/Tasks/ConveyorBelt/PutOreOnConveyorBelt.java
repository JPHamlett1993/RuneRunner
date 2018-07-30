package BlastFurnace.Tasks.ConveyorBelt;

import BlastFurnace.Constants;
import BlastFurnace.Furnace;
import BlastFurnace.Tasks.Task;
import org.powerbot.bot.rt6.client.Skill;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Skills;

import java.util.concurrent.Callable;

public class PutOreOnConveyorBelt extends Task<ClientContext> {

    public PutOreOnConveyorBelt(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GameObject belt = ctx.objects.select().id(9100).nearest().poll();
        return !ctx.bank.opened() && Furnace.ORES_TO_SMELT[Constants.oreIndex] != null && !ctx.players.local().inMotion() &&
            Constants.hasOresInInventory(ctx)
            && belt.inViewport();
    }

    @Override
    public void execute() {
        GameObject belt = ctx.objects.select().id(Constants.BELT_ID).nearest().poll();
        belt.interact("Put-ore-on");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !Constants.hasOresInInventory(ctx);
            }
        }, 1000, 20);
        if (!Constants.hasOresInInventory(ctx)) {
            Constants.oreIndex = (Constants.oreIndex + 1) % Furnace.ORES_TO_SMELT.length;
        }
    }

    @Override
    public String getName() {
        return "Putting ore on cart";
    }
}
