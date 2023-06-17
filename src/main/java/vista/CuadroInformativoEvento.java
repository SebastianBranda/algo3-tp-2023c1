package vista;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.Evento;
import modelo.EventoRepetido;

public class CuadroInformativoEvento {
    public static VBox obtenerVista(Evento evento){
        return generarVista(evento.obtenerTitulo(), evento.obtenerDescripcion());
    }
    public static VBox obtenerVista(EventoRepetido evento){
        return generarVista(evento.obtenerTitulo(), evento.obtenerDescripcion());
    }
    private static VBox generarVista(String titulo, String descripcion){
        VBox cuadroInformativo = new VBox();

        Label labelTitulo = new Label();
        labelTitulo.setText("Titulo: " + titulo);

        Label LabelDescripcion = new Label();
        LabelDescripcion.setText("Desc: " + descripcion);

        cuadroInformativo.getChildren().add(labelTitulo);
        cuadroInformativo.getChildren().add(LabelDescripcion);
        return cuadroInformativo;
    }

}
