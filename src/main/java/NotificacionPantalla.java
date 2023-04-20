public class NotificacionPantalla extends Notificacion{

    public NotificacionPantalla(){
        super();
    }
    public NotificacionPantalla(String mensaje){
        super(mensaje);
    }
    @Override
    protected void notificar(){
        //Se va a redefinir notificar para que esta muestre notificacion en pantalla.
    }

}
