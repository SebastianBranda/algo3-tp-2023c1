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
    public void persistenciaTestTareaDelDiaCompleta() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea tarea = new Tarea("Test Tarea del dia completa", "Esto es una tarea del dia completa", fecha, true, true);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTarearDelDiaCompleta.txt";

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
        Tarea tarea = new Tarea("Test Tarea del dia no completa", "Esto es una tarea del dia no completa", fecha, true, false);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTarearDelDiaNoCompleta.txt";

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
        Tarea tarea = new Tarea("Test Tarea no del dia completa", "Esto es una tarea no del dia completa", fecha, false, true);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTarearNoDelDiaCompleta.txt";

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
        Tarea tarea = new Tarea("Test Tarea no del dia no completa", "Esto es una tarea no del dia no completa", fecha, false, false);
        calendario.agregarTarea(tarea);
        String nombreArchivo = "testTarearNoDelDiaNoCompleta.txt";

        calendario.guardarActividades(nombreArchivo);
         ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad tareaCargada = actividadesCargadas.get(0);

        Assert.assertEquals(tarea.titulo, tareaCargada.titulo);
        Assert.assertEquals(tarea.descripcion, tareaCargada.descripcion);
        Assert.assertEquals(tarea.fechaHora, tareaCargada.fechaHora);
        Assert.assertEquals(tarea.esActividadDelDia, tareaCargada.esActividadDelDia);
        Assert.assertEquals(tarea.estaCompletada, tareaCargada.estaCompletada)
    }
}