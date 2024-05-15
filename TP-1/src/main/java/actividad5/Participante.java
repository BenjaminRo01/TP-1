package actividad5;

import java.util.Objects;

public class Participante {
    private String nombre;
    private String dni;
    private int puntaje;

    public Participante(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        this.puntaje = 0;
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

    public void sumar(int puntaje){
        this.puntaje += puntaje;
    }
    public int obtenerPuntaje(){
        return this.puntaje;
    }
    public String obtenerNombre(){
        return this.nombre;
    }
}
