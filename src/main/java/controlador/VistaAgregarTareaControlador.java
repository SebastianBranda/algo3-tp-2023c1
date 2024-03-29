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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class VistaAgregarTareaControlador extends BaseControlador implements Initializable {
    @FXML
    private Button buttonAgregarNuevaActividad;
    @FXML
    private CheckBox checkboxEsActividadDelDia;
    @FXML
    private CheckBox checkboxEstaCompletada;
    @FXML
    private ComboBox<String> comboBoxTipoAlarma;
    @FXML
    private DatePicker datePickerInicio;
    @FXML
    private HBox hBoxBotones;
    @FXML
    private Label labelTituloVentana;
    @FXML
    private Label labelFaltaInformacion;
    @FXML
    private Label labelHora;
    @FXML
    private Label labelMinutos;
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
    private Boolean estaCompletada;

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
            Tarea nuevaTarea = new Tarea(titulo, descripcion, fechaVencimiento, esActividadDelDia, estaCompletada);

            this.agregarAlarmasAActividad(nuevaTarea);
            this.principalControlador.agregarTarea(nuevaTarea);
            ventana.cerrarEscenarioActual();
            ventana.mostrarVentanaDiaria(LocalDateTime.now());
        }catch(Exception e){
            this.labelFaltaInformacion.setVisible(true);
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
        this.agregarAlarmaAListaDeAlarmas(minutosAntes, tipoAlarma);
    }
    @FXML
    void cancelarAction() {
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaDiaria(LocalDateTime.now());
    }
    @FXML
    void checkboxEsActividadDelDiaAction(ActionEvent event){
        if(checkboxEsActividadDelDia.isSelected()){
            setVisibilidadHorasInicioYFin(false);
        }else{
            setVisibilidadHorasInicioYFin(true);
        }
    }
    private void setVisibilidadHorasInicioYFin(Boolean b){
        this.labelHora.setVisible(b);
        this.textFieldHora.setVisible(b);
        this.labelMinutos.setVisible(b);
        this.textFieldMinutos.setVisible(b);
    }
    private void agregarAlarmaAListaDeAlarmas(int minutosAntes, TipoAlarma tipoAlarma){
        CuadroInformativoAlarmaControlador cuadroAlarma = new CuadroInformativoAlarmaControlador(minutosAntes, tipoAlarma);
        VBox vBoxAlarma = cuadroAlarma.obtenerCuadroInformativoAlarma();
        vBoxListadoAlarmas.getChildren().add(vBoxAlarma);
        listaAlarmas.add(cuadroAlarma);
    }
    private void rellenarInformacionDeLaTarea(){
        this.labelTituloVentana.setText("Modifica la tarea");
        this.textFieldTitulo.setText(this.tarea.obtenerTitulo());
        this.textFieldDescripcion.setText(this.tarea.obtenerDescripcion());
        this.datePickerInicio.setValue(this.tarea.obtenerFecha().toLocalDate());
        this.textFieldHora.setText(String.valueOf(this.tarea.obtenerFecha().getHour()));
        this.textFieldMinutos.setText(String.valueOf(this.tarea.obtenerFecha().getMinute()));
        this.checkboxEstaCompletada.setSelected(tarea.obtenerEstaCompletada());
        this.checkboxEsActividadDelDia.setSelected(tarea.obtenerEsActividadDelDia());
        ArrayList<Alarma> listaDeAlarmasDelEvento = this.tarea.obtenerAlarmas();
        for(Alarma alarma: listaDeAlarmasDelEvento){
            int minutosAntes = (int) alarma.getHorarioAlarma().until(this.tarea.obtenerFecha(), ChronoUnit.MINUTES);
            TipoAlarma tipoAlarma = alarma.getTipoAlarma();
            agregarAlarmaAListaDeAlarmas(minutosAntes, tipoAlarma);
        }

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
            Tarea nuevaTarea = new Tarea(titulo, descripcion, fechaVencimiento, esActividadDelDia, estaCompletada);
            this.agregarAlarmasAActividad(nuevaTarea);
            this.principalControlador.modificarTarea(this.tarea, nuevaTarea);
            ventana.cerrarEscenarioActual();
            ventana.mostrarVentanaDiaria(LocalDateTime.now());
        }catch(Exception e){
            this.labelFaltaInformacion.setVisible(true);
        }
    }
    private void eliminarTareaAccion(){
        this.principalControlador.eliminarTarea(this.tarea);
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaDiaria(LocalDateTime.now());
    }
    private void obtenerInformacionDelFormulario() throws Exception {
        try {
            this.esActividadDelDia = checkboxEsActividadDelDia.isSelected();
            this.estaCompletada = checkboxEstaCompletada.isSelected();

            this.titulo = textFieldTitulo.getText();
            this.descripcion = textFieldDescripcion.getText();

            LocalDate fechaVencimientoPicker = datePickerInicio.getValue();
            int horaVencimiento = Integer.parseInt(textFieldHora.getText());
            int minutosVencimiento = Integer.parseInt(textFieldMinutos.getText());
            this.fechaVencimiento = fechaVencimientoPicker.atTime(horaVencimiento, minutosVencimiento);
        }catch (Exception e){
            if(this.esActividadDelDia){
                LocalDate fechaVencimientoPicker = datePickerInicio.getValue();
                if(fechaVencimientoPicker!=null){
                    this.fechaVencimiento = fechaVencimientoPicker.atTime(0,1);
                }else{
                    this.fechaVencimiento = LocalDateTime.now().withHour(0).withMinute(1);
                }
            }else{
                throw new Exception("Faltan datos");
            }
        }
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

        this.labelFaltaInformacion.setVisible(false);

        if(esModificable){
            this.rellenarInformacionDeLaTarea();
        }else{
            this.datePickerInicio.setValue(LocalDateTime.now().toLocalDate());
        }
    }
}
