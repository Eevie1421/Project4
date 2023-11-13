/*
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;

public class GameLogic implements ActionListener, ItemListener {
    private Player[] players;
    private Room currentRoom;
    private GamePanel currentPanel;
    //state 0: Character selection
    //state 1: Non combat
    //state 3: combat
    private int state;
    private Map map;
    private JFrame gui;
    private Hashtable<Integer, JPanel> panelMap;
    private JPanel test;

    public GameLogic() {
        test = new JPanel();
        test.setPreferredSize(new Dimension(1600, 1200));
        test.add(new JTextArea("test works"));

        gui = new JFrame();
        gui.setSize(1600, 1200);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        players = new Player[4];
        state = 0;
        this.map = new Map();
        currentRoom = map.getRoom(0);
        panelMap = new Hashtable<>(13);
        Room temp;
        for(int i = 0; i < 13; i++){
            temp = map.getRoom(i);
            if(temp.getType() == 0){
                panelMap.put(i, new GameStartPanel());
            }
            else if(temp.getType() == 1){
                panelMap.put(i, new CombatPanel());
            }
            else if(temp.getType() > 1 && temp.getType() < 5){
                panelMap.put(i, new DefaultPanel());
            }
            else if(temp.getType() == 5){
                panelMap.put(i, new CombatPanel());
            }
            else {
                panelMap.put(i,new EndPanel());
            }
        }
        currentPanel = (GamePanel) panelMap.get(0);
        startGame();
    }

    private void startGame(){
        currentPanel.activatePanel(this, this);
        gui.add((JPanel) currentPanel);
        gui.pack();
        gui.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int signal;
        if(currentPanel.getClass().isInstance(new GameStartPanel())){
            signal = currentPanel.actionSignal(e);
            if(signal == 1){
                GameStartPanel temp = (GameStartPanel) currentPanel;
                players = temp.getPlayers();
                gui.remove((JPanel) currentPanel);
                currentPanel = (GamePanel) panelMap.get(1);
                currentRoom = map.getRoom(1);
                currentPanel.activatePanel(this, this);
                gui.add((JPanel) currentPanel);
                gui.pack();
                gui.revalidate();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(currentPanel.getClass().isInstance(new GameStartPanel())){
            GameStartPanel temp = (GameStartPanel) currentPanel;
            temp.updateClassPanel(e);
        }
    }

    /*
    private boolean moveRooms(Player p, Integer pointer) {
        if(!map.getRoom(pointer).isLocked()) {
            enterRoom(p, pointer);
            return true;
        }
        else {
            if(!map.getRoom(pointer).unlock(p)) {
                enterRoom(p, pointer);
                return true;
            }
        }
        return false;
    }
    private void enterRoom(Player p, Integer pointer) {
        map.getRoom(p.getCurrentRoom()).removePlayer(p);
        p.setCurrentRoom(pointer);
        map.getRoom(pointer).addPlayer(p);
    }
    public String moveBack(Player p) {
        Room r = map.getRoom(p.getCurrentRoom());
        if(r.getBack() != null) {
            if(moveRooms(p, r.getBack())) {
                return "Proceeding on.";
            }
            else {
                return "The door is locked...";
            }
        }
        return "No way through...";
    }
    public String moveForward(Player p) {
        Room r = map.getRoom(p.getCurrentRoom());
        if(r.getForward() != null) {
            if(moveRooms(p, r.getForward())) {
                return "Proceeding on.";
            }
            else {
                return "The door is locked...";
            }
        }
        return "No way through...";
    }
    public String moveLeft(Player p) {
        Room r = map.getRoom(p.getCurrentRoom());
        if(r.getLeft() != null) {
            if(moveRooms(p, r.getLeft())) {
                return "Proceeding on.";
            }
            else {
                return "The door is locked...";
            }
        }
        return "No way through...";
    }
    public String moveRight(Player p) {
        Room r = map.getRoom(p.getCurrentRoom());
        if(r.getRight() != null) {
            if(moveRooms(p, r.getRight())) {
                return "Proceeding on.";
            }
            else {
                return "The door is locked...";
            }
        }
        return "No way through...";
    }
     */
}
