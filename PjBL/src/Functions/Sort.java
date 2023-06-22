package Functions;

import Pessoa.Client;
import Veiculo.Vehicle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort {
    public static void sortClientListModel(DefaultListModel<Client> model) {
        ArrayList<Client> data = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            data.add(model.get(i));
        }
        Collections.sort(data, Comparator.comparing(Client::getName));
        model.clear();
        for (Client item : data) {
            model.addElement(item);
        }
    }

    public static void sortVehicleListModel(DefaultListModel<Vehicle> model) {
        ArrayList<Vehicle> data = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            data.add(model.get(i));
        }
        Collections.sort(data, Comparator.comparing(Vehicle::getPlate));
        Collections.sort(data, Comparator.comparing(Vehicle::getModel));
        Collections.sort(data, Comparator.comparing(Vehicle::getMaker));
        model.clear();
        for (Vehicle item : data) {
            model.addElement(item);
        }
    }
}
