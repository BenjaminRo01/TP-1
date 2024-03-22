package actividad2;

public class Dispositivo {
    public static double realizarPago(Pedido pedido, TarjetaCredito tarjetaCredito, double propina){
        return tarjetaCredito.pago(pedido, propina);
    }
}
