package actividad2;

public class Visa extends TarjetaCredito{
    //se podría crear una variable "descuento"?
    //Se podría crear un metodo "aplicarDescuento"?
    public Visa(double descuento){
        super(descuento);
    }
    @Override
    public double pago(Pedido pedido, double propina) {
        double costoPlatos = pedido.obtenerPrecioTotalPlatos();
        double costoBebidas = pedido.obtenerPrecioTotalBebidas();
        double costoFinal = costoPlatos + costoBebidas;
        costoFinal -= calcularDescuento(costoBebidas);
        costoFinal += calcularPropina(costoFinal, propina);
        costoFinal = Math.round(costoFinal * 100d) / 100d;
        return costoFinal;
    }
}
