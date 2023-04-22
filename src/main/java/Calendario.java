import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Calendario {
    private List<Evento> eventos;
    private List<Tarea> tareas;
    public Calendario(){
        this.eventos = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }
    public void agregarEvento(Evento evento){
        this.eventos.add(evento);
    }
    public void agregarTarea(Tarea tarea){
        this.tareas.add(tarea);
    }
    public void modificarEvento(Evento eViejo, Evento eNuevo){
            if(this.eventos.contains(eViejo)){
                int index = this.eventos.indexOf(eViejo);
                this.eventos.set(index, eNuevo);
            }else{
                this.agregarEvento(eNuevo);
            }
    }
    public void modificarTarea(Tarea tVieja, Tarea tNueva){
        if(this.tareas.contains(tVieja)){
            int index = this.tareas.indexOf(tVieja);
            this.tareas.set(index, tNueva);
        }else{
            this.agregarTarea(tNueva);
        }
    }
    public void eliminarEvento(Evento evento){
        this.eventos.remove(evento);
    }
    public void eliminarTarea(Tarea tarea){
        this.tareas.remove(tarea);
    }

    public ArrayList<Alarma> proximasAlarmas(LocalDateTime time){
        ArrayList<Alarma> alarmas = new ArrayList<>();
        long tiempoMinimo = Long.MAX_VALUE;
        for(var actividad: this.eventos){
            for(var alarma: actividad.alarmas){
                long tiempoParaAlarma = time.until(alarma.getHorarioAlarma(), ChronoUnit.MINUTES);
                if( tiempoParaAlarma >= 0 && tiempoParaAlarma < tiempoMinimo ){
                    alarmas.add(alarma);
                }
            }
        }
        for(var actividad: this.tareas){
            for(var alarma: actividad.alarmas){
                long tiempoParaAlarma = time.until(alarma.getHorarioAlarma(), ChronoUnit.MINUTES);
                if( tiempoParaAlarma >= 0 && tiempoParaAlarma < tiempoMinimo ){
                    alarmas.add(alarma);
                }
            }
        }
        return alarmas;
    }

    public ArrayList<Actividad> obtenerActividadesDelDia(LocalDateTime fecha){
        ArrayList<Actividad> actividadesDelDia = new ArrayList<>();
        LocalDateTime inicio = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth(), 0, 0);
        LocalDateTime fin = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth(), 23, 59);
        for(var evento: eventos){
            actividadesDelDia.addAll(evento.eventosRepetidosEntreFechas(inicio, fin));
        }
        for(var tarea: tareas){
            actividadesDelDia.addAll(tarea.obtenerTareaEntreFechas(inicio, fin));
        }
        return actividadesDelDia;
    }

    public ArrayList<Actividad> obtenerActividadesDeLaSemana(LocalDateTime fecha){
        ArrayList<Actividad> actividadesDeLaSemana = new ArrayList<>();
        int diaDelMesInicioSemana = fecha.getDayOfMonth() - fecha.getDayOfWeek().getValue() + 1;
        int diaDelMesFinSemana = fecha.getDayOfMonth() + (7 - fecha.getDayOfWeek().getValue());
        LocalDateTime inicio = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), diaDelMesInicioSemana, 0, 0);
        LocalDateTime fin = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), diaDelMesFinSemana, 23, 59);
        for(var evento: eventos){
            actividadesDeLaSemana.addAll(evento.eventosRepetidosEntreFechas(inicio, fin));
        }
        for(var tarea: tareas){
            actividadesDeLaSemana.addAll(tarea.obtenerTareaEntreFechas(inicio, fin));
        }
        return actividadesDeLaSemana;
    }

    public ArrayList<Actividad> obtenerActividadesDelMes(LocalDateTime fecha){
        ArrayList<Actividad> actividadesDelMes = new ArrayList<>();
        int ultimoDiaDelMes = fecha.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        LocalDateTime inicio = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), ultimoDiaDelMes, 23, 59);
        for(var evento: eventos){
            actividadesDelMes.addAll(evento.eventosRepetidosEntreFechas(inicio, fin));
        }
        for(var tarea: tareas){
            actividadesDelMes.addAll(tarea.obtenerTareaEntreFechas(inicio, fin));
        }
        return actividadesDelMes;
    }
}
