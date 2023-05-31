import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

abstract class Frecuencia implements Serializable {
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
    public LocalDateTime getFechaInicial(){
        return this.fechaInicial;
    }
}
