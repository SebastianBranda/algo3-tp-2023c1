import java.time.LocalDateTime;

public class Tarea extends Actividad{
    private Boolean estaCompletada;

    public Tarea(String titulo,
                 String descripcion,
                 LocalDateTime fechaHora,
                 Boolean esActividadDelDia,
                 Boolean estaCompletada){
        this.setTitulo(titulo);
        this.setDescripcion(descripcion);
        this.setFechaHora(fechaHora);
        this.setEsActividadDelDia(esActividadDelDia);
        this.estaCompletada = estaCompletada;
    }
    public void setEstaCompletada(Boolean b) {
        this.estaCompletada = b;
    }

    public void modificar(String titulo,
                          String descripcion,
                          LocalDateTime fechaHora,
                          Boolean esActividadDelDia,
                          Boolean estaCompletada){
        this.setTitulo(titulo);
        this.setDescripcion(descripcion);
        this.setFechaHora(fechaHora);
        this.setEsActividadDelDia(esActividadDelDia);
        this.estaCompletada = estaCompletada;
    }

}
