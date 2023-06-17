package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
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
    private Button buttonAgregarNuevaActividad;
    @FXML
    private CheckBox checkboxEsActividadDelDia;
    @FXML
    private ComboBox<String> comboBoxTipoAlarma;
    @FXML
    private DatePicker datePickerInicio;
    @FXML
    private HBox hBoxBotones;
    @FXML
    private Label labelTituloVentana;
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
    private VBox vBoxListadoAlarmas;
    private ArrayList<CuadroInformativoAlarmaControlador> listaAlarmas;
    private Tarea tarea;
    private final Boolean esModificable;
    private Button buttonModificarTarea;
    private Button buttonEliminarTarea;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaVencimiento;
    private Boolean esActividadDelDia;

    public VistaAgregarTareaControlador(PrincipalControlador principalControlador, Ventana ventana, String archivoFXML) {
        super(principalControlador, ventana, archivoFXML);
        listaAlarmas = new ArrayList<>();
        this.esModificable = false;
    }
    public VistaAgregarTareaControlador(PrincipalControlador principalControlador, Ventana ventana, String archivoFXML, Tarea tarea) {
        super(principalControlador, ventana, archivoFXML);
        listaAlarmas = new ArrayList<>();
        this.esModificable = true;
        this.tarea = tarea;
    }
    @FXML
    void agregarNuevaActividadAccion() {
        try {
            this.obtenerInformacionDelFormulario();
            Tarea nuevaTarea = new Tarea(titulo, descripcion, fechaVencimiento, esActividadDelDia, false);

            this.agregarAlarmasAActividad(nuevaTarea);
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
    private void rellenarInformacionDeLaTarea(){
        this.labelTituloVentana.setText("Modifica la tarea");
        this.textFieldTitulo.setText(this.tarea.obtenerTitulo());
        this.textFieldDescripcion.setText(this.tarea.obtenerDescripcion());
        this.datePickerInicio.setValue(this.tarea.obtenerFecha().toLocalDate());
        this.buttonAgregarNuevaActividad.setVisible(false);

        this.buttonModificarTarea = new Button("Modificar Tarea");
        this.buttonModificarTarea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modificarTareaAccion();
            }
        });
        this.buttonEliminarTarea = new Button("Eliminar Evento");
        this.buttonEliminarTarea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                eliminarTareaAccion();
            }
        });
        this.hBoxBotones.getChildren().add(0, buttonEliminarTarea);
        this.hBoxBotones.getChildren().add(0, buttonModificarTarea);
    }
    private void modificarTareaAccion(){
        try {
            this.obtenerInformacionDelFormulario();
            Tarea nuevaTarea = new Tarea(titulo, descripcion, fechaVencimiento, esActividadDelDia, false);
            this.agregarAlarmasAActividad(nuevaTarea);
            this.principalControlador.modificarTarea(this.tarea, nuevaTarea);
        }catch(Exception e){
            // TODO: tratar de agregar interaccion con el usuario (si es que el tiempo alcanza)
            System.err.println(e);
            System.err.println("El ingreso de datos ha sido invalido");
        }finally {
            ventana.cerrarEscenarioActual();
            ventana.mostrarVentanaDiaria(LocalDateTime.now());
        }
    }
    private void eliminarTareaAccion(){
        this.principalControlador.eliminarTarea(this.tarea);
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaDiaria(LocalDateTime.now());
    }
    private void obtenerInformacionDelFormulario(){
        this.titulo = textFieldTitulo.getText();
        this.descripcion = textFieldDescripcion.getText();

        LocalDate fechaVencimientoPicker = datePickerInicio.getValue();
        int horaVencimiento = Integer.parseInt(textFieldHora.getText());
        int minutosVencimiento = Integer.parseInt(textFieldMinutos.getText());
        this.fechaVencimiento = fechaVencimientoPicker.atTime(horaVencimiento, minutosVencimiento);

        this.esActividadDelDia = checkboxEsActividadDelDia.isSelected();
    }
    private void agregarAlarmasAActividad(Actividad actividad){
        for(var alarmaInfo: listaAlarmas){
            Alarma alarma;
            switch (alarmaInfo.getTipoAlarma()){
                case EMAIL -> alarma = new AlarmaEmail(fechaVencimiento.minusMinutes(alarmaInfo.getMinutosAntes()));
                case SONIDO -> alarma = new AlarmaSonido(fechaVencimiento.minusMinutes(alarmaInfo.getMinutosAntes()));
                case VISUAL -> alarma = new AlarmaVisual(fechaVencimiento.minusMinutes(alarmaInfo.getMinutosAntes()));
                default -> alarma = new AlarmaEmail(fechaVencimiento);
            }
            actividad.agregarAlarma(alarma);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.comboBoxTipoAlarma.getItems().add("Email");
        this.comboBoxTipoAlarma.getItems().add("Sonido");
        this.comboBoxTipoAlarma.getItems().add("Visual");
        if(esModificable){
            this.rellenarInformacionDeLaTarea();
        }
    }
}
