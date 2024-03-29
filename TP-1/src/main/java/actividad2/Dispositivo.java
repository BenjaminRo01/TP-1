package actividad2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dispositivo {
    public static double realizarPago(Pedido pedido, TarjetaCredito tarjetaCredito, double propina, RegistroDeCostos registro){
        double costo = tarjetaCredito.pago(pedido, propina);
        String formatoCosto = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()) + " || " + costo + "\n";
        registro.registrar(formatoCosto);
        return costo;
    }
}
