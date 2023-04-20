import java.time.LocalDateTime;
import java.util.HashMap;

public class Calendario {
    private HashMap<String,Tarea> tareas = new HashMap<>();
    private HashMap<String,Evento> eventos = new HashMap<>();
    public Calendario(HashMap<String,Tarea> tareas, HashMap<String,Evento> eventos){
        this.tareas = tareas;
        this.eventos = eventos;
    }
    public Tarea agregarTarea(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, HashMap<String, Alarma> alarmas, Boolean esActividadDelDia, Boolean estaCompletada){
     return tareas.put(titulo, new Tarea(titulo, descripcion, fechaHora, fechaHoraFin, alarmas, esActividadDelDia, estaCompletada));}
    public Evento agregarEvento(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, HashMap<String, Alarma> alarmas, Boolean esActividadDelDia, Boolean esRepetible, Frecuencia frecuencia){
        return eventos.put(titulo, new Evento(titulo, descripcion, fechaHora, fechaHoraFin, alarmas, esActividadDelDia, esRepetible, frecuencia));}
    //public Tarea modificarTarea(){};
    //public Evento modificarEvento(){};
    //public Tarea eliminarTarea(){};
    //public Evento eliminarEvento(){};
    //public obtenerActividadesDelDia(){};
    //public obtenerActividadesDeLaSemana(){};
    //public obtenerActividadesDelMes(){};
}
