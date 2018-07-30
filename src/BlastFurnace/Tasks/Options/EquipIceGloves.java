package BlastFurnace.Tasks.Options;

import BlastFurnace.Constants;
import BlastFurnace.Tasks.Task;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Equipment;

public class EquipIceGloves extends Task<ClientContext> {

    public EquipIceGloves(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.equipment.itemAt(Equipment.Slot.HANDS).id() != Constants.ICE_GLOVE_ID & ctx.inventory.select().id(Constants.ICE_GLOVE_ID).count() > 0;
    }

    @Override
    public void execute() {
        ctx.inventory.select().id(Constants.ICE_GLOVE_ID).poll().interact("Wear");
    }

    @Override
    public String getName() {
        return "Equipping ice gloves";
    }
}
