package vista;

import controlador.PrincipalControlador;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private PrincipalControlador controlador;

    @Override
    public void start(Stage stage) throws IOException {
        this.controlador = new PrincipalControlador("archivoCalendario");
        Ventana ventana = new Ventana(this.controlador);
        ventana.mostrarVentanaDiaria();
    }

    @Override
    public void stop() throws Exception{
        System.out.println("Cerrando escenario");
        this.controlador.guardarEstadoCalendario();
    }

    public static void main(String[] args) {
        launch();
    }
}
