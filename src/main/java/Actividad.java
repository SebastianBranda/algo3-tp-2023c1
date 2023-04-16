import java.time.LocalDateTime;
import java.util.ArrayList;

abstract class Actividad {
    protected String titulo;
    protected String descripcion;
    private LocalDateTime fechaHora;
    protected ArrayList alarmas;
    protected Boolean esActividadDelDia;

}
