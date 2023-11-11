import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;

import static java.awt.GridBagConstraints.*;

public class GameStartPanel extends JPanel{
    private JTextArea txt;
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

    public GameStartPanel(){
        classText = new Hashtable<>();
        String classBffr;
        classBffr = "Fighter Class\n" + "Max Health: 30\n" + "Armor Class: 15\n" + "Attack Bonus: +3\n" + "Initiative Bonus: 0\n" + "Ability: none";
        classText.put("Fighter", classBffr);
        classBffr  = "Cleric Class\n" + "Max Health: 40\n" + "Armor Class: 12\n" + "Attack Bonus: +2\n" + "Initiative Bonus: 3\n" + "Ability: cure wounds";
        classText.put("Cleric", classBffr);
        player1 = new CharSelect(1);
        player2 = new CharSelect(2);
        player3 = new CharSelect(3);
        player4 = new CharSelect(4);
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(400,200));
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
    }

    public void addPlayer(ActionEvent e){
        if(e.getSource().equals(player1.start)){
            panel1.remove(player1.start);
            panel1.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.fill = HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridheight = 1;
            panel1.add(player1.name, c);
            c.gridy = 1;
            panel1.add(player1.dropDown, c);
            c.weighty = 1;
            c.fill = BOTH;
            c.gridy = 2;
            c.gridheight = 8;
            panel1.add(player1.classStats, c);
            c.gridy = 10;
            c.gridheight = 2;
            panel1.add(player1.lockInButton, c);
            panel1.revalidate();

        }
        else if(e.getSource().equals(player2.start)){
            panel2.remove(player2.getStart());
            panel2.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.fill = HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridheight = 1;
            panel2.add(player2.name,c);
            c.gridy = 1;
            panel2.add(player2.dropDown,c);
            c.weighty = 1;
            c.fill = BOTH;
            c.gridy = 2;
            c.gridheight = 8;
            panel2.add(player2.classStats,c);
            c.gridy = 10;
            c.gridheight = 2;
            panel2.add(player2.lockInButton,c);
            panel2.revalidate();
        }
        else if(e.getSource().equals(player3.start)){
            panel3.remove(player3.getStart());
            panel3.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.fill = HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridheight = 1;
            panel3.add(player3.name,c);
            c.gridy = 1;
            panel3.add(player3.dropDown,c);
            c.weighty = 1;
            c.fill = BOTH;
            c.gridy = 2;
            c.gridheight = 8;
            panel3.add(player3.classStats,c);
            c.gridy = 10;
            c.gridheight = 2;
            panel3.add(player3.lockInButton,c);
            panel3.revalidate();
        }
        else if(e.getSource().equals(player4.start)){
            panel4.remove(player4.getStart());
            panel4.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 1;
            c.fill = HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridheight = 1;
            panel4.add(player4.name,c);
            c.gridy = 1;
            panel4.add(player4.dropDown,c);
            c.weighty = 1;
            c.fill = BOTH;
            c.gridy = 2;
            c.gridheight = 8;
            panel4.add(player4.classStats,c);
            c.gridy = 10;
            c.gridheight = 2;
            panel4.add(player4.lockInButton,c);
            panel4.revalidate();
        }
    }

    public void updateClassPanel(ItemEvent e){
        String text =(String) e.getItem();
        if(e.getSource().equals(player1.dropDown)){
            player1.playerClass = text;
            text = classText.get(text);
            player1.classStats.setText(text);
        }
        else if(e.getSource().equals(player2.dropDown)){
            player2.playerClass = text;
            text = classText.get(text);
            player2.classStats.setText(text);
        }
        else if(e.getSource().equals(player3.dropDown)){
            player3.playerClass = text;
            text = classText.get(text);
            player3.classStats.setText(text);
        }
        else if(e.getSource().equals(player4.dropDown)){
            player4.playerClass = text;
            text = classText.get(text);
            player4.classStats.setText(text);
        }
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
        player1.lockInButton.addActionListener(a);
        player2.lockInButton.addActionListener(a);
        player3.lockInButton.addActionListener(a);
        player4.lockInButton.addActionListener(a);
    }

    private class CharSelect extends JComponent{
        private int playerNum;
        private boolean lockedIn;
        private String playerName;
        private String playerClass;
        private JButton start;
        private JTextField name;
        private JComboBox<String> dropDown;
        private JTextArea classStats;
        private JButton lockInButton;

        private CharSelect(int playerNum){
            this.playerNum = playerNum;
            start = new JButton("Add Player");
            start.setPreferredSize(new Dimension(395,1000));
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
        }

        public int getPlayerNum() {
            return playerNum;
        }

        public JButton getStart() {
            return start;
        }

        public boolean isLockedIn(){
            return lockedIn;
        }

        public void lockIn(){
            lockedIn = true;
        }

        public JTextField getNameBox() {
            return name;
        }
    }
}
