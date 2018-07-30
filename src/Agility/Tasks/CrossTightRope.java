package Agility.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class CrossTightRope extends Task<ClientContext> {

    private int tightRopeId = 10284;

    public CrossTightRope(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() && ctx.objects.select().name("Tightrope").poll().inViewport();
    }

    @Override
    public void execute() {
        ctx.objects.select().name("Tightrope").poll().click();
        Condition.sleep(750);
    }

    @Override
    public String getName() {
        return "Crossing TightRope";
    }
}
