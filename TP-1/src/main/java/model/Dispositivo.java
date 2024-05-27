package model;

import java.util.List;

public class Dispositivo extends Observable{
    private RegistroDeCostos registro;
    public Dispositivo(RegistroDeCostos registro, List<Observer> observadores) {
        this.registro = registro;
        for (Observer o : observadores){
            this.agregarObservadores(o);
        }
    }

    public void realizarPago(Pedido pedido, TarjetaCredito tarjetaCredito, double propina,
                             ProveedorFecha fecha){
        double costo = tarjetaCredito.pago(pedido, propina);
        registro.registrar(fecha.fecha(), costo);
        this.notificarAObservadores(costo);
    }
}
