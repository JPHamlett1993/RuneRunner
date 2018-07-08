package AlKharidSmelter.Tasks;

import AlKharidSmelter.Ore;
import AlKharidSmelter.Constants;
import AlKharidSmelter.JPSmelters;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;


public class BankInventory extends Task<ClientContext> {

    public BankInventory(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return Constants.inBank(ctx) &&
                !Constants.hasOres(ctx);
    }

    private void getOreFromBank(Ore[] ores) {
        for (final Ore ore: ores) {
            while (ore.amount - ctx.inventory.select().id(ore.id).count() > 0) {
               ctx.bank.withdraw(ore.id, ore.amount - ctx.inventory.select().id(ore.id).count());
               Condition.sleep(450);
            }
        }
    }
    @Override
    public void execute() {
        JPSmelters.numberSmelted += ctx.inventory.select().count();
        while (!ctx.bank.opened()) {
            ctx.bank.open();
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.bank.opened();
                }
            }, 250, 500);
        }
        while (ctx.inventory.select().count() > 0) {
            ctx.bank.depositInventory();
            Condition.sleep(350);
        }
        Ore[] ores = {};
        switch (JPSmelters.BAR_TO_SMELT) {
            case BRONZE: {
                ores = Constants.BRONZE_ORES;
                break;
            }
            case IRON: {
                ores = Constants.IRON_ORES;
                break;
            }
            case SILVER: {
                ores = Constants.SILVER_ORES;
                break;
            }
            case STEEL: {
                ores = Constants.STEEL_ORES;
                break;
            }
            case GOLD: {
                ores = Constants.GOLD_ORES;
                break;
            }
            case MITHRIL: {
                ores = Constants.MITHRIL_ORES;
                break;
            }
            case ADAMANT: {
                ores = Constants.ADAMANT_ORES;
                break;
            }
            case RUNE: {
                ores = Constants.RUNE_ORES;
                break;
            }
        }
        getOreFromBank(ores);
    }

    @Override
    public String getName() {
        return "Depositing Bars";
    }
}
