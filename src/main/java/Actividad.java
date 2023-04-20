import java.time.LocalDateTime;
import java.util.Dictionary;

abstract class Actividad {
    protected String titulo;
    protected String descripcion;
    private LocalDateTime fechaHora;
    protected Dictionary diccionarioAlarmas;
    protected Boolean esActividadDelDia;

    protected Boolean esRepetible;


}
