package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modelo.*;
import vista.Ventana;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VistaSemanalControlador extends BaseControlador implements Initializable {
    private LocalDateTime fechaSemanalAMostrar;
    @FXML
    private GridPane gridpane;
    @FXML
    private Label labelSemanaAMostrarVentana;
    @FXML
    private ComboBox<String> comboBoxSeleccionTipoVista;
    @FXML
    private Label labelLunes;
    @FXML
    private Label labelMartes;
    @FXML
    private Label labelMiercoles;
    @FXML
    private Label labelJueves;
    @FXML
    private Label labelSabado;
    @FXML
    private Label labelDomingo;
    @FXML
    private Label labelViernes;
    @FXML
    private Label labelTipoDeVista;
    private LocalDateTime fechaFinActividad;
    public VistaSemanalControlador(PrincipalControlador principalControlador, Ventana ventana, String archivoFXML, LocalDateTime fecha) {
        super(principalControlador, ventana, archivoFXML);
        this.fechaSemanalAMostrar = fecha;
    }
    @FXML
    void botonVerSemanaActualAccion(ActionEvent event) {
        this.cambiarEscenarioAVentanaSemanal(LocalDateTime.now());
    }
    @FXML
    void botonVerSemanaPosteriorAccion(ActionEvent event) {
        this.cambiarEscenarioAVentanaSemanal(this.fechaSemanalAMostrar.plusDays(7));
    }
    @FXML
    void botonVerSemanaPreviaAccion(ActionEvent event) {
        this.cambiarEscenarioAVentanaSemanal(this.fechaSemanalAMostrar.minusDays(7));
    }
    @FXML
    void crearEventoAccion(ActionEvent event) {
        this.cambiarEscenarioAVentanaCrearEvento();
    }
    @FXML
    void crearTareaAccion(ActionEvent event) {
        this.cambiarEscenarioAVentanaCrearTarea();
    }
    @FXML
    void comboBoxSeleccionTipoVistaAction(ActionEvent event) {
        String eleccionVista = this.comboBoxSeleccionTipoVista.getValue();
        switch (eleccionVista) {
            case "Diaria" -> this.cambiarEscenarioAVentanaDiaria(fechaSemanalAMostrar);
            case "Mensual" -> this.cambiarEscenarioAVentanaMensual(fechaSemanalAMostrar);
            default -> throw new IllegalStateException("Valor inesperado en la eleccion de tipo de Vista: " + eleccionVista);
        }
    }
    private void agregarActividadesAVistaSemanal(){
        this.ventana.agregarVBoxAGridpane(this.gridpane, 0,25, 1, 8);
        ArrayList<Actividad> listaActividades = this.principalControlador.obtenerActividadesDeLaSemana(this.fechaSemanalAMostrar);
        for(var actividad: listaActividades){
            this.agregarActividadAHorario(actividad);
        }
    }
    private void agregarActividadAHorario(Actividad actividad){
        LocalDateTime fechaInicioActividad = actividad.obtenerFecha();
        DayOfWeek diaDeSemana = fechaInicioActividad.getDayOfWeek();
        int dia = diaDeSemana.getValue();
        int hora = fechaInicioActividad.getHour()+1;
        actividad.aceptarVisitante(new VisitanteActividad() {
            @Override
            public void visitarEvento(Evento evento) {
            }
            @Override
            public void visitarTarea(Tarea tarea) {
                fechaFinActividad = tarea.obtenerFecha();
            }
            @Override
            public void visitarEventoRepetido(EventoRepetido eventoRepetido) {
                fechaFinActividad = eventoRepetido.obtenerFechaFin();
            }
        });
        CuadroInformativoActividadControlador cuadroInformativo = new CuadroInformativoActividadControlador(actividad);
        if(actividad.obtenerEsActividadDelDia()){
            hora = 0;
            if(perteneceActividadAVista(fechaInicioActividad)){
                agregarActividadADiaYHoraEspecifica(actividad, hora, dia, cuadroInformativo);
            }
            while(fechaFinActividad.isAfter(fechaInicioActividad.withHour(0).withMinute(1).plusDays(1))){
                fechaInicioActividad = fechaInicioActividad.withHour(0).withMinute(1).plusDays(1);
                cuadroInformativo = new CuadroInformativoActividadControlador(actividad);
                diaDeSemana = fechaInicioActividad.getDayOfWeek();
                dia = diaDeSemana.getValue();
                if(perteneceActividadAVista(fechaInicioActividad)){
                    agregarActividadADiaYHoraEspecifica(actividad, hora, dia, cuadroInformativo);
                }
            }
            return;
        }

        if(perteneceActividadAVista(fechaInicioActividad)){
            agregarActividadADiaYHoraEspecifica(actividad, hora, dia, cuadroInformativo);
        }

        while(fechaFinActividad.isAfter(fechaInicioActividad.withMinute(0).plusHours(1))){
            fechaInicioActividad = fechaInicioActividad.withMinute(0).plusHours(1);
            cuadroInformativo = new CuadroInformativoActividadControlador(actividad);

            hora = fechaInicioActividad.getHour()+1;
            diaDeSemana = fechaInicioActividad.getDayOfWeek();
            dia = diaDeSemana.getValue();

            if(perteneceActividadAVista(fechaInicioActividad)){
                agregarActividadADiaYHoraEspecifica(actividad, hora, dia, cuadroInformativo);
            }
        }
    }
    private void agregarActividadADiaYHoraEspecifica(Actividad actividad, int hora, int dia, CuadroInformativoActividadControlador cuadroInformativo){
        VBox cuadro = cuadroInformativo.obtenerCuadroInformativoVista();
        cuadro.setOnMouseClicked(e->this.cambiarEscenarioAVentanaModificarActividad(actividad));
        VBox vbox = (VBox) this.ventana.obtenerElementoDeCeldaEnGridpane(this.gridpane, hora, dia);
        vbox.getChildren().add(cuadro);
    }
    private Boolean perteneceActividadAVista(LocalDateTime fecha){
        if((fecha.getDayOfYear() < this.fechaSemanalAMostrar.with(DayOfWeek.MONDAY).getDayOfYear())
            || (fecha.getDayOfYear() > this.fechaSemanalAMostrar.with(DayOfWeek.SUNDAY).getDayOfYear())){
            return false;
        }
        return true;
    }
    public void inicializarEtiquetas(){
        String mes = this.fechaSemanalAMostrar.getMonth().name();
        String anio = String.valueOf(this.fechaSemanalAMostrar.getYear());
        this.labelSemanaAMostrarVentana.setText(mes + " / " + anio);

        this.comboBoxSeleccionTipoVista.getItems().add("Diaria");
        this.comboBoxSeleccionTipoVista.getItems().add("Mensual");

        this.labelLunes.setText("Lunes " + String.valueOf(this.fechaSemanalAMostrar.with(DayOfWeek.MONDAY).getDayOfMonth()));
        this.labelMartes.setText("Martes " + String.valueOf(this.fechaSemanalAMostrar.with(DayOfWeek.TUESDAY).getDayOfMonth()));
        this.labelMiercoles.setText("Miercoles " + String.valueOf(this.fechaSemanalAMostrar.with(DayOfWeek.WEDNESDAY).getDayOfMonth()));
        this.labelJueves.setText("Jueves " + String.valueOf(this.fechaSemanalAMostrar.with(DayOfWeek.THURSDAY).getDayOfMonth()));
        this.labelViernes.setText("Viernes " + String.valueOf(this.fechaSemanalAMostrar.with(DayOfWeek.FRIDAY).getDayOfMonth()));
        this.labelSabado.setText("Sabado " + String.valueOf(this.fechaSemanalAMostrar.with(DayOfWeek.SATURDAY).getDayOfMonth()));
        this.labelDomingo.setText("Domingo " + String.valueOf(this.fechaSemanalAMostrar.with(DayOfWeek.SUNDAY).getDayOfMonth()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.inicializarEtiquetas();
        this.agregarActividadesAVistaSemanal();
    }
}
