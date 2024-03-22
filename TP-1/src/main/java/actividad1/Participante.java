package actividad1;

import java.util.Objects;

public class Participante {
    private String nombre;
    private String dni;

    public Participante(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
