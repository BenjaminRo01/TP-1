package actividad2;

public class Mastercard extends TarjetaCredito{
    public Mastercard(double descuento){
        super(descuento);
    }
    @Override
    public double pago(Pedido pedido, double propina) {
        double costoBebidas = pedido.obtenerPrecioTotalBebidas();
        double costoPlatos = pedido.obtenerPrecioTotalPlatos();
        double costoFinal = costoPlatos + costoBebidas;
        costoFinal -= calcularDescuento(costoPlatos);
        costoFinal += calcularPropina(costoFinal, propina);
        costoFinal = Math.round(costoFinal * 100d) / 100d;
        return costoFinal;
    }
}
