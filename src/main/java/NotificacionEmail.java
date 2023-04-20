public class NotificacionEmail extends Notificacion{

    private String mailAdressDestinatario;
    public NotificacionEmail(){
        super();
    }
    public NotificacionEmail(String mensaje, String mailAdressDestinatario){
        super(mensaje);
        this.mailAdressDestinatario = mailAdressDestinatario;
    }
    @Override
    protected void notificar(){
        //Se va a redefinir notificar() para que esta mande la notificacion por email.
    }
}
