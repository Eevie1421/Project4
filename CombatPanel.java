import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CombatPanel extends JPanel {
    private playerPanel player1;
    private playerPanel player2;
    private playerPanel player3;
    private playerPanel player4;
    private JTextArea textarea;
    private JPanel combatUi;


    public CombatPanel(){
        player1 = new playerPanel("Joe1");
        player2 = new playerPanel("Joe2");
        player3 = new playerPanel("Joe3");
        player4 = new playerPanel("Joe4");
        textarea = new JTextArea();
        textarea.setPreferredSize(new Dimension(1600, 400));
        textarea.setText("Test");
        combatUi = new JPanel();
        combatUi.setPreferredSize(new Dimension(400, 800));
        JButton startCombat = new JButton("Start Combat");
        startCombat.setPreferredSize(new Dimension(400, 800));
        combatUi.add(startCombat);

        setPreferredSize(new Dimension(1600, 1200));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = .35;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 8;
        c.gridwidth = 4;
        add(combatUi, c);

        c.weightx = 1;
        c.gridx = 4;
        c.gridwidth = 12;
        c.gridheight = 2;
        add(player1, c);
        c.gridy = 2;
        add(player2, c);
        c.gridy = 4;
        add(player3, c);
        c.gridy = 6;
        add(player4, c);

        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 16;
        c.gridheight = 4;
        add(textarea, c);

    }

    public void activatePanel(ActionListener a) {
        player1.attack.addActionListener(a);
        player2.attack.addActionListener(a);
        player3.attack.addActionListener(a);
        player4.attack.addActionListener(a);
        player1.item.addActionListener(a);
        player2.item.addActionListener(a);
        player3.item.addActionListener(a);
        player4.item.addActionListener(a);
        player1.rollInitiative.addActionListener(a);
        player2.rollInitiative.addActionListener(a);
        player3.rollInitiative.addActionListener(a);
        player4.rollInitiative.addActionListener(a);
        player1.useAbility.addActionListener(a);
        player2.useAbility.addActionListener(a);
        player3.useAbility.addActionListener(a);
        player4.useAbility.addActionListener(a);
    }

    public int actionSignal(ActionEvent e){
        int actionType = -1;
        if(e.getSource().equals(player1.rollInitiative)){
            player1.enterCombat();
            actionType = 0;
        }
        else if(e.getSource().equals(player2.rollInitiative)){
            player2.enterCombat();
            actionType = 0;
        }
        else if(e.getSource().equals(player3.rollInitiative)){
            player3.enterCombat();
            actionType = 0;
        }
        else if(e.getSource().equals(player4.rollInitiative)){
            player4.enterCombat();
            actionType = 0;
        }
        else if(e.getActionCommand().equals("atk")){
            actionType = 1;
            textarea.setText("Haiiiiiiii!!!! :3");
        }
        else if(e.getActionCommand().equals("abl")){
            actionType = 2;
            textarea.setText("Haiiiiiiii!!!! :3");
        }
        else if(e.getActionCommand().equals("itm")) {
            actionType = 3;
            textarea.setText("Haiiiiiiii!!!! :3");
        }
        return actionType;
    }

    private class playerPanel extends JPanel{
        private JTextArea playerName;
        private JButton attack;
        private JButton item;
        private JButton rollInitiative;
        private JButton useAbility;
        private playerPanel(String name){
            rollInitiative = new JButton("Roll Initiative");
            rollInitiative.setPreferredSize(new Dimension(1200, 200));
            useAbility = new JButton("Ability");
            useAbility.setActionCommand("abl");
            useAbility.setPreferredSize(new Dimension(300, 200));
            playerName = new JTextArea(name);
            playerName.setPreferredSize(new Dimension(300, 200));
            attack = new JButton("Attack");
            attack.setActionCommand("atk");
            attack.setPreferredSize(new Dimension(300, 200));
            item = new JButton("Item");
            item.setActionCommand("itm");
            item.setPreferredSize(new Dimension(300, 200));
            add(rollInitiative);
        }
        private void enterCombat(){
            remove(rollInitiative);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.weighty = .15;
            c.weightx = 1;
            add(playerName, c);
            c.fill = GridBagConstraints.BOTH;
            c.weighty = 1;
            c.gridx = 1;
            add(attack, c);
            c.gridx = 2;
            add(useAbility, c);
            c.gridx = 3;
            add(item, c);
            revalidate();
        }

    }
}