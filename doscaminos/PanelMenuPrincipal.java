package doscaminos;

import javax.swing.*;
import java.awt.*;

public class PanelMenuPrincipal extends JPanel {
    
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color MARRON_TARJETA = new Color(100, 60, 40);
    private final Color AMARILLO_TARJETA = new Color(255, 235, 150);
    
    public PanelMenuPrincipal() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        
        // Header con título y usuario
        JLabel lblTitulo = new JLabel("Menú Principal");
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
        
        // Tarjetas de estadísticas, esta es promera fila
        JPanel tarjetaVentas = crearTarjetaEstadistica("Ventas Totales", "$24,580", 
            "📈 12% más que el mes pasado", new Color(34, 197, 94), 20, 100);
        add(tarjetaVentas);
        
        JPanel tarjetaProyectos = crearTarjetaEstadistica("Proyectos Activos", "12", 
            "📈 3 nuevos esta semana", new Color(34, 197, 94), 480, 100);
        add(tarjetaProyectos);
        
        //Segunda fila
        JPanel tarjetaClientes = crearTarjetaEstadistica("Clientes", "45", 
            "📈 5 nuevos este mes", new Color(34, 197, 94), 20, 230);
        add(tarjetaClientes);
        
        JPanel tarjetaInventario = crearTarjetaEstadistica("Inventario", "128", 
            "📉 100 artículos con bajo stock", new Color(239, 68, 68), 480, 230);
        add(tarjetaInventario);
        
        //Sección Proyectos Recientes
        JLabel lblProyectosRecientes = new JLabel("Proyectos Recientes");
        lblProyectosRecientes.setBounds(20, 370, 300, 30);
        lblProyectosRecientes.setFont(new Font("Arial", Font.BOLD, 24));
        lblProyectosRecientes.setForeground(MARRON_OSCURO);
        add(lblProyectosRecientes);
        
        JLabel lblVerTodos = new JLabel("Ver todos");
        lblVerTodos.setBounds(820, 375, 80, 20);
        lblVerTodos.setFont(new Font("Arial", Font.PLAIN, 13));
        lblVerTodos.setForeground(Color.GRAY);
        lblVerTodos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(lblVerTodos);
        
        //Tarjetas de proyectos
        JPanel proyecto1 = crearTarjetaProyecto(
            "Muebles de cocina - Residencia Torres",
            "Diseño e instalación de gabinetes y\nencimas para cocina moderna",
            "En progreso",
            new Color(34, 197, 94),
            20, 420
        );
        add(proyecto1);
        
        JPanel proyecto2 = crearTarjetaProyecto(
            "Estanterías - Librería Central",
            "Fabricación de estanterías personalizadas\npara local comercial",
            "Planificación",
            new Color(59, 130, 246),
            310, 420
        );
        add(proyecto2);
        
        JPanel proyecto3 = crearTarjetaProyecto(
            "Puertas de Madera - Hotel Oasis",
            "Restauración y fabricación de puertas de\nmadera maciza",
            "Cotización",
            new Color(168, 85, 247),
            600, 420
        );
        add(proyecto3);
    }
    
    private JPanel crearTarjetaEstadistica(String titulo, String valor, 
                                          String estadistica, Color colorEstadistica, 
                                          int x, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(MARRON_TARJETA);
        panel.setBounds(x, y, 420, 110);
        panel.setBorder(BorderFactory.createLineBorder(MARRON_OSCURO, 0));
        panel.setOpaque(true);
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setBounds(20, 15, 250, 20);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo);
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setBounds(20, 40, 250, 35);
        lblValor.setFont(new Font("Arial", Font.BOLD, 32));
        lblValor.setForeground(Color.WHITE);
        panel.add(lblValor);
        
        JLabel lblEstadistica = new JLabel(estadistica);
        lblEstadistica.setBounds(20, 80, 300, 20);
        lblEstadistica.setFont(new Font("Arial", Font.PLAIN, 11));
        lblEstadistica.setForeground(colorEstadistica);
        panel.add(lblEstadistica);
        
        //Icono circular a la derecha
        JPanel iconoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 30));
                g2d.fillOval(0, 0, 60, 60);
            }
        };
        iconoPanel.setBounds(340, 25, 60, 60);
        iconoPanel.setOpaque(false);
        panel.add(iconoPanel);
        
        return panel;
    }
    
    private JPanel crearTarjetaProyecto(String titulo, String descripcion, 
                                       String estado, Color colorEstado, 
                                       int x, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(AMARILLO_TARJETA);
        panel.setBounds(x, y, 280, 230);
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 130), 1));
        
        JLabel lblTitulo = new JLabel("<html>" + titulo + "</html>");
        lblTitulo.setBounds(15, 140, 250, 35);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        lblTitulo.setForeground(MARRON_OSCURO);
        panel.add(lblTitulo);
        
        JLabel lblDescripcion = new JLabel("<html>" + descripcion.replace("\n", "<br>") + "</html>");
        lblDescripcion.setBounds(15, 175, 250, 35);
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 11));
        lblDescripcion.setForeground(Color.GRAY);
        panel.add(lblDescripcion);
        
        //Badge de estado
        JLabel lblEstado = new JLabel(estado);
        lblEstado.setBounds(15, 210, 100, 15);
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 10));
        lblEstado.setForeground(colorEstado);
        lblEstado.setOpaque(true);
        lblEstado.setBackground(new Color(colorEstado.getRed(), colorEstado.getGreen(), 
                                         colorEstado.getBlue(), 30));
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblEstado);
        
        //Información adicional si es necesario
        if (titulo.contains("Hotel Oasis")) {
            JLabel lblAdicional = new JLabel("Pendiente aprobación");
            lblAdicional.setBounds(130, 210, 140, 15);
            lblAdicional.setFont(new Font("Arial", Font.PLAIN, 9));
            lblAdicional.setForeground(Color.GRAY);
            panel.add(lblAdicional);
        }
        
        return panel;
    }
}