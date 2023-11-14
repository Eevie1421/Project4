import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import static java.awt.GridBagConstraints.BOTH;


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
            this.players[i] = new playerPane("TEST", 0);
            add(players[i], c);
            c.gridy = i + 1;
        }

    }

    public DefaultPanel(){
        this(4);
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
        for(int i = 0; i < names.length; i++){
            if(!names[i].isALive()){
                remove(players[i]);
            }
        }
        players[0].status.setText(names[0].getName() + " Health : " + names[0].checkHealth());
        players[1].status.setText(names[0].getName() + " Health : " + names[1].checkHealth());
        players[2].status.setText(names[0].getName() + " Health : " + names[2].checkHealth());
        players[3].status.setText(names[0].getName() + " Health : " + names[3].checkHealth());
        revalidate();
    }

    @Override
    public int actionSignal(ActionEvent e) {
        int signal = -1;
        for(int i = 0; i < players.length; i++){
            if(players[i].contains(e.getSource())){
                return i + 1;
            }
        }
        if(e.getSource().equals(moveRooms)){
            signal = 0;
        }
        return signal;
    }

    private class playerPane extends JComponent{
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
            items.setActionCommand("Item");
            interact.setActionCommand("Interact");
        }

        private void setButtons(ActionListener a){
            items.addActionListener(a);
            interact.addActionListener(a);
        }


        public boolean contains(Object j) {
            return j.equals(items) || j.equals(interact);
        }
    }
}
