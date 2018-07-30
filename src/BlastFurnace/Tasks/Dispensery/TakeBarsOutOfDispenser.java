package BlastFurnace.Tasks.Dispensery;

import BlastFurnace.Constants;
import BlastFurnace.Tasks.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class TakeBarsOutOfDispenser extends Task<ClientContext> {

    public TakeBarsOutOfDispenser(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.bank.opened() && Constants.isBarInterfaceOpen(ctx);
    }

    @Override
    public void execute() {
        ctx.input.send(" ");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return Constants.hasBarsInInventory(ctx);
            }
        }, 100, 10);
        if (Constants.hasBarsInInventory(ctx)) {
            Constants.oreIndex = 0;
        }
    }

    @Override
    public String getName() {
        return "Taking bars from dispenser";
    }
}
