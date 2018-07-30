package BlastFurnace;

import BlastFurnace.Tasks.Bank.*;
import BlastFurnace.Tasks.*;
import BlastFurnace.Tasks.ConveyorBelt.*;
import BlastFurnace.Tasks.Dispensery.*;
import BlastFurnace.Tasks.Options.DrinkStaminaPotion;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name="JPs Blast Furnace", description = "Smelts All Bars in Al Khararid", properties = "author=John; topic=999; client=4")
public class Furnace extends PollingScript<ClientContext> implements PaintListener {


    public static Ore[] ORES_TO_SMELT = {null};
    static SettingsGui settingsGui = new SettingsGui();

    private List<Task> taskList = new ArrayList<Task>();
    private String state = "None";
    public static int numberSmelted = 0;
    public static boolean useIceGloves = false;
    public static boolean useStaminaPotions = false;
    public static boolean useGoldGloves = false;
    public static boolean useCoalBag = false;
    private int startingExp = ctx.skills.experience(Constants.SMITHING_SKILL_INDEX);

    @Override
    public void start() {
        settingsGui.setVisible(true);
        taskList.addAll(Arrays.asList(
                new CloseBank(ctx),
                new DepositInventory(ctx),
                new OpenBank(ctx),
                new WalkToBank(ctx),
                new WithdrawOres(ctx),
                new MoveToBelt(ctx),
                new PutOreOnConveyorBelt(ctx),
                new InteractWithDispenser(ctx),
                new MoveToDispensery(ctx),
                new TakeBarsOutOfDispenser(ctx),
                new DrinkStaminaPotion(ctx)));
    }

    //START: Code generated using Enfilade's Easel
    private final Color color1 = new Color(255, 51, 51);
    private final Color color2 = new Color(255, 255, 255);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 0, 17);

    public String longToTimeStamp(long millis) {
        int seconds = (int) (millis / 1000) % 60 ;
        int minutes = (int) ((millis / (1000*60)) % 60);
        int hours   = (int) ((millis / (1000*60*60)) % 24);
        return String.format("%02d:%02d:%02d",
                hours, minutes, seconds);
    }

    public double estimatePerHour(int amount) {
        return (amount * 3600000D) / getRuntime();
    }

    @Override
    public void repaint(Graphics g1) {
        final Color color1 = new Color(255, 255, 255);
        final Color color2 = new Color(0, 0, 0);
        final Color color3 = new Color(255, 0, 51);

        final BasicStroke stroke1 = new BasicStroke(1);

        final Font font1 = new Font("Al Bayan", 0, 15);
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.fillRect(7, 393, 504, 131);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRect(7, 393, 504, 131);
        g.setFont(font1);
        g.setColor(color3);
        g.drawString("Time Running: " + longToTimeStamp(getRuntime()), 9, 415);
        g.drawString("Amount Smelted: " + numberSmelted, 9, 435);
        g.drawString("Amount Per Hour: " + estimatePerHour(numberSmelted), 9, 455);
        int expGained = ctx.skills.experience(Constants.SMITHING_SKILL_INDEX) - startingExp;
        g.drawString("Exp Gained: " + expGained, 9, 475);
        g.drawString("Exp Per Hour: " + estimatePerHour(expGained), 9, 495);
        g.drawString("Current State: " + state, 9, 515);
    }
    //END: Code generated using Enfilade's Easel

    @Override
    public void poll() {
        for (Task task: taskList) {
            if (task.activate()) {
                state = task.getName();
                task.execute();
            }
        }
    }

    public static void printMessage(String message) {
        System.out.println("--------------------------------------------------");
        System.out.println(message);
        System.out.println("--------------------------------------------------");
    }


}
