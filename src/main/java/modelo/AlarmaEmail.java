package modelo;

import java.time.LocalDateTime;

public class AlarmaEmail extends Alarma{
    public AlarmaEmail(LocalDateTime horario){
        this.horarioAlarma = horario;
        this.tipoAlarma = TipoAlarma.EMAIL;
    }
    @Override
    public void notificar() {
        // TODO
    }
}
