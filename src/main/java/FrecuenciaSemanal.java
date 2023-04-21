import java.time.DayOfWeek;
import java.util.ArrayList;

public class FrecuenciaSemanal extends Frecuencia{
    private ArrayList<DayOfWeek> diasRepeticion= new ArrayList<>();
    public FrecuenciaSemanal(){
        this.duracion = 0;
        this.esDuracionInfinita = false;
    }
    public FrecuenciaSemanal(int duracion, boolean esDuracionInfinita){
        this.duracion = duracion;
        this.esDuracionInfinita = esDuracionInfinita;
    }
    public void agregarDiasSemana(DayOfWeek d){
        diasRepeticion.add(d);
    }
}
