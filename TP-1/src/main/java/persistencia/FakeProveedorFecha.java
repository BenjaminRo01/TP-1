package persistencia;

import actividad2.ProveedorFecha;

import java.time.LocalDate;

public class FakeProveedorFecha implements ProveedorFecha {
    @Override
    public LocalDate fecha() {
        return LocalDate.of(2024,04,01);
    }
}
