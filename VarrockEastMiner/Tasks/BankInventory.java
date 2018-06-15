package VarrockEastMiner.Tasks;

import VarrockEastMiner.Constants;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Bank;

import java.util.concurrent.Callable;

public class BankInventory extends Task<ClientContext> {

    public BankInventory(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28 && Constants.inBank(ctx);
    }

    @Override
    public void execute() {
        ctx.bank.open();
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.bank.opened();
            }
        }, 50, 500);
        while (ctx.inventory.select().count() > 0) {
            ctx.bank.depositInventory();
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().count() == 0;
                }
            }, 100, 10);
        }
    }

    @Override
    public String getName() {
        return "Banking Inventory";
    }
}
