package controlador;

import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.TipoRepeticion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class BuilderEleccionFrecuencia {
    private LocalDateTime fechaInicio;
    private TipoRepeticion tipoRepeticion;
    private ArrayList<CheckBox> checkboxesDias;
    private TextField textFieldIntervaloDias;
    private DatePicker datePickerEleccionFechaLimite;
    private TextField textFieldEleccionCantidadRepeticiones;

    public void setTextFieldIntervaloDias(TextField textFieldIntervaloDias) {
        this.textFieldIntervaloDias = textFieldIntervaloDias;
    }
    public void setDatePickerEleccionFechaLimite(DatePicker datePickerEleccionFechaLimite) {
        this.datePickerEleccionFechaLimite = datePickerEleccionFechaLimite;
    }
    public void setTextFieldEleccionCantidadRepeticiones(TextField textFieldEleccionCantidadRepeticiones) {
        this.textFieldEleccionCantidadRepeticiones = textFieldEleccionCantidadRepeticiones;
    }
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void setTipoRepeticion(TipoRepeticion tipoRepeticion) {
        this.tipoRepeticion = tipoRepeticion;
    }
    public void setCheckboxesDias(ArrayList<CheckBox> checkboxesDias) {
        this.checkboxesDias = checkboxesDias;
    }
    public DayOfWeek[] getDiasElegidos(){
        TreeSet<DayOfWeek> diasRepeticion = new TreeSet<>();
        DayOfWeek[] dias = DayOfWeek.values();
        for(int i=0; i<this.checkboxesDias.size(); i++){
            CheckBox c = checkboxesDias.get(i);
            if(c.isSelected()) {
                diasRepeticion.add(dias[i]);
            }
        }
        DayOfWeek[] diasElegidos = diasRepeticion.toArray(DayOfWeek[]::new);
        return diasElegidos;
    }
    public TipoRepeticion getTipoRepeticion(){
        return this.tipoRepeticion;
    }
    public LocalDateTime getFechaInicio(){
        return this.fechaInicio;
    }
    public int getIntervaloDias(){
        return Integer.parseInt(this.textFieldIntervaloDias.getText());
    }
    public LocalDateTime getFechaLimite(){
        LocalDate fecha = this.datePickerEleccionFechaLimite.getValue();
        return fecha.atStartOfDay();
    }
    public int getCantidadRepeticiones(){
        return Integer.parseInt(textFieldEleccionCantidadRepeticiones.getText());
    }
}
