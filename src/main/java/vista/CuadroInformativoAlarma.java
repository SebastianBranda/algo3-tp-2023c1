package vista;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CuadroInformativoAlarma {
    public VBox obtenerVista(String tipo, String hora){
        VBox cuadroInformativo = new VBox();

        Label labelTipoAlarma = new Label();
        labelTipoAlarma.setText("Tipo de alarma: " + tipo);

        Label LabelHorarioAlarma = new Label();
        LabelHorarioAlarma.setText("Minutos antes: " + hora);

        cuadroInformativo.getChildren().add(labelTipoAlarma);
        cuadroInformativo.getChildren().add(LabelHorarioAlarma);
        return cuadroInformativo;
    }
}
