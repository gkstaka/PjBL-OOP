package Veiculo;

import Pessoa.Cliente;
import Servicos.LimpezaExterna;
import Servicos.*;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Veiculo {
    private String placa;
    private String modelo;
    private String fabricante;
    private HashSet<Servico> servicoContratado;

    public Veiculo(String placa, String modelo, String fabricante) {
        this.placa = placa;
        this.modelo = modelo;
        this.fabricante = fabricante;
    }

    public abstract double custoLimpeza();
    public abstract double custoEstacionamento();


}
