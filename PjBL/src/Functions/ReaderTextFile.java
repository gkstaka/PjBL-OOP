package Functions;

import Excecoes.*;
import Pessoa.Client;
import Veiculo.LargeCar;
import Veiculo.MediumCar;
import Veiculo.Motorcycle;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderTextFile {
    public static ArrayList<String> readTxt(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return lines;
    }

    public static DefaultListModel<Client> selectClientsFromList() {
        ArrayList<String> clientsTxt = readTxt("people.txt");
        System.out.println(clientsTxt.size());
        DefaultListModel<Client> clientObj = new DefaultListModel<>();
        for (int i = 0; i < clientsTxt.size(); i++) {
            String[] clienteField = clientsTxt.get(i).split(" ");
            try {
                System.out.println(i);
                Client client = new Client(clienteField[0] + " " + clienteField[1], clienteField[2], clienteField[3]);
                clientObj.addElement(client);
            } catch (PersonExceptionInvalidCPF e) {
                System.out.println("Nao foi possivel criar novo cliente com esse cpf");
            } catch (PersonExceptionInvalidPhone e) {
                System.out.println("Nao foi possiveo criar novo cliente com esse telefone");
            }
        }
        return clientObj;
    }

    public static void putVehiclesToClients(DefaultListModel<Client> clients) {
        ArrayList<String> vehiclesTxt = readTxt("vehicles.txt");

        for (int i = 0; i < vehiclesTxt.size(); i++) {
            String[] vehicleField = vehiclesTxt.get(i).split(" ");
            int size = vehicleField.length;
            int j = size - 1;
            int parsePerson = Integer.parseInt(vehicleField[j]);
            j--;
            String vehicleSize = vehicleField[j];
            j--;
            String plate = vehicleField[j];
            j--;
            String model = "";
            while (j >= 1) {
                model += vehicleField[j] + " ";
                j--;
            }
            String maker = vehicleField[0];
            System.out.println(maker);
            System.out.println(model);
            System.out.println(plate);
            System.out.println(vehicleSize);
            System.out.println(parsePerson);
            try {
                switch (vehicleSize) {
                    case "small":
                        clients.getElementAt(parsePerson).addVehicle(new Motorcycle(maker, model, plate));
                        break;
                    case "medium":
                        clients.getElementAt(parsePerson).addVehicle(new MediumCar(maker, model, plate));
                        break;
                    case "large":
                        clients.getElementAt(parsePerson).addVehicle((new LargeCar(maker, model, plate)));
                        break;
                }
            } catch (VehicleExceptionInvalidMaker e) {
                System.out.println("Fabricante em branco");
            } catch (VehicleExceptionInvalidModel e) {
                System.out.println("Modelo em branco");
            } catch (VehicleExceptionInvalidPlate e) {
                System.out.println("Placa em branco");
            }
        }
    }
}
