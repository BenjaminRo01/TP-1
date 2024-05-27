package ui;

import model.*;
import persistencia.EnBBDDRegistoCostos;
import persistencia.FakeProveedorFecha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MonitorPedido extends JFrame {
    private JComboBox<String> comboBoxProducto;
    private JSpinner spinnerCantidad;
    private JTextField campoPrecio;
    private JComboBox<String> comboBoxTarjeta;
    private JComboBox<Double> comboBoxPropina;
    private DefaultListModel<String> modeloListaPedidos;
    private JList<String> listaPedidos;
    private Map<String, Double> preciosProductos;
    private Map<String, TarjetaCredito> mapaTarjetas;
    private Pedido pedido;
    private JComboBox<String> comboBoxTipo;
    private Dispositivo dispositivo;

    public MonitorPedido(Dispositivo dispositivo) {
        setTitle("Formulario de Pedido");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.pedido = new Pedido();
        this.dispositivo = dispositivo;
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel de entrada de datos
        JPanel panelEntradaDatos = new JPanel(new GridLayout(6, 2, 5, 5));

        // Inicializar precios de productos
        preciosProductos = new HashMap<>();
        preciosProductos.put("", 0.0);
        preciosProductos.put("Producto 1", 1000.0);
        preciosProductos.put("Producto 2", 2200.0);
        preciosProductos.put("Producto 3", 30000.0);
        preciosProductos.put("Producto 4", 23000.5);
        preciosProductos.put("Producto 5", 33050.0);
        preciosProductos.put("Producto 6", 29000.5);
        preciosProductos.put("Producto 7", 50350.0);
        // Inicializar tarjetas
        mapaTarjetas = new HashMap<>();
        mapaTarjetas.put("MasterCard", new Mastercard(2));
        mapaTarjetas.put("Visa", new Visa(3));
        mapaTarjetas.put("ComarcaPlus", new Comarcaplus(2));
        mapaTarjetas.put("Otro", new TarjetaCredito(0));

        // ComboBox para el tipo (Bebida o Plato)
        JLabel etiquetaTipo = new JLabel("Tipo:");
        comboBoxTipo = new JComboBox<>(new String[]{"Bebida", "Plato"});
        panelEntradaDatos.add(etiquetaTipo);
        panelEntradaDatos.add(comboBoxTipo);

        // ComboBox para el nombre del producto
        JLabel etiquetaProducto = new JLabel("Nombre del producto:");
        comboBoxProducto = new JComboBox<>(preciosProductos.keySet().toArray(new String[0]));
        comboBoxProducto.addActionListener(e -> this.actualizarPrecio());
        panelEntradaDatos.add(etiquetaProducto);
        panelEntradaDatos.add(comboBoxProducto);

        // Spinner para la cantidad
        JLabel etiquetaCantidad = new JLabel("Cantidad:");
        spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        panelEntradaDatos.add(etiquetaCantidad);
        panelEntradaDatos.add(spinnerCantidad);

        // TextField para el precio
        JLabel etiquetaPrecio = new JLabel("Precio (por unidad):");
        campoPrecio = new JTextField();
        campoPrecio.setEnabled(false);
        panelEntradaDatos.add(etiquetaPrecio);
        panelEntradaDatos.add(campoPrecio);

        panelPrincipal.add(panelEntradaDatos, BorderLayout.NORTH);

        // Panel para la lista de pedidos y botones
        JPanel panelPedidos = new JPanel(new BorderLayout());

        // Modelo y JList para mostrar los pedidos
        modeloListaPedidos = new DefaultListModel<>();
        listaPedidos = new JList<>(modeloListaPedidos);
        panelPedidos.add(new JScrollPane(listaPedidos), BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();

        // Botón para agregar producto
        JButton botonAgregar = new JButton("Agregar Producto");
        botonAgregar.addActionListener(e -> this.agregarProductoAPedido());
        panelBotones.add(botonAgregar);

        // ComboBox para seleccionar la tarjeta
        JLabel etiquetaTarjeta = new JLabel("Tarjeta:");
        comboBoxTarjeta = new JComboBox<>(mapaTarjetas.keySet().toArray(new String[0]));
        panelEntradaDatos.add(etiquetaTarjeta);
        panelEntradaDatos.add(comboBoxTarjeta);

        panelPrincipal.add(panelEntradaDatos, BorderLayout.NORTH);

        // TextField para la propina
        JLabel etiquetaPropina = new JLabel("Propina (% del total):");
        comboBoxPropina = new JComboBox<>(new Double[]{2.0,3.0,5.0});
        panelEntradaDatos.add(etiquetaPropina);
        panelEntradaDatos.add(comboBoxPropina);

        panelPrincipal.add(panelEntradaDatos, BorderLayout.NORTH);

        // Botón para pagar
        JButton botonPagar = new JButton("Pagar");
        botonPagar.addActionListener(e -> this.pagarPedido());
        panelBotones.add(botonPagar);
        panelPedidos.add(panelBotones, BorderLayout.SOUTH);
        panelPrincipal.add(panelPedidos, BorderLayout.CENTER);

        add(panelPrincipal);

        setVisible(true);
    }

    private void actualizarPrecio() {
        String productoSeleccionado = (String) comboBoxProducto.getSelectedItem();
        double precio = preciosProductos.get(productoSeleccionado);
        campoPrecio.setText(Double.toString(precio));
    }

    private void agregarProductoAPedido(){
        String nombreProducto = (String) comboBoxProducto.getSelectedItem();
        int cantidad = (int) spinnerCantidad.getValue();
        double precio = Double.parseDouble(campoPrecio.getText());
        String pedidoFila = "Producto: " + nombreProducto + ", Cantidad: " + cantidad + ", Precio: " + precio;
        modeloListaPedidos.addElement(pedidoFila);
        if(Objects.equals(comboBoxTipo.getSelectedItem(), "Bebida")){
            pedido.agregarProducto(new Bebida(nombreProducto, cantidad, precio));
        }
        else{
            pedido.agregarProducto(new Plato(nombreProducto, cantidad, precio));
        }
        limpiarCamposProductos();
    }
    private void pagarPedido(){
        if (modeloListaPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en la lista para pagar.");
            return;
        }

        String tarjetaSeleccionada = (String) comboBoxTarjeta.getSelectedItem();
        TarjetaCredito tarjetaCredito = mapaTarjetas.get(tarjetaSeleccionada);
        double propina = comboBoxPropina.getItemAt(comboBoxPropina.getSelectedIndex());

        dispositivo.realizarPago(pedido, tarjetaCredito, propina, new FakeProveedorFecha());

        pedido = new Pedido();
        limpiarCamposProductos();
        limpiarCamposDePago();
    }

    private void limpiarCamposProductos() {
        this.comboBoxTipo.setSelectedIndex(0);
        this.spinnerCantidad.setValue(1);
        this.comboBoxProducto.setSelectedIndex(0);
    }
    private void limpiarCamposDePago(){
        this.comboBoxTarjeta.setSelectedIndex(0);
        this.campoPrecio.setText("");
        modeloListaPedidos.clear();
    }
}
