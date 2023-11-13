import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemPanel extends JPanel implements GamePanel {
    private JButton useItemButton;
    private JTextArea itemResultText;

    public ItemPanel() {
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        useItemButton = new JButton("Use Item");

        itemResultText = new JTextArea();
        itemResultText.setEditable(false);

        add(useItemButton, BorderLayout.CENTER);
        add(itemResultText, BorderLayout.SOUTH);
    }

    @Override
    public void activatePanel(ActionListener a) {
        useItemButton.addActionListener(a);
    }

    public void updateItemResult(String result) {
        itemResultText.setText(result);
    }
}