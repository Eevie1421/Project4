import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovePanel extends JPanel {
    private JButton btnMoveBack;
    private JButton btnMoveForward;
    private JButton btnMoveLeft;
    private JButton btnMoveRight;
    private JTextArea txtMoveResult;

    public MovePanel() {
        setLayout(new BorderLayout());

        btnMoveBack = new JButton("Move Backward");
        btnMoveForward = new JButton("Move Forward");
        btnMoveLeft = new JButton("Move Left");
        btnMoveRight = new JButton("Move Right");

        txtMoveResult = new JTextArea();
        txtMoveResult.setEditable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(btnMoveBack);
        buttonPanel.add(btnMoveForward);
        buttonPanel.add(btnMoveLeft);
        buttonPanel.add(btnMoveRight);

        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(txtMoveResult), BorderLayout.SOUTH);
    }

    public void activatePanel(ActionListener listener) {
        btnMoveBack.addActionListener(listener);
        btnMoveForward.addActionListener(listener);
        btnMoveLeft.addActionListener(listener);
        btnMoveRight.addActionListener(listener);
    }

    public int actionSignal(ActionEvent e) {
        if (e.getSource() == btnMoveBack) {
            return 0;
        } else if (e.getSource() == btnMoveForward) {
            return 1;
        } else if (e.getSource() == btnMoveLeft) {
            return 2;
        } else if (e.getSource() == btnMoveRight) {
            return 3;
        }
        return -1;
    }

    public void updateMoveResult(String result) {
        txtMoveResult.setText(result);
    }
}