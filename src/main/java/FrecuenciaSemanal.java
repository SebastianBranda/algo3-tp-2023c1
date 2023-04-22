import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class FrecuenciaSemanal extends Frecuencia{
    private ArrayList<DayOfWeek> diasRepeticion= new ArrayList<>();

    public FrecuenciaSemanal(LocalDateTime fecha, DayOfWeek [] dias){
        this.fechaInicial = fecha;
        this.cantidadRepeticiones = dias.length;
        this.esDuracionInfinita = false;
        this.agregarDiasSemana(dias);
    }
    public FrecuenciaSemanal(LocalDateTime fecha, boolean esDuracionInfinita, DayOfWeek [] dias){
        this.fechaInicial = fecha;
        this.cantidadRepeticiones = 0;
        this.esDuracionInfinita = esDuracionInfinita;
        this.agregarDiasSemana(dias);
    }
    public FrecuenciaSemanal(LocalDateTime fechaInicial, LocalDateTime fechaFinal, DayOfWeek [] dias){
        this.fechaInicial = fechaInicial;
        long cantDias = fechaInicial.until(fechaFinal, ChronoUnit.DAYS);
        this.cantidadRepeticiones = ((int) (cantDias/7)) * dias.length;
        this.esDuracionInfinita = false;
        this.agregarDiasSemana(dias);
    }
    public FrecuenciaSemanal(LocalDateTime fechaInicial, int cantRepeticiones, DayOfWeek [] dias){
        this.fechaInicial = fechaInicial;
        this.cantidadRepeticiones = cantRepeticiones;
        this.esDuracionInfinita = false;
        this.agregarDiasSemana(dias);
    }
    private void agregarDiasSemana(DayOfWeek [] dias){
        for(var d: dias){
            diasRepeticion.add(d);
        }
    }

    @Override
    public ArrayList<Integer> reglaDeRepeticion() {
        ArrayList<Integer> regla = new ArrayList<>();
        for(var d: this.diasRepeticion){
            regla.add(d.getValue());
        }
        return regla;
    }
}
