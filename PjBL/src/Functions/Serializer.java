package Functions;

import javax.swing.*;
import java.io.*;

public class Serializer {
    public static void write(String nomeArquivo, Object objeto) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(nomeArquivo);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(objeto);
        fileOutputStream.close();
        objectOutputStream.close();
    }

    public static Object read(String nomeArquivo) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(nomeArquivo);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object objeto = objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();
        return objeto;
    }


}
