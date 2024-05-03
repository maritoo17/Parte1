import java.io.Serializable;
import java.util.Date;

public class Poblacion implements Serializable {
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private int bacteriasIniciales;
    private double temperatura;
    private String luminosidad;  // Alta, Media, Baja
    private int comidaInicial;
    private int diaIncremento;
    private int comidaIncremento;
    private int comidaFinal;

    public Poblacion(String nombre, Date fechaInicio, Date fechaFin, int bacteriasIniciales,
                     double temperatura, String luminosidad, int comidaInicial, int diaIncremento,
                     int comidaIncremento, int comidaFinal) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.bacteriasIniciales = bacteriasIniciales;
        this.temperatura = temperatura;
        this.luminosidad = luminosidad;
        this.comidaInicial = comidaInicial;
        this.diaIncremento = diaIncremento;
        this.comidaIncremento = comidaIncremento;
        this.comidaFinal = comidaFinal;
    }

    public String getNombre() {
        return nombre;
    }

    // Add getters for other fields if necessary
}
