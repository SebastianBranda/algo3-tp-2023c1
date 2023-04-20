import java.time.LocalDateTime;
import java.util.Dictionary;

public class Tarea extends Actividad{

    private Boolean estaCompletada;

    public Tarea(){
        super();
    }
    public Tarea(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, Dictionary<String, Alarma> diccionarioAlarmas, Boolean esActividadDelDia, Boolean esRepetible, Boolean estaCompletada){
        super(titulo, descripcion, fechaHora, fechaHoraFin, diccionarioAlarmas, esActividadDelDia, esRepetible);
        this.estaCompletada = estaCompletada;
    }

    public Boolean marcarComoCompleta() {
        return this.estaCompletada = true;
    }
}
