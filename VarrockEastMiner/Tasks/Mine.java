package VarrockEastMiner.Tasks;

import VarrockEastMiner.Constants;
import VarrockEastMiner.Runner;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;


public class Mine extends Task<ClientContext> {

    public Mine(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.count() < 28 &&
                !ctx.players.local().inMotion() &&
                ctx.players.local().animation() == -1 &&
                Constants.inMine(ctx);
    }

    private Rock determineWhatToMine() {
        if (Runner.BARS_TO_MINE == Bar.BRONZE) {
            if (ctx.inventory.select().id(Constants.COPPER_INV_IDS).count() < 14) {
                return Rock.COPPER;
            } else {
                return Rock.TIN;
            }
        }
        return Rock.IRON;
    }

    @Override
    public void execute() {
        int[] rockIds;
        switch (determineWhatToMine()) {
            case COPPER: {
                rockIds = Constants.COPPER_IDS;
                break;
            }
            case TIN: {
                rockIds = Constants.TIN_IDS;
                break;
            }
            default: {
                rockIds = Constants.IRON_IDS;
            }
        }
        final GameObject rock = ctx.objects.select().id(rockIds).nearest().poll();
        if (!rock.inViewport()) {
            ctx.camera.turnTo(rock.tile());
        }
        rock.interact("Mine");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1;
            }
        }, 10, 500);
    }

    @Override
    public String getName() {
        return "Mining";
    }
}
