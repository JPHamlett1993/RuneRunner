package BlastFurnace.Tasks.Bank;

import BlastFurnace.Constants;
import BlastFurnace.Furnace;
import BlastFurnace.Tasks.Task;
import org.powerbot.Con;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class DepositInventory extends Task<ClientContext> {

    public DepositInventory(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.opened() && Constants.hasBarsInInventory(ctx);
    }

    @Override
    public void execute() {
        for (int barId : Constants.BAR_IDS) {
            Furnace.numberSmelted += ctx.inventory.select().id(barId).count();
        }
        ctx.bank.depositAllExcept(Constants.PERM_ITEMS);
    }

    @Override
    public String getName() {
        return "Depositing Bars";
    }
}
