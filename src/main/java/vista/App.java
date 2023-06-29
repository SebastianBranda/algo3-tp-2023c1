package vista;

import controlador.PrincipalControlador;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class App extends Application {
    private PrincipalControlador controlador;

    @Override
    public void start(Stage stage) throws IOException {
        this.controlador = new PrincipalControlador("archivoCalendario");
        Ventana ventana = new Ventana(this.controlador);
        ventana.mostrarVentanaDiaria(LocalDateTime.now());
    }

    @Override
    public void stop() throws Exception{
        this.controlador.guardarEstadoCalendario();
    }

    public static void main(String[] args) {
        launch();
    }
}
