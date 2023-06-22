package Parking;

import Pessoa.Client;
import Veiculo.LargeCar;
import Veiculo.MediumCar;
import Veiculo.Motorcycle;
import Veiculo.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Parking implements Serializable {
    ArrayList<JButton> motorcylesParkedButton;
    ArrayList<JButton> mediumCarsParkedButton;
    ArrayList<JButton> largeCarsParkedButton;
    private int maxMotorcycleSpot;
    private int maxMediumCarSpot;
    private int maxLargeCarSpot;

    private final String GREEN = "#A4E675";
    private final String RED = "#E68075";

    public Parking(int maxMotorcycleSpot, int maxMediumCarSpot, int maxLargeCarSpot) {
        this.maxMotorcycleSpot = maxMotorcycleSpot;
        this.maxMediumCarSpot = maxMediumCarSpot;
        this.maxLargeCarSpot = maxLargeCarSpot;

        motorcylesParkedButton = new ArrayList<>();
        for (int i = 0; i < getMaxMotorcycleSpot(); i++) {
            JButton button = new JButton(String.format("%02d", (i + 1)));
            button.putClientProperty("index", i);
            button.setBackground(Color.decode(GREEN));
            button.setMargin(new Insets(5, 20, 5, 20));
            motorcylesParkedButton.add(button);
        }
        mediumCarsParkedButton = new ArrayList<>();
        for (int i = 0; i < getMaxMediumCarSpot(); i++) {
            JButton button = new JButton(String.format("%02d", (i + 1)));
            button.putClientProperty("index", i);
            button.setBackground(Color.decode(GREEN));
            button.setMargin(new Insets(5, 20, 5, 20));
            mediumCarsParkedButton.add(button);
        }

        largeCarsParkedButton = new ArrayList<>();
        for (int i = 0; i < getMaxLargeCarSpot(); i++) {
            JButton button = new JButton(String.format("%02d", (i + 1)));
            button.putClientProperty("index", i);
            button.setBackground(Color.decode(GREEN));
            button.setMargin(new Insets(5, 20, 5, 20));
            largeCarsParkedButton.add(button);
        }
    }

    public int getMaxMotorcycleSpot() {
        return maxMotorcycleSpot;
    }

    public int getMaxMediumCarSpot() {
        return maxMediumCarSpot;
    }


    public int getMaxLargeCarSpot() {
        return maxLargeCarSpot;
    }


    public DefaultListModel<Motorcycle> getMotorcyclesDLM(DefaultListModel<Client> clients) {
        DefaultListModel<Motorcycle> motorcyclesDefaultListModel = new DefaultListModel<>();
        for (int i = 0; i < clients.getSize(); i++) {
            Client client = clients.getElementAt(i);
            for (int j = 0; j < client.getVehicles().getSize(); j++) {
                Vehicle vehicle = client.getVehicle(j);
                if (vehicle instanceof Motorcycle && ! vehicle.isParked()) {
                    motorcyclesDefaultListModel.addElement((Motorcycle) vehicle);
                }
            }
        }
        return motorcyclesDefaultListModel;
    }

    public DefaultListModel<MediumCar> getMediumCarsDLM(DefaultListModel<Client> clients) {
        DefaultListModel<MediumCar> mediumCarDefaultListModel = new DefaultListModel<>();
        for (int i = 0; i < clients.getSize(); i++) {
            Client client = clients.getElementAt(i);
            for (int j = 0; j < client.getVehicles().getSize(); j++) {
                Vehicle vehicle = client.getVehicle(j);
                if (vehicle instanceof MediumCar && ! vehicle.isParked()) {
                    mediumCarDefaultListModel.addElement((MediumCar) vehicle);
                }
            }
        }
        return mediumCarDefaultListModel;
    }

    public DefaultListModel<LargeCar> getLargeCarsDLM(DefaultListModel<Client> clients) {
        DefaultListModel<LargeCar> largeCarDefaultListModel = new DefaultListModel<>();
        for (int i = 0; i < clients.getSize(); i++) {
            Client client = clients.getElementAt(i);
            for (int j = 0; j < client.getVehicles().getSize(); j++) {
                Vehicle vehicle = client.getVehicle(j);
                if (vehicle instanceof LargeCar && ! vehicle.isParked()) {
                    largeCarDefaultListModel.addElement((LargeCar) vehicle);
                }
            }
        }
        return largeCarDefaultListModel;
    }

    public ArrayList<JButton> getMotorcylesParkedButton() {
        return motorcylesParkedButton;
    }

    public ArrayList<JButton> getMediumCarsParkedButton() {
        return mediumCarsParkedButton;
    }

    public ArrayList<JButton> getLargeCarsParkedButton() {
        return largeCarsParkedButton;
    }

    public void accessMotorcycleIndex(int index) {

    }
}
