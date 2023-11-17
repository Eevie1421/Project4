import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
/**
 * A Panel that allows Players to move between rooms.
 * @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */
public class MovePanel extends JPanel {
    private JButton forward;
    private JButton back;
    private JButton left;
    private JButton right;
    private Hashtable<String, Integer> roomPointers;

    /**
     * Constructor. Assigns the current Room's pointers to the corresponding buttons
     * @param r Room that players are currently located in.
     */
    public MovePanel(Room r){
        setPreferredSize(new Dimension(900, 900));
        setLayout(new GridBagLayout());
        roomPointers = new Hashtable<>(7);
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
            back.setActionCommand("prev");
            back.setText("Previous");
            roomPointers.put("prev", r.getBack());
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
    /**
     * Activates the buttons on the panel by adding ActionListeners
     * @param a signal from GameLogic's current panel
     */
    public void activatePanel(ActionListener a){
        forward.addActionListener(a);
        back.addActionListener(a);
        left.addActionListener(a);
        right.addActionListener(a);
    }
    /**
     * Checks RoomPointers for the corresponding pointer and returns it.
     * @param e signal from a button's ActionListener
     * @return Integer value representing a Room pointer
     */
    public Integer moveSignal(ActionEvent e){
        Integer roomVal = -1;
        if(roomPointers.containsKey(e.getActionCommand())){
            roomVal = roomPointers.get(e.getActionCommand());
        }
        return roomVal;
    }

    /**
     * Checks if obj is a part of the current MovePanel Object.
     * @param obj Object to search for
     * @return true if obj is part of current MovePanel Object
     */
    public boolean contains(Object obj){
        if(obj == null) return false;
        if(obj.getClass().isInstance(new JButton())){
            JButton temp = (JButton) obj;
            return temp.equals(forward) || temp.equals(back) || temp.equals(left) || temp.equals(right);
        }
        else {
            return false;
        }
    }
}