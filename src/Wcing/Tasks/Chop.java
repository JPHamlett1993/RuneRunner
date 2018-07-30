package Wcing.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class Chop extends Task<ClientContext> {

    public Chop(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() && ctx.players.local().animation() == -1 && ctx.inventory.select().count() < 28;
    }

    @Override
    public void execute() {
        ctx.objects.select().id(1750).nearest().poll().click();
        do {
            Condition.sleep();
        } while (ctx.players.local().animation() == -1 || ctx.players.local().inMotion());
    }

    @Override
    public String getName() {
        return "WCing";
    }
}
