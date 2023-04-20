import java.util.HashMap;

public class Calendario {
    private HashMap<String,Tarea> mapaTareas = new HashMap<>();
    private HashMap<String,Evento> mapaEventos = new HashMap<>();
    public Calendario(HashMap<String,Tarea> mapaTareas, HashMap<String,Evento> mapaEventos){
        this.mapaTareas = mapaTareas;
        this.mapaEventos = mapaEventos;
    };

    //public Tarea agregarTarea(){};
    //public Evento agregarEvento(){};
    //public Tarea modificarTarea(){};
    //public Evento modificarEvento(){};
    //public Tarea eliminarTarea(){};
    //public Evento eliminarEvento(){};
    //public obtenerActividadesDelDia(){};
    //public obtenerActividadesDeLaSemana(){};
    //public obtenerActividadesDelMes(){};
}
