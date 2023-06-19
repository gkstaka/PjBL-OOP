package Functions;

import Pessoa.Client;
import Pessoa.Employee;
import Veiculo.Vehicle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort{
    public static DefaultListModel<Client> sortClient(DefaultListModel<Client> listModel){
        System.out.println("sort");
        ArrayList<Client> elements = new ArrayList<>();
        for (int i = 0; i < listModel.getSize(); i++) {
            elements.add(listModel.getElementAt(i));
        }
        Collections.sort(elements,Comparator.comparing(Client::getName));
        DefaultListModel<Client> clientsSorted = new DefaultListModel<Client>();
        for(Client c : elements){
            clientsSorted.addElement(c);
        }
        return clientsSorted;
    }

    public static DefaultListModel<Vehicle> sortVehicle(DefaultListModel<Vehicle> listModel){
        System.out.println("sort");
        ArrayList<Vehicle> elements = new ArrayList<>();
        for (int i = 0; i < listModel.getSize(); i++) {
            elements.add(listModel.getElementAt(i));
        }
        Collections.sort(elements,Comparator.comparing(Vehicle::getPlate));
        Collections.sort(elements,Comparator.comparing(Vehicle::getModel));
        Collections.sort(elements,Comparator.comparing(Vehicle::getMaker));

        DefaultListModel<Vehicle> vehiclesSorted = new DefaultListModel<Vehicle>();
        for(Vehicle v : elements){
            vehiclesSorted.addElement(v);
        }
        return vehiclesSorted;
    }


}
