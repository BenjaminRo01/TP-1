package actividad5;

import java.time.LocalDate;

public interface InscripcionDecorator {
    void inscribir(Participante participante, Concurso concurso, LocalDate fechaInscripcion);
}
