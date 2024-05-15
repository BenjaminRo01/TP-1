package actividad5;

import java.time.LocalDate;

public interface RegistroDeInscripcion {
    void registrar(LocalDate fecha, Participante participante, Concurso concurso);
}
