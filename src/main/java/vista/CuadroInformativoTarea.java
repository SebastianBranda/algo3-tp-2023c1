package vista;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.Tarea;

public class CuadroInformativoTarea {
    public static VBox obtenerVista(Tarea tarea){
        VBox cuadroInformativo = new VBox();

        Label labelTitulo = new Label();
        labelTitulo.setText(tarea.obtenerTitulo());
        labelTitulo.getStyleClass().add("label-titulo");

        Label labelDescripcion = new Label();
        labelDescripcion.setText(tarea.obtenerDescripcion());
        labelDescripcion.getStyleClass().add("label-descripcion");

        cuadroInformativo.getChildren().add(labelTitulo);
        cuadroInformativo.getChildren().add(labelDescripcion);

        if(tarea.obtenerEsActividadDelDia()){
            Label labelActividadDelDia = new Label();
            labelActividadDelDia.setText("Tarea del dia!");
            labelActividadDelDia.getStyleClass().add("label-actividad-del-dia");

            cuadroInformativo.getChildren().add(labelActividadDelDia);
            cuadroInformativo.getStyleClass().add("cuadro-tarea-del-dia");
        }else{
            Label labelfechaInicio = new Label();
            labelfechaInicio.setText("Vence: " + tarea.obtenerFecha().toString());
            labelfechaInicio.getStyleClass().add("label-fecha-inicio");

            cuadroInformativo.getChildren().add(labelfechaInicio);
            cuadroInformativo.getStyleClass().add("cuadro-tarea");
        }
        return cuadroInformativo;
    }
}
