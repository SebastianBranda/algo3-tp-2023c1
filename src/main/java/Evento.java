import java.time.LocalDateTime;

public class Evento extends Actividad {
    private LocalDateTime fechaHoraFin;
    private Frecuencia frecuencia;
    private Boolean esRepetible;

    public Evento(String titulo,
                  String descripcion,
                  LocalDateTime fechaHora,
                  LocalDateTime fechaHoraFin,
                  Boolean esActividadDelDia,
                  Frecuencia frecuencia,
                  Boolean esRepetible){
        this.setTitulo(titulo);
        this.setDescripcion(descripcion);
        this.setFechaHora(fechaHora);
        this.setEsActividadDelDia(esActividadDelDia);
        this.frecuencia = frecuencia;
        this.esRepetible = esRepetible;
    }

    void modificar(String titulo,
                   String descripcion,
                   LocalDateTime fechaHora,
                   LocalDateTime fechaHoraFin,
                   Boolean esActividadDelDia,
                   Frecuencia frecuencia,
                   Boolean esRepetible){
        this.setTitulo(titulo);
        this.setDescripcion(descripcion);
        this.setFechaHora(fechaHora);
        this.setEsActividadDelDia(esActividadDelDia);
        this.frecuencia = frecuencia;
        this.esRepetible = esRepetible;
    }
}
