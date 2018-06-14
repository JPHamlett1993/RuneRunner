package VarrockWestSmither.Tasks;

import VarrockWestSmither.Constants;
import org.powerbot.script.rt4.ClientContext;

public class OpenBank extends Task<ClientContext>  {

    public OpenBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(Constants.getBronzeBarId()).count() == 0
                && ctx.movement.distance(Constants.getBankerTile()) < 5;
    }

    @Override
    public void execute() {
        ctx.bank.open();
    }

    @Override
    public String getName() {
        return "Opening OpenBank";
    }
}
