package RunecraftingRunner.Task;

import RunecraftingRunner.RunecraftingRunner;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class ExchangeNotes extends Task<ClientContext> {

    public ExchangeNotes(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return Constants.inBank(ctx) && ctx.inventory.select().id(Constants.pureEssId).size() == 0;
    }

    @Override
    public void execute() {
        while (!ctx.bank.opened()) {
            ctx.bank.open();
            int tries = 0;
            while (tries < 15 && !ctx.bank.opened()) {
                Condition.sleep(100);
                tries++;
            }
        }
        ctx.bank.depositInventory();

        Condition.sleep(150);
        ctx.bank.withdraw(Constants.pureEssId, RunecraftingRunner.amountOfRuneEss);
        Condition.sleep(250);
        while (ctx.bank.opened()) {
            ctx.bank.close();
            int tries = 0;
            while (tries < 15 && ctx.bank.opened()) {
                Condition.sleep(100);
                tries++;
            }
        }
    }

    @Override
    public String getName() {
        return "Exchange notes";
    }
}
