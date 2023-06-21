//package Swing.Tabs;
//
//import Functions.Serializer;
//import Pessoa.Client;
//import Veiculo.Vehicle;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.FocusAdapter;
//import java.awt.event.FocusEvent;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//
//public class Tab extends JPanel {
//    protected DefaultListModel<Client> clientsDefaultListModel;
//    protected DefaultListModel<Vehicle> vehiclesDefaultListModel;
//
//    public Tab(DefaultListModel<Client> clientsDefaultListModel, DefaultListModel<Vehicle> vehiclesDefaultListModel) {
//        this.clientsDefaultListModel = clientsDefaultListModel;
//        this.vehiclesDefaultListModel = vehiclesDefaultListModel;
//        this.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//                super.focusLost(e);
//                updateListsUI();
//            }
//        });
//    }
//
//    public void popError(String message) {
//        JFrame frame = new JFrame(message);
//        JLabel label = new JLabel(message);
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
//        frame.setLayout(new FlowLayout());
//        frame.add(label);
//        frame.pack();
//        frame.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) frame.dispose();
//            }
//        });
//        frame.setVisible(true);
//    }
//
//    protected static void sortClientListModel(DefaultListModel<Client> model) {
//        ArrayList<Client> data = new ArrayList<>();
//        for (int i = 0; i < model.getSize(); i++) {
//            data.add(model.get(i));
//        }
//        Collections.sort(data, Comparator.comparing(Client::getName));
//        model.clear();
//        for (Client item : data) {
//            model.addElement(item);
//        }
//    }
//
//    protected static void sortVehicleListModel(DefaultListModel<Vehicle> model) {
//        ArrayList<Vehicle> data = new ArrayList<>();
//        for (int i = 0; i < model.getSize(); i++) {
//            data.add(model.get(i));
//        }
//        Collections.sort(data, Comparator.comparing(Vehicle::getPlate));
//        Collections.sort(data, Comparator.comparing(Vehicle::getModel));
//        Collections.sort(data, Comparator.comparing(Vehicle::getMaker));
//        model.clear();
//        for (Vehicle item : data) {
//            model.addElement(item);
//        }
//    }
//
//    public void updateListsUI() {
//        try {
//            clientsDefaultListModel = (DefaultListModel<Client>) Serializer.read("clients.clt");
//        } catch (IOException | ClassNotFoundException exception) {
//            clientsDefaultListModel = new DefaultListModel<Client>();
//        }
//        try {
//            vehiclesDefaultListModel = (DefaultListModel<Vehicle>) Serializer.read("vehicles.vhc");
//        } catch (IOException | ClassNotFoundException exception) {
//            vehiclesDefaultListModel = new DefaultListModel<Vehicle>();
//        }
//
//    }
//
//}
