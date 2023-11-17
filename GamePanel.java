/**
 * Interface for gamepanels used in game
 * @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public interface GamePanel {
    //activates the swing components of specific panel
    void activatePanel(ActionListener a, ItemListener e, Player[] players);
    //sends a signal from gamepanel to logic class based on ActionEvent
    int actionSignal(ActionEvent e);
    //sets the text box of the panel
    void setText(String s);
}
