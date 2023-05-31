import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
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
    public void tareaDiaCompleto(){
        LocalDateTime fecha = LocalDateTime.of(2023, 05, 01, 01, 01);
        Tarea t = new Tarea("t",
                "d",
                fecha,
                true,
                false
        );
        Assert.assertEquals(true, t.esActividadDelDia);
    }
}