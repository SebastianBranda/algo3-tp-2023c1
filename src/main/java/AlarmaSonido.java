import java.time.LocalDateTime;

public class AlarmaSonido extends Alarma{
    public AlarmaSonido(LocalDateTime horario, TipoAlarma tipo){
        this.horarioAlarma = horario;
        this.tipoAlarma = tipo;
    }
    @Override
    public void notificar() {
        // TODO
    }
}
