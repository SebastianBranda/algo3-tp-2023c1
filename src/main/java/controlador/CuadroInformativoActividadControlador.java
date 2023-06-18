package controlador;

import javafx.scene.layout.VBox;
import modelo.*;
import vista.CuadroInformativoEvento;
import vista.CuadroInformativoTarea;

public class CuadroInformativoActividadControlador {
    private Actividad actividad;
    private VBox cuadroInformativo;
    public CuadroInformativoActividadControlador(Actividad actividad){
        this.actividad = actividad;
    }
    public VBox obtenerCuadroInformativoVista(){
        this.actividad.aceptarVisitante(new VisitanteActividad() {
            @Override
            public void visitarEvento(Evento evento) {
                // cuadroInformativo = CuadroInformativoEvento.obtenerVista(evento);
            }
            @Override
            public void visitarTarea(Tarea tarea) {
                cuadroInformativo = CuadroInformativoTarea.obtenerVista(tarea);
            }
            @Override
            public void visitarEventoRepetido(EventoRepetido eventoRepetido) {
                cuadroInformativo = CuadroInformativoEvento.obtenerVista(eventoRepetido);
            }
        });
        return cuadroInformativo;
    }
}
