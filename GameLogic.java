/** GameLogic - implements functionality for all steps of GameLogic
 * Connects peripheral classes with gui.
 * @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class GameLogic implements ActionListener, ItemListener {
    private Player[] players;
    private Room currentRoom;
    private GamePanel currentPanel;
    private Map map;
    private JFrame gui;
    private Hashtable<Integer, JPanel> panelMap;
    private JFrame move;
    private MovePanel movePanel;
    private boolean moving;
    private LinkedList<Creature> combat;
    private ArrayList<Enemy> enemies;
    private int[] initiative;

    public GameLogic() {

        move = new JFrame("Move Panel");
        move.setSize(900,900);
        move.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        moving = false;

        gui = new JFrame();
        gui.setSize(1600, 1200);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        players = new Player[4];
        this.map = new Map();
        currentRoom = map.getRoom(map.getKeys().get(0));
        panelMap = new Hashtable<>(27);
        Room temp;
        //links rooms to a panel corresponding to their type.
        for(int i = 0; i < 13; i++){
            int key = map.getKeys().get(i);
            temp = map.getRoom(key);
            if(temp.getType() == 0){
                panelMap.put(key, new GameStartPanel());
            }
            else if(temp.getType() == 1){
                panelMap.put(key, new CombatPanel());
            }
            else if(temp.getType() > 1 && temp.getType() < 4){
                panelMap.put(key, new DefaultPanel());
            }
            else if(temp.getType() == 4){
                panelMap.put(key, new SanctuaryPanel());
            }
            else if(temp.getType() == 5){
                panelMap.put(key, new CombatPanel());
            }
        }
        currentPanel = (GamePanel) panelMap.get(currentRoom.hashCode());
        movePanel = new MovePanel(currentRoom);
        combat = new LinkedList<>();
        enemies = new ArrayList<>();
        initiative = new int[]{-1, -1, -1, -1, -1};
        startGame();
    }

    /**
     * adds the gameStartPanel and makes it visible
     */
    private void startGame(){
        currentPanel.activatePanel(this, this, players);
        gui.add((JPanel) currentPanel);
        gui.pack();
        gui.setVisible(true);
    }

    /**
     * Adds enemies and players to combat deque in order of their initiative values than calls takeNextTurn to initiate combat.
     */
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
            else if(initiative[order[i]] > 0){
                combat.offer(players[order[i]]);
            }
        }
        takeNextTurn();
    }

    /**
     * takeNextTurn - takes the next turn and updates the gui correspondingly.
     */
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
                //Enemy turn functionality.
                if(temp.isALive()){
                    int alivePlayers = 0;
                    for (Player p: players) {
                        if(p != null && p.isALive()){
                            alivePlayers++;
                        }
                    }
                    int playerAttacked = temp.attackPlayer(alivePlayers);
                    // - val corresponds to an aoe attack
                    if(playerAttacked < 0) {
                        int damage = temp.attack(0);
                        currentPanel.setText("AOE attack add text later " + damage);
                        for (Player p : players) {
                            if (p != null) {
                                p.updateHealth(damage);
                                if (!p.isALive()) {
                                    currentPanel.setText(p.getName() + " is dead");
                                    combat.remove(p);
                                }
                            }
                        }
                        combat.offerLast(temp);
                        CombatPanel c = (CombatPanel) currentPanel;
                        c.updatePlayers(players);
                    }
                    else {
                        Player target = players[playerAttacked];
                        if(target == null || !target.isALive()){
                            combat.offerFirst(temp);
                            takeNextTurn();
                        }
                        else{
                            int damage = temp.attack(target.getAc());
                            target.updateHealth(damage);
                            currentPanel.setText(target.getName() + " took " + damage + " damage." + "\n" + "HP: " + target.checkHealth());
                            combat.offerLast(temp);
                            if(!target.isALive()){
                                currentPanel.setText(target.getName() + " is dead");
                                combat.remove(target);
                            }
                            CombatPanel c = (CombatPanel) currentPanel;
                            c.updatePlayers(players);
                        }
                    }
                }
                else{
                    currentPanel.setText(temp.getName() + " is dead");
                }
                if(!checkPlayers()){
                    endGame(false);
                }
            }
        }
    }

    /**
     * sets program back to initial state prior to combat.
     */
    private void endCombat(){
        while (!combat.isEmpty()){
            combat.poll();
        }
        enemies = new ArrayList<>();
        for(Player p : players){
            if(p != null){
                p.setAbilityUsed(false);
            }
        }
        initiative = new int[]{-1, -1, -1, -1, -1};
        if(currentRoom.getType() == 5 && checkPlayers()){
            currentRoom.setCleared(true);
            currentPanel.setText(currentRoom.getText());
            endGame(true);
        }
        currentRoom.setCleared(true);
    }

    /**
     * endGame - brings up the end panel.
     * @param gameWon - true if the game is successfully completed. false if lost
     */
    private void endGame(boolean gameWon){
        gui.remove((JPanel) currentPanel);
        currentPanel = new EndPanel(gameWon);
        currentPanel.activatePanel(this, this, players);
        gui.add((JPanel)currentPanel);
    }

    /**
     * checkPlayers - checks if players are alive
     * @return - true if there is any alive players. false if everyone is dead.
     */
    private boolean checkPlayers(){
        return (players[0] != null && players[0].isALive()) || (players[1] != null && players[1].isALive()) || (players[2] != null && players[3].isALive()) || (players[3] != null && players[3].isALive());
    }

    /**
     * moveRooms - moves into the room corresponding to the key and updates the GUI
     * @param i - key corresponding to the target room
     */
    private void moveRooms(Integer i){
        gui.remove((JPanel) currentPanel);
        currentRoom.setCleared(true);
        currentRoom = map.getRoom(i);
        currentPanel = (GamePanel) panelMap.get(i);
        if(!currentRoom.isCleared()){
            currentPanel.activatePanel(this, this, players);
        }
        gui.add((JPanel) currentPanel);
        gui.pack();
        currentPanel.setText(currentRoom.getText());
        gui.revalidate();

        moving = false;
        move.remove(movePanel);
        move.revalidate();
        move.setVisible(false);
    }

    /**
     * actionPerformed - implements ActionListener method
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //If signal comes from move panel move into corresponding room if it isn't locked.
        if(movePanel.contains(e.getSource()) && moving){
            int moveSignal = movePanel.moveSignal(e);
            boolean locked = false;
            //if room is locked check players for a key. if one is found unlock room.
            if(map.getRoom(moveSignal).isLocked()){
                locked = true;
                for(Player p : players){
                    if(p != null && map.getRoom(moveSignal).unlock(p)){
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
                moving = false;
                move.remove(movePanel);
                move.revalidate();
                move.setVisible(false);
            }
        }
        //gets a signal from the current panel which will indicate function to be performed.
        int signal;
        signal = currentPanel.actionSignal(e);
        //Opens move panel if signal is 0.
        if(signal == 0){
            moving = true;
            movePanel = new MovePanel(currentRoom);
            movePanel.activatePanel(this);
            move.add(movePanel);
            move.revalidate();
            move.setVisible(true);
        }
        //GameStartPanel returns 1 to start game.
        else if(currentPanel.getClass().isInstance(new GameStartPanel())){
            if(signal == 1){
                GameStartPanel temp = (GameStartPanel) currentPanel;
                players = temp.getPlayers();
                currentRoom.setCleared(true);
                moveRooms(currentRoom.getForward());
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
                //negative signal corresponds to initiative.
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
            if(signal > 0){
                //add specific targets as a later functionality
                if(combat.peek() != null && !combat.peek().getClass().isInstance(new Player())){
                    return;
                }
                //takes the next player in the queue for combat and performs action corresponding to signal.
                //1 - attack signal
                //2 - ability signal
                //3 - item signal
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
                        temp.setText(currentPlayer.getName() + " tosses a massive fireball at the enemies dealing " + abl + " damage to all enemies");
                    }
                    else if(abl == 0){
                        int i = 0;
                        Enemy target = enemies.get(i);
                        int attackResolved = -1;
                        while(attackResolved < 0){
                            if(target.checkStatus()){
                                int damage = currentPlayer.attack(target.getAc()) + currentPlayer.attack(target.getAc());
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
                    else {
                        for(Player p : players){
                            p.updateHealth(abl);
                        }
                        temp.setText(currentPlayer.getName() + " heals the party for " + -1 * abl + " health");
                        temp.updatePlayers(players);
                    }
                }
                else if(signal == 3){
                    //item panel not implemented for now it checks if player has hp pot and uses it
                    currentPlayer.useItem("Health pot");
                }
                boolean roomClear = temp.updateEnemies(enemies);
                combat.offerLast(currentPlayer);
                if(roomClear){
                    temp.endCombat();
                    endCombat();
                }
            }
        }
        else if(currentPanel.getClass().isInstance(new SanctuaryPanel())){
            //signal is = to 1 + player num.
            if(signal > 0){
                signal--;
                int heal = -1 * Dice.rollD12(1, 0);
                players[signal].updateHealth(heal);
                currentPanel.setText(players[signal].getName() + " heals for " + -heal + " health.");
            }
        }
        else if(currentPanel.getClass().isInstance(new DefaultPanel())){
            if(currentRoom.hasItem() && e.getActionCommand().equals("Interact")){
                if(signal == 1){
                    if(currentRoom.getType() == 3){
                        players[0].pickItem(currentRoom.getItem());
                        currentRoom.setItem(null);
                        currentRoom.setCleared(true);
                        currentPanel.setText(players[0].getName() + " pulls a shiny gold key from the floor.");
                    }
                }
                else if(signal == 2){
                    if(currentRoom.getType() == 3){
                        players[1].pickItem(currentRoom.getItem());
                        currentRoom.setItem(null);
                        currentRoom.setCleared(true);
                        currentPanel.setText(players[1].getName() + " pulls a shiny gold key from the floor.");
                    }
                }
                else if(signal == 3){
                    if(currentRoom.getType() == 3){
                        players[2].pickItem(currentRoom.getItem());
                        currentRoom.setItem(null);
                        currentRoom.setCleared(true);
                        currentPanel.setText(players[2].getName() + " pulls a shiny gold key from the floor.");
                    }
                }
                else if(signal == 4){
                    if(currentRoom.getType() == 3){
                        players[3].pickItem(currentRoom.getItem());
                        currentRoom.setItem(null);
                        currentRoom.setCleared(true);
                        currentPanel.setText(players[3].getName() + " pulls a shiny gold key from the floor.");
                    }
                }
            }
            else if(e.getActionCommand().equals("Item")){
                if (signal == 1) {
                    players[0].useItem("Health pot");
                    currentPanel.setText(players[0].getName() + "drinks a health pot");
                }
                else if(signal == 2){
                    players[1].useItem("Health pot");
                    currentPanel.setText(players[1].getName() + "drinks a health pot");
                }
                else if(signal == 3){
                    players[2].useItem("Health pot");
                    currentPanel.setText(players[2].getName() + "drinks a health pot");
                }
                else if(signal == 4){
                    players[3].useItem("Health pot");
                    currentPanel.setText(players[3].getName() + "drinks a health pot");
                }
            }
        }
        else if(currentPanel.getClass().isInstance(new EndPanel())){
           //placeholder for adding new game. right now just exits game
            gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
        }
    }

    /**
     * implements ItemListener method for GameStartPanel classPanel.
     * @param e the event to be processed
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(currentPanel.getClass().isInstance(new GameStartPanel())){
            GameStartPanel temp = (GameStartPanel) currentPanel;
            temp.updateClassPanel(e);
        }
    }


}
