package controlador;

import modelo.*;
import vista.Ventana;

import java.time.LocalDateTime;

public abstract class BaseControlador {
    protected PrincipalControlador principalControlador;
    protected Ventana ventana;
    protected String archivoFXML;
    public BaseControlador(PrincipalControlador principalControlador, Ventana ventana, String archivoFXML) {
        this.principalControlador = principalControlador;
        this.ventana = ventana;
        this.archivoFXML = archivoFXML;
    }
    public String obtenerNombreArchivoFXML(){
        return this.archivoFXML;
    }
    protected void cambiarEscenarioAVentanaDiaria(LocalDateTime fecha){
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaDiaria(fecha);
    }
    protected void cambiarEscenarioAVentanaSemanal(LocalDateTime fecha){
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaSemanal(fecha);
    }
    protected void cambiarEscenarioAVentanaMensual(LocalDateTime fecha){
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaMensual(fecha);
    }
    protected void cambiarEscenarioAVentanaCrearEvento(){
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaAgregarEvento();
    }
    protected void cambiarEscenarioAVentanaCrearTarea(){
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaAgregarTarea();
    }
    protected void cambiarEscenarioAVentanaModificarActividad(Actividad actividad){
        ventana.cerrarEscenarioActual();
        actividad.aceptarVisitante(new VisitanteActividad() {
            @Override
            public void visitarEvento(Evento evento) {
                ventana.mostrarVentanaModificarEvento(evento);
            }
            @Override
            public void visitarTarea(Tarea tarea) {
                 ventana.mostrarVentanaModificarTarea(tarea);
            }
            @Override
            public void visitarEventoRepetido(EventoRepetido eventoRepetido) {
                 ventana.mostrarVentanaModificarEvento(eventoRepetido);
            }
        });
    }
}
