package actividad1;

import java.time.LocalDate;

public class Inscripcion {
    private Participante participante;
    private Concurso concurso;
    private LocalDate fechaInscripcion;
    private int puntaje;
    public Inscripcion(Participante participante, Concurso concurso, LocalDate fechaInscripcion, Integer puntaje) {
        this.participante = participante;
        this.concurso = concurso;
        this.fechaInscripcion = fechaInscripcion;
        this.puntaje = puntaje;
    }
    public static void inscribirAEn(Participante participante, Concurso concurso, LocalDate fechaInscripcion) {
        if(!concurso.estaInscripto(participante) && concurso.puedeInscribirse(fechaInscripcion)) {
            int p = concurso.asignarPuntaje(fechaInscripcion);
            Inscripcion inscripcion = new Inscripcion(participante, concurso, fechaInscripcion, p);
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
    public int obtenerPuntaje(){
        return this.puntaje;
    }
}
