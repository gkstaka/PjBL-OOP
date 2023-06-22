package Excecoes;

public class PersonExceptionInvalidPhone extends PersonException {
    public PersonExceptionInvalidPhone(){
        super("Telefone invalido");
    }
}
