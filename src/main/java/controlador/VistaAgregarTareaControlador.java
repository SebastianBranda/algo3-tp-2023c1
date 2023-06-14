package controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import modelo.*;
import vista.Ventana;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VistaAgregarTareaControlador extends BaseControlador implements Initializable {
    @FXML
    private CheckBox checkboxEsActividadDelDia;
    @FXML
    private ComboBox<String> comboBoxTipoAlarma;
    @FXML
    private DatePicker datePickerInicio;
    @FXML
    private TextField textFieldDescripcion;
    @FXML
    private TextField textFieldHora;
    @FXML
    private TextField textFieldMinutos;
    @FXML
    private TextField textFieldTiempoAntesAlarma;
    @FXML
    private TextField textFieldTitulo;
    @FXML
    private VBox vBoxActividadDinamico;
    @FXML
    private VBox vBoxListadoAlarmas;
    private ArrayList<CuadroInformativoAlarmaControlador> listaAlarmas;

    public VistaAgregarTareaControlador(PrincipalControlador principalControlador, Ventana ventana, String archivoFXML) {
        super(principalControlador, ventana, archivoFXML);
        listaAlarmas = new ArrayList<>();
    }
    @FXML
    void agregarNuevaActividadAccion() {
        try {
            String titulo = textFieldTitulo.getText();
            String descripcion = textFieldDescripcion.getText();

            LocalDate fechaVencimientoPicker = datePickerInicio.getValue();
            int horaVencimiento = Integer.parseInt(textFieldHora.getText());
            int minutosVencimiento = Integer.parseInt(textFieldMinutos.getText());
            LocalDateTime fechaVencimiento = fechaVencimientoPicker.atTime(horaVencimiento, minutosVencimiento);

            Boolean esActividadDelDia = checkboxEsActividadDelDia.isSelected();

            Tarea nuevaTarea = new Tarea(titulo, descripcion, fechaVencimiento, esActividadDelDia, false);

            for(var alarmaInfo: listaAlarmas){
                Alarma alarma;
                switch (alarmaInfo.getTipoAlarma()){
                    case EMAIL -> alarma = new AlarmaEmail(fechaVencimiento.minusMinutes(alarmaInfo.getMinutosAntes()));
                    case SONIDO -> alarma = new AlarmaSonido(fechaVencimiento.minusMinutes(alarmaInfo.getMinutosAntes()));
                    case VISUAL -> alarma = new AlarmaVisual(fechaVencimiento.minusMinutes(alarmaInfo.getMinutosAntes()));
                    default -> alarma = new AlarmaEmail(fechaVencimiento);
                }
                nuevaTarea.agregarAlarma(alarma);
            }
            this.principalControlador.agregarTarea(nuevaTarea);
        }catch(Exception e){
            System.err.println(e);
            System.err.println("El ingreso de datos ha sido invalido");
        }finally {
            ventana.cerrarEscenarioActual();
            ventana.mostrarVentanaDiaria(LocalDateTime.now());
        }
    }
    @FXML
    void buttonAgregarNuevaAlarmaAction() {
        String seleccionTipoDeAlarma = this.comboBoxTipoAlarma.getValue();
        if(seleccionTipoDeAlarma == null) return;
        TipoAlarma tipoAlarma;
        switch (seleccionTipoDeAlarma){
            case "Email":
                tipoAlarma = TipoAlarma.EMAIL;
                break;
            case "Sonido":
                tipoAlarma = TipoAlarma.SONIDO;
                break;
            case "Visual":
                tipoAlarma = TipoAlarma.VISUAL;
                break;
            default:
                tipoAlarma = TipoAlarma.EMAIL;
        }
        int minutosAntes = Integer.parseInt(this.textFieldTiempoAntesAlarma.getText());
        CuadroInformativoAlarmaControlador cuadroAlarma = new CuadroInformativoAlarmaControlador(minutosAntes, tipoAlarma);
        VBox vBoxAlarma = cuadroAlarma.obtenerCuadroInformativoAlarma();
        vBoxListadoAlarmas.getChildren().add(vBoxAlarma);
        listaAlarmas.add(cuadroAlarma);
    }
    @FXML
    void cancelarAction() {
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaDiaria(LocalDateTime.now());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.comboBoxTipoAlarma.getItems().add("Email");
        this.comboBoxTipoAlarma.getItems().add("Sonido");
        this.comboBoxTipoAlarma.getItems().add("Visual");
    }
}
