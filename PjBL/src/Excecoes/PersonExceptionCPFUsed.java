package Excecoes;

public class PersonExceptionCPFUsed extends PersonException {
    public PersonExceptionCPFUsed() {
        super("CPF ja cadastrado");
    }
}
