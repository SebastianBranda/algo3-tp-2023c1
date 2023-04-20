import java.time.LocalDateTime;
import java.util.HashMap;

public class Tarea extends Actividad{

    private Boolean estaCompletada;
    @Override
    protected void modificar(){};
    public Tarea(){
        super();
    }
    public Tarea(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, HashMap<String, Alarma> alarmas, Boolean esActividadDelDia, Boolean estaCompletada){
        super(titulo, descripcion, fechaHora, fechaHoraFin, alarmas, esActividadDelDia);
        this.estaCompletada = estaCompletada;
    }
    public Boolean marcarComoCompleta() {
        return this.estaCompletada = true;
    }
}
