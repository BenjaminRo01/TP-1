package persistencia;

import actividad1.Concurso;
import actividad1.Participante;
import actividad1.RegistroDeInscripcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FakeRegistroInscripcion implements RegistroDeInscripcion {
    private String content;
    @Override
    public void registrar(LocalDate fecha, Participante participante, Concurso concurso) {
        String registro = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(fecha)
                + " || " + participante.obtenerNombre()
                + " || " + concurso.obtenerNombre();
        this.content = registro;
    }
    public boolean startWith(String str){
        return this.content.startsWith(str);
    }
}
