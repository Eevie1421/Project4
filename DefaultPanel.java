import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.NONE;

public class DefaultPanel extends JPanel implements GamePanel {
    private playerPane[] players;
    private JTextArea txt;
    private JButton moveRooms;

    public DefaultPanel(int playerNum){
        this.players = new playerPane[playerNum];
        txt = new JTextArea();
        moveRooms = new JButton("Move Rooms");
        moveRooms.setActionCommand("Move");
        moveRooms.setPreferredSize(new Dimension(400,800));
        txt.setPreferredSize(new Dimension(1600, 400));
        setPreferredSize(new Dimension(1600, 1200));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill =BOTH;
        c.weightx = 1;
        c.weighty = 1;
        //sets the MoveRooms button
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridheight = 4;
        add(moveRooms, c);
        //sets the text area
        c.gridy = 4;
        c.gridheight = 2;
        c.gridwidth = 8;
        add(txt, c);
        //sets the player areas
        c.gridy = 0;
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        for(int i = 0; i < playerNum; i++){
            c.gridx = 2;
            this.players[i] = new playerPane("TEST", 0);
            add(this.players[i].getStatus(), c);
            c.gridx = 4;
            add(this.players[i].getItems(), c);
            c.gridx = 6;
            add(this.players[i].getInteract(), c);
            c.gridy = i + 1;
        }

    }

    public DefaultPanel(){
        this(4);
    }

    public void action(ActionEvent e){

    }

    @Override
    public void setText(String s){
        txt.setText(s);
    }
    @Override
    public void activatePanel(ActionListener a, ItemListener e, Player[] names) {
        moveRooms.addActionListener(a);
        for (playerPane player : players) {
            player.setButtons(a);
        }
        players[0].status.setText(names[0].getName() + " Health : " + names[0].checkHealth());
        players[1].status.setText(names[0].getName() + " Health : " + names[1].checkHealth());
        players[2].status.setText(names[0].getName() + " Health : " + names[2].checkHealth());
        players[3].status.setText(names[0].getName() + " Health : " + names[3].checkHealth());
    }

    @Override
    public int actionSignal(ActionEvent e) {
        return 0;
    }

    private class playerPane{
        private JButton items;
        private JButton interact;
        private JTextArea status;

        private playerPane(String name, int health){
            status = new JTextArea(name + ": " + health);
            items = new JButton("Use Item");
            interact = new JButton("Interact");
            items.setPreferredSize(new Dimension(400,200));
            interact.setPreferredSize(new Dimension(400,200));
            status.setPreferredSize(new Dimension(400, 200));
        }

        public JButton getInteract() {
            return interact;
        }

        public JButton getItems() {
            return items;
        }

        public JTextArea getStatus() {
            return status;
        }

        private void setButtons(ActionListener a){
            items.addActionListener(a);
            interact.addActionListener(a);
        }
    }
}
