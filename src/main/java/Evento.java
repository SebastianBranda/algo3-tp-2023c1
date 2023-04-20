import java.time.LocalDateTime;
import java.util.HashMap;

public class Evento extends Actividad {
    protected Frecuencia frecuencia;
    protected Boolean esRepetible;
    @Override
    protected void modificar(){};
    public Evento(){
        super();
    }
    public Evento(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, HashMap<String, Alarma> alarmas, Boolean esActividadDelDia, Boolean esRepetible, Frecuencia frecuencia){
        super(titulo, descripcion, fechaHora, fechaHoraFin, alarmas, esActividadDelDia);
        this.frecuencia = frecuencia;
        this.esRepetible = esRepetible;
    }
}
