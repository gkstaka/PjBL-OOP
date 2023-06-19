package Pessoa;

import Excecoes.CPFInvalido;
import Excecoes.TelefoneInvalido;

public class Employee extends Person {
    double salario;

    public Employee(String nome, String cpf, String contato, double salario) throws CPFInvalido, TelefoneInvalido {
        super(nome, cpf, contato);
        this.salario = salario;
    }

    @Override
    public String toString() {
        return getName();
    }
}
