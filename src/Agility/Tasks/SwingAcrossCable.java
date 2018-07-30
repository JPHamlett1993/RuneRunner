package Agility.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class SwingAcrossCable extends Task<ClientContext> {

    private int cableId = 10355;

    public SwingAcrossCable(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() &&
                ctx.objects.select().id(cableId).poll().inViewport();
    }

    @Override
    public void execute() {
        ctx.objects.select().id(cableId).poll().click();
        Condition.sleep(750);
    }

    @Override
    public String getName() {
        return "Crossing Cable";
    }
}
