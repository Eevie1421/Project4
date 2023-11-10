import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.*;

public class GameStartPanel extends JPanel implements GamePanel{
    private JTextArea txt;
    private CharSelect player1;
    private CharSelect player2;
    private CharSelect player3;
    private CharSelect player4;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    public GameStartPanel(){
        player1 = new CharSelect(1);
        player2 = new CharSelect(2);
        player3 = new CharSelect(3);
        player4 = new CharSelect(4);
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
        c.weighty = 1;
        c.weightx = 1;
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
    @Override
    public void activatePanel(ActionListener a) {
        player1.start.addActionListener(a);
        player2.start.addActionListener(a);
        player3.start.addActionListener(a);
        player4.start.addActionListener(a);
        player1.dropDown.addActionListener(a);
        player2.dropDown.addActionListener(a);
        player3.dropDown.addActionListener(a);
        player4.dropDown.addActionListener(a);
        player1.lockInButton.addActionListener(a);
        player2.lockInButton.addActionListener(a);
        player3.lockInButton.addActionListener(a);
        player4.lockInButton.addActionListener(a);
    }

    private class CharSelect extends JComponent{
        private int playerNum;
        private boolean lockedIn;
        private JButton start;
        private JTextField name;
        private JComboBox<String> dropDown;
        private JTextArea classStats;
        private JButton lockInButton;

        private CharSelect(int playerNum){
            this.playerNum = playerNum;
            start = new JButton("Add Player");
            start.setPreferredSize(new Dimension(380,1200));
            name = new JTextField("Enter Name");
            name.setPreferredSize(new Dimension(380, 100));
            lockedIn = false;
            String[] classes = new String[2];
            classes[0] = "Fighter";
            classes[1] = "Cleric";
            dropDown = new JComboBox<>(classes);
            dropDown.setPreferredSize(new Dimension(300, 100));
            classStats = new JTextArea("Hey kid you seeing this shit?????");
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
