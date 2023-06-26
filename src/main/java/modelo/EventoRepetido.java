package modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EventoRepetido extends Actividad{
    private LocalDateTime fechaHoraFin;
    private Evento eventoOriginal;

    public EventoRepetido(Evento original, LocalDateTime fechaHora, LocalDateTime fechaHoraFin){
        this.alarmas = new ArrayList<Alarma>();
        this.titulo = original.titulo;
        this.descripcion = original.descripcion;
        this.fechaHora = fechaHora;
        this.fechaHoraFin = fechaHoraFin;
        this.esActividadDelDia = original.esActividadDelDia;
        this.eventoOriginal = original;
        for(var alarmaOriginal: this.eventoOriginal.alarmas){
            long minutosDiferencia = alarmaOriginal.getHorarioAlarma().until(this.eventoOriginal.fechaHora, ChronoUnit.MINUTES);
            switch (alarmaOriginal.tipoAlarma){
                case EMAIL:
                    this.agregarAlarmaDiferida(new AlarmaEmail(fechaHora), minutosDiferencia);
                    break;
                case SONIDO:
                    this.agregarAlarmaDiferida(new AlarmaSonido(fechaHora), minutosDiferencia);
                    break;
                case VISUAL:
                    this.agregarAlarmaDiferida(new AlarmaVisual(fechaHora), minutosDiferencia);
                    break;
            }
        }
    }

    public void modificar(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, Boolean esActividadDelDia, Evento original){
        original.modificar(titulo, descripcion, fechaHora, fechaHoraFin, esActividadDelDia);
    }

    public Evento obtenerEventoOriginal(){
        return this.eventoOriginal;
    }
    public LocalDateTime obtenerFechaFin(){
        return this.fechaHoraFin;
    }
    public TipoFrecuencia obtenerTipoFrecuencia(){
        return this.obtenerEventoOriginal().obtenerTipoFrecuencia();
    }

    @Override
    public void aceptarVisitante(VisitanteActividad visitante) {
        visitante.visitarEventoRepetido(this);
    }
}
