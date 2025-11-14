package VistaInicioSesion.VistaCliente;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelFavoritos extends JPanel {
    
    public PanelFavoritos(ArrayList<?> productos, JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(239, 228, 206));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // Título
        JLabel lblTitulo = new JLabel("Mis productos favoritos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(94, 73, 62));
        
        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(239, 228, 206));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        
        // Panel de productos favoritos
        JPanel panelFavoritos = new JPanel(new GridLayout(0, 2, 20, 20));
        panelFavoritos.setBackground(new Color(239, 228, 206));
        
        // Contar favoritos
        int cantidadFavoritos = 0;
        for (Object prod : productos) {
            CatalogoPrincipalCliente.ProductoPanel producto = 
                (CatalogoPrincipalCliente.ProductoPanel) prod;
            if (producto.isFavorito()) {
                panelFavoritos.add(producto);
                cantidadFavoritos++;
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(panelFavoritos);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        JLabel lblMensaje = new JLabel("Agrega más productos a tus favoritos haciendo clic en el corazón de cada producto");
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 13));
        lblMensaje.setForeground(new Color(94, 73, 62));
        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
        
        // Botón Ver más productos
        JButton btnVerMas = new JButton("Ver más productos");
        btnVerMas.setFont(new Font("Arial", Font.BOLD, 14));
        btnVerMas.setForeground(Color.WHITE);
        btnVerMas.setBackground(new Color(218, 165, 32));
        btnVerMas.setBorderPainted(false);
        btnVerMas.setFocusPainted(false);
        btnVerMas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerMas.setPreferredSize(new Dimension(200, 40));
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(new Color(239, 228, 206));
        panelBoton.add(btnVerMas);
        
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(239, 228, 206));
        panelInferior.add(lblMensaje, BorderLayout.NORTH);
        panelInferior.add(panelBoton, BorderLayout.SOUTH);
        
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
}