import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoTest {
    @Test
    public void listaDeAlarmasVacia(){
        Evento e = new Evento("t",
                "d",
                LocalDateTime.now(),
                LocalDateTime.of(2050, 01, 01, 01, 01),
                true,
                new FrecuenciaAnual(LocalDateTime.now()),
                TipoFrecuencia.ANUAL,
                false
                );
        Assert.assertEquals(0, e.alarmas.size());
    }

    @Test
    public void nuevaAlarmaAgregaAListaDeAlarmas(){
        Evento e = new Evento("t",
                "d",
                LocalDateTime.now(),
                LocalDateTime.of(2050, 01, 01, 01, 01),
                true,
                new FrecuenciaAnual(LocalDateTime.now()),
                TipoFrecuencia.ANUAL,
                false
        );
        Alarma alarma= new AlarmaEmail(LocalDateTime.now(), TipoAlarma.EMAIL);
        e.agregarAlarma(alarma);
        Assert.assertEquals(1, e.alarmas.size());
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
}
