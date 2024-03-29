package actividad1;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.SimpleFormatter;

public class Inscripcion {
    private Participante participante;
    private Concurso concurso;
    private LocalDate fechaInscripcion;
    private RegistroDeInscripcion registro;
    private int puntaje;
    public Inscripcion(Participante participante, Concurso concurso, LocalDate fechaInscripcion,
                       RegistroDeInscripcion registro) {
        this.participante = participante;
        this.concurso = concurso;
        this.fechaInscripcion = fechaInscripcion;
        this.registro = registro; //Siempre que se inyecta dependencias se realiza por el constructor.
    }
    public static void inscribirAEn(Participante participante, Concurso concurso, LocalDate fechaInscripcion, RegistroDeInscripcion registro){
        if (concurso.estaInscripto(participante) || !concurso.puedeInscribirse(fechaInscripcion)) {
            throw new RuntimeException("Intentas inscribirte fuera de la fecha de inscripción.");
        }
        Inscripcion inscripcion = new Inscripcion(participante, concurso, fechaInscripcion, registro);
        concurso.asignarPuntaje(participante, fechaInscripcion);
        concurso.agregarInscripcion(inscripcion);

        String formatoInscripcion = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(fechaInscripcion)
                + " || " + participante.obtenerNombre()
                + " || " + concurso.obtenerNombre() + "\n";
        registro.registrar(formatoInscripcion);
    }
    public boolean existeParticipante(Participante participante){
        return this.participante.equals(participante);
    }
}
