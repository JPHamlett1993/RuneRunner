package BlastFurnace;

import AlKharidSmelter.Tasks.Bar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsGui extends JFrame {

    JFrame mainFrame;

    public SettingsGui() {
        final JRadioButton ironButton = new JRadioButton("Iron");
        final JRadioButton steelButton = new JRadioButton("Steel");
        final JRadioButton goldButton = new JRadioButton("Gold");
        final JRadioButton mithrilButton = new JRadioButton("Mithril");
        final JRadioButton adamantButton = new JRadioButton("Adamant");
        final JRadioButton runeButton = new JRadioButton("Rune");
        JButton startButton = new JButton("Start");

        JLabel label = new JLabel("What bars would you like to Smelt?");

        mainFrame = new JFrame();
        mainFrame.setLayout(new GridLayout(4, 1));

        JPanel buttonFrame = new JPanel();
        buttonFrame.setLayout(new GridLayout(6, 1));
        buttonFrame.add(ironButton);
        buttonFrame.add(steelButton);
        buttonFrame.add(goldButton);
        buttonFrame.add(mithrilButton);
        buttonFrame.add(adamantButton);
        buttonFrame.add(runeButton);

        JPanel optionsPane = new JPanel();
        final JRadioButton iceGlovesButton = new JRadioButton("Use Ice Gloves");
        final JRadioButton goldSmithingGloves = new JRadioButton("Use Gold Gloves");
        final JRadioButton staminaPotions = new JRadioButton("Use Stamina Potion");
        final JRadioButton coalBag = new JRadioButton("Use Coal Bag");
        optionsPane.add(iceGlovesButton);
        optionsPane.add(goldSmithingGloves);
        optionsPane.add(staminaPotions);
        optionsPane.add(coalBag);



        goldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goldSmithingGloves.setEnabled(goldButton.isSelected());
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ironButton.isSelected()) {
                    Furnace.ORES_TO_SMELT = Constants.IRON_ORDER;
                }
                if (steelButton.isSelected()) {
                    Furnace.ORES_TO_SMELT = Constants.STEEL_ORDER;
                }
                if (goldButton.isSelected()) {
                    Furnace.ORES_TO_SMELT = Constants.GOLD_ORDER;
                }
                if (mithrilButton.isSelected()) {
                    Furnace.ORES_TO_SMELT = Constants.MITHRIL_ORDER;
                }
                if (adamantButton.isSelected()) {
                    Furnace.ORES_TO_SMELT = Constants.ADAMANT_ORDER;
                }
                if (runeButton.isSelected()) {
                    Furnace.ORES_TO_SMELT = Constants.RUNITE_ORDER;
                }
            }
        });

        staminaPotions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Furnace.useStaminaPotions = staminaPotions.isSelected();
            }
        });

        goldSmithingGloves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Furnace.useGoldGloves = goldSmithingGloves.isSelected();
            }
        });

        iceGlovesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Furnace.useIceGloves = iceGlovesButton.isSelected();
            }
        });

        coalBag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Furnace.useCoalBag = coalBag.isSelected();
            }
        });

        mainFrame.setSize(250, 500);
        mainFrame.add(label);
        mainFrame.add(buttonFrame);
        mainFrame.add(optionsPane);
        mainFrame.add(startButton);
    }

    public void setVisible(boolean show) {
        mainFrame.setVisible(show);
    }

    public static void main(String[] args) {
        new SettingsGui().setVisible(true);
    }


}
