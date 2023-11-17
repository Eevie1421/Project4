import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
/**
 * A simple Panel that displays a message.
 * @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */
public class EndPanel extends JPanel implements GamePanel{
    private JTextArea message;
    private JButton newGame;
    private JButton quit;

    /**
     * Main Constructor. Creates a JPanel with a message and 2 buttons.
     * @param gameWon boolean determining which message is displayed
     */
    public EndPanel(boolean gameWon) {
        setPreferredSize(new Dimension(800, 800));
        if (gameWon) {
            message = new JTextArea("Congratulations you win!");
        } else {
            message = new JTextArea("Game over! Try again");
        }
        message.setPreferredSize(new Dimension(800, 400));
        newGame = new JButton("New Game");
        quit = new JButton("Quit");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        add(message, c);
        c.gridwidth = 1;
        c.gridy = 1;
        add(newGame, c);
        c.gridx = 1;
        add(quit, c);
    }
    /**
     * Default Constructor, passes 'true' boolean to the Main Constructor
     */
    public EndPanel(){
        this(true);
    }

    /**
     * activates panel
     * @param a - action listener
     * @param e - item listener
     * @param players - players
     */
    @Override
    public void activatePanel(ActionListener a, ItemListener e, Player[] players) {
        newGame.addActionListener(a);
        quit.addActionListener(a);
    }

    /**
     * actionSignal - Returns 1 for now. later will have restart game functionality
     * @param e - action event
     * @return - returns 1.
     */
    @Override
    public int actionSignal(ActionEvent e) {
        return 1;
    }

    /**
     * sets the text box
     * @param s - text to set it to.
     */
    @Override
    public void setText(String s) {
        message.setText(s);
    }
}