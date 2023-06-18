package modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Evento extends Actividad {
    private LocalDateTime fechaHoraFin;
    private Frecuencia frecuencia;
    private TipoFrecuencia tipoFrecuencia;
    private Boolean esRepetible;

    public Evento(String titulo,
                  String descripcion,
                  LocalDateTime fechaHora,
                  LocalDateTime fechaHoraFin,
                  Boolean esActividadDelDia,
                  Frecuencia frecuencia,
                  TipoFrecuencia tipoFrecuencia,
                  Boolean esRepetible){
        this.alarmas = new ArrayList<Alarma>();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.fechaHoraFin = fechaHoraFin;
        this.esActividadDelDia = esActividadDelDia;
        this.frecuencia = frecuencia;
        this.tipoFrecuencia = tipoFrecuencia;
        this.esRepetible = esRepetible;
    }

    public void modificar(String titulo,
                          String descripcion,
                          LocalDateTime fechaHora,
                          LocalDateTime fechaHoraFin,
                          Boolean esActividadDelDia,
                          Frecuencia frecuencia,
                          Boolean esRepetible){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.fechaHoraFin = fechaHoraFin;
        this.esActividadDelDia = esActividadDelDia;
        this.frecuencia = frecuencia;
        this.esRepetible = esRepetible;
    }
    public void modificar(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, Boolean esActividadDelDia){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.fechaHoraFin = fechaHoraFin;
        this.esActividadDelDia = esActividadDelDia;
    }
    public ArrayList<EventoRepetido> eventosRepetidosEntreFechas(LocalDateTime inicio, LocalDateTime fin){
        return switch (this.tipoFrecuencia) {
            case DIARIA ->
                    CalculadorDeEventosRepetidos.repeticionesEntreFechasCasoDiario(this, this.fechaHoraFin, this.frecuencia, inicio, fin);
            case SEMANAL ->
                    CalculadorDeEventosRepetidos.repeticionesEntreFechasCasoSemanal(this, this.fechaHoraFin, this.frecuencia, inicio, fin);
            case MENSUAL ->
                    CalculadorDeEventosRepetidos.repeticionesEntreFechasCasoMensual(this, this.fechaHoraFin, this.frecuencia, inicio, fin);
            case ANUAL ->
                    CalculadorDeEventosRepetidos.repeticionesEntreFechasCasoAnual(this, this.fechaHoraFin, this.frecuencia, inicio, fin);
        };
    }
    @Override
    public void aceptarVisitante(VisitanteActividad visitante) {
        visitante.visitarEvento(this);
    }
}

