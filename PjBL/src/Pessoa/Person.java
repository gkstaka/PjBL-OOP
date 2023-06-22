package Pessoa;

import Excecoes.PersonExceptionInvalidCPF;
import Excecoes.PersonExceptionInvalidPhone;

import java.io.Serializable;
import java.util.Locale;


public class Person implements Serializable {
    private String name;
    private String cpf;
    private String phone;

    public Person(String name, String cpf, String phone) throws PersonExceptionInvalidCPF, PersonExceptionInvalidPhone {
        this.name = capitalizeName(name);

        String formattedCPF = formatCPF(cpf);
        if (formattedCPF.length() != 11) throw new PersonExceptionInvalidCPF();
        this.cpf = formattedCPF.substring(0, 3) + "." + formattedCPF.substring(3, 6) + "." + formattedCPF.substring(6, 9) + "-" + formattedCPF.substring(9, 11);
        String formattedPhoneNumber = formatPhoneNumber(phone);
        if (phone.length() != 11) throw new PersonExceptionInvalidPhone();
        this.phone = "(" + formattedPhoneNumber.substring(0, 2) + ") " + formattedPhoneNumber.substring(2, 7) + " - " + formattedPhoneNumber.substring(7, 11);
    }

    private String capitalizeName(String name) {
        String nameFormatted = "";
        String[] aux = name.split(" ");
        for (int i = 0; i < aux.length; i++) {
            nameFormatted += aux[i].substring(0, 1).toUpperCase(Locale.ROOT) + aux[i].substring(1).toLowerCase() + " ";
        }
        nameFormatted.trim();
        return nameFormatted;
    }

    public String getName() {
        return name;
    }

    private String formatCPF(String cpf) {
        char[] cpfArray = cpf.toCharArray();
        String cpfFormatted = "";
        for (char c : cpfArray) {
            if (Character.isDigit(c)) cpfFormatted += Character.toString(c);
        }
        return cpfFormatted;
    }

    private String formatPhoneNumber(String phone) {
        char[] phoneArray = phone.toCharArray();
        String phoneFormatted = "";
        for (char c : phoneArray) {
            if (Character.isDigit(c)) phoneFormatted += Character.toString(c);
        }
        return phoneFormatted;
    }


    public String getCpf() {
        return cpf;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws PersonExceptionInvalidPhone {
        String formattedPhoneNumber = formatPhoneNumber(phone);
        if (phone.length() != 11) throw new PersonExceptionInvalidPhone();
        this.phone = "(" + formattedPhoneNumber.substring(0, 2) + ") " + formattedPhoneNumber.substring(2, 7) + " - " + formattedPhoneNumber.substring(7, 11);

    }


}