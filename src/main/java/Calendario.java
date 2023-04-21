import java.util.ArrayList;
import java.util.List;

public class Calendario {
    private List<Actividad> actividades;
    public Calendario(){
        this.actividades = new ArrayList<>();
    }
    public void agregarActividad(Actividad actividad){
        this.actividades.add(actividad);
    }

    public void modificarActividad(Actividad vieja, Actividad nueva){
            if(this.actividades.contains(vieja)){
                int index = this.actividades.indexOf(vieja);
                this.actividades.set(index, nueva);
            }else{
                this.agregarActividad(nueva);
            }
    }

    public void eliminarActividad(Actividad actividad){
        this.actividades.remove(actividad);
    }

    //public Tarea modificarTarea(){};
    //public Evento modificarEvento(){};
    //public Tarea eliminarTarea(){};
    //public Evento eliminarEvento(){};
    //public obtenerActividadesDelDia(){};
    //public obtenerActividadesDeLaSemana(){};
    //public obtenerActividadesDelMes(){};
}
