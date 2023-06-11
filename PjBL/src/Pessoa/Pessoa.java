package Pessoa;

import ContaBancaria.Conta;
import Excecoes.ExcecaoPessoa;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private String nome;
    private String cpf;
    private String contato;
    private Conta conta;

    public Pessoa(String nome, String cpf, String contato, Conta conta) throws ExcecaoPessoa{
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
        this.conta = conta;
    }

    public void pagar(double valor){

    }


}
