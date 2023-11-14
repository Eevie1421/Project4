import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class CombatPanel extends JPanel  implements GamePanel{
    private playerPanel player1;
    private playerPanel player2;
    private playerPanel player3;
    private playerPanel player4;
    private JTextArea textarea;
    private JPanel combatUi;
    private JButton startCombat;
    private JButton nextTurn;
    private ArrayList<Enemy> enemies;
    private ArrayList<JTextArea> enemyStatus;
    private JButton moveButton;
    private boolean complete;


    public CombatPanel(Player[] players){
        player1 = new playerPanel(players[0].getName());
        player2 = new playerPanel(players[1].getName());
        player3 = new playerPanel(players[2].getName());
        player4 = new playerPanel(players[3].getName());
        textarea = new JTextArea();
        textarea.setPreferredSize(new Dimension(1600, 400));
        textarea.setText("Test");
        combatUi = new JPanel();
        combatUi.setPreferredSize(new Dimension(400, 800));
        startCombat = new JButton("Start Combat");
        startCombat.setActionCommand("Start Combat");
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

        enemies = new ArrayList<>();
        enemyStatus = new ArrayList<>();
        nextTurn = new JButton("Take next turn");
        nextTurn.setPreferredSize(new Dimension(400, 200));
        nextTurn.setActionCommand("Next Turn");

        moveButton = new JButton("Move rooms");
        moveButton.setPreferredSize(new Dimension(400, 800));
        moveButton.setActionCommand("Move");
    }

    public CombatPanel(){
        this(new Player[]{new Player("Joe", 1), new Player("Jim",1), new Player("Jill", 0), new Player("Greg", 0)});
    }

    @Override
    public void activatePanel(ActionListener a, ItemListener e, Player[] players) {
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

        if(players[0] != null && players[0].isALive()){
            player1.playerName.setText(players[0].getName());
        }
        else{
            player1.enterCombat();
            remove(player1);
        }

        if(players[1] != null && players[1].isALive()){
            player2.playerName.setText(players[1].getName());
        }
        else {
            player2.enterCombat();
            remove(player2);
        }

        if(players[2] != null && players[2].isALive()){
            player3.playerName.setText(players[2].getName());
        }
        else {
            player3.enterCombat();
            remove(player3);
        }

        if(players[3] != null && players[3].isALive()){
            player4.playerName.setText(players[3].getName());
        }
        else {
            player4.enterCombat();
            remove(player4);
        }

        startCombat.addActionListener(a);
        nextTurn.addActionListener(a);
        moveButton.addActionListener(a);
    }

    @Override
    public int actionSignal(ActionEvent e){
        int actionType = -6;
        if(e.getSource().equals(player1.rollInitiative)){
            player1.enterCombat();
            actionType = -1;
        }
        else if(e.getSource().equals(player2.rollInitiative)){
            player2.enterCombat();
            actionType = -2;
        }
        else if(e.getSource().equals(player3.rollInitiative)){
            player3.enterCombat();
            actionType = -3;
        }
        else if(e.getSource().equals(player4.rollInitiative)){
            player4.enterCombat();
            actionType = -4;
        }
        else if(e.getActionCommand().equals("atk")){
            actionType = 1;
        }
        else if(e.getActionCommand().equals("abl")){
            actionType = 2;
        }
        else if(e.getActionCommand().equals("itm")) {
            actionType = 3;
        }
        else if(e.getActionCommand().equals("Move")){
            actionType = 0;
        }
        else if(e.getActionCommand().equals("Next Turn")){
            actionType = -5;
        }
        return actionType;
    }

    @Override
    public void setText(String s) {
        textarea.setText(s);
    }

    public void beginCombat(ArrayList<Enemy> enemies1){
        enemies.addAll(enemies1);
        combatUi.remove(startCombat);
        combatUi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = 1;
        for(int i = 0; i < enemies.size(); i++){
            c.gridy = i;
            Enemy temp = enemies.get(i);
            enemyStatus.add(new JTextArea(temp.getName() + i +" HP: " + temp.getHealth()));
            enemyStatus.get(i).setPreferredSize(new Dimension(400, 100));
            combatUi.add(enemyStatus.get(i), c);
        }
        c.gridy = enemies.size();
        combatUi.add(nextTurn, c);
        combatUi.revalidate();
    }

    public void updateEnemies(ArrayList<Enemy> e){
        enemies = e;
        for(int i = 0; i < e.size(); i++){
            Enemy temp = e.get(i);
            if(!temp.isALive()){
                enemyStatus.get(i).setText(temp.getName() + i + " DEAD");
            }
            enemyStatus.get(i).setText(temp.getName() + i + " HP: " + temp.getHealth());
        }
    }
    public void endCombat(){
        for(JTextArea t : enemyStatus){
            combatUi.remove(t);
        }
        combatUi.remove(nextTurn);
        combatUi.add(moveButton);
        revalidate();
    }

    public boolean isCombatReady(){
        return player1.inCombat && player2.inCombat && player3.inCombat && player4.inCombat;
    }

    private static class playerPanel extends JPanel{
        private JTextArea playerName;
        private JButton attack;
        private JButton item;
        private JButton rollInitiative;
        private JButton useAbility;
        private boolean inCombat;
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
            inCombat = false;
        }
        private void enterCombat(){
            remove(rollInitiative);
            inCombat = true;

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
