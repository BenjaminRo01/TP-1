package persistencia;

import actividad1.RegistroDeInscripcion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class EnDiscoRegistroInscripcion implements RegistroDeInscripcion {
    @Override
    public void registrar(String registro) {
        try {
            Files.write(Paths.get("C:/Registros/Inscripciones.txt"),
                    registro.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e){
            throw new RuntimeException("No se puede persistir.", e);
        }
    }
}
