/**
 * GameStartPanel Handles the gui for character selection
 * @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;

import static java.awt.GridBagConstraints.*;

public class GameStartPanel extends JPanel  implements GamePanel{
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

    /**
     * initializes the player selection areas as well as creates
     * a hashtable with the text for each class to update the
     * text area.
     */
    public GameStartPanel(){
        classText = new Hashtable<>();
        String classBffr;
        classBffr = "Fighter Class\n" + "Max Health: 30\n" + "Armor Class: 15\n" + "Attack Bonus: +3\n" + "Initiative Bonus: 0\n" + "Ability: double strike";
        classText.put("Fighter", classBffr);
        classBffr  = "Cleric Class\n" + "Max Health: 25\n" + "Armor Class: 16\n" + "Attack Bonus: +2\n" + "Initiative Bonus: 3\n" + "Ability: healing chant";
        classText.put("Cleric", classBffr);
        classBffr = "Sorcerer Class\n" + "Max Health: 20\n" + "Armor Class: 12\n" + "Attack Bonus: +3\n" + "Initiative Bonus: 1\n" + "Ability: fireball";
        classText.put("Sorcerer", classBffr);
        player1 = new CharSelect(1);
        player2 = new CharSelect(2);
        player3 = new CharSelect(3);
        player4 = new CharSelect(4);
        startButton = new JButton("Start");
        startButton.setActionCommand("Start");
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

    /**
     * returns a player array of all the players with the class
     * selected by the dropDown box and the name entered in the text area
     * @return Player[] - array of players to initialize game with only the locked in players.
     */
    public Player[] getPlayers(){
        Player[] players = new Player[4];
        if(player1.lockedIn){
            if(player1.playerClass.equals("Fighter")){
                players[0] = new Player(player1.getPlayerName(), 1);
            }
            else if(player1.playerClass.equals("Cleric")){
                players[0] = new Player(player1.getPlayerName(), 2);
            }
            else if(player1.playerClass.equals("Sorcerer")){
                players[0] = new Player(player1.getPlayerName(), 3);
            }
        }
        if(player2.lockedIn){
            if(player2.playerClass.equals("Fighter")){
                players[1] = new Player(player2.getPlayerName(), 1);
            }
            else if(player2.playerClass.equals("Cleric")){
                players[1] = new Player(player2.getPlayerName(), 2);
            }
            else if(player2.playerClass.equals("Sorcerer")){
                players[1] = new Player(player2.getPlayerName(), 3);
            }
        }
        if(player3.lockedIn){
            if(player3.playerClass.equals("Fighter")){
                players[2] = new Player(player3.getPlayerName(), 1);
            }
            else if(player3.playerClass.equals("Cleric")){
                players[2] = new Player(player3.getPlayerName(), 2);
            }
            else if(player3.playerClass.equals("Sorcerer")){
                players[2] = new Player(player3.getPlayerName(), 3);
            }
        }
        if(player4.lockedIn){
            if(player4.playerClass.equals("Fighter")){
                players[3] = new Player(player4.getPlayerName(), 1);
            }
            else if(player4.playerClass.equals("Cleric")){
                players[3] = new Player(player4.getPlayerName(), 2);
            }
            else if(player4.playerClass.equals("Sorcerer")){
                players[3] = new Player(player4.getPlayerName(), 3);
            }
        }
        return players;
    }

    /**
     * checks  if all the players in the charselect are locked
     * @return boolean - true if players are locked false if not
     */
    public boolean playersLocked(){
        boolean i = true;
        if(player1.playing && !player1.lockedIn){
            i = false;
        }
        else if(player2.playing && !player2.lockedIn){
            i = false;
        }
        else if(player3.playing && !player3.lockedIn){
            i = false;
        }
        else if(player4.playing && !player4.lockedIn){
            i = false;
        }
        return i;
    }

    /**
     * returns 1 to GameLogic if players are ready to start.
     * if an Add button was clicked initializes the corresponding player for charSelect
     * @param e - the ActionEvent that occured on this object
     * @return 1 if ready to start otherwhise -1
     */
    @Override
    public int actionSignal(ActionEvent e) {
        int sig = -1;
        if(e.getActionCommand().equals("Add")){
            addPlayer(e);
        }
        else if(e.getActionCommand().equals("Lock In")){
            if(e.getSource().equals(player1.lockInButton)){
                lockIn(1);
            }
            else if(e.getSource().equals(player2.lockInButton)){
                lockIn(2);
            }
            else if (e.getSource().equals(player3.lockInButton)){
                lockIn(3);
            }
            else{
                lockIn(4);
            }
        }
        else if(e.getActionCommand().equals("Start") && playersLocked()){
            sig = 1;
        }
        return sig;
    }

    //does nothing
    @Override
    public void setText(String s) {

    }

    /**
     * sets player class to the selected option and sets player to locked in
     * @param player - the player which is being locked in
     */
    private void lockIn(int player){
        if(player == 1){
            player1.playerClass = (String) player1.dropDown.getSelectedItem();
            player1.lockedIn = true;
        }
        else if(player == 2){
            player2.playerClass = (String) player2.dropDown.getSelectedItem();
            player2.lockedIn = true;
        }
        else if(player == 3){
            player3.playerClass = (String) player3.dropDown.getSelectedItem();
            player3.lockedIn = true;
        }
        else {
            player4.playerClass = (String) player4.dropDown.getSelectedItem();
            player4.lockedIn = true;
        }
    }

    /**
     * Initializes a charselect area in the JPanel on which Action occured
     * @param e the ActionEvent that triggered method call
     */
    private void addPlayer(ActionEvent e){
        if(e.getSource().equals(player1.start)){
            player1.playing = true;

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
            player2.playing = true;

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
            player3.playing = true;

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
            player4.playing = true;

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

    /**
     * updates the class text area whenever the dropdown is changed
     * @param e - the ItemEvent triggered by changing drop down box
     */
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

    /**
     * initializes the ActionListeners and ItemListeners of all components
     * @param a - the ActionListener calling method
     * @param i - the ItemListener calling method
     * @param players - not used
     */
    @Override
    public void activatePanel(ActionListener a, ItemListener i, Player[] players) {
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
        startButton.addActionListener(a);
    }

    /**
     * Inner class which holds the Swing components for each charselect area
     */
    private class CharSelect extends JComponent{
        private int playerNum;
        private boolean lockedIn;
        private boolean playing;
        private String playerClass;
        private JButton start;
        private JTextField name;
        private JComboBox<String> dropDown;
        private JTextArea classStats;
        private JButton lockInButton;

        private CharSelect(int playerNum){
            playing = false;
            this.playerNum = playerNum;
            start = new JButton("Add Player");
            start.setActionCommand("Add");
            start.setPreferredSize(new Dimension(395,1000));
            name = new JTextField("Enter Name");
            name.setPreferredSize(new Dimension(395, 100));
            lockedIn = false;
            String[] classes = new String[3];
            classes[0] = "Fighter";
            playerClass = "Fighter";
            classes[1] = "Cleric";
            classes [2] = "Sorcerer";
            dropDown = new JComboBox<>(classes);
            dropDown.setPreferredSize(new Dimension(395, 100));
            classStats = new JTextArea(classText.get("Fighter"));
            classStats.setPreferredSize(new Dimension(400, 800));
            lockInButton = new JButton("Lock In");
            lockInButton.setActionCommand("Lock In");
            lockInButton.setPreferredSize(new Dimension(400, 200));
        }

        private int getPlayerNum() {
            return playerNum;
        }
        private String getPlayerName(){
            return name.getText();
        }

        private JButton getStart() {
            return start;
        }

        private boolean isLockedIn() {
            return lockedIn;
        }
    }
}
