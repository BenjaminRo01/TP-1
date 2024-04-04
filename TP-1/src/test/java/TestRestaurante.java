import actividad2.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.MasterDetailRecord;
import persistencia.EnDiscoRegistroCostos;
import persistencia.FakeProveedorFecha;
import persistencia.FakeRegistroCostos;

import static org.junit.jupiter.api.Assertions.*;

public class TestRestaurante {
    @Test
    public void testVisa(){
        //String path = "C:/Registros/Costos.txt";
        Pedido pedido = new Pedido();
        pedido.agregarProducto(new Bebida("Coca-Cola", 2, 1.50));
        pedido.agregarProducto(new Bebida("Agua Mineral", 1, 1.00));
        pedido.agregarProducto(new Plato("Hamburguesa Especial", 2, 5.99));
        pedido.agregarProducto(new Plato("Pizza Margarita", 1, 7.99));
        pedido.agregarProducto(new Plato("Ensalada César", 1, 4.99));
        pedido.agregarProducto(new Plato("Tarta de Queso", 2, 3.50));
        Visa tarjetaVisa = new Visa(3);
        double propina = 3.00; // 3%

        //Actividad 2
        //double costo = Dispositivo.realizarPago(pedido, tarjetaVisa, propina, new EnDiscoRegistroCostos(path), new FakeProveedorFecha());
        //assertEquals(costo, 36.92);

        //Actividad 5
        FakeRegistroCostos fakeRegistroCostos = new FakeRegistroCostos();
        String str = "01/04/2024 || 36.92";
        Dispositivo.realizarPago(pedido, tarjetaVisa, propina, fakeRegistroCostos, new FakeProveedorFecha());

        assertTrue(fakeRegistroCostos.startWith(str));
    }
    @Test
    public void testMastercard(){
        //String path = "C:/Registros/Costos.txt";
        Pedido pedido = new Pedido();
        pedido.agregarProducto(new Bebida("Coca-Cola", 2, 1.50));
        pedido.agregarProducto(new Bebida("Agua Mineral", 1, 1.00));
        pedido.agregarProducto(new Plato("Hamburguesa Especial", 2, 5.99));
        pedido.agregarProducto(new Plato("Pizza Margarita", 1, 7.99));
        pedido.agregarProducto(new Plato("Ensalada César", 1, 4.99));
        pedido.agregarProducto(new Plato("Tarta de Queso", 2, 3.50));
        Mastercard tarjetaMastercard = new Mastercard(2);
        double propina = 3.00;

        //Actividad 2
        //double costo = Dispositivo.realizarPago(pedido, tarjetaMastercard, propina, new EnDiscoRegistroCostos(path), new FakeProveedorFecha());
        //assertEquals(costo, 36.38);

        //Actividad 5
        FakeRegistroCostos fakeRegistroCostos = new FakeRegistroCostos();
        String str = "01/04/2024 || 36.38";
        Dispositivo.realizarPago(pedido, tarjetaMastercard, propina, fakeRegistroCostos, new FakeProveedorFecha());

        assertTrue(fakeRegistroCostos.startWith(str));
    }
    @Test
    public void testComarcaplus(){
        //String path = "C:/Registros/Costos.txt";
        Pedido pedido = new Pedido();
        pedido.agregarProducto(new Bebida("Coca-Cola", 2, 1.50));
        pedido.agregarProducto(new Bebida("Agua Mineral", 1, 1.00));
        pedido.agregarProducto(new Plato("Hamburguesa Especial", 2, 5.99));
        pedido.agregarProducto(new Plato("Pizza Margarita", 1, 7.99));
        pedido.agregarProducto(new Plato("Ensalada César", 1, 4.99));
        pedido.agregarProducto(new Plato("Tarta de Queso", 2, 3.50));
        Comarcaplus tarjetaComarcaPlus = new Comarcaplus(2);
        double propina = 3.00;

        //Actividad 2
        //double costo = Dispositivo.realizarPago(pedido, tarjetaComarcaPlus, propina, new EnDiscoRegistroCostos(path), new FakeProveedorFecha());
        //assertEquals(costo, 36.3);

        //Actividad 5
        FakeRegistroCostos fakeRegistroCostos = new FakeRegistroCostos();
        String str = "01/04/2024 || 36.3";
        Dispositivo.realizarPago(pedido, tarjetaComarcaPlus, propina, fakeRegistroCostos, new FakeProveedorFecha());

        assertTrue(fakeRegistroCostos.startWith(str));
    }
    @Test
    public void testViedma(){
        //String path = "C:/Registros/Costos.txt";
        Pedido pedido = new Pedido();
        pedido.agregarProducto(new Bebida("Coca-Cola", 2, 1.50));
        pedido.agregarProducto(new Bebida("Agua Mineral", 1, 1.00));
        pedido.agregarProducto(new Plato("Hamburguesa Especial", 2, 5.99));
        pedido.agregarProducto(new Plato("Pizza Margarita", 1, 7.99));
        pedido.agregarProducto(new Plato("Ensalada César", 1, 4.99));
        pedido.agregarProducto(new Plato("Tarta de Queso", 2, 3.50));
        TarjetaCredito otraTarjeta = new TarjetaCredito(0);
        double propina = 3.00;

        //Actividad 2
        //double costo = Dispositivo.realizarPago(pedido, otraTarjeta, propina, new EnDiscoRegistroCostos(path), new FakeProveedorFecha());
        //assertEquals(costo, 37.04);

        //Actividad 5
        FakeRegistroCostos fakeRegistroCostos = new FakeRegistroCostos();
        String str = "01/04/2024 || 37.04";
        Dispositivo.realizarPago(pedido, otraTarjeta, propina, fakeRegistroCostos, new FakeProveedorFecha());

        assertTrue(fakeRegistroCostos.startWith(str));
    }

}
