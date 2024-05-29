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
        setSize(750, 450); // Ajustar el tamaño total del frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.pedido = new Pedido();
        this.dispositivo = dispositivo;

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

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));

        // Panel para la creación de pedidos
        JPanel panelPedidos = new JPanel(new BorderLayout());
        panelPedidos.setPreferredSize(new Dimension(500, 400));
        panelPedidos.setBorder(BorderFactory.createTitledBorder("Formulario de Pedido"));

        // Panel de entrada de datos para el pedido
        JPanel panelEntradaDatos = new JPanel(new GridLayout(5, 2, 5, 5));

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

        panelPedidos.add(panelEntradaDatos, BorderLayout.NORTH);

        // Modelo y JList para mostrar los pedidos
        modeloListaPedidos = new DefaultListModel<>();
        listaPedidos = new JList<>(modeloListaPedidos);
        panelPedidos.add(new JScrollPane(listaPedidos), BorderLayout.CENTER);

        // Botón para agregar producto
        JButton botonAgregar = new JButton("Agregar Producto");
        botonAgregar.addActionListener(e -> this.agregarProductoAPedido());
        panelPedidos.add(botonAgregar, BorderLayout.SOUTH);

        // Panel para el formulario de pago
        JPanel panelPago = new JPanel(new GridLayout(8, 2, 5, 5));
        panelPago.setPreferredSize(new Dimension(200, 400));
        panelPago.setBorder(BorderFactory.createTitledBorder("Formulario de Pago"));

        // TextField para la propina
        JLabel etiquetaPropina = new JLabel("Propina (% del total):");
        comboBoxPropina = new JComboBox<>(new Double[]{2.0, 3.0, 5.0});
        panelPago.add(etiquetaPropina);
        panelPago.add(comboBoxPropina);

        // ComboBox para seleccionar la tarjeta
        JLabel etiquetaTarjeta = new JLabel("Tarjeta:");
        comboBoxTarjeta = new JComboBox<>(mapaTarjetas.keySet().toArray(new String[0]));
        panelPago.add(etiquetaTarjeta);
        panelPago.add(comboBoxTarjeta);

        // Botón para pagar
        JButton botonPagar = new JButton("Pagar");
        botonPagar.addActionListener(e -> this.pagarPedido());
        panelPago.add(new JLabel()); // Empty label for alignment
        panelPago.add(botonPagar);

        panelPrincipal.add(panelPedidos, BorderLayout.CENTER);
        panelPrincipal.add(panelPago, BorderLayout.EAST);

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
        String pedidoFila = "Producto: " + nombreProducto + ", Cantidad: " + cantidad + ", Precio (por unidad): " + precio;
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
