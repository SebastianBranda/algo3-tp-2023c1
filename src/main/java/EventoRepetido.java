import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class EventoRepetido extends Actividad{
    private LocalDateTime fechaHoraFin;
    private Evento eventoOriginal;

    public EventoRepetido(Evento original, LocalDateTime fechaHora, LocalDateTime fechaHoraFin){
        this.setTitulo(original.getTitulo());
        this.setDescripcion(original.getTitulo());
        this.setFechaHora(fechaHora);
        this.fechaHoraFin = fechaHoraFin;
        this.setEsActividadDelDia(original.getEsActividadDelDia());
        this.eventoOriginal = original;
        // TODO: añadir alarmas del evento original, con las fechas modificadas a las del evento repetido para que posea sus propias alarmas
        for(var alarmaOriginal: this.eventoOriginal.alarmas){
            long minutosDiferencia = alarmaOriginal.getHorarioAlarma().until(this.eventoOriginal.getFechaHora(), ChronoUnit.MINUTES);
            switch (alarmaOriginal.tipoAlarma){
                case EMAIL:
                    this.agregarAlarmaDiferida(new AlarmaEmail(fechaHora, TipoAlarma.EMAIL), minutosDiferencia);
                    break;
                case SONIDO:
                    this.agregarAlarmaDiferida(new AlarmaSonido(fechaHora, TipoAlarma.SONIDO), minutosDiferencia);
                    break;
                case VISUAL:
                    this.agregarAlarmaDiferida(new AlarmaVisual(fechaHora, TipoAlarma.VISUAL), minutosDiferencia);
                    break;
            }
        }
    }

    public void modificar(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, Boolean esActividadDelDia, Evento original){
        original.modificar(titulo, descripcion, fechaHora, fechaHoraFin, esActividadDelDia);
    }
}
