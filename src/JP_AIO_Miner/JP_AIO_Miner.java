package JP_AIO_Miner;

import JP_AIO_Miner.Enums.Bars;
import JP_AIO_Miner.Enums.Locations;
import JP_AIO_Miner.Enums.Ores;
import JP_AIO_Miner.GUI.StartForm;
import JP_AIO_Miner.Task.*;

import org.powerbot.script.Condition;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Script.Manifest(name="JP AIO Miner", description="JP AIO Miner")
public class JP_AIO_Miner extends PollingScript<ClientContext>  implements PaintListener {

    private List<Task> taskList = new ArrayList();

    public static String state = "None";

    public static Locations location;
    public static Ores ore;
    public static Bars bar;
    public static int amountMined = 0;

    @Override
    public void start() {
        new StartForm();
        taskList.addAll(Arrays.asList(new Mine(ctx), new WalkToRocks(ctx), new WalkToBank(ctx), new Bank(ctx)));
    }

    @Override
    public void poll() {
        while (ore == null && bar == null) {
            Condition.sleep();
        }
        for (Task task: taskList) {
            if (task.activate()) {
                task.execute();
            }
        }

    }

    public static void printStatus(String status) {
        System.out.println("---------------------------- Status ----------------------------");
        System.out.println(status);
    }

    //START: Code generated using Enfilade's Easel
    private final Color color1 = new Color(255, 51, 51);
    private final Color color2 = new Color(0, 0, 0);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 0, 17);

    public String longToTimeStamp(long millis) {
        long seconds, minutes, hours;
        seconds = millis / 1000;
        minutes = seconds / 60;
        seconds = seconds % 60;
        hours = minutes / 60;
        minutes = minutes % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public double estimatePerHour() {
        return (int) (3600000d / (long) getRuntime() * (double) (amountMined));
    }

    public static int getAmountMined(ClientContext ctx) {
        if (JP_AIO_Miner.ore == null) {
            switch (JP_AIO_Miner.bar) {
                case BRONZE: {
                    return ctx.inventory.select().id(Constants.TIN_ORE).count();
                }
                case IRON: {
                    return ctx.inventory.select().id(Constants.IRON_ORE).count();
                }
                case SILVER: {
                    return ctx.inventory.select().id(Constants.SILVER_ORE).count();
                }
                case STEEL: {
                    return ctx.inventory.select().id(Constants.IRON_ORE).count();
                }
                case GOLD: {
                    return ctx.inventory.select().id(Constants.GOLD_ORE).count();
                }
                case MITHRIL: {
                    return ctx.inventory.select().id(Constants.MITHRIL_ORE).count();
                }
                case ADAMANT: {
                    return ctx.inventory.select().id(Constants.ADAMANT_ORE).count();
                }
                case RUNE: {
                    return ctx.inventory.select().id(Constants.RUNE_ORE).count();
                }
            }
        } else {
            switch (JP_AIO_Miner.ore) {
                case TIN : {
                    return ctx.inventory.select().id(Constants.TIN_ORE).count();
                }
                case COPPER : {
                    return ctx.inventory.select().id(Constants.COPPER_ORE).count();
                }
                case IRON : {
                    return ctx.inventory.select().id(Constants.IRON_ORE).count();
                }
                case SILVER: {
                    return ctx.inventory.select().id(Constants.SILVER_ORE).count();
                }
                case COAL: {
                    return ctx.inventory.select().id(Constants.COAL_ORE).count();
                }
                case GOLD: {
                    return ctx.inventory.select().id(Constants.GOLD_ORE).count();
                }
                case MITHRIL: {
                    return ctx.inventory.select().id(Constants.MITHRIL_ORE).count();
                }
                case ADAMANT: {
                    return ctx.inventory.select().id(Constants.ADAMANT_ORE).count();
                }
                case RUNE: {
                    return ctx.inventory.select().id(Constants.RUNE_ORE).count();
                }
            }
        }
        return 0;
    }

    @Override
    public void repaint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.fillRoundRect(3, 4, 250, 160, 16, 16);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRoundRect(3, 4, 250, 160, 16, 16);
        g.setFont(font1);
        g.drawString("Time Running: " + longToTimeStamp(getRuntime()), 9, 35);
        if (bar == null) {
            g.drawString("Mining: " + ore + " ores", 9, 65);
        } else {
            g.drawString("Mining: " + bar + " bars", 9, 65);
        }
        g.drawString("Amount Mined: " + amountMined, 9, 95);
        g.drawString("Amount Per Hour: " + estimatePerHour(), 9, 125);
        g.drawString("Current State: " + state, 9, 155);
    }
    //END: Code generated using Enfilade's Easel
}