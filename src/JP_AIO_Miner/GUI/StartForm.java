package JP_AIO_Miner.GUI;

import JP_AIO_Miner.Enums.Bars;
import JP_AIO_Miner.Enums.Locations;
import JP_AIO_Miner.Enums.Ores;
import JP_AIO_Miner.JP_AIO_Miner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class StartForm extends JFrame {

    private JPanel setupLocationTab() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        String[] locations = {"Extended Mining Guild", "F2P Mining Guild", "Varrock West (Not supported)", "Varrock East"};
        final JComboBox jComboBox = new JComboBox(locations);
        jPanel.add(jComboBox, BorderLayout.NORTH);
        JButton setButton = new JButton("Set Location");
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JP_AIO_Miner.location = getLocationFromString(jComboBox.getSelectedIndex());
            }

            private Locations getLocationFromString(int selectedItem) {
                switch (selectedItem) {
                    case 0: {
                        return Locations.EXTENDED_MINING_GUILD;
                    }
                    case 1 : {
                        return Locations.F2P_MINING_GUILD;
                    }
                    case 2: {
                        return null;
                    }
                    case 3: {
                        return Locations.VARROCK_EAST;
                    }
                }
                return null;
            }
        });
        jPanel.add(setButton, BorderLayout.SOUTH);
        return jPanel;
    }

    private JPanel setupOresPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4, 1));
        String[] ores = {"Tin", "Copper", "Iron", "Silver", "Coal", "Gold", "Mithril", "Adamant", "Rune"};
        String[] bars = {"Bronze", "Iron", "Silver", "Steel", "Gold", "Mithril", "Adamant", "Rune"};

        final JComboBox oresComboBox = new JComboBox(ores);
        final JComboBox barComboBox = new JComboBox(bars);
        barComboBox.setEnabled(false);
        final JCheckBox oreCheckBox = new JCheckBox("Do you want to mine full inventory or ores or for bars");
        oreCheckBox.setSelected(true);
        oreCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oresComboBox.setEnabled(oreCheckBox.isSelected());
                barComboBox.setEnabled(!oreCheckBox.isSelected());
            }
        });
        JButton setButton = new JButton("Set Ores");
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (oreCheckBox.isSelected()) {
                    JP_AIO_Miner.ore = getOreFromString(oresComboBox.getSelectedIndex());
                    JP_AIO_Miner.bar = null;
                } else {
                    JP_AIO_Miner.bar = getBarFromString(barComboBox.getSelectedIndex());
                    JP_AIO_Miner.ore = null;
                }

            }

            private Ores getOreFromString(int selectedItem) {
                switch (selectedItem) {
                    case 0: {
                        return Ores.TIN;
                    }
                    case 1 : {
                        return Ores.COPPER;
                    }
                    case 2 : {
                        return Ores.IRON;
                    }
                    case 3 : {
                        return Ores.SILVER;
                    }
                    case 4 : {
                        return Ores.COAL;
                    }
                    case 5 : {
                        return Ores.GOLD;
                    }
                    case 6 : {
                        return Ores.MITHRIL;
                    }
                    case 7 : {
                        return Ores.ADAMANT;
                    }
                    case 8 : {
                        return Ores.RUNE;
                    }
                }
                return null;
            }

            private Bars getBarFromString(int selectedItem) {
                switch (selectedItem) {
                    case 0 : {
                        return Bars.BRONZE;
                    }
                    case 1 : {
                        return Bars.IRON;
                    }
                    case 2 : {
                        return Bars.SILVER;
                    }
                    case 3 : {
                        return Bars.STEEL;
                    }
                    case 4 : {
                        return Bars.GOLD;
                    }
                    case 5 : {
                        return Bars.MITHRIL;
                    }
                    case 6 : {
                        return Bars.ADAMANT;
                    }
                    case 7 : {
                        return Bars.RUNE;
                    }
                }
                return null;
            }
        });
        jPanel.add(oreCheckBox);
        jPanel.add(oresComboBox);
        jPanel.add(barComboBox);
        jPanel.add(setButton);
        return jPanel;
    }

    public StartForm() {
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.add("Location", setupLocationTab());
        jTabbedPane.add("Ore", setupOresPanel());
        this.setLayout(new GridLayout(2, 3));
        this.add(jTabbedPane);
        this.setVisible(true);
    }

}
