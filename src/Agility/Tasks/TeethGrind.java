package Agility.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TeethGrind extends Task<ClientContext> {

    private Tile startTile = new Tile(3301, 3164, 3);
    private int zipLineId = 10356;

    public TeethGrind(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        System.out.println(ctx.movement.distance(startTile));
        return ctx.movement.distance(startTile) != -1;
    }

    @Override
    public void execute() {
        while (!ctx.objects.select().id(zipLineId).poll().inViewport()) {
            if (!ctx.players.local().inMotion()) {
                ctx.movement.findPath(startTile).traverse();
            }
            Condition.sleep(750);
        }
        ctx.objects.select().id(zipLineId).poll().click();


    }

    @Override
    public String getName() {
        return "Teeth Grind";
    }
}
