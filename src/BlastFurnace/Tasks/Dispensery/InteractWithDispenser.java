package BlastFurnace.Tasks.Dispensery;

import BlastFurnace.Constants;
import BlastFurnace.Furnace;
import BlastFurnace.Tasks.Task;
import org.powerbot.Con;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class InteractWithDispenser extends Task<ClientContext> {

    public InteractWithDispenser(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GameObject dispenser = ctx.objects.select().name("Bar dispenser").nearest().poll();
        return !ctx.players.local().inMotion() && !ctx.bank.opened() && Furnace.ORES_TO_SMELT[Constants.oreIndex] == null && !Constants.isBarInterfaceOpen(ctx) &&
            Arrays.asList(dispenser.actions()).contains("Take") && dispenser.inViewport();
    }

    @Override
    public void execute() {
        ctx.objects.select().name("Bar dispenser").nearest().poll().interact("Take");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return Constants.isBarInterfaceOpen(ctx);
            }
        }, 100, 100);
    }

    @Override
    public String getName() {
        return "Opening Dispenser";
    }
}
