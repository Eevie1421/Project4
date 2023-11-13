import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestGui implements ActionListener, ItemListener {
    private DefaultPanel defaultPanel;
    private JPanel currentFrame;
    private GameStartPanel startPanel;
    private MovePanel movePanel;
    private GameLogic gameLogic;

    private JFrame test;

    public TestGui() {
        test = new JFrame("Adventure Game");
        test.setSize(800, 600);
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.defaultPanel = new DefaultPanel();
        this.startPanel = new GameStartPanel();
        this.movePanel = new MovePanel();

        defaultPanel.activatePanel(this);
        startPanel.activatePanel(this, this);
        movePanel.activatePanel(this);

        currentFrame = defaultPanel;
        test.add(currentFrame);

        test.pack();
        test.setVisible(true);

        // Initialize game logic
        // Assuming you have a Map instance called gameMap
        Map gameMap = new Map(); // Adjust this line based on your Map implementation
        Player player = new Player("John", 1, gameMap);
        Room startingRoom = new Room("Start", null, 1, null, null, 0);
        gameLogic = new GameLogic(player, startingRoom, gameMap);

        // Start the game loop
        runGameLoop();
    }

    public static void main(String[] args) {
        new TestGui();
    }

    private void runGameLoop() {
        while (true) {
            // Handle player input or GUI events
            updateGameDisplay();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateGameDisplay() {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            startPanel.updateClassPanel(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentFrame == startPanel) {
            int actionType = startPanel.actionSignal(e);
            if (actionType == 2) {
                // Start the game - Switch to the move panel
                test.remove(currentFrame);
                currentFrame = movePanel;
                test.add(currentFrame);
                test.revalidate();
            }
        } else if (currentFrame == movePanel) {
            int actionType = movePanel.actionSignal(e);
            String result = "";
            switch (actionType) {
                case 0:
                    result = gameLogic.moveBack();
                    break;
                case 1:
                    result = gameLogic.moveForward();
                    break;
                case 2:
                    result = gameLogic.moveLeft();
                    break;
                case 3:
                    result = gameLogic.moveRight();
                    break;
            }
            movePanel.updateMoveResult(result);
        }
    }
}
