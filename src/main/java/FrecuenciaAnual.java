import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class FrecuenciaAnual extends Frecuencia{
    public FrecuenciaAnual(LocalDateTime fecha){
        this.fechaInicial = fecha;
        this.cantidadRepeticiones = 0;
        this.esDuracionInfinita = false;
    }
    public FrecuenciaAnual(LocalDateTime fechaInicial, boolean esDuracionInfinita){
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = 0;
        this.esDuracionInfinita = esDuracionInfinita;
    }
    public FrecuenciaAnual(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = (int) fechaInicial.until(fechaInicial, ChronoUnit.YEARS);
        this.esDuracionInfinita = false;
    }
    public FrecuenciaAnual(LocalDateTime fechaInicial, int cantReps){
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = cantReps;
        this.esDuracionInfinita = false;
    }

    @Override
    public ArrayList<Integer> reglaDeRepeticion() {
        return null;
    }
}
