import java.time.LocalDateTime;

public class AlarmaVisual extends Alarma{
    public AlarmaVisual(LocalDateTime horario, TipoAlarma tipo){
        this.horarioAlarma = horario;
        this.tipoAlarma = tipo;
    }
    @Override
    public void notificar() {
        // TODO
    }
}
