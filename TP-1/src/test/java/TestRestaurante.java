import actividad2.*;
import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.MasterDetailRecord;
import persistencia.EnDiscoRegistroCostos;

import static org.junit.jupiter.api.Assertions.*;

public class TestRestaurante {
    @Test
    public void testVisa(){
        Pedido pedido = new Pedido();
        pedido.agregarProducto(new Bebida("Coca-Cola", 2, 1.50));
        pedido.agregarProducto(new Bebida("Agua Mineral", 1, 1.00));
        pedido.agregarProducto(new Plato("Hamburguesa Especial", 2, 5.99));
        pedido.agregarProducto(new Plato("Pizza Margarita", 1, 7.99));
        pedido.agregarProducto(new Plato("Ensalada César", 1, 4.99));
        pedido.agregarProducto(new Plato("Tarta de Queso", 2, 3.50));
        Visa tarjetaVisa = new Visa(3);
        double propina = 3.00; // 3%

        double costo = Dispositivo.realizarPago(pedido, tarjetaVisa, propina, new EnDiscoRegistroCostos());

        assertEquals(costo, 36.92);
    }
    @Test
    public void testMastercard(){
        Pedido pedido = new Pedido();
        pedido.agregarProducto(new Bebida("Coca-Cola", 2, 1.50));
        pedido.agregarProducto(new Bebida("Agua Mineral", 1, 1.00));
        pedido.agregarProducto(new Plato("Hamburguesa Especial", 2, 5.99));
        pedido.agregarProducto(new Plato("Pizza Margarita", 1, 7.99));
        pedido.agregarProducto(new Plato("Ensalada César", 1, 4.99));
        pedido.agregarProducto(new Plato("Tarta de Queso", 2, 3.50));
        Mastercard tarjetaMastercard = new Mastercard(2);
        double propina = 3.00;

        double costo = Dispositivo.realizarPago(pedido, tarjetaMastercard, propina, new EnDiscoRegistroCostos());

        assertEquals(costo, 36.38);
    }
    @Test
    public void testComarcaplus(){
        Pedido pedido = new Pedido();
        pedido.agregarProducto(new Bebida("Coca-Cola", 2, 1.50));
        pedido.agregarProducto(new Bebida("Agua Mineral", 1, 1.00));
        pedido.agregarProducto(new Plato("Hamburguesa Especial", 2, 5.99));
        pedido.agregarProducto(new Plato("Pizza Margarita", 1, 7.99));
        pedido.agregarProducto(new Plato("Ensalada César", 1, 4.99));
        pedido.agregarProducto(new Plato("Tarta de Queso", 2, 3.50));
        Comarcaplus tarjetaComarcaPlus = new Comarcaplus(2);
        double propina = 3.00;

        double costo = Dispositivo.realizarPago(pedido, tarjetaComarcaPlus, propina, new EnDiscoRegistroCostos());
        assertEquals(costo, 36.3);
    }
    @Test
    public void testViedma(){
        Pedido pedido = new Pedido();
        pedido.agregarProducto(new Bebida("Coca-Cola", 2, 1.50));
        pedido.agregarProducto(new Bebida("Agua Mineral", 1, 1.00));
        pedido.agregarProducto(new Plato("Hamburguesa Especial", 2, 5.99));
        pedido.agregarProducto(new Plato("Pizza Margarita", 1, 7.99));
        pedido.agregarProducto(new Plato("Ensalada César", 1, 4.99));
        pedido.agregarProducto(new Plato("Tarta de Queso", 2, 3.50));
        TarjetaCredito otraTarjeta = new TarjetaCredito(0);
        double propina = 3.00;

        double costo = Dispositivo.realizarPago(pedido, otraTarjeta, propina, new EnDiscoRegistroCostos());

        assertEquals(costo, 37.04);
    }

}
