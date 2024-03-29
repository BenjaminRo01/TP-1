package actividad1;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Inscripcion> inscriptos;

    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inscriptos = new ArrayList<>();
    }
    public void agregarInscripcion(Inscripcion inscripcion){
        this.inscriptos.add(inscripcion);
    }
    public boolean estaInscripto(Participante participante){
        return this.inscriptos.stream().anyMatch(i -> i.existeParticipante(participante));
    }
    public boolean puedeInscribirse(LocalDate fechaInscripcion){
        return fechaInscripcion.isEqual(this.fechaInicio) || fechaInscripcion.isAfter(this.fechaInicio) && fechaInscripcion.isBefore(this.fechaFin);
    }
    public void asignarPuntaje(Participante participante, LocalDate fechaInscripcion){
        int puntajeGanado = fechaInicio.isEqual(fechaInscripcion) ? 10 : 0;
        participante.sumar(puntajeGanado);
    }
    public int puntosGanados(Participante participante){ //el "::" se utiliza para crear una referencia al metodo de Inscripcion
        return participante.obtenerPuntaje();
    }

    public String obtenerNombre(){
        return this.nombre;
    }
}
