package controlador;

import modelo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PrincipalControlador{
    private Calendario calendario;
    String archivoCalendario;
    public PrincipalControlador(String archivoCalendario){
        this.calendario = new Calendario();
        this.archivoCalendario = archivoCalendario;
        this.calendario.cargarActividades(archivoCalendario);
    }
    public void agregarEvento(Evento evento){
        this.calendario.agregarEvento(evento);
    }
    public void agregarTarea(Tarea tarea){
        this.calendario.agregarTarea(tarea);
    }
    public ArrayList<Actividad> obtenerActividadesDelDia(LocalDateTime fecha){
        return this.calendario.obtenerActividadesDelDia(fecha);
    }
    public ArrayList<Actividad> obtenerActividadesDeLaSemana(LocalDateTime fecha){
        return this.calendario.obtenerActividadesDeLaSemana(fecha);
    }
    public ArrayList<Actividad> obtenerActividadesDelMes(LocalDateTime fecha){
        return this.calendario.obtenerActividadesDelMes(fecha);
    }
    public ArrayList<Alarma> obtenerProximasAlarmas(){
        return this.calendario.proximasAlarmas(LocalDateTime.now().plusMinutes(1));
    }
    public void guardarEstadoCalendario(){
        this.calendario.guardarActividades(this.archivoCalendario);
    }
}
