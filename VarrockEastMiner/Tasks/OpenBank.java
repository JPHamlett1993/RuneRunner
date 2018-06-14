package VarrockEastMiner.Tasks;

import VarrockEastMiner.Constants;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Bank;

public class OpenBank extends Task<ClientContext> {

    Bank bank;



    public OpenBank(ClientContext ctx) {
        super(ctx);
        bank = new Bank(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28 &&
        Constants.inBank(ctx)
        && !bank.opened();
    }

    @Override
    public void execute() {
        bank.open();
    }

    @Override
    public String getName() {
        return "Opening OpenBank";
    }
}