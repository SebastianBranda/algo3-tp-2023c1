import java.time.LocalDateTime;

public class EventoRepetido extends Actividad{
    private LocalDateTime fechaHoraFin;
    private Evento eventoOriginal;

    public EventoRepetido(String titulo,
                  String descripcion,
                  LocalDateTime fechaHora,
                  LocalDateTime fechaHoraFin,
                  Boolean esActividadDelDia,
                  Evento original){
        this.setTitulo(titulo);
        this.setDescripcion(descripcion);
        this.setFechaHora(fechaHora);
        this.fechaHoraFin = fechaHoraFin;
        this.setEsActividadDelDia(esActividadDelDia);
        this.eventoOriginal = original;
    }

    public void modificar(String titulo,
                     String descripcion,
                     LocalDateTime fechaHora,
                     LocalDateTime fechaHoraFin,
                     Boolean esActividadDelDia,
                     Evento original){
        original.modificar(titulo, descripcion, fechaHora, fechaHoraFin, esActividadDelDia);
    }
}
