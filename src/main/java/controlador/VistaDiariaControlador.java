package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.*;
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
    private HBox hBoxActividadesDelDia;
    @FXML
    private ComboBox<String> seleccionTipoDeVistaComboBox;
    private LocalDateTime fechaFinActividad;

    public VistaDiariaControlador(PrincipalControlador principalControlador, Ventana ventana, String archivoFXML, LocalDateTime fecha){
        super(principalControlador, ventana, archivoFXML);
        this.fechaVistaDiaria = fecha;
    }
    @FXML
    void botonVerDiaPrevioAccion(ActionEvent event) {
        this.cambiarEscenarioAVentanaDiaria(this.fechaVistaDiaria.minusDays(1));
    }
    @FXML
    void botonVerDiaActualAccion(ActionEvent event) {
        this.cambiarEscenarioAVentanaDiaria(LocalDateTime.now());
    }
    @FXML
    void botonVerDiaPosteriorAccion(ActionEvent event) {
        this.cambiarEscenarioAVentanaDiaria(this.fechaVistaDiaria.plusDays(1));
    }
    @FXML
    public void crearEventoAccion() {
        this.cambiarEscenarioAVentanaCrearEvento();
    }
    public void crearTareaAccion() {
        this.cambiarEscenarioAVentanaCrearTarea();
    }
    @FXML
    void seleccionTipoDeVistaAction(ActionEvent event) {
        String eleccionVista = this.seleccionTipoDeVistaComboBox.getValue();
        switch (eleccionVista) {
            case "Semanal" -> this.cambiarEscenarioAVentanaSemanal(fechaVistaDiaria);
            case "Mensual" -> this.cambiarEscenarioAVentanaMensual(fechaVistaDiaria);
            default -> throw new IllegalStateException("Valor inesperado en la eleccion de tipo de Vista: " + eleccionVista);
        }
    }
    private void agregarActividadAHoraEspecifica(Actividad actividad, int inicioHora, VBox cuadro){
        var listadoHoras = vboxHoras.getChildren();
        cuadro.setOnMouseClicked(e->this.cambiarEscenarioAVentanaModificarActividad(actividad));
        HBox hBoxHoraEspecifica = (HBox) listadoHoras.get(inicioHora);
        hBoxHoraEspecifica.getChildren().add(cuadro);
    }
    public void agregarActividadAHorario(Actividad actividad){
        LocalDateTime fechaInicioActividad = actividad.obtenerFecha();
        int inicioHora = fechaInicioActividad.getHour();
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
        VBox cuadro = cuadroInformativo.obtenerCuadroInformativoVista();

        if(actividad.obtenerEsActividadDelDia()){
            cuadro.setOnMouseClicked(e->this.cambiarEscenarioAVentanaModificarActividad(actividad));
            this.hBoxActividadesDelDia.getChildren().add(cuadro);
            return;
        }
        if(perteneceActividadAVista(fechaInicioActividad)){
            agregarActividadAHoraEspecifica(actividad, inicioHora, cuadro);
        }
        while(fechaFinActividad.isAfter(fechaInicioActividad.withMinute(0).plusHours(1))){
            fechaInicioActividad = fechaInicioActividad.withMinute(0).plusHours(1);
            if(!perteneceActividadAVista(fechaInicioActividad)){
                continue;
            }
            cuadroInformativo = new CuadroInformativoActividadControlador(actividad);
            cuadro = cuadroInformativo.obtenerCuadroInformativoVista();
            inicioHora = fechaInicioActividad.getHour();
            agregarActividadAHoraEspecifica(actividad, inicioHora, cuadro);
        }
    }
    private Boolean perteneceActividadAVista(LocalDateTime fecha){
        if(fecha.getYear() != this.fechaVistaDiaria.getYear()){
            return false;
        }
        if(fecha.getMonth() != this.fechaVistaDiaria.getMonth()){
            return false;
        }
        if(fecha.getDayOfMonth() != this.fechaVistaDiaria.getDayOfMonth()){
            return false;
        }
        return true;
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
        this.seleccionTipoDeVistaComboBox.getItems().add("Mensual");
    }
}
