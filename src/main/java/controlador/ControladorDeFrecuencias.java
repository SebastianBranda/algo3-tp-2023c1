package controlador;

import modelo.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ControladorDeFrecuencias {
    public static Frecuencia determinarFrecuenciaDiaria(BuilderEleccionFrecuencia builderFrecuencia){
        Frecuencia frecuencia;
        LocalDateTime fechaInicio = builderFrecuencia.getFechaInicio();
        int intervalo = builderFrecuencia.getIntervaloDias();

        switch (builderFrecuencia.getTipoRepeticion()){
            case INFINITA:
                frecuencia = new FrecuenciaDiaria(fechaInicio, true, intervalo);
                break;
            case FECHA_DETERMINADA:
                LocalDateTime fechaFinRepeticion = builderFrecuencia.getFechaLimite();
                frecuencia = new FrecuenciaDiaria(fechaInicio, fechaFinRepeticion, intervalo);
                break;
            case CANT_DETERMINADA:
                int cantidadReps = builderFrecuencia.getCantidadRepeticiones();
                frecuencia = new FrecuenciaDiaria(fechaInicio, cantidadReps, intervalo);
                break;
            default:
                frecuencia = new FrecuenciaDiaria(fechaInicio);
                break;
        }
        return frecuencia;
    }
    public static Frecuencia determinarFrecuenciaSemanal(BuilderEleccionFrecuencia builderFrecuencia){
        Frecuencia frecuencia;
        LocalDateTime fechaInicio = builderFrecuencia.getFechaInicio();
        DayOfWeek[] diasElegidos = builderFrecuencia.getDiasElegidos();

        switch (builderFrecuencia.getTipoRepeticion()){
            case INFINITA:
                frecuencia = new FrecuenciaSemanal(fechaInicio, true, diasElegidos);
                break;
            case FECHA_DETERMINADA:
                LocalDateTime fechaFinRepeticion = builderFrecuencia.getFechaLimite();
                frecuencia = new FrecuenciaSemanal(fechaInicio, fechaFinRepeticion, diasElegidos);
                break;
            case CANT_DETERMINADA:
                int cantidadReps = builderFrecuencia.getCantidadRepeticiones();
                frecuencia = new FrecuenciaSemanal(fechaInicio, cantidadReps, diasElegidos);
                break;
            default:
                frecuencia = new FrecuenciaSemanal(fechaInicio, diasElegidos);
                break;
        }
        return frecuencia;
    }
    public static Frecuencia determinarFrecuenciaMensual(BuilderEleccionFrecuencia builderFrecuencia){
        Frecuencia frecuencia;
        LocalDateTime fechaInicio = builderFrecuencia.getFechaInicio();

        switch (builderFrecuencia.getTipoRepeticion()){
            case INFINITA:
                frecuencia = new FrecuenciaMensual(fechaInicio, true);
                break;
            case FECHA_DETERMINADA:
                LocalDateTime fechaFinRepeticion = builderFrecuencia.getFechaLimite();
                frecuencia = new FrecuenciaMensual(fechaInicio, fechaFinRepeticion);
                break;
            case CANT_DETERMINADA:
                int cantidadReps = builderFrecuencia.getCantidadRepeticiones();
                frecuencia = new FrecuenciaMensual(fechaInicio, cantidadReps);
                break;
            default:
                frecuencia = new FrecuenciaMensual(fechaInicio);
                break;
        }
        return frecuencia;
    }
    public static Frecuencia determinarFrecuenciaAnual(BuilderEleccionFrecuencia builderFrecuencia){
        Frecuencia frecuencia;
        LocalDateTime fechaInicio = builderFrecuencia.getFechaInicio();

        switch (builderFrecuencia.getTipoRepeticion()){
            case INFINITA:
                frecuencia = new FrecuenciaAnual(fechaInicio, true);
                break;
            case FECHA_DETERMINADA:
                LocalDateTime fechaFinRepeticion = builderFrecuencia.getFechaLimite();
                frecuencia = new FrecuenciaAnual(fechaInicio, fechaFinRepeticion);
                break;
            case CANT_DETERMINADA:
                int cantidadReps = builderFrecuencia.getCantidadRepeticiones();
                frecuencia = new FrecuenciaAnual(fechaInicio, cantidadReps);
                break;
            default:
                frecuencia = new FrecuenciaAnual(fechaInicio);
                break;
        }
        return frecuencia;
    }
}