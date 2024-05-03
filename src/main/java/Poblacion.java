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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public int getBacteriasIniciales() {
        return bacteriasIniciales;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public String getLuminosidad() {
        return luminosidad;
    }

    public int getComidaInicial() {
        return comidaInicial;
    }

    public int getDiaIncremento() {
        return diaIncremento;
    }

    public int getComidaIncremento() {
        return comidaIncremento;
    }

    public int getComidaFinal() {
        return comidaFinal;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setBacteriasIniciales(int bacteriasIniciales) {
        this.bacteriasIniciales = bacteriasIniciales;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public void setLuminosidad(String luminosidad) {
        this.luminosidad = luminosidad;
    }

    public void setComidaInicial(int comidaInicial) {
        this.comidaInicial = comidaInicial;
    }

    public void setDiaIncremento(int diaIncremento) {
        this.diaIncremento = diaIncremento;
    }

    public void setComidaIncremento(int comidaIncremento) {
        this.comidaIncremento = comidaIncremento;
    }

    public void setComidaFinal(int comidaFinal) {
        this.comidaFinal = comidaFinal;
    }
}

