package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Producto> productos;
    public Pedido(){
        this.productos = new ArrayList<>();
    }
    public void agregarProducto(Producto producto){
        this.productos.add(producto);
    }
    public double obtenerPrecioTotalBebidas(){
        return this.productos.stream()
                .filter(p -> p.getClass() == Bebida.class)
                .mapToDouble(Producto::obtenerPrecio)
                .sum();
    }
    public double obtenerPrecioTotalPlatos(){
        return this.productos.stream()
                .filter(p -> p.getClass() == Plato.class)
                .mapToDouble(Producto::obtenerPrecio)
                .sum();
    }
    public double obtenerPrecioTotal(){
        return this.productos.stream()
                .mapToDouble(Producto::obtenerPrecio)
                .sum();
    }
}
