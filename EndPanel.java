import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * A simple Panel that displays a message.
 * @author Jonathan Murphy
 */
public class EndPanel extends JPanel implements GamePanel{
    private JTextArea message;
    private JButton newGame;
    private JButton quit;

    public EndPanel(boolean gameWon) {
        setPreferredSize(new Dimension(800, 800));
        if (gameWon) {
            message = new JTextArea("Congratulations you win!");
        } else {
            message = new JTextArea("Game over! Try again");
        }
        message = new JTextArea("Success!");
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

    public EndPanel(){
        this(true);
    }

    @Override
    public void activatePanel(ActionListener a, ItemListener e, Player[] players) {
        newGame.addActionListener(a);
    }

    @Override
    public int actionSignal(ActionEvent e) {
        if(e.getSource().equals(quit)){
            return -1;
        }
        return 1;
    }

    @Override
    public void setText(String s) {
        message.setText(s);
    }
}