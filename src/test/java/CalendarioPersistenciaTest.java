import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CalendarioPersistenciaTest {

    @Test
    public void persistenciaTestEvento() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento evento = new Evento("Test Evento", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEvento.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.titulo, eventoCargado.titulo);
        Assert.assertEquals(evento.descripcion, eventoCargado.descripcion);
        Assert.assertEquals(evento.fechaHora, eventoCargado.fechaHora);
        Assert.assertEquals(evento.esActividadDelDia, eventoCargado.esActividadDelDia);
    }

    @Test
    public void persistenciaTestEventoConAlarma() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento evento = new Evento("Test Evento", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);
        Alarma alarma= new AlarmaEmail(fecha);
        evento.agregarAlarma(alarma);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEvento.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.titulo, eventoCargado.titulo);
        Assert.assertEquals(evento.descripcion, eventoCargado.descripcion);
        Assert.assertEquals(evento.fechaHora, eventoCargado.fechaHora);
        Assert.assertEquals(evento.esActividadDelDia, eventoCargado.esActividadDelDia);
        Assert.assertEquals(evento.alarmas, eventoCargado.alarmas);
    }

    @Test
    public void persistenciaTestEventoDeDiaCompletoFrecDiaria() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento evento = new Evento("Test Evento", "Esto es un evento de dia completo frecuencia diaria", fecha, fecha.plusMinutes(30), true, freq, TipoFrecuencia.DIARIA, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEventoDDCFD.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.titulo, eventoCargado.titulo);
        Assert.assertEquals(evento.descripcion, eventoCargado.descripcion);
        Assert.assertEquals(evento.fechaHora, eventoCargado.fechaHora);
        Assert.assertEquals(evento.esActividadDelDia, eventoCargado.esActividadDelDia);
    }

    @Test
    public void persistenciaTestEventoDeDiaCompletoFrecSemanal() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaSemanal freq = new FrecuenciaSemanal(fecha);
        Evento evento = new Evento("Test Evento ", "Esto es un evento de dia completo frecuencia semanal", fecha, fecha.plusMinutes(30), true, freq, TipoFrecuencia.SEMANAL, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEventoDDCFS.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.titulo, eventoCargado.titulo);
        Assert.assertEquals(evento.descripcion, eventoCargado.descripcion);
        Assert.assertEquals(evento.fechaHora, eventoCargado.fechaHora);
        Assert.assertEquals(evento.esActividadDelDia, eventoCargado.esActividadDelDia);
    }

    @Test
    public void persistenciaTestEventoDeDiaCompletoFrecMensual() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaMensual freq = new FrecuenciaMensual(fecha);
        Evento evento = new Evento("Test Evento ", "Esto es un evento de dia completo frecuencia mensual", fecha, fecha.plusMinutes(30), true, freq, TipoFrecuencia.MENSUAL, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEventoDDCFM.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.titulo, eventoCargado.titulo);
        Assert.assertEquals(evento.descripcion, eventoCargado.descripcion);
        Assert.assertEquals(evento.fechaHora, eventoCargado.fechaHora);
        Assert.assertEquals(evento.esActividadDelDia, eventoCargado.esActividadDelDia);
    }


    @Test
    public void persistenciaTestEventoDeDiaCompletoFrecAnual() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaAnual freq = new FrecuenciaAnual(fecha);
        Evento evento = new Evento("Test Evento ", "Esto es un evento de dia completo frecuencia anual", fecha, fecha.plusMinutes(30), true, freq, TipoFrecuencia.A, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "testEventoDDCFA.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.titulo, eventoCargado.titulo);
        Assert.assertEquals(evento.descripcion, eventoCargado.descripcion);
        Assert.assertEquals(evento.fechaHora, eventoCargado.fechaHora);
        Assert.assertEquals(evento.esActividadDelDia, eventoCargado.esActividadDelDia);
    }


    @Test
    public void persistenciaTestTareaDelDiaCompleta() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea", "Esto es una tarea del dia completa", fecha, true, true);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTareaDDC.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.titulo, tareaCargada.titulo);
        Assert.assertEquals(tarea.descripcion, tareaCargada.descripcion);
        Assert.assertEquals(tarea.fechaHora, tareaCargada.fechaHora);
        Assert.assertEquals(tarea.esActividadDelDia, tareaCargada.esActividadDelDia);
        Assert.assertEquals(tarea.estaCompletada, tareaCargada.estaCompletada)
    }

    @Test
    public void persistenciaTestTareaDelDiaNoCompleta() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea", "Esto es una tarea del dia no completa", fecha, true, false);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTareaDDNC.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.titulo, tareaCargada.titulo);
        Assert.assertEquals(tarea.descripcion, tareaCargada.descripcion);
        Assert.assertEquals(tarea.fechaHora, tareaCargada.fechaHora);
        Assert.assertEquals(tarea.esActividadDelDia, tareaCargada.esActividadDelDia);
        Assert.assertEquals(tarea.estaCompletada, tareaCargada.estaCompletada)

    }

    @Test
    public void persistenciaTestTareaNoDelDiaCompleta() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea", "Esto es una tarea no del dia completa", fecha, false, true);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTareaNDDC.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.titulo, tareaCargada.titulo);
        Assert.assertEquals(tarea.descripcion, tareaCargada.descripcion);
        Assert.assertEquals(tarea.fechaHora, tareaCargada.fechaHora);
        Assert.assertEquals(tarea.esActividadDelDia, tareaCargada.esActividadDelDia);
        Assert.assertEquals(tarea.estaCompletada, tareaCargada.estaCompletada)
    }

    @Test
    public void persistenciaTestTareaNoDelDiaNoCompleta() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea", "Esto es una tarea no del dia no completa", fecha, false, false);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTareaNDLNC.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.titulo, tareaCargada.titulo);
        Assert.assertEquals(tarea.descripcion, tareaCargada.descripcion);
        Assert.assertEquals(tarea.fechaHora, tareaCargada.fechaHora);
        Assert.assertEquals(tarea.esActividadDelDia, tareaCargada.esActividadDelDia);
        Assert.assertEquals(tarea.estaCompletada, tareaCargada.estaCompletada)
    }

    @Test
    public void persistenciaTestTareaDelDiaNoCompletaConAlarma() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea", "Esto es una tarea no del dia no completa", fecha, false, false);
        Alarma alarma= new AlarmaEmail(fecha);
        tarea.agregarAlarma(alarma);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTarearNDLNCCA.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.titulo, tareaCargada.titulo);
        Assert.assertEquals(tarea.alarmas, tareaCargada.alarmas);
        Assert.assertEquals(tarea.descripcion, tareaCargada.descripcion);
        Assert.assertEquals(tarea.fechaHora, tareaCargada.fechaHora);
        Assert.assertEquals(tarea.esActividadDelDia, tareaCargada.esActividadDelDia);
        Assert.assertEquals(tarea.estaCompletada, tareaCargada.estaCompletada)
    }

}