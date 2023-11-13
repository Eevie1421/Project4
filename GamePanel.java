import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public interface GamePanel {
    void activatePanel(ActionListener a, ItemListener e);
    int actionSignal(ActionEvent e);
}
