package vista;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CuadroInformativoActividad {
    public VBox obtenerVistaEvento(String titulo, String descripcion){
        VBox cuadroInformativo = new VBox();

        Label labelTitulo = new Label();
        labelTitulo.setText("Titulo: " + titulo);

        Label LabelDescripcion = new Label();
        LabelDescripcion.setText("Desc: " + descripcion);

        cuadroInformativo.getChildren().add(labelTitulo);
        cuadroInformativo.getChildren().add(LabelDescripcion);
        return cuadroInformativo;
    }
    public VBox obtenerVistaTarea(String titulo, String descripcion){
        VBox cuadroInformativo = new VBox();

        // TODO

        return cuadroInformativo;
    }
}
