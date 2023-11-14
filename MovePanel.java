import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class MovePanel extends JPanel {
    private JButton forward;
    private JButton back;
    private JButton left;
    private JButton right;
    private Hashtable<String, Integer> roomPointers;
    public MovePanel(Room r){
        setPreferredSize(new Dimension(900, 900));
        setLayout(new GridBagLayout());
        roomPointers = new Hashtable<>(4);
        forward = new JButton("NO DOOR HERE");
        forward.setPreferredSize(new Dimension(300, 300));
        back = new JButton("NO DOOR HERE");
        back.setPreferredSize(new Dimension(300, 300));
        left = new JButton("NO DOOR HERE");
        left.setPreferredSize(new Dimension(300, 300));
        right = new JButton("NO DOOR HERE");
        right.setPreferredSize(new Dimension(300, 300));

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        add(forward, c);
        c.gridx = 1;
        c.gridy = 2;
        add(back, c);
        c.gridx = 0;
        c.gridy = 1;
        add(left, c);
        c.gridx = 2;
        c.gridy = 1;
        add(right, c);
        if(r.getForward() != null){
            forward.setActionCommand("forward");
            forward.setText("Forward");
            roomPointers.put("forward", r.getForward());
        }
        if(r.getBack() != null){
            back.setActionCommand("back");
            back.setText("Back");
            roomPointers.put("back", r.getBack());
        }
        if(r.getLeft() != null){
            left.setText("Left");
            left.setActionCommand("left");
            roomPointers.put("left", r.getLeft());
        }
        if(r.getRight() != null){
            right.setActionCommand("right");
            right.setText("Right");
            roomPointers.put("right", r.getRight());
        }
    }

    public void activatePanel(ActionListener a){
        forward.addActionListener(a);
        back.addActionListener(a);
        left.addActionListener(a);
        right.addActionListener(a);
    }

    public Integer moveSignal(ActionEvent e){
        Integer roomVal = -1;
        if(roomPointers.containsKey(e.getActionCommand())){
            roomVal = roomPointers.get(e.getActionCommand());
        }
        return roomVal;
    }

    public boolean contains(Object obj){
        if(obj == null) return false;
        if(obj.getClass().isInstance(new JButton())){
            JButton temp = (JButton) obj;
            return obj.equals(forward) || obj.equals(back) || obj.equals(left) || obj.equals(right);
        }
        return false;
    }
}
