package model;

import java.time.LocalDate;

public interface RegistroDeCostos {
    void registrar(LocalDate fecha, Double costo);
}
