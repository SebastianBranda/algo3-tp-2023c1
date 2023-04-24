import java.time.LocalDateTime;
import java.util.ArrayList;

abstract class Actividad {
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaHora;
    ArrayList<Alarma> alarmas = new ArrayList<>();
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
    protected boolean getEsActividadDelDia(){
        return this.esActividadDelDia;
    }
    protected void agregarAlarma(Alarma a){
        this.alarmas.add(a);
    }
    protected void agregarAlarmaDiferida(Alarma a, long minutosAntes){
        LocalDateTime horario = a.getHorarioAlarma().minusMinutes(minutosAntes);
        a.setHorarioAlarma(horario);
        this.agregarAlarma(a);
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

}
