public class FrecuenciaDiaria extends Frecuencia{
    private int intervaloDias;
    public FrecuenciaDiaria(){
        this.duracion = 0;
        this.esDuracionInfinita = false;
        this.intervaloDias = 0;
    }
    public FrecuenciaDiaria(int duracion, boolean esDuracionInfinita, int intervaloDias){
        this.duracion = duracion;
        this.esDuracionInfinita = esDuracionInfinita;
        this.intervaloDias = intervaloDias;
    }
}
