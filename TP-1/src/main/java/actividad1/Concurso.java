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
    public int asignarPuntaje(LocalDate fechaInscripcion){
        return fechaInicio.isEqual(fechaInscripcion) ? 10 : 0;
    }
    public int puntosGanados(Participante participante){ //el "::" se utiliza para crear una referencia al metodo de Inscripcion
        return this.inscriptos.stream()
                .filter(i -> i.existeParticipante(participante)).findFirst().map(Inscripcion::obtenerPuntaje).orElse(0);
    }
}
