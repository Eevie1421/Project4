import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import static java.awt.GridBagConstraints.BOTH;

/**
 * Basic panel for any room that doesn't have special functions.
 * has functionality for moving rooms, as well as interacting with the room, and item usage.
 * @author  @author Jonathan Murphy, Evelyn Totman, Salim Jday
 */
public class DefaultPanel extends JPanel implements GamePanel {
    private playerPane[] players;
    private JTextArea txt;
    private JButton moveRooms;

    /**
     * Constructor
     * Sets the player areas, moveRooms button, and text area.
     * @param playerNum - number of players
     */
    public DefaultPanel(int playerNum){
        setPreferredSize(new Dimension(1600, 1200));
        setLayout(new GridBagLayout());
        this.players = new playerPane[playerNum];
        txt = new JTextArea();
        moveRooms = new JButton("Move Rooms");
        moveRooms.setActionCommand("Move");
        moveRooms.setPreferredSize(new Dimension(400,800));
        txt.setPreferredSize(new Dimension(1600, 400));

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

    /**
     * setText - updates the text area with the given string
     * @param s - the text to update it to
     */
    @Override
    public void setText(String s){
        txt.setText(s);
    }

    /**
     * activatePanel - activates the buttons and remove any dead players
     * @param a - action listener
     * @param e - not used
     * @param names - array of players.
     */
    @Override
    public void activatePanel(ActionListener a, ItemListener e, Player[] names) {
        moveRooms.addActionListener(a);
        for (playerPane player : players) {
            player.setButtons(a);
        }
        for(int i = 0; i < names.length; i++){
            if(names[i] == null || !names[i].isALive()){
                remove(players[i]);
            }
            else {
                players[i].status.setText(names[i].getName() + " Health : " + names[i].checkHealth());
            }
        }
        revalidate();
    }

    /**
     * actionSignal - gets an ActionEvent from GameLogic and returns a signal to tell it what to do
     * @param e - the ActionEvent that triggered function call
     * @return int - 0: move signal, 1-4: player panel signal - number corresponds to which player
     */
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

    private class playerPane extends JPanel{
        private JButton items;
        private JButton interact;
        private JTextArea status;

        /**
         * Constructor for individual player panels
         * @param name - name of player
         * @param health - health of player
         */
        private playerPane(String name, int health){
            setPreferredSize(new Dimension(1200, 200));
            setLayout(new GridBagLayout());
            status = new JTextArea(name + ": " + health);
            items = new JButton("Use Item");
            interact = new JButton("Interact");
            items.setPreferredSize(new Dimension(400,200));
            interact.setPreferredSize(new Dimension(400,200));
            status.setPreferredSize(new Dimension(400, 200));
            items.setActionCommand("Item");
            interact.setActionCommand("Interact");

            GridBagConstraints c = new GridBagConstraints();

            c.gridx = 0;
            c.gridy = 0;
            c.fill = BOTH;
            c.weighty = 1;
            c.weightx = 1;
            add(status, c);
            c.gridx = 1;
            add(interact, c);
            c.gridx = 2;
            add(items, c);
        }

        /**
         * setButtons - activates actionListeners in pane
         * @param a - ActionListener to use
         */
        private void setButtons(ActionListener a){
            items.addActionListener(a);
            interact.addActionListener(a);
            revalidate();
        }

        /**
         * checks if object is in pane
         * @param j - object to be compared
         * @return boolean - true if item is in pane
         */
        public boolean contains(Object j) {
            return j.equals(items) || j.equals(interact);
        }
    }
}
