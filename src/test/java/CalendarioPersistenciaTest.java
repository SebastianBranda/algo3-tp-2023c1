import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CalendarioPersistenciaTest {

    @Test
    public void persistenciaTest() {
        Calendario calendario = new Calendario();
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        FrecuenciaDiaria freq = new FrecuenciaDiaria(fecha);
        Evento evento = new Evento("Test Evento", "Esto es un evento", fecha, fecha.plusMinutes(30), false, freq, TipoFrecuencia.DIARIA, false);
        calendario.agregarEvento(evento);
        String nombreArchivo = "test.txt";

        calendario.guardarActividades(nombreArchivo);
        ArrayList<Actividad> actividadesCargadas = calendario.cargarActividades(nombreArchivo);
        Actividad eventoCargado = actividadesCargadas.get(0);

        Assert.assertEquals(evento.titulo, eventoCargado.titulo);
        Assert.assertEquals(evento.descripcion, eventoCargado.descripcion);
        Assert.assertEquals(evento.fechaHora, eventoCargado.fechaHora);
        Assert.assertEquals(evento.esActividadDelDia, eventoCargado.esActividadDelDia);
    }
}