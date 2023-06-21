package Pessoa;

import Excecoes.CPFInvalido;
import Excecoes.TelefoneInvalido;
import Veiculo.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;

public class Client extends Person {
    private ArrayList<Vehicle> vehicles;

    public Client(String name, String cpf, String phone) throws CPFInvalido, TelefoneInvalido {
        super(name, cpf, phone);
        this.vehicles = new ArrayList<>();
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
}
