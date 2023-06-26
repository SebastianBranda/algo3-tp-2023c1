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

public class VistaAgregarEventoControlador extends BaseControlador implements Initializable {
    @FXML
    private Button buttonAgregarNuevaActividad;
    @FXML
    private CheckBox checkboxEsActividadDelDia;
    @FXML
    private ComboBox<String> comboBoxFrecuencia;
    @FXML
    private ComboBox<String> comboBoxRepeticion;
    @FXML
    private ComboBox<String> comboBoxTipoAlarma;
    @FXML
    private DatePicker datePickerFin;
    @FXML
    private DatePicker datePickerInicio;
    @FXML
    private VBox vBoxFrecuencia;
    @FXML
    private Label labelTituloVentana;
    @FXML
    private Label labelFaltaInformacion;
    @FXML
    private TextField textFieldDescripcion;
    @FXML
    private TextField textFieldHoraFin;
    @FXML
    private TextField textFieldHoraInicio;
    @FXML
    private TextField textFieldMinutos;
    @FXML
    private TextField textFieldMinutosFin;
    @FXML
    private TextField textFieldTitulo;
    @FXML
    private TextField textFieldTiempoAntesAlarma;
    @FXML
    private VBox vBoxListadoAlarmas;
    @FXML
    private HBox hBoxBotones;
    @FXML
    private HBox hBoxFrecuenciasSegunSeleccion;
    @FXML
    private HBox hBoxRepeticionesSegunSeleccion;
    private TipoFrecuencia tipoFrecuenciaElegida;
    private TipoRepeticion tipoRepeticionElegida;
    private TextField textFieldIntervaloDias;
    private ArrayList<CheckBox> checkboxesDias;
    private DatePicker datePickerEleccionFechaLimite;
    private TextField textFieldEleccionCantidadRepeticiones;
    private ArrayList<CuadroInformativoAlarmaControlador> listaAlarmas;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Boolean esActividadDelDia;
    private Evento evento;
    private EventoRepetido eventoRepetido;
    private final Boolean esModificable;
    private Button buttonModificarEvento;
    private Button buttonEliminarEvento;

