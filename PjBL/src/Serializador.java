import java.io.*;

public class Serializador {
    public static void escrever(String nomeArquivo, Object objeto) throws IOException {
        FileOutputStream fos = new FileOutputStream(nomeArquivo, true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objeto);
        fos.close();
        oos.close();
    }

    public static Object ler(String nomeArquivo) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(nomeArquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object objeto = ois.readObject();
        fis.close();
        ois.close();
        return objeto;
    }
}
