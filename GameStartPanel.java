import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;

import static java.awt.GridBagConstraints.BOTH;
import static javax.swing.SwingConstants.HORIZONTAL;

public class GameStartPanel extends JPanel {
    private CharSelect player1;
    private CharSelect player2;
    private CharSelect player3;
    private CharSelect player4;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JButton startButton;
    private Hashtable<String, String> classText;

    public GameStartPanel() {
        classText = new Hashtable<>();

        player1 = new CharSelect(1);
        player2 = new CharSelect(2);
        player3 = new CharSelect(3);
        player4 = new CharSelect(4);

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(400, 200));

        panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(1600, 200));
        panel5.add(startButton);

        panel1 = new JPanel();
        panel1.add(player1.start);

        panel2 = new JPanel();
        panel2.add(player2.start);

        panel3 = new JPanel();
        panel3.add(player3.start);

        panel4 = new JPanel();
        panel4.add(player4.start);

        setPreferredSize(new Dimension(1600, 1200));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = .8;
        c.weightx = 1;
        c.gridheight = 5;
        c.fill = BOTH;
        c.gridx = 0;
        c.gridy = 0;
        add(panel1, c);
        c.gridx = 1;
        add(panel2, c);
        c.gridx = 2;
        add(panel3, c);
        c.gridx = 3;
        add(panel4, c);
        c.gridx = 0;
        c.gridwidth = 4;
        c.gridy = 5;
        c.weighty = .2;
        add(panel5, c);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starting the game!");
            }
        });

        player1.lockInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Player 1 locked in!");
            }
        });

        player2.lockInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Player 2 locked in!");
            }
        });

        player3.lockInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Player 3 locked in!");
            }
        });

        player4.lockInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Player 4 locked in!");
            }
        });
    }

    public int actionSignal(ActionEvent e) {
        if (e.getSource() == startButton) {
            System.out.println("Start button pressed!");
            return 2; // Assuming 2 is the action type to start the game
        } else if (e.getSource() == player1.lockInButton) {
            System.out.println("Player 1 locked in!");
            return 1; // Assuming 1 is the action type for locking in a player
        } else if (e.getSource() == player2.lockInButton) {
            System.out.println("Player 2 locked in!");
            return 1;
        } else if (e.getSource() == player3.lockInButton) {
            System.out.println("Player 3 locked in!");
            return 1;
        } else if (e.getSource() == player4.lockInButton) {
            System.out.println("Player 4 locked in!");
            return 1;
        }
        return 0;
    }
    public void activatePanel(ActionListener a, ItemListener i) {
        player1.start.addActionListener(a);
        player2.start.addActionListener(a);
        player3.start.addActionListener(a);
        player4.start.addActionListener(a);
        player1.dropDown.addItemListener(i);
        player2.dropDown.addItemListener(i);
        player3.dropDown.addItemListener(i);
        player4.dropDown.addItemListener(i);
    }

    public void addPlayer(ActionEvent e) {
        if (e.getSource().equals(player1.start)) {
            System.out.println("Adding Player 1");
        } else if (e.getSource().equals(player2.start)) {
            System.out.println("Adding Player 2");
        } else if (e.getSource().equals(player3.start)) {
            System.out.println("Adding Player 3");
        } else if (e.getSource().equals(player4.start)) {
            System.out.println("Adding Player 4");
        }
    }

    public void updateClassPanel(ItemEvent e) {
        String text = (String) e.getItem();
        if (e.getSource().equals(player1.dropDown)) {
            player1.playerClass = text;
            text = classText.get(text);
            player1.classStats.setText(text);
        } else if (e.getSource().equals(player2.dropDown)) {
            player2.playerClass = text;
            text = classText.get(text);
            player2.classStats.setText(text);
        } else if (e.getSource().equals(player3.dropDown)) {
            player3.playerClass = text;
            text = classText.get(text);
            player3.classStats.setText(text);
        } else if (e.getSource().equals(player4.dropDown)) {
            player4.playerClass = text;
            text = classText.get(text);
            player4.classStats.setText(text);
        }
    }

    private class CharSelect extends JPanel {
        private int playerNum;
        private boolean lockedIn;
        private String playerName;
        private String playerClass;
        private JButton start;
        private JTextField name;
        private JComboBox<String> dropDown;
        private JTextArea classStats;
        private JButton lockInButton;

        private CharSelect(int playerNum) {
            this.playerNum = playerNum;
            start = new JButton("Add Player");
            start.setPreferredSize(new Dimension(395, 1000));
            name = new JTextField("Enter Name");
            name.setPreferredSize(new Dimension(395, 100));
            lockedIn = false;
            String[] classes = new String[2];
            classes[0] = "Fighter";
            playerClass = "Fighter";
            classes[1] = "Cleric";
            dropDown = new JComboBox<>(classes);
            dropDown.setPreferredSize(new Dimension(395, 100));
            classStats = new JTextArea(classText.get("Fighter"));
            classStats.setPreferredSize(new Dimension(400, 800));
            lockInButton = new JButton("Lock In");
            lockInButton.setPreferredSize(new Dimension(400, 200));

            lockInButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Player " + playerNum + " locked in!");
                }
            });

            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.fill = HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridheight = 1;
            add(name, c);
            c.gridy = 1;
            add(dropDown, c);
            c.weighty = 1;
            c.fill = BOTH;
            c.gridy = 2;
            c.gridheight = 8;
            add(classStats, c);
            c.gridy = 10;
            c.gridheight = 2;
            add(lockInButton, c);
        }

        public int getPlayerNum() {
            return playerNum;
        }

        public boolean isLockedIn() {
            return lockedIn;
        }

        public void lockIn() {
            lockedIn = true;
        }
    }
}
