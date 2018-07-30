package BlastFurnace.Tasks.Bank;

import BlastFurnace.Constants;
import BlastFurnace.Tasks.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class CloseBank extends Task<ClientContext> {

    public CloseBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.opened() && Constants.hasOresInInventory(ctx);
    }

    @Override
    public void execute() {
        ctx.bank.close();
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !ctx.bank.opened();
            }
        }, 100, 10);
    }

    @Override
    public String getName() {
        return "Closing Bank";
    }
}
