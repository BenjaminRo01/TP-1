package model;

public class Comarcaplus extends TarjetaCredito{
    public Comarcaplus(double descuento){
        super(descuento);
    }
    @Override
    public double pago(Pedido pedido, double propina) {
        double costoFinal = pedido.obtenerPrecioTotal();
        costoFinal -= calcularDescuento(costoFinal);
        costoFinal += calcularPropina(costoFinal, propina);
        costoFinal = Math.round(costoFinal * 100d) / 100d;
        return costoFinal;
    }
}
