import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

enum TipoFrecuencia{DIARIA, SEMANAL, MENSUAL, ANUAL}
public class Evento extends Actividad {
    private LocalDateTime fechaHoraFin;
    private Frecuencia frecuencia;
    private TipoFrecuencia tipoFrecuencia;
    private Boolean esRepetible;

    public Evento(String titulo,
                  String descripcion,
                  LocalDateTime fechaHora,
                  LocalDateTime fechaHoraFin,
                  Boolean esActividadDelDia,
                  Frecuencia frecuencia,
                  TipoFrecuencia tipoFrecuencia,
                  Boolean esRepetible){
        this.setTitulo(titulo);
        this.setDescripcion(descripcion);
        this.setFechaHora(fechaHora);
        this.fechaHoraFin = fechaHoraFin;
        this.setEsActividadDelDia(esActividadDelDia);
        this.frecuencia = frecuencia;
        this.tipoFrecuencia = tipoFrecuencia;
        this.esRepetible = esRepetible;
    }

    public void modificar(String titulo,
                          String descripcion,
                          LocalDateTime fechaHora,
                          LocalDateTime fechaHoraFin,
                          Boolean esActividadDelDia,
                          Frecuencia frecuencia,
                          Boolean esRepetible){
        this.setTitulo(titulo);
        this.setDescripcion(descripcion);
        this.setFechaHora(fechaHora);
        this.fechaHoraFin = fechaHoraFin;
        this.setEsActividadDelDia(esActividadDelDia);
        this.frecuencia = frecuencia;
        this.esRepetible = esRepetible;
    }
    public void modificar(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, Boolean esActividadDelDia){
        this.setTitulo(titulo);
        this.setDescripcion(descripcion);
        this.setFechaHora(fechaHora);
        this.fechaHoraFin = fechaHoraFin;
        this.setEsActividadDelDia(esActividadDelDia);
    }
    public ArrayList<EventoRepetido> eventosRepetidosEntreFechas(LocalDateTime inicio, LocalDateTime fin){
        if(this.getFechaHora().isAfter(fin) || this.fechaHoraFin.isBefore(inicio)){
            return null;
        }
        switch (this.tipoFrecuencia){
            case DIARIA:
                return this.repeticionesEntreFechasCasoDiario(inicio, fin);
            case SEMANAL:
                return this.repeticionesEntreFechasCasoSemanal(inicio, fin);
            case MENSUAL:
                return this.repeticionesEntreFechasCasoMensual(inicio, fin);
            case ANUAL:
                return this.repeticionesEntreFechasCasoAnual(inicio, fin);
        }
        return null;
    }
    private ArrayList<EventoRepetido> repeticionesEntreFechasCasoDiario(LocalDateTime inicio, LocalDateTime fin){
        long duracionMinutos = this.getFechaHora().until(this.fechaHoraFin, ChronoUnit.MINUTES);
        int reglaRepeticionDias = this.frecuencia.reglaDeRepeticion().get(0);
        ArrayList<EventoRepetido> eRep = new ArrayList<>();
        eRep.add(new EventoRepetido(this, this.getFechaHora(), this.getFechaHora().plusMinutes(duracionMinutos)));
        LocalDateTime aux;
        if(this.frecuencia.getEsDuracionInfinita()){
                aux = this.getFechaHora().minusDays(reglaRepeticionDias);
                while(aux.isAfter(inicio)){
                    eRep.add(new EventoRepetido(this, aux, aux.plusMinutes(duracionMinutos)));
                    aux = aux.minusDays(reglaRepeticionDias);
                }
                aux = this.getFechaHora().plusDays(reglaRepeticionDias);
                while(aux.isBefore(fin)){
                    eRep.add(new EventoRepetido(this, aux, aux.plusMinutes(duracionMinutos)));
                    aux = aux.plusDays(reglaRepeticionDias);
                }
        }else{
            aux = this.getFechaHora().plusDays(reglaRepeticionDias);
            int cantReps = this.frecuencia.getCantidadRepeticiones();
            while(aux.isBefore(fin) && cantReps >0){
                eRep.add(new EventoRepetido(this, aux, aux.plusMinutes(duracionMinutos)));
                aux = aux.plusDays(reglaRepeticionDias);
                cantReps--;
            }
        }
        return eRep;
    }
    private ArrayList<EventoRepetido> repeticionesEntreFechasCasoSemanal(LocalDateTime inicio, LocalDateTime fin){
        long duracionMinutos = this.getFechaHora().until(this.fechaHoraFin, ChronoUnit.MINUTES);
        ArrayList<EventoRepetido> eRep = new ArrayList<>();
        eRep.add(new EventoRepetido(this, this.getFechaHora(), this.getFechaHora().plusMinutes(duracionMinutos)));
        LocalDateTime aux;
        if(this.getFechaHora().isAfter(inicio)){
            aux = this.getFechaHora();
        }else{
            //  puedo comenzar en cualquier dia de la semana en el cual no haya un evento repetido
            aux = inicio;
            int diaInicio = aux.getDayOfWeek().getValue();
            int diferenciaDias = -1;
            for(var diaDeRepeticion: this.frecuencia.reglaDeRepeticion()){
                if(diaDeRepeticion >= diaInicio){
                    diferenciaDias = diaDeRepeticion - diaInicio;
                    break;
                }
            }
            if(diferenciaDias<0){
                diferenciaDias =  7 - diaInicio + this.frecuencia.reglaDeRepeticion().get(0);
            }
            aux = aux.plusDays(diferenciaDias);
        }
        int cantReps = this.frecuencia.getCantidadRepeticiones();
        while(aux.isBefore(fin) && aux.isBefore(this.fechaHoraFin) && cantReps > 0){
            int diferenciaDias;
            for(var diaDeRepeticion: this.frecuencia.reglaDeRepeticion()){
                diferenciaDias = aux.getDayOfWeek().getValue() - diaDeRepeticion;
                LocalDateTime fechaNuevoEventoRep = aux.plusDays(diferenciaDias);
                if(fechaNuevoEventoRep.isBefore(fin) && fechaNuevoEventoRep.isBefore(this.fechaHoraFin) && cantReps > 0){
                    eRep.add(new EventoRepetido(this, fechaNuevoEventoRep, fechaNuevoEventoRep.plusMinutes(duracionMinutos)));
                    cantReps--;
                }
            }
            aux = aux.plusDays(7);
        }
        return eRep;
    }
    private ArrayList<EventoRepetido> repeticionesEntreFechasCasoMensual(LocalDateTime inicio, LocalDateTime fin){
        long duracionMinutos = this.getFechaHora().until(this.fechaHoraFin, ChronoUnit.MINUTES);
        ArrayList<EventoRepetido> eRep = new ArrayList<>();
        eRep.add(new EventoRepetido(this, this.getFechaHora(), this.getFechaHora().plusMinutes(duracionMinutos)));
        LocalDateTime aux = this.getFechaHora().plusMonths(1);
        if(this.frecuencia.getEsDuracionInfinita()){
            while(aux.isBefore(fin)){
                eRep.add(new EventoRepetido(this, this.getFechaHora(), this.getFechaHora().plusMinutes(duracionMinutos)));
                aux = aux.plusMonths(1);
            }
        }else{
            int cantReps = this.frecuencia.getCantidadRepeticiones();
            while(aux.isBefore(fin) && cantReps > 0){
                eRep.add(new EventoRepetido(this, this.getFechaHora(), this.getFechaHora().plusMinutes(duracionMinutos)));
                aux = aux.plusMonths(1);
                cantReps--;
            }
        }
        return eRep;
    }
    private ArrayList<EventoRepetido> repeticionesEntreFechasCasoAnual(LocalDateTime inicio, LocalDateTime fin){
        long duracionMinutos = this.getFechaHora().until(this.fechaHoraFin, ChronoUnit.MINUTES);
        ArrayList<EventoRepetido> eRep = new ArrayList<>();
        eRep.add(new EventoRepetido(this, this.getFechaHora(), this.getFechaHora().plusMinutes(duracionMinutos)));
        LocalDateTime aux = this.getFechaHora().plusYears(1);
        if(this.frecuencia.getEsDuracionInfinita()){
            while(aux.isBefore(fin)){
                eRep.add(new EventoRepetido(this, this.getFechaHora(), this.getFechaHora().plusMinutes(duracionMinutos)));
                aux = aux.plusYears(1);
            }
        }else{
            int cantReps = this.frecuencia.getCantidadRepeticiones();
            while(aux.isBefore(fin) && cantReps > 0){
                eRep.add(new EventoRepetido(this, this.getFechaHora(), this.getFechaHora().plusMinutes(duracionMinutos)));
                aux = aux.plusYears(1);
                cantReps--;
            }
        }
        return eRep;
    }
}

