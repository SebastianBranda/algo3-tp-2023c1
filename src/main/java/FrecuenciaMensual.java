import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrecuenciaMensual extends Frecuencia{
    public FrecuenciaMensual(LocalDateTime fecha){
        this.fechaInicial = fecha;
        this.cantidadRepeticiones = 0;
        this.esDuracionInfinita = false;
    }
    public FrecuenciaMensual(LocalDateTime fechaInicial, boolean esDuracionInfinita){
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = 0;
        this.esDuracionInfinita = esDuracionInfinita;
    }
    public FrecuenciaMensual(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = (int) fechaInicial.until(fechaInicial, ChronoUnit.MONTHS);
        this.esDuracionInfinita = false;
    }
    public FrecuenciaMensual(LocalDateTime fechaInicial, int cantReps){
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = cantReps;
        this.esDuracionInfinita = false;
    }
}
