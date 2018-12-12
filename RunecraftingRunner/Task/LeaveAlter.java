package RunecraftingRunner.Task;

import RunecraftingRunner.RunecraftingRunner;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class LeaveAlter extends Task<ClientContext> {

    public LeaveAlter(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return Constants.inAlter(ctx) && ctx.inventory.select().id(Constants.notedPureEssId).poll().stackSize() == RunecraftingRunner.amountOfRuneEss;
    }

    @Override
    public void execute() {
        ctx.objects.select().name("Portal").nearest().poll().click();
        int tries = 0;
        while (tries < 100 && Constants.inAlter(ctx)) {
            tries++;
            Condition.sleep(100);
        }
    }

    @Override
    public String getName() {
        return "Leave alter";
    }
}
