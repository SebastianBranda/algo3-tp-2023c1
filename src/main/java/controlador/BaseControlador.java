package controlador;

import vista.Ventana;
public abstract class BaseControlador {
    protected PrincipalControlador principalControlador;
    protected Ventana ventana;
    protected String archivoFXML;
    public BaseControlador(PrincipalControlador principalControlador, Ventana ventana, String archivoFXML) {
        this.principalControlador = principalControlador;
        this.ventana = ventana;
        this.archivoFXML = archivoFXML;
    }
    public String obtenerNombreArchivoFXML(){
        return this.archivoFXML;
    }
}
