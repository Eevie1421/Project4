/*
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class GameLogic implements ActionListener, ItemListener {
    private Player[] players;
    private Room currentRoom;
    private GamePanel currentPanel;
    //state 0: Character selection
    //state 1: Non combat
    //state 2: combat
    private int state;
    private Map map;
    private JFrame gui;
    private Hashtable<Integer, JPanel> panelMap;
    private JFrame move;
    private MovePanel movePanel;
    private boolean moving;
    private LinkedList<Creature> combat;
    private ArrayList<Enemy> enemies;
    private int[] initiative;
    private JPanel test;

    public GameLogic() {
        test = new JPanel();
        test.setPreferredSize(new Dimension(1600, 1200));
        test.add(new JTextArea("test works"));

        move = new JFrame("Move Panel");
        move.setSize(900,900);
        move.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        moving = false;

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
            else if(temp.getType() > 1 && temp.getType() < 4){
                panelMap.put(i, new DefaultPanel());
            }
            else if(temp.getType() == 4){
                panelMap.put(i, new SanctuaryPanel());
            }
            else if(temp.getType() == 5){
                panelMap.put(i, new CombatPanel());
            }
            else {
                panelMap.put(i,new EndPanel());
            }
        }
        currentPanel = (GamePanel) panelMap.get(0);
        movePanel = new MovePanel(currentRoom);
        combat = new LinkedList<>();
        enemies = new ArrayList<>();
        initiative = new int[5];
        startGame();

    }

    private void startGame(){
        currentPanel.activatePanel(this, this, players);
        gui.add((JPanel) currentPanel);
        gui.pack();
        gui.setVisible(true);
    }

    private void startCombat(){
        initiative[4] = enemies.get(0).rollInitiative();
        int first = 0;
        int second = 1;
        int third = 2;
        int fourth = 3;
        int fifth = 4;
        int temp;
        if(initiative[second] > initiative[first]){
            temp = first;
            first = second;
            second = temp;
        }
        if(initiative[third] > initiative[second]){
            temp = second;
            second = third;
            third = temp;
            if(initiative[second] > initiative[first]){
                temp = first;
                first = second;
                second = temp;
            }
        }
        if(initiative[fourth] > initiative[third]){
            temp = third;
            third = fourth;
            fourth = temp;
            if(initiative[third] > initiative[second]){
                temp = second;
                second = third;
                third = temp;
                if(initiative[second] > initiative[first]){
                    temp = first;
                    first = second;
                    second = temp;
                }
            }
        }
        if(initiative[fifth] > initiative[fourth]){
            temp = fourth;
            fourth = fifth;
            fifth = temp;
            if(initiative[fourth] > initiative[third]){
                temp = third;
                third = fourth;
                fourth = temp;
                if(initiative[third] > initiative[second]){
                    temp = second;
                    second = third;
                    third = temp;
                    if(initiative[second] > initiative[first]){
                        temp = first;
                        first = second;
                        second = temp;
                    }
                }
            }
        }
        int [] order = {first, second, third, fourth, fifth};
        for(int i = 0; i < 5; i++){
            if(order[i] == 4){
                for(Creature c : enemies){
                    combat.offer(c);
                }
            }
            else if(players[order[i]].isALive()){
                combat.offer(players[order[i]]);
            }
        }
        state = 2;
        takeNextTurn();
    }

    private void takeNextTurn(){
        if(!combat.isEmpty()){
            Creature temp = combat.poll();
            if(temp.getClass().isInstance(new Player())){
                if(temp.isALive()){
                    currentPanel.setText(temp.getName() + " take your turn.");
                    combat.offerFirst(temp);
                }
                else{
                    currentPanel.setText(temp.getName() + " is dead.");
                }
            }
            else{
                if(temp.isALive()){
                    int playerAttacked = temp.attackPlayer(players.length);
                    Player target = players[playerAttacked];
                    int damage = temp.attack(target.getAc());
                    target.updateHealth(damage);
                    currentPanel.setText(target.getName() + " took " + damage + " damage." + "\n" + "HP: " + target.checkHealth());
                    combat.offerLast(temp);
                }
                else{
                    currentPanel.setText(temp.getName() + " is dead");
                }
                if(!checkPlayers()){
                    endGame();
                }
            }
        }
    }

    private void endCombat(){
        while (!combat.isEmpty()){
            Creature temp = combat.poll();
        }
        enemies = new ArrayList<>();
        for(Player p : players){
            p.setAbilityUsed(false);
        }
        state = 1;
    }

    private void endGame(){

    }
    private boolean checkPlayers(){
        return players[0].isALive() || players[1].isALive() || players[2].isALive() || players[3].isALive();
    }

    private void moveRooms(Integer i){
        gui.remove((JPanel) currentPanel);
        currentPanel = (GamePanel) panelMap.get(i);
        currentRoom = map.getRoom(i);
        currentPanel.activatePanel(this, this, players);
        gui.add((JPanel) currentPanel);
        gui.pack();

        if(currentPanel.getClass().isInstance(new DefaultPanel())){
            state = 1;
        }
        else if(currentPanel.getClass().isInstance(new CombatPanel())){
            state = 1;
        }
        else if(currentPanel.getClass().isInstance(new EndPanel())){
            endGame();
        }
        else if(currentPanel.getClass().isInstance(new SanctuaryPanel())){
            state = 1;
            currentPanel.setText("You enter into a room with a campfire in the center.\n" + "You feel compelled to take a moment to rest and heal by the fire.\n");
        }
        gui.revalidate();
        moving = false;
        move.remove(movePanel);
        move.revalidate();
        move.setVisible(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(movePanel.contains(e.getSource()) && moving){
            int moveSignal = movePanel.moveSignal(e);
            boolean locked = false;
            if(map.getRoom(moveSignal).isLocked()){
                locked = true;
                for(Player p : players){
                    if(map.getRoom(moveSignal).unlock(p)){
                        locked = false;
                        break;
                    }
                }
            }
            if(!locked){
                moveRooms(moveSignal);
            }
            else {
                currentPanel.setText("You try the door but it seems like it is locked.\n Maybe you can find something to open this...");
            }
        }
        int signal;
        signal = currentPanel.actionSignal(e);
        if(signal == 0){
            moving = true;
            movePanel = new MovePanel(currentRoom);
            movePanel.activatePanel(this);
            move.add(movePanel);
            move.revalidate();
            move.setVisible(true);
        }
        else if(currentPanel.getClass().isInstance(new GameStartPanel())){
            if(signal == 1){
                GameStartPanel temp = (GameStartPanel) currentPanel;
                players = temp.getPlayers();
                moveRooms(1);
            }
        }
        else if(currentPanel.getClass().isInstance(new CombatPanel())){
            CombatPanel temp = (CombatPanel) currentPanel;
            if(e.getActionCommand().equals("Start Combat") && temp.isCombatReady()){
                enemies = currentRoom.getEnemies();
                temp.beginCombat(enemies);
                startCombat();
            }
            if(e.getActionCommand().equals("Next Turn")){
                takeNextTurn();
            }
            if(signal < 0){
                if(signal == -1){
                    initiative[0] = players[0].rollInitiative();
                }
                else if(signal == -2){
                    initiative[1] = players[1].rollInitiative();
                }
                else if(signal == -3){
                    initiative[2] = players[2].rollInitiative();
                }
                else if(signal == -4){
                    initiative[3] = players[3].rollInitiative();
                }
            }
            if(signal > 0 && state == 2){
                //add specific targets as a later functionality
                Player currentPlayer = (Player) combat.poll();
                if(signal == 1){
                    int i = 0;
                    Enemy target = enemies.get(i);
                    int attackResolved = -1;
                    while(attackResolved < 0){
                     if(target.checkStatus()){
                         int damage = currentPlayer.attack(target.getAc());
                         target.updateHealth(damage);
                         temp.setText(target.getName() + " took " + damage + "\n Next players turn.(Press Take turn Button)");
                         attackResolved = 1;
                     }
                     else{
                         i++;
                         if(i == enemies.size()){
                             attackResolved = 1;
                             temp.endCombat();
                             endCombat();
                         }
                         else{
                             target = enemies.get(i);
                         }
                     }
                    }
                }
                else if(signal == 2){
                    int abl = currentPlayer.useAbility();
                    if(abl > 0){
                        for(Enemy en : enemies){
                            en.updateHealth(abl);
                        }
                        temp.setText(currentPlayer.getName() + " deals " + abl + " damage to all enemies");
                    }
                    else if(abl < 0){
                        for(Player p : players){
                            p.updateHealth(abl);
                        }
                        temp.setText(currentPlayer.getName() + " heals the party for " + abl + " health");
                    }
                }
                else if(signal == 3){

                }
                temp.updateEnemies(enemies);
                combat.offerLast(currentPlayer);
            }
        }
        else if(currentPanel.getClass().isInstance(new SanctuaryPanel())){
            if(signal > 0){
                signal--;
                players[signal].updateHealth(-1 * Dice.rollD12(1, 0));
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
