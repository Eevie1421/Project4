import javax.swing.*;
import java.awt.*;
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
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 8;
        c.gridwidth = 4;
        add(combatUi, c);

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
            useAbility.setPreferredSize(new Dimension(300, 200));
            playerName = new JTextArea(name);
            playerName.setPreferredSize(new Dimension(300, 200));
            attack = new JButton("Attack");
            attack.setPreferredSize(new Dimension(300, 200));
            item = new JButton("Item");
            item.setPreferredSize(new Dimension(300, 200));
            add(rollInitiative);
        }
        private void enterCombat(){
            remove(rollInitiative);
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.weighty = 1;
            c.weightx = 1;
            add(playerName, c);
            c.gridx = 1;
            add(attack, c);
            c.gridx = 2;
            add(useAbility, c);
            revalidate();
        }
    }
}
