package ContaBancaria;

import Excecoes.ExcecaoConta;
import Excecoes.ExcecaoSaldoInsuficiente;
import Excecoes.ExcecaoSenhaInvalida;

import java.io.Serializable;

public class Conta implements Serializable {
    private String banco;
    private String agencia;
    private String numero;
    private String senha;
    private double saldo;


    public Conta(){}
    public Conta(String banco, String agencia, String numero, String senha,double saldo) {
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
        this.senha = senha;
        this.saldo = saldo;
    }

    public void debitar(double valor, String senha) throws ExcecaoSaldoInsuficiente, ExcecaoSenhaInvalida {
        if(this.saldo < valor){
            throw new ExcecaoSaldoInsuficiente();
        }
        if(!this.senha.equals(senha)){
            throw new ExcecaoSenhaInvalida();
        }
        this.saldo -= valor;
    }
    public void creditar(double valor){
        this.saldo += valor;
    }

    public double getSaldo(){
        return this.saldo;
    }
}
