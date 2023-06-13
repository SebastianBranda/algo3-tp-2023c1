import modelo.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoTest {
    @Test
    public void listaDeAlarmasVacia(){
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Evento e = new Evento("t",
                "d",
                fecha,
                LocalDateTime.of(2050, 01, 01, 01, 01),
                true,
                new FrecuenciaAnual(fecha),
                TipoFrecuencia.ANUAL,
                false
                );
        Assert.assertEquals(0, e.obtenerAlarmas().size());
    }

    @Test
    public void nuevaAlarmaAgregaAListaDeAlarmas(){
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Evento e = new Evento("t",
                "d",
                fecha,
                LocalDateTime.of(2050, 01, 01, 01, 01),
                true,
                new FrecuenciaAnual(fecha),
                TipoFrecuencia.ANUAL,
                false
        );
        Alarma alarma= new AlarmaEmail(fecha);
        e.agregarAlarma(alarma);
        Assert.assertEquals(1, e.obtenerAlarmas().size());
    }
    @Test
    public void testEventoConRepeticionesDiariasInfinitas() {
        // Se repite todos los dias desde 1990/01/01, se busca cantidad de eventos en el año 2023
        LocalDateTime fecha = LocalDateTime.of(1990, 01, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha, true, 1);
        Evento e = new Evento("t","d",fecha, fecha.plusMinutes(20), true, freq,TipoFrecuencia.DIARIA, true);

        LocalDateTime inicio2023 = LocalDateTime.of(2023, 01, 01, 01, 01);
        LocalDateTime fin2023 = LocalDateTime.of(2023, 12, 31, 23, 59);
        ArrayList<EventoRepetido> evRepetidos = e.eventosRepetidosEntreFechas(inicio2023, fin2023);

        Assert.assertEquals(365, evRepetidos.size());
    }
    @Test
    public void testEventoConRepeticionesSemanalesInfinitas() {
        // Se repite todos los dias martes y jueves desde 1990/01/01, se busca cantidad de eventos en el año 2023
        LocalDateTime fecha = LocalDateTime.of(1990, 01, 01, 01, 01);
        DayOfWeek[] dias = new DayOfWeek[]{DayOfWeek.TUESDAY, DayOfWeek.THURSDAY};
        FrecuenciaSemanal freqSem = new FrecuenciaSemanal(fecha, true, dias);
        Evento e = new Evento("t","d",fecha, fecha.plusMinutes(20), false, freqSem, TipoFrecuencia.SEMANAL, true);

        LocalDateTime inicio2023 = LocalDateTime.of(2023, 01, 01, 01, 01);
        LocalDateTime fin2023 = LocalDateTime.of(2023, 12, 31, 23, 59);
        ArrayList<EventoRepetido> evRepetidos = e.eventosRepetidosEntreFechas(inicio2023, fin2023);

        Assert.assertEquals(104, evRepetidos.size());
    }
    @Test
    public void testEventoConRepeticionesSemanalesHastaFechaDeterminada() {
        // Se repite todos los dias martes y jueves desde 1990/01/01 hasta los primeros martes y jueves de 2023
        LocalDateTime fecha = LocalDateTime.of(1990, 01, 01, 01, 01);
        LocalDateTime fechaFinal = LocalDateTime.of(2023,01, 06, 01, 01);
        DayOfWeek[] dias = new DayOfWeek[]{DayOfWeek.TUESDAY, DayOfWeek.THURSDAY};
        FrecuenciaSemanal freqSem = new FrecuenciaSemanal(fecha, fechaFinal, dias);
        Evento e = new Evento("t","d",fecha, fecha.plusMinutes(20), false, freqSem, TipoFrecuencia.SEMANAL, true);

        LocalDateTime inicio2023 = LocalDateTime.of(2023, 01, 01, 01, 01);
        LocalDateTime fin2023 = LocalDateTime.of(2023, 12, 31, 23, 59);
        ArrayList<EventoRepetido> evRepetidos = e.eventosRepetidosEntreFechas(inicio2023, fin2023);

        Assert.assertEquals(2, evRepetidos.size());
    }
    @Test
    public void testEventoConRepeticionesSemanalesHastaCantidadDeterminada() {
        // Se repite todos los dias martes y jueves desde 2023/03/01 hasta cumplir 20 repeticiones
        int cantidadRepeticiones = 20;
        LocalDateTime fecha = LocalDateTime.of(2023, 03, 01, 01, 01);
        DayOfWeek[] dias = new DayOfWeek[]{DayOfWeek.TUESDAY, DayOfWeek.THURSDAY};
        FrecuenciaSemanal freqSem = new FrecuenciaSemanal(fecha, cantidadRepeticiones, dias);
        Evento e = new Evento("t","d",fecha, fecha.plusMinutes(20), false, freqSem, TipoFrecuencia.SEMANAL, true);

        LocalDateTime inicio2023 = LocalDateTime.of(2023, 01, 01, 01, 01);
        LocalDateTime fin2023 = LocalDateTime.of(2023, 12, 31, 23, 59);
        ArrayList<EventoRepetido> evRepetidos = e.eventosRepetidosEntreFechas(inicio2023, fin2023);

        Assert.assertEquals(cantidadRepeticiones, evRepetidos.size());
    }
    @Test
    public void testEventoConRepeticionesMensualesInfinitas() {
        // Se repite todos los meses desde 1990/01/01
        LocalDateTime fecha = LocalDateTime.of(1990, 01, 01, 01, 01);
        FrecuenciaMensual freq = new FrecuenciaMensual(fecha, true);
        Evento e = new Evento("t","d",fecha, fecha.plusMinutes(20), false, freq, TipoFrecuencia.MENSUAL, true);

        LocalDateTime inicio2023 = LocalDateTime.of(2023, 01, 01, 01, 01);
        LocalDateTime fin2023 = LocalDateTime.of(2023, 12, 31, 23, 59);
        ArrayList<EventoRepetido> evRepetidos = e.eventosRepetidosEntreFechas(inicio2023, fin2023);

        Assert.assertEquals(12, evRepetidos.size());
    }
    @Test
    public void testEventoConRepeticionesMensualesHastaFechaDeterminada() {
        // Se repite todos los meses desde 1990/01/01 hasta 2023/05/02. En el año 2023 se esperan ver 5 repeticiones
        LocalDateTime fecha = LocalDateTime.of(1990, 01, 01, 01, 01);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 05, 02, 01, 01);
        FrecuenciaMensual freq = new FrecuenciaMensual(fecha, fechaFin);
        Evento e = new Evento("t","d",fecha, fecha.plusMinutes(20), false, freq, TipoFrecuencia.MENSUAL, true);

        LocalDateTime inicio2023 = LocalDateTime.of(2023, 01, 01, 01, 01);
        LocalDateTime fin2023 = LocalDateTime.of(2023, 12, 31, 23, 59);
        ArrayList<EventoRepetido> evRepetidos = e.eventosRepetidosEntreFechas(inicio2023, fin2023);

        Assert.assertEquals(5, evRepetidos.size());
    }
    @Test
    public void testEventoConRepeticionesMensualesHastaCantidadDeterminada() {
        // Se repite todos los meses desde 2023/01/01, durante 6 repeticiones
        int cantReps = 6;
        LocalDateTime fecha = LocalDateTime.of(2023, 01, 01, 01, 01);
        FrecuenciaMensual freq = new FrecuenciaMensual(fecha, cantReps);
        Evento e = new Evento("t","d",fecha, fecha.plusMinutes(20), false, freq, TipoFrecuencia.MENSUAL, true);

        LocalDateTime inicio2023 = LocalDateTime.of(2023, 01, 01, 01, 01);
        LocalDateTime fin2023 = LocalDateTime.of(2023, 12, 31, 23, 59);
        ArrayList<EventoRepetido> evRepetidos = e.eventosRepetidosEntreFechas(inicio2023, fin2023);

        Assert.assertEquals(cantReps, evRepetidos.size());
    }
    @Test
    public void testEventoConRepeticionAnualInfinita() {
        // Se repite todos los años el 18 de diciembre
        LocalDateTime fecha = LocalDateTime.of(2022, 12, 18, 01, 01);
        FrecuenciaAnual freq = new FrecuenciaAnual(fecha, true);
        Evento e = new Evento("t","d",fecha, fecha.plusMinutes(20), false, freq, TipoFrecuencia.ANUAL, true);

        LocalDateTime inicio2023 = LocalDateTime.of(2023, 01, 01, 01, 01);
        LocalDateTime fin2026 = LocalDateTime.of(2026, 12, 31, 23, 59);
        ArrayList<EventoRepetido> evRepetidos = e.eventosRepetidosEntreFechas(inicio2023, fin2026);

        Assert.assertEquals(4, evRepetidos.size());
    }
    @Test
    public void testEventoConRepeticionAnualHastaFechaDeterminada() {
        // Se repite desde 2023/12/18 hasta 2026/07/19
        LocalDateTime fecha = LocalDateTime.of(2022, 12, 18, 01, 01);
        LocalDateTime fechaFin = LocalDateTime.of(2026, 07, 19, 01, 01);
        FrecuenciaAnual freq = new FrecuenciaAnual(fecha, fechaFin);
        Evento e = new Evento("t","d",fecha, fecha.plusMinutes(20), false, freq, TipoFrecuencia.ANUAL, true);

        LocalDateTime inicio2023 = LocalDateTime.of(2023, 01, 01, 01, 01);
        LocalDateTime fin2050 = LocalDateTime.of(2050, 12, 31, 23, 59);
        ArrayList<EventoRepetido> evRepetidos = e.eventosRepetidosEntreFechas(inicio2023, fin2050);

        Assert.assertEquals(3, evRepetidos.size());
    }
    @Test
    public void testEventoConRepeticionAnualHastaCantidadRepeticiones() {
        // Se repite desde 2023/12/18 durante 4 repeticiones
        int cantReps = 4;
        LocalDateTime fecha = LocalDateTime.of(2023, 12, 18, 01, 01);
        FrecuenciaAnual freq = new FrecuenciaAnual(fecha, cantReps);
        Evento e = new Evento("t","d",fecha, fecha.plusMinutes(20), false, freq, TipoFrecuencia.ANUAL, true);

        LocalDateTime inicio2023 = LocalDateTime.of(2023, 01, 01, 01, 01);
        LocalDateTime fin2050 = LocalDateTime.of(2050, 12, 31, 23, 59);
        ArrayList<EventoRepetido> evRepetidos = e.eventosRepetidosEntreFechas(inicio2023, fin2050);

        Assert.assertEquals(cantReps, evRepetidos.size());
    }
}
