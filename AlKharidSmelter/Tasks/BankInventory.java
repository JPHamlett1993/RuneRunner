package AlKharidSmelter.Tasks;

import AlKharidSmelter.Constants;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;

public class BankInventory extends Task<ClientContext> {

    Bank bank;

    public BankInventory(ClientContext ctx) {
        super(ctx);
        bank = new Bank(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() > 0 && bank.opened();
    }

    @Override
    public void execute() {
        bank.depositInventory();
        bank.withdraw(Constants.getTinId(), 14);
        bank.withdraw(Constants.getCopperId(), 14);
        bank.close();
    }

    @Override
    public String getName() {
        return "Depositing Bars";
    }
}
