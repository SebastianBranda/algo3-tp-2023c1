package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Actividad;
import vista.Ventana;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VistaDiariaControlador extends BaseControlador implements Initializable {
    
    @FXML
    private VBox vboxHoras;
    private LocalDateTime fechaVistaDiaria;
    @FXML
    private Label labelDiaAMostrarVentanaDiaria;
    @FXML
    private ComboBox<String> seleccionTipoDeVistaComboBox;

    public VistaDiariaControlador(PrincipalControlador principalControlador, Ventana ventana, String archivoFXML, LocalDateTime fecha){
        super(principalControlador, ventana, archivoFXML);
        this.fechaVistaDiaria = fecha;
    }
    @FXML
    void botonVerDiaPrevioAccion(ActionEvent event) {
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaDiaria(this.fechaVistaDiaria.minusDays(1));
    }
    @FXML
    void botonVerDiaActualAccion(ActionEvent event) {
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaDiaria(LocalDateTime.now());
    }
    @FXML
    void botonVerDiaPosteriorAccion(ActionEvent event) {
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaDiaria(this.fechaVistaDiaria.plusDays(1));
    }
    @FXML
    public void crearEventoAccion() {
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaAgregarEvento();
    }
    public void crearTareaAccion() {
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaAgregarTarea();
    }
    @FXML
    void seleccionTipoDeVistaAction(ActionEvent event) {
        /*
        String eleccionVista = this.seleccionTipoDeVistaComboBox.getValue();
        ventana.cerrarEscenarioActual();
        switch (eleccionVista) {
            case "Semanal" -> ventana.mostrarVentanaSemanal(fechaVistaDiaria);
            case "Anual" -> ventana.mostrarVentanaAnual(fechaVistaDiaria);
            default -> throw new IllegalStateException("Valor inesperado en la eleccion de tipo de Vista: " + eleccionVista);
        }
         */
    }
    public void agregarActividadAHorario(Actividad actividad){
        int numeroHora = actividad.obtenerFecha().getHour();
        var listadoHoras = vboxHoras.getChildren();
        HBox hBoxHoraEspecifica = (HBox) listadoHoras.get(numeroHora);

        CuadroInformativoActividadControlador cuadroInformativo = new CuadroInformativoActividadControlador(actividad);
        VBox cuadro = cuadroInformativo.obtenerCuadroInformativoVista();

        hBoxHoraEspecifica.getChildren().add(cuadro);
    }
    public void agregarActividadesAVistaDiaria(){
        ArrayList<Actividad> listaActividades = this.principalControlador.obtenerActividadesDelDia(this.fechaVistaDiaria);
        for(var actividad: listaActividades){
            this.agregarActividadAHorario(actividad);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.agregarActividadesAVistaDiaria();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.labelDiaAMostrarVentanaDiaria.setText(this.fechaVistaDiaria.format(formatter));

        this.seleccionTipoDeVistaComboBox.getItems().add("Semanal");
        this.seleccionTipoDeVistaComboBox.getItems().add("Anual");
    }
}