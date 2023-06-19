package controlador;

import modelo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PrincipalControlador{
    private Calendario calendario;
    private NotificadorDeAlarmas notificador;
    String archivoCalendario;
    public PrincipalControlador(String archivoCalendario){
        this.calendario = new Calendario();
        this.archivoCalendario = archivoCalendario;
        this.calendario.cargarActividades(archivoCalendario);
        this.iniciarNotificador();
    }
    public void agregarEvento(Evento evento){
        this.calendario.agregarEvento(evento);
        this.reiniciarNotificador();
    }
    public void agregarTarea(Tarea tarea){
        this.calendario.agregarTarea(tarea);
        this.reiniciarNotificador();
    }
    public void modificarEvento(Evento eventoViejo, Evento eventoNuevo){
        this.calendario.modificarEvento(eventoViejo, eventoNuevo);
        this.reiniciarNotificador();
    }
    public void eliminarEvento(Evento evento){
        this.calendario.eliminarEvento(evento);
        this.reiniciarNotificador();
    }
    public void modificarTarea(Tarea tareaVieja, Tarea tareaNueva){
        this.calendario.modificarTarea(tareaVieja, tareaNueva);
        this.reiniciarNotificador();
    }
    public void eliminarTarea(Tarea tarea){
        this.calendario.eliminarTarea(tarea);
        this.reiniciarNotificador();
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
    public void iniciarNotificador(){
        this.notificador = new NotificadorDeAlarmas(this.obtenerProximasAlarmas(), this);
    }
    public void reiniciarNotificador(){
        this.notificador.setAlarmas(this.obtenerProximasAlarmas());
    }
}
