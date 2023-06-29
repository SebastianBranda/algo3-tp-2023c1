import modelo.Alarma;
import modelo.AlarmaEmail;
import modelo.Tarea;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDateTime;

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
        Assert.assertEquals(0, t.obtenerAlarmas().size());
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
        Assert.assertEquals(1, t.obtenerAlarmas().size());
    }
}