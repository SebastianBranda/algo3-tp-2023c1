import modelo.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CalendarioPersistenciaTest {

    @Test
    public void persistenciaTestEvento() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento evento = new Evento("Test modelo.Evento", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEvento.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();
                Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.obtenerTitulo(), eventoCargado.obtenerTitulo());
        Assert.assertEquals(evento.obtenerDescripcion(), eventoCargado.obtenerDescripcion());
    }

    @Test
    public void persistenciaTestEventoConAlarma() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento evento = new Evento("Test modelo.Evento", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);
        Alarma alarma= new AlarmaEmail(fecha);
        evento.agregarAlarma(alarma);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEvento.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.obtenerTitulo(), eventoCargado.obtenerTitulo());
        Assert.assertEquals(evento.obtenerDescripcion(), eventoCargado.obtenerDescripcion());
        Assert.assertEquals(evento.obtenerAlarmas().get(0).getHorarioAlarma(), eventoCargado.obtenerAlarmas().get(0).getHorarioAlarma());
    }

    @Test
    public void persistenciaTestEventoDeDiaCompletoFrecDiaria() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento evento = new Evento("Test modelo.Evento", "Esto es un evento de dia completo frecuencia diaria", fecha, fecha.plusMinutes(30), true, freq, TipoFrecuencia.DIARIA, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEventoDDCFD.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.obtenerTitulo(), eventoCargado.obtenerTitulo());
        Assert.assertEquals(evento.obtenerDescripcion(), eventoCargado.obtenerDescripcion());
    }

    @Test
    public void persistenciaTestEventoDeDiaCompletoFrecSemanal() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        DayOfWeek[] dias = new DayOfWeek[]{DayOfWeek.MONDAY};
        FrecuenciaSemanal freq = new FrecuenciaSemanal(fecha, dias);
        Evento evento = new Evento("Test modelo.Evento ", "Esto es un evento de dia completo frecuencia semanal", fecha, fecha.plusMinutes(30), true, freq, TipoFrecuencia.SEMANAL, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEventoDDCFS.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.obtenerTitulo(), eventoCargado.obtenerTitulo());
        Assert.assertEquals(evento.obtenerDescripcion(), eventoCargado.obtenerDescripcion());
    }

    @Test
    public void persistenciaTestEventoDeDiaCompletoFrecMensual() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaMensual freq = new FrecuenciaMensual(fecha);
        Evento evento = new Evento("Test modelo.Evento ", "Esto es un evento de dia completo frecuencia mensual", fecha, fecha.plusMinutes(30), true, freq, TipoFrecuencia.MENSUAL, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEventoDDCFM.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.obtenerTitulo(), eventoCargado.obtenerTitulo());
        Assert.assertEquals(evento.obtenerDescripcion(), eventoCargado.obtenerDescripcion());
    }


    @Test
    public void persistenciaTestEventoDeDiaCompletoFrecAnual() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaAnual freq = new FrecuenciaAnual(fecha);
        Evento evento = new Evento("Test modelo.Evento ", "Esto es un evento de dia completo frecuencia anual", fecha, fecha.plusMinutes(30), true, freq, TipoFrecuencia.ANUAL, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEventoDDCFA.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.obtenerTitulo(), eventoCargado.obtenerTitulo());
        Assert.assertEquals(evento.obtenerDescripcion(), eventoCargado.obtenerDescripcion());
    }


    @Test
    public void persistenciaTestTareaDelDiaCompleta() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test modelo.Tarea", "Esto es una tarea del dia completa", fecha, true, true);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTareaDDC.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();

        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.obtenerTitulo(), tareaCargada.obtenerTitulo());
        Assert.assertEquals(tarea.obtenerDescripcion(), tareaCargada.obtenerDescripcion());
    }

    @Test
    public void persistenciaTestTareaDelDiaNoCompleta() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test modelo.Tarea", "Esto es una tarea del dia no completa", fecha, true, false);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTareaDDNC.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();
        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.obtenerTitulo(), tareaCargada.obtenerTitulo());
        Assert.assertEquals(tarea.obtenerDescripcion(), tareaCargada.obtenerDescripcion());
    }

    @Test
    public void persistenciaTestTareaNoDelDiaCompleta() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test modelo.Tarea", "Esto es una tarea no del dia completa", fecha, false, true);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTareaNDDC.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();
        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.obtenerTitulo(), tareaCargada.obtenerTitulo());
        Assert.assertEquals(tarea.obtenerDescripcion(), tareaCargada.obtenerDescripcion());
    }

    @Test
    public void persistenciaTestTareaNoDelDiaNoCompleta() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test modelo.Tarea", "Esto es una tarea no del dia no completa", fecha, false, false);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTareaNDLNC.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();
        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.obtenerTitulo(), tareaCargada.obtenerTitulo());
        Assert.assertEquals(tarea.obtenerDescripcion(), tareaCargada.obtenerDescripcion());
    }

    @Test
    public void persistenciaTestTareaDelDiaNoCompletaConAlarma() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test modelo.Tarea", "Esto es una tarea no del dia no completa", fecha, false, false);
        Alarma alarma= new AlarmaEmail(fecha);
        tarea.agregarAlarma(alarma);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTarearNDLNCCA.txt";

        calendario.guardarActividades(nombreArchivo);
        calendario.cargarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.obtenerTodasActividades();
        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.obtenerTitulo(), tareaCargada.obtenerTitulo());
        Assert.assertEquals(tarea.obtenerDescripcion(), tareaCargada.obtenerDescripcion());
        Assert.assertEquals(tarea.obtenerAlarmas().get(0).getHorarioAlarma(), tareaCargada.obtenerAlarmas().get(0).getHorarioAlarma());
    }

}
