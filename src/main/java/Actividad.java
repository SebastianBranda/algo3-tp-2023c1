import java.time.LocalDateTime;
import java.util.ArrayList;

abstract class Actividad {
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaHora;
    private ArrayList<Alarma> alarmas = new ArrayList<>();
    private Boolean esActividadDelDia;

    protected void setTitulo(String titulo){
        this.titulo = titulo;
    }
    protected String getTitulo(){
        return this.titulo;
    }
    protected void setDescripcion(String desc){
        this.descripcion = desc;
    }
    protected String getDescripcion(){
        return this.descripcion;
    }
    protected void setFechaHora(LocalDateTime fechaYHora){
        this.fechaHora = fechaYHora;
    }
    protected LocalDateTime getFechaHora(){
        return this.fechaHora;
    }
    protected void setEsActividadDelDia(boolean b){
        this.esActividadDelDia = b;
    }
    protected void agregarAlarma(Alarma a){
        this.alarmas.add(a);
    }
    public void modificarAlarma(Alarma vieja, Alarma nueva){
        if(this.alarmas.contains(vieja)){
            int index = this.alarmas.indexOf(vieja);
            this.alarmas.set(index, nueva);
        }else{
            this.alarmas.add(nueva);
        }
    }
    public void eliminarAlarma(Alarma a){
        this.alarmas.remove(a);
    }
    abstract void modificar();

}
