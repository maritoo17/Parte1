import java.io.*;

public class FileManager {
    private Experimento experimento;

    public FileManager(Experimento experimento) {
        this.experimento = experimento;
    }

    public void abrirArchivo(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            experimento = (Experimento) ois.readObject();
        }
    }

    public void guardarArchivo(String ruta) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(experimento);
        }
    }

    public Experimento getExperimento() {
        return experimento;
    }

    public void setExperimento(Experimento experimento) {
        this.experimento = experimento;
    }
}
