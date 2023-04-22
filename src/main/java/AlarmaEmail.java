import java.time.LocalDateTime;

public class AlarmaEmail extends Alarma{
    public AlarmaEmail(LocalDateTime horario, TipoAlarma tipo){
        this.horarioAlarma = horario;
        this.tipoAlarma = tipo;
    }
    @Override
    public void notificar() {
        // TODO
    }
}
