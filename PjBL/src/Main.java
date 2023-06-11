import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Swing Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create check boxes
        JCheckBox checkBox1 = createCheckBox("Option 1", panel);
        JCheckBox checkBox2 = createCheckBox("Option 2", panel);
        JCheckBox checkBox3 = createCheckBox("Option 3", panel);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static JCheckBox createCheckBox(String label, JPanel panel) {
        JCheckBox checkBox = new JCheckBox(label);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();
                boolean selected = source.isSelected();
                System.out.println(label + " selected: " + selected);
            }
        });
        panel.add(checkBox);
        return checkBox;
    }
}
