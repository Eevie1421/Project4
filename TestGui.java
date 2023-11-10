import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestGui implements ActionListener {
    private DefaultPanel defaultPanel;
    private JPanel currentFrame;
    private CombatPanel combatPanel;
    private GameStartPanel startPanel;
    private MapPanel map;
    private SanctuaryPanel sanctuary;

    public TestGui(){
        JFrame test = new JFrame("test frame");
        test.setSize(1600, 1200);
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.defaultPanel = new DefaultPanel();
        this.startPanel = new GameStartPanel();
        //defaultPanel.activatePanel(this);
        //test.add(defaultPanel);
        startPanel.activatePanel(this);
        test.add(startPanel);
        test.setVisible(true);
    }
    public static void main(String args[]){
        new TestGui();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startPanel.addPlayer(e);
    }
}
