package model;

public class TarjetaCredito {
    protected double descuento;
    public TarjetaCredito(double descuento){
        this.descuento = descuento;
    }
    public double pago(Pedido pedido, double propina){
        double costoFinal = pedido.obtenerPrecioTotal();
        costoFinal += calcularPropina(costoFinal, propina);
        costoFinal = Math.round(costoFinal * 100d) / 100d;
        return costoFinal;
    }
    protected double calcularPropina(double costo, double propina){
        return costo * (propina / 100d);
    }
    protected double calcularDescuento(double costo){
        return costo * (this.descuento / 100d);
    }
}
