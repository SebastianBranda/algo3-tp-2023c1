package controlador;

import modelo.Actividad;
import modelo.Calendario;
import modelo.Evento;

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
    public ArrayList<Actividad> obtenerActividadesDelDia(LocalDateTime fecha){
        return this.calendario.obtenerActividadesDelDia(fecha);
    }

    public void guardarEstadoCalendario(){
        this.calendario.guardarActividades(this.archivoCalendario);
    }
}
