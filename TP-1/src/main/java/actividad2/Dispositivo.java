package actividad2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dispositivo {
    public static double realizarPago(Pedido pedido, TarjetaCredito tarjetaCredito, double propina, RegistroDeCostos registro, ProveedorFecha fecha){
        double costo = tarjetaCredito.pago(pedido, propina);
        registro.registrar(fecha.fecha(), costo);
        return costo;
    }
}
