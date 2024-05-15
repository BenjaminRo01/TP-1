package persistencia;

import actividad5.Concurso;
import actividad5.Participante;
import actividad5.RegistroDeInscripcion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnDiscoRegistroInscripcion implements RegistroDeInscripcion {
    private String path;
    public EnDiscoRegistroInscripcion(String path){
        this.path = path;
    }
    @Override
    public void registrar(LocalDate fecha, Participante participante, Concurso concurso) {
        try {
            String registro = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(fecha)
                    + " || " + participante.obtenerNombre()
                    + " || " + concurso.obtenerNombre() + "\n";
            Files.write(Paths.get(this.path),
                    registro.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e){
            throw new RuntimeException("No se puede persistir.", e);
        }
    }
}
