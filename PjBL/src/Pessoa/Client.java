package Pessoa;

import Excecoes.PersonExceptionInvalidCPF;
import Excecoes.PersonExceptionInvalidPhone;
import Functions.Sort;
import Veiculo.Vehicle;

import javax.swing.*;

public class Client extends Person {
    private DefaultListModel<Vehicle> vehicles;

    public Client(String name, String cpf, String phone) throws PersonExceptionInvalidCPF, PersonExceptionInvalidPhone {
        super(name, cpf, phone);
        this.vehicles = new DefaultListModel<>();
    }


    @Override
    public String toString() {
        return super.getName();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (super.getCpf().equals(((Client) obj).getCpf())) result = true;
        return result;
    }

    public DefaultListModel<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle[] getVehiclesArray() {
        Vehicle[] vehiclesArray = new Vehicle[vehicles.getSize()];
        for (int i = 0; i < vehiclesArray.length; i++) {
            vehiclesArray[i] = vehicles.getElementAt(i);
        }
        return vehiclesArray;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.addElement(vehicle);
        Sort.sortVehicleListModel(vehicles);
    }

    public Vehicle getVehicle(int i) {
        try {
            return vehicles.getElementAt(i);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("nao encontrado");
            return null;
        }

    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.removeElement(vehicle);
    }

    public void removeVehicle(int i) {
        vehicles.removeElementAt(i);
    }

}
