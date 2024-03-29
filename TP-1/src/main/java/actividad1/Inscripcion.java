package actividad1;

import java.time.LocalDate;

public class Inscripcion {
    private Participante participante;
    private Concurso concurso;
    private LocalDate fechaInscripcion;
    private int puntaje;
    public Inscripcion(Participante participante, Concurso concurso, LocalDate fechaInscripcion) {
        this.participante = participante;
        this.concurso = concurso;
        this.fechaInscripcion = fechaInscripcion;
    }
    public static void inscribirAEn(Participante participante, Concurso concurso, LocalDate fechaInscripcion) {
        if(!concurso.estaInscripto(participante) && concurso.puedeInscribirse(fechaInscripcion)) {
            Inscripcion inscripcion = new Inscripcion(participante, concurso, fechaInscripcion);
            concurso.asignarPuntaje(participante, fechaInscripcion);
            concurso.agregarInscripcion(inscripcion);
        }
        else{
            System.out.println("Intentas inscribirte fuera de la fecha de inscripción.");
            //throw new RuntimeException("Intentas inscribirte fuera de la fecha de inscripción.");
        }
    }
    public boolean existeParticipante(Participante participante){
        return this.participante.equals(participante);
    }

}
