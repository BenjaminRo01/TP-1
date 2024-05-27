package ui;

import model.Observer;

import javax.swing.*;
import java.awt.*;

public class MonitorGerente implements Observer {
    public static final double VALOR_MAX = 300000.0;
    private JLabel campoValorUltimaVenta;
    private JLabel etiquetaUltimaVenta;
    private JFrame frame;
    private JPanel panelPrincipal;

    public MonitorGerente() {
        frame = new JFrame("Monitor Gerente");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiqueta para mostrar la última venta
        etiquetaUltimaVenta = new JLabel("Última venta:");
        etiquetaUltimaVenta.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaUltimaVenta.setVerticalAlignment(SwingConstants.CENTER);
        etiquetaUltimaVenta.setFont(new Font("Arial", Font.BOLD, 16));

        panelPrincipal.add(etiquetaUltimaVenta, BorderLayout.NORTH);

        // Etiqueta para mostrar el valor de la última venta
        campoValorUltimaVenta = new JLabel("0");
        campoValorUltimaVenta.setHorizontalAlignment(SwingConstants.CENTER);
        campoValorUltimaVenta.setVerticalAlignment(SwingConstants.CENTER);
        campoValorUltimaVenta.setFont(new Font("Arial", Font.BOLD, 16));

        panelPrincipal.add(campoValorUltimaVenta, BorderLayout.CENTER);

        // Botón para cerrar la ventana
        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> frame.dispose());

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonCerrar);

        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        frame.add(panelPrincipal);
        frame.setVisible(true);
    }

    @Override
    public void actualizar(double valor) {
        this.campoValorUltimaVenta.setText("$" + String.valueOf(valor));
        if(valor > VALOR_MAX){
            this.campoValorUltimaVenta.setForeground(new Color(255,0,0));
        }
        else{
            this.campoValorUltimaVenta.setForeground(new Color(0,0,0));
        }
    }
}

