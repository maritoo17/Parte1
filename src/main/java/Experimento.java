import java.util.ArrayList;
import java.util.List;

public class Experimento {
    private List<Poblacion> poblaciones;

    public Experimento() {
        this.poblaciones = new ArrayList<>();
    }

    public void addPoblacion(Poblacion poblacion) {
        poblaciones.add(poblacion);
    }

    public void removePoblacion(String nombre) {
        poblaciones.removeIf(p -> p.getNombre().equals(nombre));
    }

    public List<String> getPoblacionesNombres() {
        List<String> nombres = new ArrayList<>();
        for (Poblacion p : poblaciones) {
            nombres.add(p.getNombre());
        }
        return nombres;
    }

    public Poblacion getPoblacion(String nombre) {
        for (Poblacion p : poblaciones) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null; // o lanzar una excepción si se prefiere
    }
}
