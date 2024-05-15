package actividad5;

import java.time.LocalDate;

public class Inscripcion implements InscripcionDecorator{
    private Participante participante;
    private Concurso concurso;
    private LocalDate fechaInscripcion;
    private RegistroDeInscripcion registro;
    private int puntaje;
    public void inscribir(Participante participante, Concurso concurso, LocalDate fechaInscripcion){
        if (concurso.estaInscripto(participante) || !concurso.puedeInscribirse(fechaInscripcion)) {
            throw new RuntimeException("Intentas inscribirte fuera de la fecha de inscripción.");
        }
        concurso.asignarPuntaje(participante, fechaInscripcion);
        concurso.agregarInscripcion(this);
    }
    public boolean existeParticipante(Participante participante){
        return this.participante.equals(participante);
    }
}
