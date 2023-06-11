import javax.swing.*;
import java.awt.*;

public class Swing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Estacionamento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        // Add components to the panel or customize it further
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use BoxLayout with vertical alignment

        // Create check boxes
        JCheckBox checkBox1 = new JCheckBox("Option 1");
        JCheckBox checkBox2 = new JCheckBox("Option 2");
        JCheckBox checkBox3 = new JCheckBox("Option 3");
        // Add check boxes to the panel
        panel.add(checkBox1);
        panel.add(checkBox2);
        panel.add(checkBox3);


        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
