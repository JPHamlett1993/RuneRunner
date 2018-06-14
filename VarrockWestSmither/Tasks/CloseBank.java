package VarrockWestSmither.Tasks;

import VarrockWestSmither.Constants;
import org.powerbot.script.rt4.ClientContext;

public class CloseBank extends Task<ClientContext>  {

    public CloseBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.opened() && ctx.inventory.select().id(Constants.getBronzeBarId()).count() == 27;
    }

    @Override
    public void execute() {
        ctx.bank.close();
    }

    @Override
    public String getName() {
        return "Closing Bank";
    }
}
