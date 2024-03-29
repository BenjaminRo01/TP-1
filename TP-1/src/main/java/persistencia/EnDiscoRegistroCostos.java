package persistencia;

import actividad2.RegistroDeCostos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class EnDiscoRegistroCostos implements RegistroDeCostos {
    @Override
    public void registrar(String registro) {
        try {
            Files.write(Paths.get("C:/Registros/Costos.txt"),
                    registro.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e){
            throw new RuntimeException("No se puede persistir.", e);
        }
    }
}
