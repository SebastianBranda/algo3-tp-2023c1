import java.time.LocalDateTime;
import java.util.Dictionary;

public class Evento extends Actividad {
    protected Frecuencia frecuencia;
    public Evento(){
        super();
    }

    public Evento(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, Dictionary<String, Alarma> diccionarioAlarmas, Boolean esActividadDelDia, Boolean esRepetible, Frecuencia frecuencia){
        super(titulo, descripcion, fechaHora, fechaHoraFin, diccionarioAlarmas, esActividadDelDia, esRepetible);
        this.frecuencia = frecuencia;
    }
    //public modificar(){ Discutur implementación}
}