    public VistaAgregarEventoControlador(PrincipalControlador controlador, Ventana ventana, String archivoFXML){
        super(controlador, ventana, archivoFXML);
        listaAlarmas = new ArrayList<>();
        this.esModificable = false;
    }
    public VistaAgregarEventoControlador(PrincipalControlador controlador, Ventana ventana, String archivoFXML, Evento evento){
        super(controlador, ventana, archivoFXML);
        listaAlarmas = new ArrayList<>();
        this.evento = evento;
        this.esModificable = true;
    }
    public VistaAgregarEventoControlador(PrincipalControlador controlador, Ventana ventana, String archivoFXML, EventoRepetido evento){
        super(controlador, ventana, archivoFXML);
        listaAlarmas = new ArrayList<>();
        this.eventoRepetido = evento;
        this.esModificable = true;
    }
    @FXML
    void seleccionFrecuenciaAction(ActionEvent event) {
        hBoxFrecuenciasSegunSeleccion.getChildren().clear();
        String seleccionFrecuencia = this.comboBoxFrecuencia.getValue();
        switch (seleccionFrecuencia){
            case "Diaria":
                this.tipoFrecuenciaElegida = TipoFrecuencia.DIARIA;
                Label labelIntervaloDias = new Label();
                labelIntervaloDias.setText("Intervalo de dias");
                this.textFieldIntervaloDias = new TextField();
                hBoxFrecuenciasSegunSeleccion.getChildren().add(labelIntervaloDias);
                hBoxFrecuenciasSegunSeleccion.getChildren().add(this.textFieldIntervaloDias);
                break;
            case "Semanal":
                this.tipoFrecuenciaElegida = TipoFrecuencia.SEMANAL;
                VBox vBox = new VBox();
                hBoxFrecuenciasSegunSeleccion.getChildren().add(vBox);
                this.checkboxesDias = new ArrayList<>();
                String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
                for(var dia: dias){
                    CheckBox checkbox = new CheckBox(dia);
                    checkboxesDias.add(checkbox);
                    vBox.getChildren().add(checkbox);
                }
                break;
            case "Mensual":
                this.tipoFrecuenciaElegida = TipoFrecuencia.MENSUAL;
                break;
            case "Anual":
                this.tipoFrecuenciaElegida = TipoFrecuencia.ANUAL;
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + seleccionFrecuencia);
        }
    }
    @FXML
    void seleccionRepeticionAction(ActionEvent event) {
        hBoxRepeticionesSegunSeleccion.getChildren().clear();
        String seleccionDeRepeticion = this.comboBoxRepeticion.getValue();
        switch (seleccionDeRepeticion){
            case "Infinita":
                this.tipoRepeticionElegida = TipoRepeticion.INFINITA;
                break;
            case "Fecha limite":
                this.tipoRepeticionElegida = TipoRepeticion.FECHA_DETERMINADA;
                Label labelFechaLimite = new Label();
                labelFechaLimite.setText("Hasta");
                this.datePickerEleccionFechaLimite = new DatePicker();
                hBoxRepeticionesSegunSeleccion.getChildren().add(this.datePickerEleccionFechaLimite);
                break;
            case "Cantidad repeticiones":
                this.tipoRepeticionElegida = TipoRepeticion.CANT_DETERMINADA;
                Label labelCantReps = new Label();
                labelCantReps.setText("Cantidad");
                this.textFieldEleccionCantidadRepeticiones = new TextField();
                hBoxRepeticionesSegunSeleccion.getChildren().add(labelCantReps);
                hBoxRepeticionesSegunSeleccion.getChildren().add(this.textFieldEleccionCantidadRepeticiones);
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + seleccionDeRepeticion);
        }
    }
    @FXML
    void agregarNuevaActividadAccion() {
        try {
            this.obtenerInformacionDelFormulario();
            Frecuencia frecuencia = this.determinarFrecuenciaElegida(fechaInicio);
            Evento nuevoEvento = new Evento(titulo, descripcion, fechaInicio, fechaFin, esActividadDelDia, frecuencia, tipoFrecuenciaElegida, true);
            this.agregarAlarmasAActividad(nuevoEvento);
            this.principalControlador.agregarEvento(nuevoEvento);
            ventana.cerrarEscenarioActual();
            ventana.mostrarVentanaDiaria(LocalDateTime.now());
        }catch(Exception e) {
            this.labelFaltaInformacion.setVisible(true);
        }
    }
    @FXML
    void buttonAgregarNuevaAlarmaAction(){
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
    private void agregarAlarmaAListaDeAlarmas(int minutosAntes, TipoAlarma tipoAlarma){
        CuadroInformativoAlarmaControlador cuadroAlarma = new CuadroInformativoAlarmaControlador(minutosAntes, tipoAlarma);
        VBox vBoxAlarma = cuadroAlarma.obtenerCuadroInformativoAlarma();
        vBoxListadoAlarmas.getChildren().add(vBoxAlarma);
        listaAlarmas.add(cuadroAlarma);
    }
    private Frecuencia determinarFrecuenciaElegida(LocalDateTime fechaInicio){
        BuilderEleccionFrecuencia builderFrecuencia = new BuilderEleccionFrecuencia();
        builderFrecuencia.setFechaInicio(fechaInicio);
        builderFrecuencia.setTipoRepeticion(this.tipoRepeticionElegida);
        builderFrecuencia.setDatePickerEleccionFechaLimite(datePickerEleccionFechaLimite);
        builderFrecuencia.setTextFieldEleccionCantidadRepeticiones(textFieldEleccionCantidadRepeticiones);

        Frecuencia frecuencia;
        switch (this.tipoFrecuenciaElegida){
            case DIARIA:
                builderFrecuencia.setTextFieldIntervaloDias(textFieldIntervaloDias);
                frecuencia = ControladorDeFrecuencias.determinarFrecuenciaDiaria(builderFrecuencia);
                break;
            case SEMANAL:
                builderFrecuencia.setCheckboxesDias(checkboxesDias);
                frecuencia = ControladorDeFrecuencias.determinarFrecuenciaSemanal(builderFrecuencia);
                break;
            case MENSUAL:
                frecuencia = ControladorDeFrecuencias.determinarFrecuenciaMensual(builderFrecuencia);
                break;
            case ANUAL:
                frecuencia = ControladorDeFrecuencias.determinarFrecuenciaAnual(builderFrecuencia);
                break;
            default:
                frecuencia = new FrecuenciaAnual(fechaInicio);
        }
        return frecuencia;
    }
    @FXML
    void cancelarAction(ActionEvent event) {
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaDiaria(LocalDateTime.now());
    }
    private void agregarAlarmasAActividad(Actividad actividad){
        for(var alarmaInfo: listaAlarmas){
            Alarma alarma;
            switch (alarmaInfo.getTipoAlarma()){
                case EMAIL -> alarma = new AlarmaEmail(fechaInicio.minusMinutes(alarmaInfo.getMinutosAntes()));
                case SONIDO -> alarma = new AlarmaSonido(fechaInicio.minusMinutes(alarmaInfo.getMinutosAntes()));
                case VISUAL -> alarma = new AlarmaVisual(fechaInicio.minusMinutes(alarmaInfo.getMinutosAntes()));
                default -> alarma = new AlarmaEmail(fechaInicio);
            }
            actividad.agregarAlarma(alarma);
        }
    }
    private void obtenerInformacionDelFormulario() throws Exception {
        try {
            this.esActividadDelDia = checkboxEsActividadDelDia.isSelected();
            this.titulo = textFieldTitulo.getText();
            this.descripcion = textFieldDescripcion.getText();

            LocalDate fechaInicioPicker = datePickerInicio.getValue();

            int horaInicio = Integer.parseInt(textFieldHoraInicio.getText());
            int minutosInicio = Integer.parseInt(textFieldMinutos.getText());
            this.fechaInicio = fechaInicioPicker.atTime(horaInicio, minutosInicio);

            LocalDate fechaFinPicker = datePickerFin.getValue();
            int horaFin = Integer.parseInt(textFieldHoraFin.getText());
            int minutosFin = Integer.parseInt(textFieldMinutosFin.getText());
            this.fechaFin = fechaFinPicker.atTime(horaFin, minutosFin);
        }catch(Exception e){
            if(this.esActividadDelDia){
                LocalDate fechaInicioPicker = datePickerInicio.getValue();
                if(fechaInicioPicker != null){
                    this.fechaInicio = fechaInicioPicker.atTime(0, 1);
                    this.fechaFin = fechaInicioPicker.atTime(23, 59);
                }else{
                    this.fechaInicio = LocalDateTime.now().withHour(0).withMinute(1);
                    this.fechaFin = LocalDateTime.now().withHour(23).withMinute(59);
                }
            }else{
                throw new Exception("Falta ingresar datos");
            }
        }
    }
    private void rellenarInformacionDelEvento(){
    }
    private void rellenarInformacionDelEventoRepetido(){
        this.labelTituloVentana.setText("Modifica el evento");
        this.textFieldTitulo.setText(this.eventoRepetido.obtenerTitulo());
        this.textFieldDescripcion.setText(this.eventoRepetido.obtenerDescripcion());
        this.datePickerInicio.setValue(this.eventoRepetido.obtenerFecha().toLocalDate());
        this.textFieldHoraInicio.setText(String.valueOf(this.eventoRepetido.obtenerFecha().getHour()));
        this.textFieldMinutos.setText(String.valueOf(this.eventoRepetido.obtenerFecha().getMinute()));
        this.datePickerFin.setValue(this.eventoRepetido.obtenerFechaFin().toLocalDate());
        this.textFieldHoraFin.setText(String.valueOf(this.eventoRepetido.obtenerFechaFin().getHour()));
        this.textFieldMinutosFin.setText(String.valueOf(this.eventoRepetido.obtenerFechaFin().getMinute()));
        this.checkboxEsActividadDelDia.setSelected(this.eventoRepetido.obtenerEsActividadDelDia());
        Label labelRepeticionOriginal = new Label();
        labelRepeticionOriginal.setText("Repeticion original: " + String.valueOf(this.eventoRepetido.obtenerTipoFrecuencia()));
        vBoxFrecuencia.getChildren().add(0, labelRepeticionOriginal);
        ArrayList<Alarma> listaDeAlarmasDelEvento = this.eventoRepetido.obtenerAlarmas();
        for(Alarma alarma: listaDeAlarmasDelEvento){
            int minutosAntes = (int) alarma.getHorarioAlarma().until(this.eventoRepetido.obtenerFecha(), ChronoUnit.MINUTES);
            TipoAlarma tipoAlarma = alarma.getTipoAlarma();
            agregarAlarmaAListaDeAlarmas(minutosAntes, tipoAlarma);
        }

        this.buttonAgregarNuevaActividad.setVisible(false);

        this.buttonModificarEvento = new Button("Modificar Evento");
        this.buttonModificarEvento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modificarEventoOriginalAccion();
            }
        });
        this.buttonEliminarEvento = new Button("Eliminar Evento");
        this.buttonEliminarEvento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                eliminarEventoOriginalAccion();
            }
        });
        this.hBoxBotones.getChildren().add(0, buttonEliminarEvento);
        this.hBoxBotones.getChildren().add(0, buttonModificarEvento);
    }
    private void modificarEventoOriginalAccion(){
        try {
            this.obtenerInformacionDelFormulario();
            Frecuencia frecuencia = this.determinarFrecuenciaElegida(this.fechaInicio);
            Evento eventoOriginal = this.eventoRepetido.obtenerEventoOriginal();
            Evento eventoNuevo = new Evento(titulo, descripcion, fechaInicio, fechaFin, esActividadDelDia, frecuencia, this.tipoFrecuenciaElegida, true);
            this.agregarAlarmasAActividad(eventoNuevo);

            this.principalControlador.modificarEvento(eventoOriginal, eventoNuevo);
            ventana.cerrarEscenarioActual();
            ventana.mostrarVentanaDiaria(LocalDateTime.now());
        }catch(Exception e){
            this.labelFaltaInformacion.setVisible(true);
        }
    }
    private void eliminarEventoOriginalAccion(){
        this.principalControlador.eliminarEvento(this.eventoRepetido.obtenerEventoOriginal());
        ventana.cerrarEscenarioActual();
        ventana.mostrarVentanaDiaria(LocalDateTime.now());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.comboBoxFrecuencia.getItems().add("Diaria");
        this.comboBoxFrecuencia.getItems().add("Semanal");
        this.comboBoxFrecuencia.getItems().add("Mensual");
        this.comboBoxFrecuencia.getItems().add("Anual");

        this.comboBoxRepeticion.getItems().add("Infinita");
        this.comboBoxRepeticion.getItems().add("Fecha limite");
        this.comboBoxRepeticion.getItems().add("Cantidad repeticiones");

        this.comboBoxTipoAlarma.getItems().add("Email");
        this.comboBoxTipoAlarma.getItems().add("Sonido");
        this.comboBoxTipoAlarma.getItems().add("Visual");

        this.labelFaltaInformacion.setVisible(false);

        if(esModificable){
            this.rellenarInformacionDelEventoRepetido();
        }else{
            this.datePickerInicio.setValue(LocalDateTime.now().toLocalDate());

            this.tipoFrecuenciaElegida = TipoFrecuencia.DIARIA;
            this.textFieldIntervaloDias = new TextField("1");
            this.tipoRepeticionElegida = TipoRepeticion.CANT_DETERMINADA;
            this.textFieldEleccionCantidadRepeticiones = new TextField("1");
        }
    }
}
