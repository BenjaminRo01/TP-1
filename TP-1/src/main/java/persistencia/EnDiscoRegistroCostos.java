package persistencia;

import model.RegistroDeCostos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnDiscoRegistroCostos implements RegistroDeCostos {
    private String path;
    public EnDiscoRegistroCostos(String path){
        this.path = path;
    }
    @Override
    public void registrar(LocalDate fecha, Double costo) {
        try {
            String registro = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()) + " || " + costo + "\n";
            Files.write(Paths.get(this.path),
                    registro.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e){
            throw new RuntimeException("No se puede persistir.", e);
        }
    }
}
