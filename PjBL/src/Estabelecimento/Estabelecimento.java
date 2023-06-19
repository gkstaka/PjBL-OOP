package Estabelecimento;

import Pessoa.Client;
import Pessoa.Employee;
import Functions.Serializer;
import Veiculo.Vehicle;

import javax.swing.*;
import java.util.*;

public class Estabelecimento {
    private String nome;
    private DefaultListModel<Client> clienteDefaultListModel;
    private JList<Client> clientes;
    private ArrayList<Employee> employees;
    private HashMap<String, Vehicle> veiculos;

    public Estabelecimento(String nome) {
        this.nome = nome;
        this.clientes = new JList<>();
        this.employees = new ArrayList<>();
        this.veiculos = new HashMap<>();

    }

    public void iniciar() {
        System.out.println("iniciar");
        try {
            clientes = (JList) Serializer.read("clientes.clt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    public void adicionarCliente(Client cliente) {
        clientes.add(cliente);
        Comparator compararNome = new Comparator<Client>() {
            @Override
            public int compare(Client cliente1, Client cliente2) {
                return cliente1.getName().compareTo(cliente2.getName());
            }
        };
        Collections.sort(clientes, compararNome);
        try {
            Serializer.write("clientes.clt", clientes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerCliente(String cpf) {
        for (Client c : clientes) {
            if (c.getCpf().equals(cpf)) {
                try {
                    clientes.remove(c);
                    Serializer.write("clientes.clt", clientes);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(c + " removido");
                    return;
                }
            }
        }
        System.out.println("Client nao encontrado");
    }

    public void listarClientes() {
        System.out.println("Listar");
        try {
            clientes = (ArrayList) Serializer.read("clientes.clt");

            for (Client c : clientes) {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void adicionarFuncionario(Employee funcionario) {
        employees.add(funcionario);
        Comparator compararNome = new Comparator<Client>() {
            @Override
            public int compare(Client cliente1, Client cliente2) {
                return cliente1.getName().compareTo(cliente2.getName());
            }
        };
        Collections.sort(employees, compararNome);
        try {
            Serializer.write("employees.fun", employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerFuncionario(String cpf) {
        for (Employee f : employees) {
            if (f.getCpf().equals(cpf)) {
                try {
                    clientes.remove(f);
                    Serializer.write("employees.fun", clientes);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(f + " removido");
                    return;
                }
            }
        }
        System.out.println("Client nao encontrado");
    }

    public void listarFuncionarios(){
        System.out.println("Listar");
        try {
            employees = (ArrayList) Serializer.read("employees.fun");

            for (Employee f : employees) {
                System.out.println(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void adicionarVeiculo(Vehicle veiculo) {
        veiculos.put(veiculo.getPlate(), veiculo);
    }

    public void removerVeiculo(Vehicle veiculo) {
    }

*/
}
