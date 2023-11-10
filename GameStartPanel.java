import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.BOTH;

public class GameStartPanel extends JPanel implements GamePanel{
    private JTextArea txt;
    private CharSelect player1;
    private CharSelect player2;
    private CharSelect player3;
    private CharSelect player4;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    public GameStartPanel(){
        player1 = new CharSelect(1);
        player2 = new CharSelect(2);
        player3 = new CharSelect(3);
        player4 = new CharSelect(4);
        panel1 = new JPanel();
        panel1.add(player1.getStart());
        panel2 = new JPanel();
        panel2.add(player2.getStart());
        panel3 = new JPanel();
        panel3.add(player3.getStart());
        panel4 = new JPanel();
        panel4.add(player4.getStart());
        setPreferredSize(new Dimension(1600, 1200));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.weightx = 1;
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
    }

    public void addPlayer(ActionEvent e){
        if(e.getSource().equals(player1.getStart())){
            panel1.remove(player1.getStart());
        }
        else if(e.getSource().equals(player2.getStart())){
            panel2.remove(player2.getStart());
        }
        else if(e.getSource().equals(player3.getStart())){
            panel3.remove(player3.getStart());
        }
        else if(e.getSource().equals(player4.getStart())){
            panel4.remove(player4.getStart());
        }
    }
    @Override
    public void activatePanel(ActionListener a) {

    }

    private class CharSelect extends JComponent{
        private int playerNum;
        private boolean playing;
        private boolean lockedIn;
        private JButton start;

        private CharSelect(int playerNum){
            this.playerNum = playerNum;
            start = new JButton("Add Player");
            start.setPreferredSize(new Dimension(400,1200));
            lockedIn = false;
            playing = false;
        }

        public int getPlayerNum() {
            return playerNum;
        }

        public JButton getStart() {
            return start;
        }

        public boolean isPlaying() {
            return playing;
        }

        public void setPlaying(boolean playing) {
            this.playing = playing;
        }
        public void startLockIn(){

        }
    }
}
