package Pessoa;

import Excecoes.CPFInvalido;
import Excecoes.ExceptionPerson;
import Excecoes.TelefoneInvalido;

import java.io.Serializable;
import java.util.Locale;


public class Person implements Serializable {
    private String name;
    private String cpf;
    private String phone;

    public Person(String name, String cpf, String phone) throws CPFInvalido, TelefoneInvalido {
        this.name = capitalizeName(name);

        this.cpf = formatCPF(cpf);
        if(cpf.length() != 11) throw new CPFInvalido();
        this.phone = formatPhoneNumber(phone);
        if(phone.length() != 11) throw new TelefoneInvalido();

    }

    private String capitalizeName(String name) {
        String nameFormatted = "";
        String[] aux = name.split(" ");
        for(int i = 0; i < aux.length; i++){
            nameFormatted += aux[i].substring(0,1).toUpperCase(Locale.ROOT) + aux[i].substring(1).toLowerCase() + " ";
        }
        nameFormatted.trim();
        return nameFormatted;
    }

    public String getName() {
        return name;
    }

    private String formatCPF(String cpf){
        char [] cpfArray = cpf.toCharArray();
        String cpfFormatted = "";
        for(char c : cpfArray){
            if(Character.isDigit(c)) cpfFormatted += Character.toString(c);
        }
        return cpfFormatted;
    }

    private String formatPhoneNumber(String phone){
        char [] phoneArray = cpf.toCharArray();
        String phoneFormatted = "";
        for(char c : phoneArray){
            if(Character.isDigit(c)) phoneFormatted += Character.toString(c);
        }
        return phoneFormatted;
    }


    public String getCpf() {
        return cpf;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws TelefoneInvalido{
        this.phone = phone;
        if(phone.length() != 11) throw new TelefoneInvalido();
    }


}