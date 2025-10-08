package doscaminos;

import javax.swing.*;
import java.awt.*;

public class PanelProductos extends JPanel {
    
    // Colores
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_TARJETA = new Color(255, 235, 150);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color VERDE_STOCK = new Color(134, 239, 172);
    private final Color ROJO_STOCK = new Color(252, 165, 165);
    
    public PanelProductos() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 920, 675);
        
        // Header con título y usuario
        JLabel lblTitulo = new JLabel("Productos");
        lblTitulo.setBounds(20, 20, 300, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(MARRON_OSCURO);
        add(lblTitulo);
        
        // Iconos de notificación y usuario
        JLabel lblNotificacion = new JLabel("🔔");
        lblNotificacion.setBounds(820, 25, 30, 30);
        lblNotificacion.setFont(new Font("Arial", Font.PLAIN, 24));
        add(lblNotificacion);
        
        JLabel lblUsuario = new JLabel("👤 Admin");
        lblUsuario.setBounds(860, 25, 80, 30);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsuario.setForeground(MARRON_OSCURO);
        add(lblUsuario);
        
        // Panel de catálogo
        JPanel panelCatalogo = new JPanel();
        panelCatalogo.setLayout(null);
        panelCatalogo.setBackground(BLANCO);
        panelCatalogo.setBounds(20, 90, 880, 560);
        panelCatalogo.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 160), 1));
        
        // Título del panel
        JLabel lblCatalogo = new JLabel("Catálogo de Productos");
        lblCatalogo.setBounds(20, 20, 400, 30);
        lblCatalogo.setFont(new Font("Arial", Font.BOLD, 24));
        lblCatalogo.setForeground(MARRON_OSCURO);
        panelCatalogo.add(lblCatalogo);
        
        // Botón Nuevo Producto
        JButton btnNuevoProducto = new JButton("+ Nuevo Producto");
        btnNuevoProducto.setBounds(690, 20, 170, 35);
        btnNuevoProducto.setBackground(AMARILLO_BOTON);
        btnNuevoProducto.setForeground(MARRON_OSCURO);
        btnNuevoProducto.setFont(new Font("Arial", Font.BOLD, 13));
        btnNuevoProducto.setFocusPainted(false);
        btnNuevoProducto.setBorderPainted(false);
        btnNuevoProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelCatalogo.add(btnNuevoProducto);
        
        // Tarjetas de productos
        JPanel producto1 = crearTarjetaProducto(
            "Mesa de Comedor",
            "Mesa de madera de roble con acabado natural",
            "$450,000",
            "En stock",
            VERDE_STOCK,
            "🪑",
            20, 70
        );
        panelCatalogo.add(producto1);
        
        JPanel producto2 = crearTarjetaProducto(
            "Estantería Modular",
            "Estantería personalizable con múltiples compartimientos",
            "$640,000",
            "En stock",
            VERDE_STOCK,
            "📚",
            20, 240
        );
        panelCatalogo.add(producto2);
        
        JPanel producto3 = crearTarjetaProducto(
            "Puertas de Madera",
            "Puerta de madera maciza con diseño clásico",
            "$100,000",
            "Bajo stock",
            ROJO_STOCK,
            "🚪",
            20, 410
        );
        panelCatalogo.add(producto3);
        
        add(panelCatalogo);
    }
    
    private JPanel crearTarjetaProducto(String nombre, String descripcion, String precio,
                                       String stock, Color colorStock, String icono,
                                       int x, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BLANCO);
        panel.setBounds(x, y, 840, 150);
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 160), 1));
        
        // Panel amarillo con icono
        JPanel panelIcono = new JPanel();
        panelIcono.setLayout(null);
        panelIcono.setBackground(AMARILLO_TARJETA);
        panelIcono.setBounds(0, 0, 840, 80);
        
        JLabel lblIcono = new JLabel(icono);
        lblIcono.setBounds(390, 10, 60, 60);
        lblIcono.setFont(new Font("Arial", Font.PLAIN, 48));
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        panelIcono.add(lblIcono);
        
        panel.add(panelIcono);
        
        // Nombre del producto
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setBounds(15, 90, 400, 25);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setForeground(MARRON_OSCURO);
        panel.add(lblNombre);
        
        // Descripción
        JLabel lblDescripcion = new JLabel(descripcion);
        lblDescripcion.setBounds(15, 115, 500, 20);
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDescripcion.setForeground(Color.GRAY);
        panel.add(lblDescripcion);
        
        // Precio
        JLabel lblPrecio = new JLabel(precio);
        lblPrecio.setBounds(600, 95, 120, 30);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 18));
        lblPrecio.setForeground(MARRON_OSCURO);
        panel.add(lblPrecio);
        
        // Badge de stock
        JLabel lblStock = new JLabel(stock);
        lblStock.setBounds(730, 100, 90, 20);
        lblStock.setFont(new Font("Arial", Font.BOLD, 11));
        lblStock.setForeground(MARRON_OSCURO);
        lblStock.setOpaque(true);
        lblStock.setBackground(colorStock);
        lblStock.setHorizontalAlignment(SwingConstants.CENTER);
        lblStock.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
        panel.add(lblStock);
        
        return panel;
    }
}