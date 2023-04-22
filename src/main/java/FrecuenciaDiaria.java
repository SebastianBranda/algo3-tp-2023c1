import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrecuenciaDiaria extends Frecuencia{
    private int intervaloDias;

    public FrecuenciaDiaria(LocalDateTime fecha){
        this.fechaInicial = fecha;
        this.cantidadRepeticiones = 0;
        this.esDuracionInfinita = false;
        this.intervaloDias = 0;
    }
    public FrecuenciaDiaria(LocalDateTime fechaInicial, boolean esDuracionInfinita, int intervaloDias){
        this.fechaInicial = fechaInicial;
        this.esDuracionInfinita = esDuracionInfinita;
        this.intervaloDias = intervaloDias;
    }
    public FrecuenciaDiaria(LocalDateTime fechaInicial, LocalDateTime fechaFinal,int intervaloDias){
        this.fechaInicial = fechaInicial;
        long cantDias = fechaInicial.until(fechaFinal, ChronoUnit.DAYS);
        this.cantidadRepeticiones = (int) (cantDias % intervaloDias);
        this.esDuracionInfinita = false;
        this.intervaloDias = intervaloDias;
    }
    public FrecuenciaDiaria(LocalDateTime fechaInicial, int cantidadRepeticiones, int intervaloDias){
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = cantidadRepeticiones;
        this.esDuracionInfinita = false;
        this.intervaloDias = intervaloDias;
    }
}
