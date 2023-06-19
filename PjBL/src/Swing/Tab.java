package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tab extends JPanel {

    public Tab() {
    }

    public void popError(String message) {
        JFrame frame = new JFrame(message);
        JLabel label = new JLabel(message);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.pack();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) frame.dispose();
            }
        });
        frame.setVisible(true);
    }


}
