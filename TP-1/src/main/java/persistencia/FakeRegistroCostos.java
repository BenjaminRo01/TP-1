package persistencia;

import actividad2.RegistroDeCostos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FakeRegistroCostos implements RegistroDeCostos {
    private String content;
    @Override
    public void registrar(LocalDate fecha, Double costo) {
        String registro = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(fecha) + " || " + costo;
        this.content = registro;
    }
    public boolean startWith(String str) {
        return this.content.startsWith(str);
    }
}
