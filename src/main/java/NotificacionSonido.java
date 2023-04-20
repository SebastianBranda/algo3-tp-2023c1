public class NotificacionSonido extends Notificacion{

    public NotificacionSonido(){
        super();
    }
    public NotificacionSonido(String mensaje){
        super(mensaje);
    }
    @Override
    protected void notificar(){
        //Se va a redefinir notificar() para que esta reproduzca un sonido como notificacion.
    }

}
