import java.time.LocalDateTime;
import java.util.HashMap;

abstract class Actividad {
    protected String titulo;
    protected String descripcion;
    protected LocalDateTime fechaHora;
    protected LocalDateTime fechaHoraFin;
    protected HashMap<String, Alarma> alarmas;
    protected Boolean esActividadDelDia;
    protected void modificar(){};
    public Actividad(){
    }

    public Actividad(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, HashMap<String, Alarma> alarmas, Boolean esActividadDelDia){
        this.titulo= titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.fechaHoraFin = fechaHoraFin;
        this.alarmas = alarmas;
        this.esActividadDelDia = esActividadDelDia;
    }


}
