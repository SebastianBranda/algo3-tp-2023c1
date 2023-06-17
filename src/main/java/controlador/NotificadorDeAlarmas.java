package controlador;

import javafx.scene.control.Alert;
import modelo.Alarma;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class NotificadorDeAlarmas  {
    private ArrayList<Alarma> alarmas;
    public String mensaje;

    public NotificadorDeAlarmas(ArrayList<Alarma> alarmas) {
        this.alarmas = alarmas;
    }
    public void notificar(){
        this.mensaje = alarmas.stream().map(alarma -> alarma.getHorarioAlarma().toString()).collect(Collectors.joining("\n"));
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, this.mensaje);
        alerta.show();
    }
}
