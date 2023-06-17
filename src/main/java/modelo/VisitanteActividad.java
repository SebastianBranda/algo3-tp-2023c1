package modelo;

public interface VisitanteActividad {
    void visitarEvento(Evento evento);
    void visitarTarea(Tarea tarea);
    void visitarEventoRepetido(EventoRepetido eventoRepetido);
}
