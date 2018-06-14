package VarrockEastMiner.Tasks;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Bank;

public class BankInventory extends Task<ClientContext> {

    Bank bank;



    public BankInventory(ClientContext ctx) {
        super(ctx);
        bank = new Bank(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28 && bank.opened();
    }

    @Override
    public void execute() {
        bank.depositInventory();
        bank.close();
    }

    @Override
    public String getName() {
        return "Banking Inventory";
    }
}
