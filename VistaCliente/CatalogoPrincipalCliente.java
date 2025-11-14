package VistaInicioSesion.VistaCliente;

import VistaInicioSesion.VistaCliente.CarritoCliente;
import VistaInicioSesion.VistaCliente.CarritoCliente;
import VistaInicioSesion.VistaCliente.PanelContacto;
import VistaInicioSesion.VistaCliente.PanelContacto;
import VistaInicioSesion.VistaCliente.PanelFavoritos;
import VistaInicioSesion.VistaCliente.PanelFavoritos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.File;

public class CatalogoPrincipalCliente extends JFrame {
    
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color MARRON_MEDIO = new Color(120, 90, 70);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color GRIS_BORDE = new Color(200, 180, 160);
    
    private ArrayList<ProductoPanel> productos;
    private JPanel panelProductos;
    private JTextField txtBuscar;
    private JComboBox<String> cboFiltro;
    private JComboBox<String> cboOrdenar;
    private JPanel contenidoPanel;
    
    private JButton btnCatalogo;
    private JButton btnFavoritos;
    private JButton btnContacto;
    private JButton btnUbicacion;
    
    public CatalogoPrincipalCliente() {
        cargarProductos();
        initComponents();
        mostrarCatalogo();
    }
    
    private void initComponents() {
        setTitle("Dos Caminos - Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BEIGE);
        
        JPanel menuLateral = crearMenuLateral();
        mainPanel.add(menuLateral, BorderLayout.WEST);
        
        contenidoPanel = new JPanel(new BorderLayout());
        contenidoPanel.setBackground(BEIGE);
        mainPanel.add(contenidoPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JPanel crearMenuLateral() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setPreferredSize(new Dimension(380, 800));
        menuPanel.setBackground(MARRON_OSCURO);
        
        JPanel logoPanel = crearPanelLogo();
        menuPanel.add(logoPanel);
        agregarBotonesMenu(menuPanel);
        
        return menuPanel;
    }
    
    private JPanel crearPanelLogo() {
        JPanel logoPanel = new JPanel();
        logoPanel.setBounds(40, 30, 300, 200);
        logoPanel.setBackground(MARRON_OSCURO);
        logoPanel.setLayout(new BorderLayout());
        
        String[] rutasPosibles = {
            "src/imagenes/logo_doscaminos.png",
           };
        
        boolean logoCargado = false;
        
        for (String ruta : rutasPosibles) {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                try {
                    ImageIcon iconoOriginal = new ImageIcon(ruta);
                    if (iconoOriginal.getIconWidth() > 0) {
                        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                        280, 180, Image.SCALE_SMOOTH);
                        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
                        JLabel lblLogo = new JLabel(iconoEscalado);
                        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
                        logoPanel.add(lblLogo, BorderLayout.CENTER);
                        logoCargado = true;
                        System.out.println("Logo cargado desde: " + ruta);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Error al cargar desde " + ruta + ": " + e.getMessage());
                }
            }
        }
        
        if (!logoCargado) {
            JPanel panelTexto = new JPanel();
            panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
            panelTexto.setBackground(MARRON_OSCURO);
            
            JLabel lblLogoTexto = new JLabel("DOS");
            lblLogoTexto.setFont(new Font("Arial", Font.BOLD, 40));
            lblLogoTexto.setForeground(BEIGE);
            lblLogoTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel lblLogoTexto2 = new JLabel("CAMINOS");
            lblLogoTexto2.setFont(new Font("Arial", Font.BOLD, 36));
            lblLogoTexto2.setForeground(new Color(230, 190, 80));
            lblLogoTexto2.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            panelTexto.add(Box.createVerticalGlue());
            panelTexto.add(lblLogoTexto);
            panelTexto.add(Box.createVerticalStrut(5));
            panelTexto.add(lblLogoTexto2);
            panelTexto.add(Box.createVerticalGlue());
            
            logoPanel.add(panelTexto, BorderLayout.CENTER);
        }
        
        return logoPanel;
    }
    
    private void agregarBotonesMenu(JPanel menuPanel) {
        int yPos = 270;
        
        btnCatalogo = crearBotonMenu("📦 Catálogo", yPos, true);
        btnCatalogo.addActionListener(e -> mostrarCatalogo());
        menuPanel.add(btnCatalogo);
        yPos += 80;
        
        btnFavoritos = crearBotonMenu("❤️ Favoritos", yPos, false);
        btnFavoritos.addActionListener(e -> mostrarFavoritos());
        menuPanel.add(btnFavoritos);
        yPos += 80;
        
        btnContacto = crearBotonMenu("📞 Contacto", yPos, false);
        btnContacto.addActionListener(e -> mostrarContacto());
        menuPanel.add(btnContacto);
        yPos += 80;
        
        btnUbicacion = crearBotonMenu("📍 Ubicación", yPos, false);
        btnUbicacion.addActionListener(e -> mostrarUbicacion());
        menuPanel.add(btnUbicacion);
        yPos += 80;
        
        JButton btnPerfil = crearBotonMenu("👤 Mi perfil", 650, false);
        btnPerfil.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sección de perfil - Próximamente");
        });
        menuPanel.add(btnPerfil);
    }
    
    private JButton crearBotonMenu(String texto, int y, boolean activo) {
        JButton btn = new JButton(texto);
        btn.setBounds(80, y, 240, 50);
        btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        btn.setForeground(BLANCO);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (activo) {
            btn.setBackground(MARRON_MEDIO);
            btn.setOpaque(true);
        } else {
            btn.setBackground(MARRON_OSCURO);
            btn.setOpaque(false);
            btn.setContentAreaFilled(false);
        }
        
        return btn;
    }
    
    private void actualizarMenuActivo(JButton botonActivo) {
        JButton[] botones = {btnCatalogo, btnFavoritos, btnContacto, btnUbicacion};
        for (JButton btn : botones) {
            btn.setBackground(MARRON_OSCURO);
            btn.setOpaque(false);
            btn.setContentAreaFilled(false);
        }
        
        // Activar el botón seleccionado
        botonActivo.setBackground(MARRON_MEDIO);
        botonActivo.setOpaque(true);
        botonActivo.setContentAreaFilled(true);
    }
    
    
    private void mostrarCatalogo() {
        actualizarMenuActivo(btnCatalogo);
        contenidoPanel.removeAll();
        contenidoPanel.add(crearContenidoCatalogo(), BorderLayout.CENTER);
        contenidoPanel.revalidate();
        contenidoPanel.repaint();
    }
    
    private void mostrarFavoritos() {
        actualizarMenuActivo(btnFavoritos);
        PanelFavoritos panelFav = new PanelFavoritos(productos, this);
        contenidoPanel.removeAll();
        contenidoPanel.add(panelFav, BorderLayout.CENTER);
        contenidoPanel.revalidate();
        contenidoPanel.repaint();
    }
    
    private void mostrarContacto() {
        actualizarMenuActivo(btnContacto);
        PanelContacto panelCont = new PanelContacto();
        contenidoPanel.removeAll();
        contenidoPanel.add(panelCont, BorderLayout.CENTER);
        contenidoPanel.revalidate();
        contenidoPanel.repaint();
    }
    
    private void mostrarUbicacion() {
        actualizarMenuActivo(btnUbicacion);
        JOptionPane.showMessageDialog(this, "Sección de Ubicación - Próximamente");
    }
    
    private JPanel crearContenidoCatalogo() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BEIGE);
        
        crearHeaderCatalogo(panel);
        
        JPanel panelPrincipal = crearPanelProductos();
        panel.add(panelPrincipal);
        
        return panel;
    }
    
    private void crearHeaderCatalogo(JPanel panel) {
        JLabel lblTitulo = new JLabel("Catálogo de Productos");
        lblTitulo.setBounds(40, 25, 500, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(MARRON_OSCURO);
        panel.add(lblTitulo);
        
        JButton btnCarrito = new JButton("🛒 Carrito");
        btnCarrito.setBounds(880, 28, 120, 40);
        btnCarrito.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        btnCarrito.setBackground(AMARILLO_BOTON);
        btnCarrito.setForeground(MARRON_OSCURO);
        btnCarrito.setFocusPainted(false);
        btnCarrito.setBorderPainted(false);
        btnCarrito.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCarrito.addActionListener(e -> abrirCarrito());
        panel.add(btnCarrito);
    }
    
    private JPanel crearPanelProductos() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(BLANCO);
        panelPrincipal.setBounds(40, 90, 970, 650);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        crearBarraBusqueda(panelPrincipal);
        
        panelProductos = new JPanel(new GridLayout(0, 3, 20, 20));
        panelProductos.setBackground(BLANCO);
        panelProductos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        for (ProductoPanel prod : productos) {
            panelProductos.add(prod);
        }
        
        JScrollPane scrollPane = new JScrollPane(panelProductos);
        scrollPane.setBounds(25, 80, 920, 550);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panelPrincipal.add(scrollPane);
        
        return panelPrincipal;
    }
    
    private void crearBarraBusqueda(JPanel panel) {
        JLabel lblBuscar = new JLabel("🔍");
        lblBuscar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lblBuscar.setBounds(25, 25, 30, 35);
        panel.add(lblBuscar);
        
        txtBuscar = new JTextField("Buscar productos");
        txtBuscar.setBounds(60, 25, 300, 35);
        txtBuscar.setFont(new Font("Arial", Font.PLAIN, 14));
        txtBuscar.setForeground(Color.GRAY);
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        agregarPlaceholder(txtBuscar, "Buscar productos...");
        panel.add(txtBuscar);
        
        JLabel lblFiltro = new JLabel("Filtrar por:");
        lblFiltro.setBounds(390, 30, 80, 25);
        lblFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
        lblFiltro.setForeground(MARRON_OSCURO);
        panel.add(lblFiltro);
        
        cboFiltro = new JComboBox<>(new String[]{"Todos", "Mesas", "Sillas", "Armarios"});
        cboFiltro.setBounds(470, 25, 140, 35);
        cboFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(cboFiltro);
        
        cboOrdenar = new JComboBox<>(new String[]{
            "Ordenar por", "Precio: Menor a Mayor", "Precio: Mayor a Menor", "Nombre A-Z"
        });
        cboOrdenar.setBounds(630, 25, 180, 35);
        cboOrdenar.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(cboOrdenar);
    }
    
    
    private void abrirCarrito() {
        new CarritoCliente(productos).setVisible(true);
    }
    
    private void agregarPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
        
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(MARRON_OSCURO);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }
    
    private void cargarProductos() {
        productos = new ArrayList<>();
        
        productos.add(new ProductoPanel(
            "Mesa de Comedor Roble",
            "Mesa de comedor en madera de roble macizo con acabado natural",
            "$450,000", false
        ));
        
        productos.add(new ProductoPanel(
            "Silla Moderna",
            "Silla de diseño moderno con estructura de madera y asiento tapizado",
            "$120,000", true
        ));
        
        productos.add(new ProductoPanel(
            "Armario Vintage",
            "Armario de estilo vintage con dos puertas y cajones interiores",
            "$380,000", false
        ));
        
        productos.add(new ProductoPanel(
            "Mesa de Centro",
            "Mesa de centro con superficie de vidrio y estructura de madera",
            "$220,000", true
        ));
        
        productos.add(new ProductoPanel(
            "Escritorio Ejecutivo",
            "Escritorio de madera con cajones y acabado elegante",
            "$550,000", false
        ));
        
        productos.add(new ProductoPanel(
            "Estantería Modular",
            "Estantería de 5 niveles en madera de pino",
            "$280,000", false
        ));
    }
    
    public class ProductoPanel extends JPanel {
        private boolean favorito;
        private JLabel lblCorazon;
        
        public ProductoPanel(String nombre, String descripcion, String precio, boolean esFavorito) {
            this.favorito = esFavorito;
            
            setLayout(new BorderLayout(10, 10));
            setBackground(BLANCO);
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GRIS_BORDE, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));
            setPreferredSize(new Dimension(280, 300));
            
            JPanel panelImagen = crearImagenProducto();
            
           
            JPanel panelInfo = crearInfoProducto(nombre, descripcion, precio);
            
            add(panelImagen, BorderLayout.NORTH);
            add(panelInfo, BorderLayout.CENTER);
        }
        
        private JPanel crearImagenProducto() {
            JPanel panelImagen = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                        RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(245, 245, 245),
                        0, getHeight(), new Color(230, 230, 230)
                    );
                    g2.setPaint(gradient);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                    
                    g2.setColor(new Color(200, 200, 200));
                    g2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
                    String icono = "🪑";
                    FontMetrics fm = g2.getFontMetrics();
                    int x = (getWidth() - fm.stringWidth(icono)) / 2;
                    int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                    g2.drawString(icono, x, y);
                }
            };
            panelImagen.setPreferredSize(new Dimension(250, 150));
            panelImagen.setBackground(BLANCO);
            return panelImagen;
        }
        
        private JPanel crearInfoProducto(String nombre, String descripcion, String precio) {
            JPanel panelInfo = new JPanel(new BorderLayout(5, 5));
            panelInfo.setBackground(BLANCO);
            
            JPanel panelTitulo = new JPanel(new BorderLayout());
            panelTitulo.setBackground(BLANCO);
            
            JLabel lblNombre = new JLabel(nombre);
            lblNombre.setFont(new Font("Arial", Font.BOLD, 15));
            lblNombre.setForeground(MARRON_OSCURO);
            
            lblCorazon = new JLabel(favorito ? "❤️" : "🤍");
            lblCorazon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            lblCorazon.setCursor(new Cursor(Cursor.HAND_CURSOR));
            lblCorazon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    toggleFavorito();
                }
            });
            
            panelTitulo.add(lblNombre, BorderLayout.CENTER);
            panelTitulo.add(lblCorazon, BorderLayout.EAST);

            JLabel lblDescripcion = new JLabel("<html>" + descripcion + "</html>");
            lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
            lblDescripcion.setForeground(new Color(120, 120, 120));
            lblDescripcion.setPreferredSize(new Dimension(250, 40));

            JPanel panelPrecio = crearPanelPrecio(nombre, precio);
            
            panelInfo.add(panelTitulo, BorderLayout.NORTH);
            panelInfo.add(lblDescripcion, BorderLayout.CENTER);
            panelInfo.add(panelPrecio, BorderLayout.SOUTH);
            
            return panelInfo;
        }
        
        private JPanel crearPanelPrecio(String nombre, String precio) {
            JPanel panelPrecio = new JPanel(new BorderLayout(10, 0));
            panelPrecio.setBackground(BLANCO);
            
            JLabel lblPrecio = new JLabel(precio);
            lblPrecio.setFont(new Font("Arial", Font.BOLD, 18));
            lblPrecio.setForeground(MARRON_OSCURO);
            
            JButton btnAnadir = new JButton("🛒 Añadir");
            btnAnadir.setFont(new Font("Segoe UI Emoji", Font.BOLD, 12));
            btnAnadir.setForeground(BLANCO);
            btnAnadir.setBackground(AMARILLO_BOTON);
            btnAnadir.setBorderPainted(false);
            btnAnadir.setFocusPainted(false);
            btnAnadir.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnAnadir.addActionListener(e -> {
                JOptionPane.showMessageDialog(ProductoPanel.this, 
                    nombre + " añadido al carrito", 
                    "Producto añadido", 
                    JOptionPane.INFORMATION_MESSAGE);
            });
            
            panelPrecio.add(lblPrecio, BorderLayout.WEST);
            panelPrecio.add(btnAnadir, BorderLayout.EAST);
            
            return panelPrecio;
        }
        
        private void toggleFavorito() {
            favorito = !favorito;
            lblCorazon.setText(favorito ? "❤️" : "🤍");
        }
        
        public boolean isFavorito() {
            return favorito;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CatalogoPrincipalCliente().setVisible(true);
        });
    }
}