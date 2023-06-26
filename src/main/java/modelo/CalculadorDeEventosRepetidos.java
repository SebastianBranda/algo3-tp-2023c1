package modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CalculadorDeEventosRepetidos {
    private CalculadorDeEventosRepetidos(){}
    public static ArrayList<EventoRepetido> repeticionesEntreFechasCasoDiario(Evento evento, LocalDateTime finEvento, Frecuencia frecuencia, LocalDateTime inicio, LocalDateTime fin){
        ArrayList<EventoRepetido> eventosRepetidos = new ArrayList<>();
        LocalDateTime inicioEvento = evento.obtenerFecha();
        long duracionMinutosDelEvento = inicioEvento.until(finEvento, ChronoUnit.MINUTES);
        int reglaRepeticionDias = frecuencia.reglaDeRepeticion().get(0);
        agregarEventoRepetidoAListaSiEstaEntreFechas(evento, eventosRepetidos, inicioEvento, inicio, fin, duracionMinutosDelEvento);
        if(frecuencia.getEsDuracionInfinita()){
            agregarEventoRepetidoAListaCasoDiarioDuracionInfinita(evento, eventosRepetidos, inicio, fin, duracionMinutosDelEvento, reglaRepeticionDias);
        }else{
            agregarEventoRepetidoAListaCasoDiarioDuracionFinita(evento, eventosRepetidos, inicio, fin, frecuencia, duracionMinutosDelEvento, reglaRepeticionDias);
        }
        return eventosRepetidos;
    }

    private static void agregarEventoRepetidoAListaCasoDiarioDuracionInfinita(Evento evento, ArrayList<EventoRepetido> eventosRepetidos, LocalDateTime inicio, LocalDateTime fin, long duracionEvento, int reglaRepeticionDias){
        LocalDateTime inicioEvento = evento.obtenerFecha();
        LocalDateTime fechaRepeticion = inicioEvento.minusDays(reglaRepeticionDias);
        while(fechaRepeticion.isAfter(inicio)){
            agregarEventoRepetidoAListaSiEstaEntreFechas(evento, eventosRepetidos, fechaRepeticion, inicio, fin, duracionEvento);
            fechaRepeticion = fechaRepeticion.minusDays(reglaRepeticionDias);
        }
        fechaRepeticion = inicioEvento.plusDays(reglaRepeticionDias);
        while(fechaRepeticion.isBefore(fin)){
            agregarEventoRepetidoAListaSiEstaEntreFechas(evento, eventosRepetidos, fechaRepeticion, inicio, fin, duracionEvento);
            fechaRepeticion = fechaRepeticion.plusDays(reglaRepeticionDias);
        }
    }
    private static void agregarEventoRepetidoAListaCasoDiarioDuracionFinita(Evento evento, ArrayList<EventoRepetido> eventosRepetidos, LocalDateTime inicio, LocalDateTime fin, Frecuencia frecuencia, long duracionEvento, int reglaRepeticionDias){
        LocalDateTime inicioEvento = evento.obtenerFecha();
        LocalDateTime fechaRepeticion = inicioEvento.plusDays(reglaRepeticionDias);
        int cantReps = frecuencia.getCantidadRepeticiones();
        while(fechaRepeticion.isBefore(fin) && cantReps >0){
            agregarEventoRepetidoAListaSiEstaEntreFechas(evento, eventosRepetidos, fechaRepeticion, inicio, fin, duracionEvento);
            fechaRepeticion = fechaRepeticion.plusDays(reglaRepeticionDias);
            cantReps--;
        }
    }
    public static ArrayList<EventoRepetido> repeticionesEntreFechasCasoSemanal(Evento evento, LocalDateTime finEvento, Frecuencia frecuencia, LocalDateTime inicio, LocalDateTime fin){
        ArrayList<EventoRepetido> eRep = new ArrayList<>();
        LocalDateTime inicioEvento = evento.obtenerFecha();
        long duracionMinutos = inicioEvento.until(finEvento, ChronoUnit.MINUTES);
        LocalDateTime fechaPosibleRepeticion = inicioEvento;
        if(frecuencia.getEsDuracionInfinita()){
            fechaPosibleRepeticion = inicio;
        }
        int cantReps = calcularCantidadDeRepeticionesCasoSemanal(evento, inicio, frecuencia);
        int diferenciaDias;
        while(fechaPosibleRepeticion.isBefore(fin) && cantReps > 0){
            for(var diaDeRepeticion: frecuencia.reglaDeRepeticion()){
                diferenciaDias = diaDeRepeticion - fechaPosibleRepeticion.getDayOfWeek().getValue();
                fechaPosibleRepeticion = fechaPosibleRepeticion.plusDays(diferenciaDias);
                if(fechaPosibleRepeticion.isAfter(inicio) && fechaPosibleRepeticion.isBefore(fin) && cantReps > 0) {
                    agregarEventoRepetidoAListaSiEstaEntreFechas(evento, eRep, fechaPosibleRepeticion, inicio, fin, duracionMinutos);
                    cantReps--;
                }
            }
            fechaPosibleRepeticion = fechaPosibleRepeticion.plusDays(7);
        }
        return eRep;
    }
    private static int calcularCantidadDeRepeticionesCasoSemanal(Evento evento, LocalDateTime inicio, Frecuencia frecuencia){
        int cantReps = frecuencia.getCantidadRepeticiones();
        LocalDateTime fechaEvento = evento.obtenerFecha();
        if(fechaEvento.isBefore(inicio)){
            long cantDiasNoIncluidos = frecuencia.getFechaInicial().until(inicio, ChronoUnit.DAYS);
            int cantRepsNoIncluidas = ((int) (cantDiasNoIncluidos/7)) * frecuencia.reglaDeRepeticion().size();
            cantReps = cantReps - cantRepsNoIncluidas;
        }
        if(frecuencia.getEsDuracionInfinita()){
            cantReps = Integer.MAX_VALUE;
        }
        return cantReps;
    }
    public static ArrayList<EventoRepetido> repeticionesEntreFechasCasoMensual(Evento evento, LocalDateTime finEvento, Frecuencia frecuencia, LocalDateTime inicio, LocalDateTime fin){
        ArrayList<EventoRepetido> listaEventosRepetidos = new ArrayList<>();
        LocalDateTime inicioEvento = evento.obtenerFecha();
        long duracionMinutos = inicioEvento.until(finEvento, ChronoUnit.MINUTES);
        LocalDateTime fechaPosibleRepeticion = inicioEvento;
        int cantReps = calcularCantidadDeRepeticionesCasoMensual(evento, inicio, frecuencia);
        if(inicioEvento.isBefore(inicio) || frecuencia.getEsDuracionInfinita()){
            fechaPosibleRepeticion = inicioEvento.withMonth(inicio.getMonthValue());
            fechaPosibleRepeticion = fechaPosibleRepeticion.withYear(inicio.getYear());
        }
        while(fechaPosibleRepeticion.isBefore(fin) && cantReps > 0){
            agregarEventoRepetidoAListaSiEstaEntreFechas(evento, listaEventosRepetidos, fechaPosibleRepeticion, inicio, fin, duracionMinutos);
            cantReps--;
            fechaPosibleRepeticion = fechaPosibleRepeticion.plusMonths(1);
        }
        return listaEventosRepetidos;
    }
    private static int calcularCantidadDeRepeticionesCasoMensual(Evento evento, LocalDateTime inicio, Frecuencia frecuencia){
        int cantReps = frecuencia.getCantidadRepeticiones();
        LocalDateTime inicioEvento = evento.obtenerFecha();
        if(inicioEvento.isBefore(inicio)){
            cantReps = frecuencia.getCantidadRepeticiones() - (int) inicioEvento.until(inicio, ChronoUnit.MONTHS) + 1;
        }
        if(frecuencia.getEsDuracionInfinita()){
            cantReps = Integer.MAX_VALUE;
        }
        return cantReps;
    }
    public static ArrayList<EventoRepetido> repeticionesEntreFechasCasoAnual(Evento evento, LocalDateTime finEvento, Frecuencia frecuencia, LocalDateTime inicio, LocalDateTime fin){
        ArrayList<EventoRepetido> listaEventosRepetidos = new ArrayList<>();
        LocalDateTime inicioEvento = evento.obtenerFecha();
        long duracionMinutos = inicioEvento.until(finEvento, ChronoUnit.MINUTES);

        LocalDateTime fechaPosibleRepeticion = inicioEvento;
        int cantReps = calcularCantidadDeRepeticionesCasoAnual(evento, inicio, frecuencia);
        if(inicioEvento.isBefore(inicio) || frecuencia.getEsDuracionInfinita()){
            fechaPosibleRepeticion = inicioEvento.withYear(inicio.getYear());
        }
        while(fechaPosibleRepeticion.isBefore(fin) && cantReps > 0){
            agregarEventoRepetidoAListaSiEstaEntreFechas(evento, listaEventosRepetidos,fechaPosibleRepeticion, inicio, fin, duracionMinutos);
            cantReps--;
            fechaPosibleRepeticion = fechaPosibleRepeticion.plusYears(1);
        }
        return listaEventosRepetidos;
    }
    private static int calcularCantidadDeRepeticionesCasoAnual(Evento evento, LocalDateTime inicio, Frecuencia frecuencia){
        LocalDateTime inicioEvento = evento.obtenerFecha();
        int cantReps = frecuencia.getCantidadRepeticiones();
        if(inicioEvento.isBefore(inicio)){
            cantReps = frecuencia.getCantidadRepeticiones() - (int) inicioEvento.until(inicio, ChronoUnit.YEARS);
        }
        if(frecuencia.getEsDuracionInfinita()){
            cantReps = Integer.MAX_VALUE;
        }
        return cantReps;
    }

    private static void agregarEventoRepetidoAListaSiEstaEntreFechas(Evento eventoOriginal, ArrayList<EventoRepetido> lista, LocalDateTime fechaPosibleRepeticion, LocalDateTime inicio, LocalDateTime fin, long duracionEventoOriginal){
        if( (fechaPosibleRepeticion.isAfter(inicio) || fechaPosibleRepeticion.isEqual(inicio)) && fechaPosibleRepeticion.isBefore(fin)){
            lista.add(new EventoRepetido(eventoOriginal, fechaPosibleRepeticion, fechaPosibleRepeticion.plusMinutes(duracionEventoOriginal)));
        }
    }
}
