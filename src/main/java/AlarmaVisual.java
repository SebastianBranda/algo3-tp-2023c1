import java.time.LocalDateTime;

public class AlarmaVisual extends Alarma{
    public AlarmaVisual(LocalDateTime horario){
        this.horarioAlarma = horario;
        this.tipoAlarma = TipoAlarma.VISUAL;
    }
    @Override
    public void notificar() {
        // TODO
    }
}
