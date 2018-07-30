package BlastFurnace.Tasks.Bank;

import BlastFurnace.Constants;
import BlastFurnace.Furnace;
import BlastFurnace.Tasks.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class OpenBank extends Task<ClientContext> {

    public OpenBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return Furnace.ORES_TO_SMELT[Constants.oreIndex] != null && !ctx.players.local().inMotion() &&
                !Constants.hasOresInInventory(ctx) && ctx.bank.inViewport();
    }

    @Override
    public void execute() {
        ctx.bank.open();
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.bank.opened();
            }
        }, 250, 100);
    }

    @Override
    public String getName() {
        return "Opening Bank";
    }
}
