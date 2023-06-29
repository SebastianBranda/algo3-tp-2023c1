package modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class FrecuenciaMensual extends Frecuencia{
    public FrecuenciaMensual(LocalDateTime fecha){
        this.fechaInicial = fecha;
        this.cantidadRepeticiones = 1;
        this.esDuracionInfinita = false;
    }
    public FrecuenciaMensual(LocalDateTime fechaInicial, boolean esDuracionInfinita){
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = 0;
        this.esDuracionInfinita = esDuracionInfinita;
    }
    public FrecuenciaMensual(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = (int) fechaInicial.until(fechaFinal, ChronoUnit.MONTHS);
        this.esDuracionInfinita = false;
    }
    public FrecuenciaMensual(LocalDateTime fechaInicial, int cantReps){
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = cantReps;
        this.esDuracionInfinita = false;
    }

    @Override
    public ArrayList<Integer> reglaDeRepeticion() {
        return null;
    }
}
