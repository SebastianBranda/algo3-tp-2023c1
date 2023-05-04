import java.time.LocalDateTime;
import java.util.ArrayList;

abstract class Actividad {
    protected String titulo;
    protected String descripcion;
    protected LocalDateTime fechaHora;
    protected ArrayList<Alarma> alarmas;
    protected Boolean esActividadDelDia;

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
