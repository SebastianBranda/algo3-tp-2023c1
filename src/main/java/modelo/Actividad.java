package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Actividad implements Serializable {
    protected String titulo;
    protected String descripcion;
    protected LocalDateTime fechaHora;
    protected ArrayList<Alarma> alarmas;
    protected Boolean esActividadDelDia;

    public void agregarAlarma(Alarma a){
        this.alarmas.add(a);
    }
    public void agregarAlarmaDiferida(Alarma a, long minutosAntes){
        a.setHorarioAlarmaDiferida(minutosAntes);
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

    public String obtenerTitulo(){
        return this.titulo;
    }

    public String obtenerDescripcion(){
        return this.descripcion;
    }
    public LocalDateTime obtenerFecha(){
        return this.fechaHora;
    }

    public ArrayList<Alarma> obtenerAlarmas(){
        return this.alarmas;
    }
    public abstract void aceptarVisitante(VisitanteActividad visitante);
}
