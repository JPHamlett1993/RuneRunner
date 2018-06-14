package VarrockWestSmither.Tasks;

import VarrockWestSmither.Constants;
import org.powerbot.script.rt4.ClientContext;

public class DepositDaggers extends Task<ClientContext>  {

    public DepositDaggers(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.opened() && ctx.inventory.select().id(Constants.getBronzeDaggerId()).count() > 0;
    }

    @Override
    public void execute() {
        ctx.bank.depositAllExcept(Constants.getHammerId());
    }

    @Override
    public String getName() {
        return "Depositing Daggers";
    }
}
