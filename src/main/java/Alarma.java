import java.time.LocalDateTime;

public abstract class Alarma {
    protected LocalDateTime horarioAlarma;
    public void setHorarioAlarma(LocalDateTime d){
        this.horarioAlarma = d;
    }
    public LocalDateTime getHorarioAlarma(){
        return this.horarioAlarma;
    }
    public abstract void notificar();
}
