import Excecoes.ExcecaoNomeInvalido;
import Pessoa.Pessoa;

import java.util.Scanner;
import Excecoes.ExcecaoPessoa;
public class Funcoes {
    public static boolean temNumero(String arg){
        for (int i = 0; i < arg.length(); i++) {
            char c = arg.charAt(i);
            if(Character.isDigit(c)) return true;
        }
        return false;
    }

}
