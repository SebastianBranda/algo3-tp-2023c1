package controlador;

import javafx.animation.AnimationTimer;
import modelo.Alarma;
import vista.Ventana;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public abstract class BaseControlador {
    protected PrincipalControlador principalControlador;
    protected Ventana ventana;
    protected String archivoFXML;
    public BaseControlador(PrincipalControlador principalControlador, Ventana ventana, String archivoFXML) {
        this.principalControlador = principalControlador;
        this.ventana = ventana;
        this.archivoFXML = archivoFXML;
        this.inicializarNotificadorDeAlarmas();
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
    private void inicializarNotificadorDeAlarmas(){
        ArrayList<Alarma> alarmas =this.principalControlador.obtenerProximasAlarmas();
        if(alarmas.isEmpty()){
            return;
        }
        LocalDateTime horaProximaAlarma =  alarmas.get(0).getHorarioAlarma().truncatedTo(ChronoUnit.MINUTES);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(horaProximaAlarma.isEqual(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))){
                    this.stop();
                    new NotificadorDeAlarmas(alarmas).notificar();
                    inicializarNotificadorDeAlarmas();
                }
            }
        };
        animationTimer.start();
    }
}
