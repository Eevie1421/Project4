import javax.swing.*;
import java.awt.*;
/**
 * A simple Panel that displays a message.
 * @author Jonathan Murphy, Evelyn Totman, Salim Jday
 */
public class EndPanel extends JPanel {
    private JTextArea message;

    public EndPanel() {
        setPreferredSize(new Dimension(1600, 1200));
        message = new JTextArea("Success!");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 1;
        c.gridheight = 8;
        c.gridwidth = 4;
        add(message, c);
    }
}
