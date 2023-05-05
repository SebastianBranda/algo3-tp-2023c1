import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CalendarioTest {
    @Test
    public void listasVacias() {
        Calendario c = new Calendario();

        Assert.assertTrue(c.obtenerTodasActividades().isEmpty());
    }
    @Test
    public void listaNoVaciaAlAgregarEvento() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento ev = new Evento("Test Evento", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);

        cal.agregarEvento(ev);

        Assert.assertFalse(cal.obtenerTodasActividades().isEmpty());
    }
    @Test
    public void listaNoVaciaAlAgregarTarea() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea", "Esto es una tarea", fecha, false, false);

        cal.agregarTarea(tarea);

        Assert.assertFalse(cal.obtenerTodasActividades().isEmpty());
    }
    @Test
    public void eventoEsReemplazado(){
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        LocalDateTime fechaDeReemplazo = LocalDateTime.of(2023, 05, 02, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento ev = new Evento("Test Evento", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);
        cal.agregarEvento(ev);
        Evento evNuevo = new Evento("Test Evento Nuevo", "El evento fue reemplazado", fechaDeReemplazo, fechaDeReemplazo.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);

        cal.modificarEvento(ev, evNuevo);

        Assert.assertEquals("Test Evento Nuevo", cal.obtenerTodasActividades().get(0).titulo);
    }
    @Test
    public void tareaEsReemplazada() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea", "Esto es una tarea", fecha, false, false);
        cal.agregarTarea(tarea);
        Tarea tareaNueva = new Tarea("Test Tarea Nueva", "La tarea fue reemplazada", fecha, false, false);

        cal.modificarTarea(tarea, tareaNueva);

        Assert.assertEquals("La tarea fue reemplazada", cal.obtenerTodasActividades().get(0).descripcion);
    }
    @Test
    public void testEliminarEvento() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento ev = new Evento("Test Evento", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);
        cal.agregarEvento(ev);

        cal.eliminarEvento(ev);

        Assert.assertTrue(cal.obtenerTodasActividades().isEmpty());
    }
    @Test
    public void testEliminarTarea() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea", "Esto es una tarea", fecha, false, false);
        cal.agregarTarea(tarea);

        cal.eliminarTarea(tarea);

        Assert.assertTrue(cal.obtenerTodasActividades().isEmpty());
    }
    @Test
    public void siNoHayActividadesConAlarmasNoHayProximasAlarmas() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Assert.assertEquals(0,cal.proximasAlarmas(fecha).size());
    }
    @Test
    public void agregarTareaConAlarmaGeneraProximasAlarmas() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea", "Esto es una tarea", fecha, false, false);
        AlarmaEmail a = new AlarmaEmail(fecha);

        tarea.agregarAlarma(a);
        cal.agregarTarea(tarea);

        Assert.assertFalse(cal.proximasAlarmas(fecha).isEmpty());
    }
    @Test
    public void agregarTareaConAlarmasGeneraProximasAlarmas() {
        // se agregan 3 alarmas a una tarea
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea", "Esto es una tarea", fecha, false, false);
        AlarmaEmail a = new AlarmaEmail(fecha);
        AlarmaSonido b = new AlarmaSonido(fecha);
        AlarmaVisual c = new AlarmaVisual(fecha);

        tarea.agregarAlarma(a);
        tarea.agregarAlarma(b);
        tarea.agregarAlarma(c);
        cal.agregarTarea(tarea);
        ArrayList<Alarma> listaAlarmas = cal.proximasAlarmas(fecha);

        Assert.assertEquals(3, listaAlarmas.size());
    }
    @Test
    public void sinActividadesNoHayActividadesDelDia() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Assert.assertEquals(0, cal.obtenerActividadesDelDia(fecha).size());
    }
    @Test
    public void sinActividadesNoHayActividadesDeLaSemana() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Assert.assertEquals(0, cal.obtenerActividadesDeLaSemana(fecha).size());
    }
    @Test
    public void sinActividadesNoHayActividadesDelMes() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Assert.assertTrue(cal.obtenerActividadesDelMes(fecha).isEmpty());
    }
    @Test
    public void agregarActividadGeneraActividadesDelDia() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento ev = new Evento("Test Evento", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);
        cal.agregarEvento(ev);

        Assert.assertEquals(1, cal.obtenerActividadesDelDia(fecha).size());
    }
    @Test
    public void agregarActividadesGeneraActividadesDeLaSemana() {
        Calendario cal = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento ev = new Evento("Evento1", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);
        // El siguiente evento se repite todos los dias de la semana
        FrecuenciaDiaria freq2 = new FrecuenciaDiaria(fecha, true, 1);
        Evento ev2 = new Evento("Evento2", "Esto es un evento2", fecha, fecha.plusMinutes(30), false, freq2, TipoFrecuencia.DIARIA, true);

        cal.agregarEvento(ev);
        cal.agregarEvento(ev2);

        Assert.assertEquals(8, cal.obtenerActividadesDeLaSemana(fecha).size());
    }
    @Test
    public void agregarActividadesGeneraActividadesDelMes() {
        // Para marzo 2023, se agrega 1 diaria de un dia, todos los lunes (4 en total) y una mensual, en total 6 actividades.
        LocalDateTime fecha = LocalDateTime.of(2023,03, 01, 12, 25);
        Calendario cal = new Calendario();
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento ev = new Evento("Evento1", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);
        // El siguiente evento se repite 1 vez por semana los lunes
        DayOfWeek[] dias = new DayOfWeek[]{DayOfWeek.MONDAY};
        FrecuenciaSemanal freq2 = new FrecuenciaSemanal(fecha, true, dias);
        Evento ev2 = new Evento("Evento2", "Esto es un evento2", fecha, fecha.plusMinutes(120), false, freq2, TipoFrecuencia.SEMANAL, true);
        // El siguiente evento se repite 1 vez por mes
        FrecuenciaMensual freq3 = new FrecuenciaMensual(fecha);
        Evento ev3 = new Evento("Evento3", "Esto es un evento3", fecha, fecha.plusMinutes(60), false, freq3, TipoFrecuencia.MENSUAL, true);

        cal.agregarEvento(ev);
        cal.agregarEvento(ev2);
        cal.agregarEvento(ev3);

        Assert.assertEquals(6, cal.obtenerActividadesDelMes(fecha).size());
    }
    @Test
    public void agregarEventoConFrecuenciaDiariaYDuracionFechaDeterminada() {
        // se agrega un evento del 10/04/2023 hasta el 20/04/2023, que se repite cada dos dias hasta fecha limite
        LocalDateTime fechaInicio = LocalDateTime.of(2023,04, 10, 12, 25);
        LocalDateTime fechaFin = LocalDateTime.of(2023,04, 20, 1, 1);
        Calendario cal = new Calendario();
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fechaInicio, fechaFin, 2);
        Evento ev = new Evento("Evento", "evento test", fechaInicio, fechaInicio.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, true);

        cal.agregarEvento(ev);
        ArrayList<Actividad> acts = cal.obtenerActividadesDelMes(fechaInicio);

        Assert.assertEquals(5, acts.size());
    }
    @Test
    public void agregarEventoConFrecuenciaDiariaYDuracionPorCantidad() {
        // se agrega un evento del 10/04/2023, que se repite 20 veces
        LocalDateTime fechaInicio = LocalDateTime.of(2023,04, 1, 12, 25);
        int cantidadReps = 20;
        Calendario cal = new Calendario();
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fechaInicio, cantidadReps, 1);
        Evento ev = new Evento("Evento", "evento test", fechaInicio, fechaInicio.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, true);

        cal.agregarEvento(ev);
        ArrayList<Actividad> acts = cal.obtenerActividadesDelMes(fechaInicio);

        Assert.assertEquals(cantidadReps, acts.size());
    }
}