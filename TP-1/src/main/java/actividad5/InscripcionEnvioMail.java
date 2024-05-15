package actividad5;

import java.time.LocalDate;

public class InscripcionEnvioMail implements InscripcionDecorator{
    private InscripcionDecorator inscripcionDecorator;
    private RegistroDeInscripcion notificacionMail;

    public InscripcionEnvioMail(InscripcionDecorator inscripcionDecorator, RegistroDeInscripcion inscripcionMail) {
        this.inscripcionDecorator = inscripcionDecorator;
        this.notificacionMail = inscripcionMail;
    }

    @Override
    public void inscribir(Participante participante, Concurso concurso, LocalDate fechaInscripcion) {
        inscripcionDecorator.inscribir(participante, concurso, fechaInscripcion);
        this.notificacionMail.registrar(fechaInscripcion, participante, concurso);
    }
}
