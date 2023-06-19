package controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modelo.Actividad;
import vista.Ventana;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VistaMensualControlador extends BaseControlador implements Initializable {
    @FXML
    private Button botonAgregarEvento;
    @FXML
    private Button botonAgregarTarea;
    @FXML
    private Button botonAnteriorSeleccion;
    @FXML
    private Button botonHoySeleccion;
    @FXML
    private Button botonPosteriorSeleccion;
    @FXML
    private Label labelMesAMostrar;
    @FXML
    private Label labelTipoDeVista;
    @FXML
    private GridPane gridpaneMensual;
    @FXML
    private ComboBox<String> seleccionTipoDeVistaComboBox;
    private LocalDateTime fechaMensual;
    private int posicionPrimerDiaDelMes;
    public VistaMensualControlador(PrincipalControlador principalControlador, Ventana ventana, String archivoFXML, LocalDateTime fecha) {
        super(principalControlador, ventana, archivoFXML);
        this.fechaMensual = fecha;
    }
    @FXML
    void botonVerMesPrevioAccion() {
        this.cambiarEscenarioAVentanaMensual(this.fechaMensual.minusMonths(1));
    }
    @FXML
    void botonVerMesActualAccion() {
        this.cambiarEscenarioAVentanaMensual(LocalDateTime.now());
    }

    @FXML
    void botonVerMesPosteriorAccion() {
        this.cambiarEscenarioAVentanaMensual(this.fechaMensual.plusMonths(1));
    }
    @FXML
    void crearEventoAccion() {
        this.cambiarEscenarioAVentanaCrearEvento();
    }

    @FXML
    void crearTareaAccion() {
        this.cambiarEscenarioAVentanaCrearTarea();
    }

    @FXML
    void seleccionTipoDeVistaAction() {
        String seleccion = this.seleccionTipoDeVistaComboBox.getValue();
        switch (seleccion) {
            case "Diaria" -> this.cambiarEscenarioAVentanaDiaria(this.fechaMensual);
            case "Semanal" -> this.cambiarEscenarioAVentanaSemanal(this.fechaMensual);
            default -> throw new IllegalStateException("Valor inesperado al elegir otra vista: " + seleccion);
        }
    }
    private void inicializarCalendarioMensual(){
        this.seleccionTipoDeVistaComboBox.getItems().add("Diaria");
        this.seleccionTipoDeVistaComboBox.getItems().add("Semanal");
        String mes = this.fechaMensual.getMonth().name();
        String anio = String.valueOf(this.fechaMensual.getYear());
        this.labelMesAMostrar.setText(mes + " / " + anio);

        LocalDateTime primerDiaDelMes = this.fechaMensual.withDayOfMonth(1);
        DayOfWeek dowPrimerDayDelMes = primerDiaDelMes.getDayOfWeek();
        this.posicionPrimerDiaDelMes = dowPrimerDayDelMes.getValue() - 1;

        ventana.agregarVBoxAGridpane(this.gridpaneMensual, 0, 6, 0, 7);
        this.agregarLabelDiasACalendario();

        ArrayList<Actividad> listaActividades = this.principalControlador.obtenerActividadesDelMes(this.fechaMensual);
        for(var actividad: listaActividades){
            this.agregarActividadAHorario(actividad);
        }
    }
    private void agregarActividadAHorario(Actividad actividad){
        LocalDateTime fecha = actividad.obtenerFecha();
        int diaDelMes = fecha.getDayOfMonth();
        int filaEnCalendario = (this.posicionPrimerDiaDelMes + diaDelMes-1 ) / 7;;
        int columnaEnCalendario = (this.posicionPrimerDiaDelMes + diaDelMes-1 ) % 7;

        CuadroInformativoActividadControlador cuadroInformativo = new CuadroInformativoActividadControlador(actividad);
        VBox vbox = (VBox) this.ventana.obtenerElementoDeCeldaEnGridpane(this.gridpaneMensual, filaEnCalendario, columnaEnCalendario);
        VBox cuadro = cuadroInformativo.obtenerCuadroInformativoVista();
        cuadro.setOnMouseClicked(e->this.cambiarEscenarioAVentanaModificarActividad(actividad));
        vbox.getChildren().add(cuadro);
    }
    private void agregarLabelDiasACalendario(){
        LocalDateTime ultimoDiaDelMes = this.fechaMensual.with(TemporalAdjusters.lastDayOfMonth());
        int cantidadDiasDelMes = ultimoDiaDelMes.getDayOfMonth();
        for(int i=0; i<cantidadDiasDelMes + 1; i++){
            int filaCalendarioMensual = (this.posicionPrimerDiaDelMes + i) / 7;
            int columnaCalendarioMensual = (this.posicionPrimerDiaDelMes + i) % 7;
            VBox vbox = (VBox) this.ventana.obtenerElementoDeCeldaEnGridpane(this.gridpaneMensual, filaCalendarioMensual, columnaCalendarioMensual);
            Label labelDiaDelMes = new Label(Integer.toString(i+1));
            labelDiaDelMes.setStyle("-fx-padding: 5px");
            labelDiaDelMes.getStyleClass().add("labelMesAMostrar");
            vbox.getChildren().add(labelDiaDelMes);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.inicializarCalendarioMensual();
    }
}
