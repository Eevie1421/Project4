import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestGui implements ActionListener, ItemListener {
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
        this.combatPanel = new CombatPanel();
        //defaultPanel.activatePanel(this);
        //test.add(defaultPanel);
        //startPanel.activatePanel(this, this);
        //test.add(startPanel);
        combatPanel.activatePanel(this);
        test.add(combatPanel);
        test.pack();
        test.setVisible(true);
    }

    public static void main(String args[]){
        new TestGui();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            startPanel.updateClassPanel(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int actionType;
        actionType = combatPanel.actionSignal(e);
    }
}
