import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public interface GamePanel {
    void activatePanel(ActionListener a, ItemListener e, Player[] players);
    int actionSignal(ActionEvent e);
    void setText(String s);
}