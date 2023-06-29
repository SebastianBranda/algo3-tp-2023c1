package modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class FrecuenciaDiaria extends Frecuencia{
    private int intervaloDias;

    public FrecuenciaDiaria(LocalDateTime fecha){
        this.fechaInicial = fecha;
        this.cantidadRepeticiones = 0;
        this.esDuracionInfinita = false;
        this.intervaloDias = 1;
    }
    public FrecuenciaDiaria(LocalDateTime fechaInicial, boolean esDuracionInfinita, int intervaloDias){
        this.fechaInicial = fechaInicial;
        this.esDuracionInfinita = esDuracionInfinita;
        this.intervaloDias = intervaloDias;
    }
    public FrecuenciaDiaria(LocalDateTime fechaInicial, LocalDateTime fechaFinal,int intervaloDias){
        this.fechaInicial = fechaInicial;
        long cantDias = fechaInicial.until(fechaFinal, ChronoUnit.DAYS);
        this.cantidadRepeticiones = (int) (cantDias / intervaloDias);
        this.esDuracionInfinita = false;
        this.intervaloDias = intervaloDias;
    }
    public FrecuenciaDiaria(LocalDateTime fechaInicial, int cantidadRepeticiones, int intervaloDias){
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = cantidadRepeticiones-1;
        this.esDuracionInfinita = false;
        this.intervaloDias = intervaloDias;
    }

    @Override
    public ArrayList<Integer> reglaDeRepeticion() {
        ArrayList<Integer> reglaRep = new ArrayList<>();
        reglaRep.add(this.intervaloDias);
        return reglaRep;
    }
}
