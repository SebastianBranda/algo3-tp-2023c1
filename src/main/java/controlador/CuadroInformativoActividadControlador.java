package controlador;

import javafx.scene.layout.VBox;
import modelo.Actividad;
import modelo.Evento;
import vista.CuadroInformativoActividad;

public class CuadroInformativoActividadControlador {
    private Evento evento;
    private Actividad actividad;
    public CuadroInformativoActividadControlador(Evento evento){
        this.evento = evento;
    }
    public CuadroInformativoActividadControlador(Actividad actividad){
        this.actividad = actividad;
    }
    public VBox obtenerCuadroInformativoVista(){
        CuadroInformativoActividad cuadro = new CuadroInformativoActividad();
        VBox cuadroInformativo = cuadro.obtenerVistaEvento(actividad.obtenerTitulo(), actividad.obtenerDescripcion());
        return cuadroInformativo;
    }
}
