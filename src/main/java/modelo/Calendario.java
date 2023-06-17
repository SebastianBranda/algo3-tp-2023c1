package modelo;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Calendario {
    private List<Evento> eventos;
    private List<Tarea> tareas;
    public Calendario(){
        this.eventos = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }
    public void agregarEvento(Evento evento){
        this.eventos.add(evento);
    }
    public void agregarTarea(Tarea tarea){
        this.tareas.add(tarea);
    }
    public void modificarEvento(Evento eViejo, Evento eNuevo){
            if(this.eventos.contains(eViejo)){
                int index = this.eventos.indexOf(eViejo);
                this.eventos.set(index, eNuevo);
            }else{
                this.agregarEvento(eNuevo);
            }
    }
    public void modificarTarea(Tarea tVieja, Tarea tNueva){
        if(this.tareas.contains(tVieja)){
            int index = this.tareas.indexOf(tVieja);
            this.tareas.set(index, tNueva);
        }else{
            this.agregarTarea(tNueva);
        }
    }
    public void eliminarEvento(Evento evento){
        this.eventos.remove(evento);
    }
    public void eliminarTarea(Tarea tarea){
        this.tareas.remove(tarea);
    }

    public ArrayList<Alarma> proximasAlarmas(LocalDateTime time){
        ArrayList<Alarma> alarmas = new ArrayList<>();
        long tiempoMinimo = Long.MAX_VALUE;
        for(var actividad: this.eventos){
            for(var alarma: actividad.alarmas){
                long tiempoParaAlarma = time.until(alarma.getHorarioAlarma(), ChronoUnit.MINUTES);
                tiempoMinimo = agregarAlarmaAListaDeAlarmasSiEsMasProxima(alarma, alarmas, tiempoParaAlarma, tiempoMinimo);
            }
        }
        for(var actividad: this.tareas){
            for(var alarma: actividad.alarmas){
                long tiempoParaAlarma = time.until(alarma.getHorarioAlarma(), ChronoUnit.MINUTES);
                tiempoMinimo = agregarAlarmaAListaDeAlarmasSiEsMasProxima(alarma, alarmas, tiempoParaAlarma, tiempoMinimo);
            }
        }
        return alarmas;
    }
    private long agregarAlarmaAListaDeAlarmasSiEsMasProxima(Alarma alarma, ArrayList<Alarma> lista, long tiempoParaAlarma, long tiempoMinimo){
        if( tiempoParaAlarma >= 0 && tiempoParaAlarma <= tiempoMinimo ){
            if(tiempoParaAlarma < tiempoMinimo){
                lista.clear();
            }
            lista.add(alarma);
            return tiempoParaAlarma;
        }
        return tiempoMinimo;
    }

    public ArrayList<Actividad> obtenerActividadesDelDia(LocalDateTime fecha){
        ArrayList<Actividad> actividadesDelDia = new ArrayList<>();
        LocalDateTime inicio = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth(), 0, 0);
        LocalDateTime fin = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth(), 23, 59);
        for(var evento: eventos){
            actividadesDelDia.addAll(evento.eventosRepetidosEntreFechas(inicio, fin));
        }
        for(var tarea: tareas){
            actividadesDelDia.addAll(tarea.obtenerTareaEntreFechas(inicio, fin));
        }
        return actividadesDelDia;
    }

    public ArrayList<Actividad> obtenerActividadesDeLaSemana(LocalDateTime fecha){
        ArrayList<Actividad> actividadesDeLaSemana = new ArrayList<>();
        LocalDateTime inicio = fecha.with(DayOfWeek.MONDAY);
        inicio = LocalDateTime.of(inicio.getYear(), inicio.getMonth(), inicio.getDayOfMonth(), 0, 0 );
        LocalDateTime fin = fecha.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        fin = LocalDateTime.of(fin.getYear(), fin.getMonth(), fin.getDayOfMonth(), 23, 59);
        for(var evento: eventos){
            actividadesDeLaSemana.addAll(evento.eventosRepetidosEntreFechas(inicio, fin));
        }
        for(var tarea: tareas){
            actividadesDeLaSemana.addAll(tarea.obtenerTareaEntreFechas(inicio, fin));
        }
        return actividadesDeLaSemana;
    }

    public ArrayList<Actividad> obtenerActividadesDelMes(LocalDateTime fecha){
        ArrayList<Actividad> actividadesDelMes = new ArrayList<>();
        int ultimoDiaDelMes = fecha.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        LocalDateTime inicio = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), ultimoDiaDelMes, 23, 59);
        for(var evento: eventos){
            ArrayList<EventoRepetido> eventosRepetidos = evento.eventosRepetidosEntreFechas(inicio, fin);
            if(eventosRepetidos != null) {
                actividadesDelMes.addAll(eventosRepetidos);
            }
        }
        for(var tarea: tareas){
            ArrayList<Tarea> tareas = tarea.obtenerTareaEntreFechas(inicio, fin);
            if(tareas != null) {
                actividadesDelMes.addAll(tareas);
            }
        }
        return actividadesDelMes;
    }

    public ArrayList<Actividad> obtenerTodasActividades(){
        ArrayList<Actividad> actividades = new ArrayList<>();
        for(var e: this.eventos){
            actividades.add(e);
        }
        for(var a: this.tareas){
            actividades.add(a);
        }
        return actividades;
    }

    public void guardarActividades(String nombreArchivo) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(nombreArchivo);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeObject(this.eventos);
            objectOutputStream.writeObject(this.tareas);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void cargarActividades(String nombreArchivo) {
        ArrayList<Actividad> actividades;
        try{
               FileInputStream fileInputStream = new FileInputStream(nombreArchivo);
               BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
               ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
               this.eventos = (ArrayList<Evento>) objectInputStream.readObject();
               this.tareas = (ArrayList<Tarea>) objectInputStream.readObject();
               objectInputStream.close();
        } catch (FileNotFoundException notFoundExc){
            this.guardarActividades(nombreArchivo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
