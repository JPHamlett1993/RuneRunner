package VarrockWestSmither.Tasks;

import VarrockWestSmither.Constants;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class WalkToAnvil extends Task<ClientContext> {


    public WalkToAnvil(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() &&
                ctx.inventory.select().id(Constants.getBronzeBarId()).count() != 0 &&
                ctx.movement.distance(Constants.getSmitherTile()) > 5;
    }

    @Override
    public void execute() {
        System.out.println(ctx.objects.select().id(Constants.getAnvilId()).poll());
        GameObject anvil = ctx.objects.select().id(Constants.getAnvilId()).poll();
        ctx.movement.findPath(new Tile(anvil.tile().x() + Random.nextInt(-2, 2), anvil.tile().y() + Random.nextInt(-2, 2))).traverse();
    }

    @Override
    public String getName() {
        return "Walking to anvil";
    }
}
