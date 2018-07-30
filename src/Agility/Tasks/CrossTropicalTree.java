package Agility.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class CrossTropicalTree extends Task<ClientContext> {
    private int tropicalTreeId = 10357;

    public CrossTropicalTree(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() && ctx.objects.select().id(tropicalTreeId).poll().inViewport();
    }

    @Override
    public void execute() {
        ctx.objects.select().id(tropicalTreeId).poll().click();
        Condition.sleep(1000);

    }

    @Override
    public String getName() {
        return "Crossing Tree";
    }
}
