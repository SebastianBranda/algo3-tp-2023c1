public class FrecuenciaAnual extends Frecuencia{
    public FrecuenciaAnual(){
        this.duracion = 0;
        this.esDuracionInfinita = false;
    }
    public FrecuenciaAnual(int duracion, boolean esDuracionInfinita){
    this.duracion = duracion;
    this.esDuracionInfinita = esDuracionInfinita;
    }
}
