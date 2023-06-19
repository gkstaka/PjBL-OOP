package Pessoa;

import Excecoes.CPFInvalido;
import Excecoes.TelefoneInvalido;
import Veiculo.Vehicle;

import java.util.HashMap;

public class Client extends Person {
    private HashMap<String, Vehicle> vehicles;

    public Client(String name, String cpf, String phone) throws CPFInvalido, TelefoneInvalido {
        super(name, cpf, phone);
        this.vehicles = new HashMap<>();
    }

    public void adicionarVeiculo(Vehicle vehicle) {
        vehicles.put(vehicle.getPlate(), vehicle);
    }

    public void retirarVeiculo(Vehicle vehicle) {
        vehicles.remove(vehicle.getPlate());
    }

    public void mostrarVeiculos() {
        for (Vehicle v : vehicles.values()) {
            System.out.println(v);
        }
    }

    @Override
    public String toString() {
        return super.getName();
    }

}
