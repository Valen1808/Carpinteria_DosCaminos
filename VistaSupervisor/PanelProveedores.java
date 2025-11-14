package VistaInicioSesion.VistaSupervisor;

import javax.swing.*;
import java.awt.*;

public class PanelProveedores extends JPanel {
    
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color GRIS_BORDE = new Color(200, 180, 160);
    
    public PanelProveedores() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 1020, 775);
        
        JLabel lblTitulo = new JLabel("Proveedores");
        lblTitulo.setBounds(20, 20, 300, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(MARRON_OSCURO);
        add(lblTitulo);
  
        JLabel lblNotificacion = new JLabel("🔔");
        lblNotificacion.setBounds(900, 25, 35, 35);
        lblNotificacion.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        add(lblNotificacion);
        
        JLabel lblUsuario = new JLabel("👤 Supervisor");
        lblUsuario.setBounds(930, 28, 120, 30);
        lblUsuario.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblUsuario.setForeground(MARRON_OSCURO);
        add(lblUsuario);
  
        JPanel panelDirectorio = new JPanel();
        panelDirectorio.setLayout(null);
        panelDirectorio.setBackground(BLANCO);
        panelDirectorio.setBounds(20, 90, 980, 665);
        panelDirectorio.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblDirectorio = new JLabel("Directorio de Proveedores");
        lblDirectorio.setBounds(25, 25, 500, 35);
        lblDirectorio.setFont(new Font("Arial", Font.BOLD, 26));
        lblDirectorio.setForeground(MARRON_OSCURO);
        panelDirectorio.add(lblDirectorio);
        
        JTextField txtBuscar = new JTextField("🔍 Buscar Proveedor...");
        txtBuscar.setBounds(25, 80, 930, 45);
        txtBuscar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        txtBuscar.setForeground(Color.GRAY);
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        panelDirectorio.add(txtBuscar);

        JPanel tarjeta1 = crearTarjetaProveedor(
            "Maderas del Sur",
            "Proveedor de maderas",
            "+57 3145678940",
            "Contacto@maderasdelsur.com",
            25, 160
        );
        panelDirectorio.add(tarjeta1);
        
        JPanel tarjeta2 = crearTarjetaProveedor(
            "Herrajes Modernos",
            "Proveedor de herrajes y accesorios",
            "+57 3145678940",
            "info@herrajesmodernos.com",
            505, 160
        );
        panelDirectorio.add(tarjeta2);
        
        add(panelDirectorio);
    }
    
    private JPanel crearTarjetaProveedor(String nombre, String descripcion, 
        String telefono, String email, int x, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BLANCO);
        panel.setBounds(x, y, 455, 280);
        panel.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setBounds(20, 20, 415, 30);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 20));
        lblNombre.setForeground(MARRON_OSCURO);
        panel.add(lblNombre);
        
        JLabel lblDescripcion = new JLabel(descripcion);
        lblDescripcion.setBounds(20, 55, 415, 25);
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDescripcion.setForeground(Color.GRAY);
        panel.add(lblDescripcion);
        
        JLabel lblTelefono = new JLabel("📞" + telefono);
        lblTelefono.setBounds(20, 110, 415, 25);
        lblTelefono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblTelefono.setForeground(Color.GRAY);
        panel.add(lblTelefono);
        
        JLabel lblEmail = new JLabel("✉️ " + email);
        lblEmail.setBounds(20, 145, 415, 25);
        lblEmail.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblEmail.setForeground(Color.GRAY);
        panel.add(lblEmail);
        
        JLabel lblVerProductos = new JLabel("Ver productos");
        lblVerProductos.setBounds(20, 200, 120, 25);
        lblVerProductos.setFont(new Font("Arial", Font.PLAIN, 14));
        lblVerProductos.setForeground(new Color(59, 130, 246));
        lblVerProductos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblVerProductos);
        
        JButton btnContactar = new JButton("Contactar");
        btnContactar.setBounds(315, 195, 120, 35);
        btnContactar.setBackground(AMARILLO_BOTON);
        btnContactar.setForeground(MARRON_OSCURO);
        btnContactar.setFont(new Font("Arial", Font.BOLD, 13));
        btnContactar.setFocusPainted(false);
        btnContactar.setBorderPainted(false);
        btnContactar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(btnContactar);
        
        return panel;
    }
}