package JP_AIO_Miner.Task;

import JP_AIO_Miner.JP_AIO_Miner;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;

public class WalkToBank extends Task<ClientContext> {

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28 &&
                !ctx.objects.select().name(Constants.BANK_CHESTS).nearest().poll().inViewport() && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        JP_AIO_Miner.state = "Walking to bank";
        JP_AIO_Miner.printStatus("Walking To Bank");
        ctx.movement.findPath(new Tile(3013, 9718)).traverse();
    }
}
