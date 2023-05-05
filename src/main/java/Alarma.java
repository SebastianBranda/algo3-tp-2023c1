import java.time.LocalDateTime;
enum TipoAlarma{ EMAIL, SONIDO, VISUAL}

public abstract class Alarma {
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
    public abstract void notificar();
}
