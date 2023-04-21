public class FrecuenciaMensual extends Frecuencia{
    public FrecuenciaMensual(){
        this.duracion = 0;
        this.esDuracionInfinita = false;
    }
    public FrecuenciaMensual(int duracion, boolean esDuracionInfinita){
        this.duracion = duracion;
        this.esDuracionInfinita = esDuracionInfinita;
    }
}
