package controlador;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import modelo.Alarma;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class NotificadorDeAlarmas  {
    private ArrayList<Alarma> alarmas;
    private AnimationTimer animationTimer;
    private PrincipalControlador controladorObservador;
    private LocalDateTime horaDeAlarma;
    private Boolean hayAlarmas;
    private String mensaje;

    public NotificadorDeAlarmas(ArrayList<Alarma> alarmas, PrincipalControlador controlador) {
        this.alarmas = alarmas;
        this.controladorObservador = controlador;
        this.iniciar();
        this.setAlarmas(alarmas);
    }
    public void setAlarmas(ArrayList<Alarma> alarmas){
        this.alarmas = alarmas;
        if(this.alarmas.size()==0){
            this.hayAlarmas = false;
            return;
        }
        this.horaDeAlarma = alarmas.get(0).getHorarioAlarma();
        this.hayAlarmas = true;
        String nuevoMensaje = "";
        for(Alarma alarma: alarmas){
            nuevoMensaje = "Tipo: " + alarma.getTipoAlarma();
            nuevoMensaje += ". Horario: " + alarma.getHorarioAlarma().toString() + "\n";
        }
        this.mensaje = nuevoMensaje;
    }
    public void iniciar(){
        this.animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(hayAlarmas && horaDeAlarma.isEqual(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))){
                    notificar(mensaje);
                    notificarObservador();
                }
            }
        };
        animationTimer.start();
    }
    public void notificar(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, mensaje);
        alerta.show();
    }
    public void notificarObservador(){
        this.controladorObservador.reiniciarNotificador();
    }
}
