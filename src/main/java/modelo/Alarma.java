package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Alarma implements Serializable {
    protected LocalDateTime horarioAlarma;
    protected TipoAlarma tipoAlarma;
    public void setHorarioAlarma(LocalDateTime d){
        this.horarioAlarma = d;
    }
    public void setHorarioAlarmaDiferida(long minutosAntes){
        this.horarioAlarma = this.horarioAlarma.minusMinutes(minutosAntes);
    }
    public LocalDateTime getHorarioAlarma(){
        return this.horarioAlarma;
    }
    public TipoAlarma getTipoAlarma(){
        return this.tipoAlarma;
    }
    public abstract void notificar();
}
