package Excecoes;

public class PersonExceptionInvalidCPF extends PersonException {
    public PersonExceptionInvalidCPF(){
        super("CPF invalido");
    }
}
