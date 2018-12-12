package RunecraftingRunner.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class EnterAlter extends Task<ClientContext> {

    public EnterAlter(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.objects.select().name("Mysterious ruins").nearest().poll().inViewport()
                && ctx.inventory.select().id(Constants.pureEssId).size() == 25
                && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        ctx.objects.select().name("Mysterious ruins").nearest().poll().interact("Enter");
        int tries = 0;
        while (tries < 100 && !Constants.inAlter(ctx)) {
            Condition.sleep(150);
            tries++;
        }
    }

    @Override
    public String getName() {
        return "Enter alter";
    }
}
