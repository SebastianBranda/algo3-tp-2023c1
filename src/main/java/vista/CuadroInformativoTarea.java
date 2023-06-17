package vista;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.Tarea;

public class CuadroInformativoTarea {
    public static VBox obtenerVista(Tarea tarea){
        VBox cuadroInformativo = new VBox();

        Label labelTipoActividad = new Label();
        labelTipoActividad.setText("Tarea");

        Label labelTitulo = new Label();
        labelTitulo.setText("Titulo: " + tarea.obtenerTitulo());

        Label LabelDescripcion = new Label();
        LabelDescripcion.setText("Desc: " + tarea.obtenerDescripcion());

        cuadroInformativo.getChildren().add(labelTipoActividad);
        cuadroInformativo.getChildren().add(labelTitulo);
        cuadroInformativo.getChildren().add(LabelDescripcion);
        return cuadroInformativo;
    }
}
