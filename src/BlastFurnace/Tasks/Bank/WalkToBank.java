package BlastFurnace.Tasks.Bank;

import BlastFurnace.Constants;
import BlastFurnace.Furnace;
import BlastFurnace.Tasks.Task;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class WalkToBank extends Task<ClientContext> {

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        System.out.println(Furnace.ORES_TO_SMELT[Constants.oreIndex] != null);
        System.out.println(!ctx.players.local().inMotion());
        System.out.println(!Constants.hasOresInInventory(ctx));
        System.out.println(!ctx.bank.inViewport());
        return Furnace.ORES_TO_SMELT[Constants.oreIndex] != null && !ctx.players.local().inMotion() &&
                !Constants.hasOresInInventory(ctx)
                && !ctx.bank.inViewport();
    }

    @Override
    public void execute() {
        Tile bankTile = new Tile(1948 + Random.nextInt(-1, 1), 4957 + Random.nextInt(0, 1));
        ctx.movement.findPath(bankTile).traverse();
    }

    @Override
    public String getName() {
        return "Walking to bank";
    }
}
