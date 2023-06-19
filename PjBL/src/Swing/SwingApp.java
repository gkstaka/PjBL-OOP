package Swing;

import Estabelecimento.Estabelecimento;
import Functions.Serializer;
import Pessoa.Client;
import Veiculo.Vehicle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SwingApp extends JFrame {
    private ArrayList<Client> clients;
    private ArrayList<Vehicle> vehicles;
    private final String YELLOW = "#E6DC75";

    public SwingApp() {
        readClients();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ParkWash");
        setSize(800, 800);
        setResizable(false);
        ToolTipManager.sharedInstance().setInitialDelay(500);
        UIManager.put("ToolTip.background", YELLOW);
        JTabbedPane tabs = new JTabbedPane();
        /*JPanel tabParking = new TabParking();
        tabs.add("Estacionamento", tabParking);
        JPanel tabVehicle = new TabVehicle();
        tabs.add("Veiculo", tabVehicle);
        JPanel tabClient = new TabClient();
        tabs.add("Cliente", tabClient);
*/
        add(tabs);

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

    /*public static JPanel tabClient() {

    }*/

    public void readClients() {
        try {
            clients = (ArrayList) Serializer.read("clients.clt");
            System.out.println("read");
        } catch (IOException | ClassNotFoundException exception) {
            //exception.printStackTrace();
            System.out.println("erro read");
            clients = new ArrayList<Client>();
        }
    }

    public void writeClients() {
        try {
            Serializer.write("clients.clt", clients);
        } catch (IOException exception) {
            System.out.println("erro escrita");
        }
    }
}
