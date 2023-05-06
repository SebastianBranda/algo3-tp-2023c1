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
        this.alarmas = new ArrayList<Alarma>();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.fechaHoraFin = fechaHoraFin;
        this.esActividadDelDia = esActividadDelDia;
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
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.fechaHoraFin = fechaHoraFin;
        this.esActividadDelDia = esActividadDelDia;
        this.frecuencia = frecuencia;
        this.esRepetible = esRepetible;
    }
    public void modificar(String titulo, String descripcion, LocalDateTime fechaHora, LocalDateTime fechaHoraFin, Boolean esActividadDelDia){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.fechaHoraFin = fechaHoraFin;
        this.esActividadDelDia = esActividadDelDia;
    }
    public ArrayList<EventoRepetido> eventosRepetidosEntreFechas(LocalDateTime inicio, LocalDateTime fin){
        /*
        if(this.getFechaHora().isAfter(fin) || this.fechaHoraFin.isBefore(inicio)){
            return null;
        }
         */
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
    private void agregarEventoRepetidoAListaSiEstaEntreFechas(Evento eventoOriginal, ArrayList<EventoRepetido> lista, LocalDateTime fechaPosibleRepeticion, LocalDateTime inicio, LocalDateTime fin, long duracionEventoOriginal){
        if( (fechaPosibleRepeticion.isAfter(inicio) || fechaPosibleRepeticion.isEqual(inicio)) && fechaPosibleRepeticion.isBefore(fin)){
            lista.add(new EventoRepetido(eventoOriginal, fechaPosibleRepeticion, fechaPosibleRepeticion.plusMinutes(duracionEventoOriginal)));
        }
    }
    private ArrayList<EventoRepetido> repeticionesEntreFechasCasoDiario(LocalDateTime inicio, LocalDateTime fin){
        long duracionMinutosDelEvento = this.fechaHora.until(this.fechaHoraFin, ChronoUnit.MINUTES);
        int reglaRepeticionDias = this.frecuencia.reglaDeRepeticion().get(0);
        ArrayList<EventoRepetido> eRep = new ArrayList<>();
        agregarEventoRepetidoAListaSiEstaEntreFechas(this, eRep, this.fechaHora, inicio, fin, duracionMinutosDelEvento);
        LocalDateTime fechaRepeticion;
        if(this.frecuencia.getEsDuracionInfinita()){
            fechaRepeticion = this.fechaHora.minusDays(reglaRepeticionDias);
            while(fechaRepeticion.isAfter(inicio)){
                agregarEventoRepetidoAListaSiEstaEntreFechas(this, eRep, fechaRepeticion, inicio, fin, duracionMinutosDelEvento);
                fechaRepeticion = fechaRepeticion.minusDays(reglaRepeticionDias);
            }
            fechaRepeticion = this.fechaHora.plusDays(reglaRepeticionDias);
            while(fechaRepeticion.isBefore(fin)){
                agregarEventoRepetidoAListaSiEstaEntreFechas(this, eRep, fechaRepeticion, inicio, fin, duracionMinutosDelEvento);
                fechaRepeticion = fechaRepeticion.plusDays(reglaRepeticionDias);
            }
        }else{
            fechaRepeticion = this.fechaHora.plusDays(reglaRepeticionDias);
            int cantReps = this.frecuencia.getCantidadRepeticiones();
            while(fechaRepeticion.isBefore(fin) && cantReps >0){
                agregarEventoRepetidoAListaSiEstaEntreFechas(this, eRep, fechaRepeticion, inicio, fin, duracionMinutosDelEvento);
                fechaRepeticion = fechaRepeticion.plusDays(reglaRepeticionDias);
                cantReps--;
            }
        }
        return eRep;
    }
    private ArrayList<EventoRepetido> repeticionesEntreFechasCasoSemanal(LocalDateTime inicio, LocalDateTime fin){
        long duracionMinutos = this.fechaHora.until(this.fechaHoraFin, ChronoUnit.MINUTES);
        ArrayList<EventoRepetido> eRep = new ArrayList<>();
        LocalDateTime fechaPosibleRepeticion = this.fechaHora;
        int cantReps = this.frecuencia.getCantidadRepeticiones();
        if(this.fechaHora.isBefore(inicio)){
            long cantDiasNoIncluidos = this.frecuencia.getFechaInicial().until(inicio, ChronoUnit.DAYS);
            int cantRepsNoIncluidas = ((int) (cantDiasNoIncluidos/7)) * this.frecuencia.reglaDeRepeticion().size();
            cantReps = cantReps - cantRepsNoIncluidas;
        }
        if(this.frecuencia.getEsDuracionInfinita()){
            fechaPosibleRepeticion = inicio;
            cantReps = Integer.MAX_VALUE;
        }
        int diferenciaDias;
        while(fechaPosibleRepeticion.isBefore(fin) && cantReps > 0){
            for(var diaDeRepeticion: this.frecuencia.reglaDeRepeticion()){
                diferenciaDias = diaDeRepeticion - fechaPosibleRepeticion.getDayOfWeek().getValue();
                fechaPosibleRepeticion = fechaPosibleRepeticion.plusDays(diferenciaDias);
                if(fechaPosibleRepeticion.isAfter(inicio) && fechaPosibleRepeticion.isBefore(fin) && cantReps > 0) {
                    agregarEventoRepetidoAListaSiEstaEntreFechas(this, eRep, fechaPosibleRepeticion, inicio, fin, duracionMinutos);
                    cantReps--;
                }
            }
            fechaPosibleRepeticion = fechaPosibleRepeticion.plusDays(7);
        }
        return eRep;
    }
    private ArrayList<EventoRepetido> repeticionesEntreFechasCasoMensual(LocalDateTime inicio, LocalDateTime fin){
        long duracionMinutos = this.fechaHora.until(this.fechaHoraFin, ChronoUnit.MINUTES);
        ArrayList<EventoRepetido> eRep = new ArrayList<>();
        LocalDateTime fechaPosibleRepeticion = this.fechaHora;
        int cantReps = this.frecuencia.getCantidadRepeticiones();
        if(this.fechaHora.isBefore(inicio)){
            fechaPosibleRepeticion = inicio;
            cantReps = this.frecuencia.getCantidadRepeticiones() - (int) this.fechaHora.until(inicio, ChronoUnit.MONTHS) + 1;
        }
        if(this.frecuencia.getEsDuracionInfinita()){
            fechaPosibleRepeticion = inicio;
            cantReps = Integer.MAX_VALUE;
        }
        while(fechaPosibleRepeticion.isBefore(fin) && cantReps > 0){
            agregarEventoRepetidoAListaSiEstaEntreFechas(this, eRep, fechaPosibleRepeticion, inicio, fin, duracionMinutos);
            cantReps--;
            fechaPosibleRepeticion = fechaPosibleRepeticion.plusMonths(1);
        }
        return eRep;
    }
    private ArrayList<EventoRepetido> repeticionesEntreFechasCasoAnual(LocalDateTime inicio, LocalDateTime fin){
        long duracionMinutos = this.fechaHora.until(this.fechaHoraFin, ChronoUnit.MINUTES);
        ArrayList<EventoRepetido> eRep = new ArrayList<>();
        LocalDateTime fechaPosibleRepeticion = this.fechaHora;
        int cantReps = this.frecuencia.getCantidadRepeticiones();
        if(this.fechaHora.isBefore(inicio)){
            fechaPosibleRepeticion = inicio;
            cantReps = this.frecuencia.getCantidadRepeticiones() - (int) this.fechaHora.until(inicio, ChronoUnit.YEARS);
        }
        if(this.frecuencia.getEsDuracionInfinita()){
            fechaPosibleRepeticion = inicio;
            cantReps = Integer.MAX_VALUE;
        }
        while(fechaPosibleRepeticion.isBefore(fin) && cantReps > 0){
            agregarEventoRepetidoAListaSiEstaEntreFechas(this, eRep,fechaPosibleRepeticion, inicio, fin, duracionMinutos);
            cantReps--;
            fechaPosibleRepeticion = fechaPosibleRepeticion.plusYears(1);
        }
        return eRep;
    }
}

