package modelo;

import java.time.LocalDateTime;

public class AlarmaSonido extends Alarma{
    public AlarmaSonido(LocalDateTime horario){
        this.horarioAlarma = horario;
        this.tipoAlarma = TipoAlarma.SONIDO;
    }
    @Override
    public void notificar() {
        // TODO
    }
}
