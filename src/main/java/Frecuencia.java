import java.time.Duration;

abstract class Frecuencia {
    protected Duration duracionFrecuencia;

    public Frecuencia(){}

    public Frecuencia(Duration duracionFrecuencia){
        this.duracionFrecuencia = duracionFrecuencia;
    }

}
