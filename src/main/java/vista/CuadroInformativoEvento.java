package vista;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.Evento;
import modelo.EventoRepetido;

public class CuadroInformativoEvento {
    public static VBox obtenerVista(EventoRepetido evento){
        return generarVista(evento);
    }
    private static VBox generarVista(EventoRepetido evento){
        VBox cuadroInformativo = new VBox();

        Label labelTitulo = new Label();
        labelTitulo.setText(evento.obtenerTitulo());
        labelTitulo.getStyleClass().add("label-titulo");

        Label labelDescripcion = new Label();
        labelDescripcion.setText(evento.obtenerDescripcion());
        labelDescripcion.getStyleClass().add("label-descripcion");

        cuadroInformativo.getChildren().add(labelTitulo);
        cuadroInformativo.getChildren().add(labelDescripcion);

        if(evento.obtenerEsActividadDelDia()){
            Label labelActividadDelDia = new Label();
            labelActividadDelDia.setText("Evento del dia!");
            labelActividadDelDia.getStyleClass().add("label-actividad-del-dia");

            cuadroInformativo.getChildren().add(labelActividadDelDia);
            cuadroInformativo.getStyleClass().add("cuadro-evento-del-dia");
        }else{
            Label labelfechaInicio = new Label();
            labelfechaInicio.setText("Inicio: " + evento.obtenerFecha().toString());
            labelfechaInicio.getStyleClass().add("label-fecha-inicio");

            Label labelfechaFin = new Label();
            labelfechaFin.setText("Fin: " + evento.obtenerFecha().toString());
            labelfechaFin.getStyleClass().add("label-fecha-fin");

            cuadroInformativo.getChildren().add(labelfechaInicio);
            cuadroInformativo.getChildren().add(labelfechaFin);
            cuadroInformativo.getStyleClass().add("cuadro-evento");
        }
        return cuadroInformativo;
    }

}
