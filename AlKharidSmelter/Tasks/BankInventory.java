package AlKharidSmelter.Tasks;

import AlKharidSmelter.Ore;
import AlKharidSmelter.Constants;
import AlKharidSmelter.Runner;
import org.powerbot.Con;
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
            if (ctx.inventory.select().id(ore.getId()).count() == ore.getAmount()) {
                continue;
            }
            while(ctx.inventory.select().id(ore.getId()).count() == 0) {
                ctx.bank.withdraw(ore.getId(), ore.getAmount());
            }

        }
    }
    @Override
    public void execute() {
        ctx.bank.open();
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.bank.opened();
            }
        }, 10, 500);
        while (ctx.inventory.select().count() > 0) {
            ctx.bank.depositInventory();
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().count() == 0;
                }
            }, 100, 10);
        }
        Ore[] ores = {};
        switch (Runner.BAR_TO_SMELT) {
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
