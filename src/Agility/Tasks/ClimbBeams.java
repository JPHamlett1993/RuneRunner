package Agility.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class ClimbBeams extends Task<ClientContext> {

    private int beamsId = 10094;

    public ClimbBeams(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() && ctx.objects.select().name("Roof top beams").poll().inViewport();
    }

    @Override
    public void execute() {
        GameObject beams = ctx.objects.select().name("Roof top beams").poll();
        beams.interact("Climb");
        Condition.sleep(1000);

    }

    @Override
    public String getName() {
        return "Climbing Beams";
    }
}
