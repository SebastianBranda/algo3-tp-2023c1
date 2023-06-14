package controlador;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    protected void agregarVBoxAGridpane(GridPane pane, int filas, int columnas){
        for(int i=0; i<columnas;i++){
            for(int j=0; j<filas;j++){
                VBox vBox = new VBox();
                pane.add(vBox, i, j);
            }
        }
    }
    protected Node obtenerElementoDeCeldaEnGridpane(GridPane pane, int fila, int columna){
        Node nodoCelda = null;
        ObservableList<Node> children = pane.getChildren();
        for(Node nodo: children){
            var row =GridPane.getRowIndex(nodo);
            var col = GridPane.getColumnIndex(nodo);
            if(row != null && col != null && row == fila && col == columna){
                nodoCelda = nodo;
                break;
            }
        }
        return nodoCelda;
    }
}
