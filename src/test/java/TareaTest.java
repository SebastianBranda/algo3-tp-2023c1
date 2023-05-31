import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TareaTest {
    @Test
    public void listaDeAlarmasVacia(){
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea t = new Tarea("t",
                "d",
                fecha,
                false,
                false
                );
        Assert.assertEquals(0, t.alarmas.size());
    }

    @Test
    public void nuevaAlarmaAgregaAListaDeAlarmas(){
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea t = new Tarea("t",
                "d",
                fecha,
                false,
                false
        );
        Alarma alarma= new AlarmaEmail(fecha);
        t.agregarAlarma(alarma);
        Assert.assertEquals(1, t.alarmas.size());
    }

    @Test
    public void tareaFechaHoraDiaCompletoNoCompletada(){
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        fechaHoraList = [];
        Tarea t = new Tarea("t",
                "d",
                fecha,
                true,
                false
        );
        fechaHoraList.append(fecha);
        Assert.assertEquals(1 , fechaHoraList.size());
        Assert.assertEquals(true, t.esActividadDelDia);
        Assert.assertEquals(false, t.estaCompletada);
    }

    @Test
    public void tareaFechaHoraDiaCompletoCompletada(){
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        fechaHoraList = [];
        Tarea t = new Tarea("t",
                "d",
                fecha,
                true,
                true
        );
        fechaHoraList.append(fecha);
        Assert.assertEquals(1 , fechaHoraList.size());
        Assert.assertEquals(true, t.esActividadDelDia);
        Assert.assertEquals(true, t.estaCompletada);

    }


    @Test
    public void tareaFechaHoraNoDelDiaCompletada(){
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        fechaHoraList = []
        Tarea t = new Tarea("t",
                "d",
                fecha,
                false,
                true
        );
        fechaHoraList.append(fecha);
        Assert.assertEquals(1 , fechaHoraList.size());
        Assert.assertEquals(false, t.esActividadDelDia);
        Assert.assertEquals(true, t.estaCompletada);
    }

    @Test
    public void tareaFechaHoraNoDelDiaNoCompletada(){
        fechaHoraList = [];
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea t = new Tarea("t",
                "d",
                fecha,
                false,
                false
        );
        fechaHoraList.append(fecha);
        Assert.assertEquals(1 , fechaHoraList.size());
        Assert.assertEquals(false, t.esActividadDelDia);
        Assert.assertEquals(false, t.estaCompletada);
    }



}
