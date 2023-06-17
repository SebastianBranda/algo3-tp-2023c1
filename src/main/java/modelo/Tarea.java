package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tarea extends Actividad {
    private Boolean estaCompletada;

    public Tarea(String titulo, String descripcion, LocalDateTime fechaHora, Boolean esActividadDelDia, Boolean estaCompletada){
        this.alarmas = new ArrayList<Alarma>();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.esActividadDelDia = esActividadDelDia;
        this.estaCompletada = estaCompletada;
    }
    public void setEstaCompletada(Boolean b) {
        this.estaCompletada = b;
    }

    public void modificar(String titulo, String descripcion, LocalDateTime fechaHora, Boolean esActividadDelDia, Boolean estaCompletada){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.esActividadDelDia = esActividadDelDia;
        this.estaCompletada = estaCompletada;
    }

    public ArrayList<Tarea> obtenerTareaEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
        ArrayList<Tarea> tarea = new ArrayList<>();
        if(this.fechaHora.isAfter(inicio) && this.fechaHora.isBefore(fin)){
            tarea.add(this);
        }
        return tarea;
    }

    @Override
    public void aceptarVisitante(VisitanteActividad visitante) {
        visitante.visitarTarea(this);
    }
}
