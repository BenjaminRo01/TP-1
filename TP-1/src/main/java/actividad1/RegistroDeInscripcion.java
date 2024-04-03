package actividad1;

import java.time.LocalDate;

public interface RegistroDeInscripcion {
    void registrar(LocalDate fecha, Participante participante, Concurso concurso);
}
