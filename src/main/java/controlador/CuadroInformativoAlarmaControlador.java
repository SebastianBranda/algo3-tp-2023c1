package controlador;

import javafx.scene.layout.VBox;
import modelo.TipoAlarma;
import vista.CuadroInformativoAlarma;

public class CuadroInformativoAlarmaControlador {
    private int minutosAntes;
    private TipoAlarma tipoAlarma;
    public CuadroInformativoAlarmaControlador(int minutosAntes, TipoAlarma tipo){
        this.minutosAntes = minutosAntes;
        this.tipoAlarma=tipo;
    }
    public VBox obtenerCuadroInformativoAlarma(){
        CuadroInformativoAlarma cuadro = new CuadroInformativoAlarma();
        return cuadro.obtenerVista(this.tipoAlarma.toString(), String.valueOf(minutosAntes));
    }
    public int getMinutosAntes() {
        return minutosAntes;
    }
    public TipoAlarma getTipoAlarma() {
        return tipoAlarma;
    }
}
