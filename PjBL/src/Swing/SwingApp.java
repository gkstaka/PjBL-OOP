package Swing;

import Functions.Serializer;
import Pessoa.Client;
import Swing.CustomComponents.CustomButton;
//import Swing.Tabs.ClientTab;
//import Swing.Tabs.Tab;
//import Swing.Tabs.VehicleTab;
//import Swing.Tabs.VehicleTab;
import Veiculo.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class SwingApp extends JFrame {
    private DefaultListModel<Client> clients;
    private DefaultListModel<Vehicle> vehicles;
    private final String YELLOW = "#E6DC75";
    private CustomButton clientButton;


    public SwingApp() {
        addKeyListener(exitFrame()); // Ctrl + W exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        readObjects();
        setTitle("ParkWash");
        setSize(750, 750);
        setResizable(false);
        ToolTipManager.sharedInstance().setInitialDelay(500);
        UIManager.put("ToolTip.background", YELLOW);
        Font tabFont = new Font("DIALOG", Font.BOLD, 18);
        clientButton = new CustomButton("Cliente");
        clientButton.setFont(tabFont);
        clientButton.setAnchor(10, 10);
        clientButton.addActionListener(clientButtonListener());
        add(clientButton);
        JSeparator jSeparator1 = new JSeparator(SwingConstants.HORIZONTAL);
        jSeparator1.setBounds(0, 50, 750, 5);
        add(jSeparator1);

    }

    private ActionListener clientButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("teste");
                JPanel clientPanel = new JPanel();
                clientPanel.setLayout(null);
                clientPanel.setBounds(0, 60, 750, 600);
                CustomButton addClientButton = new CustomButton("Adicionar cliente");
                CustomButton removeClientButton = new CustomButton("Remover cliente");
                CustomButton updateClientButton = new CustomButton("Alterar telefone");
                addClientButton.setAnchor(10, 10);
                clientPanel.add(addClientButton);
                add(clientPanel);
            }
        };
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SwingApp app = new SwingApp();
                app.setLocationRelativeTo(null);
                app.setVisible(true);
            }
        });
    }

    private KeyAdapter exitFrame() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (e.isControlDown() && keyCode == KeyEvent.VK_W) {
                    dispose();
                }
            }
        };
    }

    private void readObjects() {
        try {
            clients = (DefaultListModel<Client>) Serializer.read("clients.clt");
        } catch (IOException | ClassNotFoundException exception) {
            clients = new DefaultListModel<Client>();
        }

    }


    private MouseAdapter tabChangeAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("update suppose");
                //clientTab.updateListsUI();
                //vehicleTab.updateListsUI();
            }
        };
    }

}
