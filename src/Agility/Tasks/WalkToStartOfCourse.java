package Agility.Tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class WalkToStartOfCourse extends Task<ClientContext> {

    private Tile startTile = new Tile(3274, 3197);

    public WalkToStartOfCourse(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() && ctx.players.local().tile().floor() == 0;
    }

    @Override
    public void execute() {
        ctx.movement.findPath(startTile).traverse();
        Condition.sleep(100);
    }

    @Override
    public String getName() {
        return "Walking to start fo course";
    }
}
