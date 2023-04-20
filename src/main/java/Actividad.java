import java.time.LocalDateTime;
import java.util.Dictionary;

abstract class Actividad {
    protected String titulo;
    protected String descripcion;
    protected LocalDateTime fechaHora;
    protected LocalDateTime fechaHoraFin;
    protected Dictionary<String, Alarma> diccionarioAlarmas;
    protected Boolean esActividadDelDia;
    protected Boolean esRepetible;

    public Actividad(){
    }

    public Actividad(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, Dictionary<String, Alarma> diccionarioAlarmas, Boolean esActividadDelDia, Boolean esRepetible){
        this.titulo= titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.fechaHoraFin = fechaHoraFin;
        this.diccionarioAlarmas = diccionarioAlarmas;
        this.esActividadDelDia = esActividadDelDia;
        this.esRepetible = esRepetible;
    }


}
