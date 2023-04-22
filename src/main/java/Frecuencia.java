import java.time.LocalDateTime;
import java.util.ArrayList;

abstract class Frecuencia {
    protected LocalDateTime fechaInicial;
    protected int cantidadRepeticiones;
    protected boolean esDuracionInfinita;
    public abstract ArrayList<Integer> reglaDeRepeticion();
    public int getCantidadRepeticiones(){
        return this.cantidadRepeticiones;
    }
    public boolean getEsDuracionInfinita(){
        return this.esDuracionInfinita;
    }
}
