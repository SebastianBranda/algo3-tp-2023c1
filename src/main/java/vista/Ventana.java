package vista;

import controlador.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class Ventana {
    private PrincipalControlador controladorPrincipal;
    private Stage escenarioActual;
    public Ventana(PrincipalControlador controlador){
        this.controladorPrincipal = controlador;
    }
    private void presentarEscenario(BaseControlador controlador) throws IOException {
        var fxmlRes = getClass().getResource(controlador.obtenerNombreArchivoFXML());
        FXMLLoader loader = new FXMLLoader(fxmlRes);
        loader.setController(controlador);
        Parent view = loader.load();
        var escena = new Scene(view);
        this.escenarioActual = new Stage();
        this.escenarioActual.setScene(escena);
        this.escenarioActual.show();
    }
    public void cerrarEscenario(Stage escenario){
        escenario.close();
    }
    public void cerrarEscenarioActual(){
        this.escenarioActual.close();
        this.escenarioActual = null;
    }
    public void mostrarVentanaDiaria(LocalDateTime fecha) {
        BaseControlador controlador = new VistaDiariaControlador(this.controladorPrincipal, this, "/ventanaDiaria.fxml", fecha);
        try {
            presentarEscenario(controlador);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void mostrarVentanaSemanal(LocalDateTime fecha) {
        BaseControlador controlador = new VistaSemanalControlador(this.controladorPrincipal, this, "/ventanaSemanal.fxml", fecha);
        try {
            presentarEscenario(controlador);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void mostrarVentanaMensual(LocalDateTime fecha) {
        BaseControlador controlador = new VistaMensualControlador(this.controladorPrincipal, this, "/ventanaMensual.fxml", fecha);
        try {
            presentarEscenario(controlador);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void mostrarVentanaAgregarEvento(){
        BaseControlador controlador = new VistaAgregarEventoControlador(this.controladorPrincipal, this, "/ventanaAgregarEvento.fxml");
        try {
            presentarEscenario(controlador);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void mostrarVentanaAgregarTarea(){
        BaseControlador controlador = new VistaAgregarTareaControlador(this.controladorPrincipal, this, "/ventanaAgregarTarea.fxml");
        try {
            presentarEscenario(controlador);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void agregarVBoxAGridpane(GridPane pane, int filas, int columnas){
        for(int i=0; i<columnas;i++){
            for(int j=0; j<filas;j++){
                VBox vBox = new VBox();
                pane.add(vBox, i, j);
            }
        }
    }
    public Node obtenerElementoDeCeldaEnGridpane(GridPane pane, int fila, int columna){
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
