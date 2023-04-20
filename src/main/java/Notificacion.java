abstract class Notificacion {
    protected String mensaje;
    public Notificacion(){};

    public Notificacion(String mensaje){
        this.mensaje = mensaje;
    }
    protected void notificar(){
        //Tipo virtual
    };
}
