import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class SanctuaryPanel extends JPanel  implements GamePanel{
    private JButton p1Heal;
    private JButton p2Heal;
    private JButton p3Heal;
    private JButton p4Heal;
    private JButton moveRooms;
    private boolean heal1used;
    private boolean heal2used;
    private boolean heal3used;
    private boolean heal4used;
    private JTextArea textArea;

    public SanctuaryPanel(){
        setPreferredSize(new Dimension(1600, 1200));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        moveRooms = new JButton("Move Rooms");
        moveRooms.setPreferredSize(new Dimension(400, 800));
        moveRooms.setActionCommand("Move");
        p1Heal = new JButton("Player 1 Heal");
        p1Heal.setPreferredSize(new Dimension(1200, 200));
        p2Heal = new JButton("Player 2 Heal");
        p2Heal.setPreferredSize(new Dimension(1200, 200));
        p3Heal = new JButton("Player 3 Heal");
        p3Heal.setPreferredSize(new Dimension(1200, 200));
        p4Heal = new JButton("Player 4 Heal");
        p4Heal.setPreferredSize(new Dimension(1200, 200));
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(1600, 400));
        heal1used = false;
        heal2used = false;
        heal3used = false;
        heal4used = false;

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = .33;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 4;
        add(moveRooms, c);

        c.gridheight = 1;
        c.gridwidth = 2;
        c.weightx = 1;
        c.gridx = 1;
        add(p1Heal, c);
        c.gridy = 1;
        add(p2Heal, c);
        c.gridy = 2;
        add(p3Heal, c);
        c.gridy = 3;
        add(p4Heal, c);

        c.gridy = 4;
        c.gridheight = 2;
        c.gridwidth = 3;
        c.gridx = 0;
        add(textArea, c);
    }

    @Override
    public void activatePanel(ActionListener a, ItemListener e, Player[] players) {
        if(players[0] != null && players[0].isALive()){
            p1Heal.addActionListener(a);
        }
        else if(players[1] != null && players[1].isALive()){
            p2Heal.addActionListener(a);
        }
        else if(players[2] != null && players[2].isALive()){
            p3Heal.addActionListener(a);
        }
        else if(players[3] != null && players[3].isALive()){
            p4Heal.addActionListener(a);
        }
        moveRooms.addActionListener(a);
    }

    @Override
    public int actionSignal(ActionEvent e){
        int actionType = -1;
        if(e.getSource().equals(moveRooms)){
            actionType = 0;
        }
        else if(e.getSource().equals(p1Heal) && !heal1used){
            actionType = 1;
            heal1used = true;
        }
        else if(e.getSource().equals(p2Heal) && !heal2used){
            actionType = 2;
            heal2used = true;
        }
        else if(e.getSource().equals(p3Heal) && !heal3used){
            actionType = 3;
            heal3used = true;
        }
        else if(e.getSource().equals(p4Heal) && !heal4used){
            actionType = 4;
            heal4used = true;
        }
        return actionType;
    }

    @Override
    public void setText(String s){
        textArea.setText(s);
    }
}
