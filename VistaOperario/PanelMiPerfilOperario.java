package VistaInicioSesion.VistaOperario;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelMiPerfilOperario extends JPanel {

    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color GRIS_BORDE = new Color(200, 180, 160);
    private final Color GRIS_CLARO = new Color(220, 220, 220);
    private final Color VERDE_RENDIMIENTO = new Color(34, 197, 94);
    private final Color AZUL_RENDIMIENTO = new Color(59, 130, 246);
    private final Color MORADO_RENDIMIENTO = new Color(168, 85, 247);
    
    public PanelMiPerfilOperario() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 1020, 775);
        
    
        JLabel lblTitulo = new JLabel("Mi Perfil");
        lblTitulo.setBounds(20, 20, 300, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(MARRON_OSCURO);
        add(lblTitulo);
        
        JLabel lblNotificacion = new JLabel("🔔");
        lblNotificacion.setBounds(875, 25, 35, 35);
        lblNotificacion.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        add(lblNotificacion);
        
        JLabel lblUsuario = new JLabel("👤 Valentina");
        lblUsuario.setBounds(920, 28, 180, 30);
        lblUsuario.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblUsuario.setForeground(MARRON_OSCURO);
        add(lblUsuario);
        
        crearPanelInfoPersonal();
        
        crearPanelHabilidades();
        
        crearPanelRendimiento();
        JButton btnActualizar = new JButton("Actualizar Información");
        btnActualizar.setBounds(670, 600, 330, 45);
        btnActualizar.setBackground(AMARILLO_BOTON);
        btnActualizar.setForeground(MARRON_OSCURO);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 15));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);
        btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnActualizar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Esta funcionalidad permite actualizar tu información personal.\n\n" +
                "Contacta al administrador para realizar cambios.",
                "Actualizar Información",
                JOptionPane.INFORMATION_MESSAGE);
        });
        add(btnActualizar);
    }
    
    private void crearPanelInfoPersonal() {
        JPanel panelInfoPersonal = new JPanel();
        panelInfoPersonal.setLayout(null);
        panelInfoPersonal.setBackground(BLANCO);
        panelInfoPersonal.setBounds(20, 90, 630, 280);
        panelInfoPersonal.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JPanel panelAvatar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(GRIS_CLARO);
                g2.fillOval(0, 0, 90, 90);
            }
        };
        panelAvatar.setBounds(30, 30, 90, 90);
        panelAvatar.setOpaque(false);
        panelAvatar.setLayout(new BorderLayout());
        
        JLabel lblAvatarEmoji = new JLabel("👤");
        lblAvatarEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 45));
        lblAvatarEmoji.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvatarEmoji.setVerticalAlignment(SwingConstants.CENTER);
        panelAvatar.add(lblAvatarEmoji, BorderLayout.CENTER);
        
        panelInfoPersonal.add(panelAvatar);
        
        JLabel lblNombre = new JLabel("");
        lblNombre.setBounds(140, 40, 300, 28);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 22));
        lblNombre.setForeground(MARRON_OSCURO);
        panelInfoPersonal.add(lblNombre);
        
        JLabel lblCargo = new JLabel("Operario de Producción");
        lblCargo.setBounds(140, 70, 300, 20);
        lblCargo.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCargo.setForeground(Color.GRAY);
        panelInfoPersonal.add(lblCargo);
        
        JLabel lblInfoPersonalTitulo = new JLabel("Información Personal");
        lblInfoPersonalTitulo.setBounds(30, 140, 250, 25);
        lblInfoPersonalTitulo.setFont(new Font("Arial", Font.BOLD, 17));
        lblInfoPersonalTitulo.setForeground(MARRON_OSCURO);
        panelInfoPersonal.add(lblInfoPersonalTitulo);
        
        JLabel lblCorreo = new JLabel("📧 Correo: valcita@doscaminos.com");
        lblCorreo.setBounds(30, 175, 570, 20);
        lblCorreo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblCorreo.setForeground(Color.GRAY);
        panelInfoPersonal.add(lblCorreo);
        
        JLabel lblTelefono = new JLabel("📱 Teléfono: +57 314 567 8940");
        lblTelefono.setBounds(30, 200, 570, 20);
        lblTelefono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblTelefono.setForeground(Color.GRAY);
        panelInfoPersonal.add(lblTelefono);
        
        JLabel lblFechaIngreso = new JLabel("📅 Fecha de Ingreso: 15/03/2024");
        lblFechaIngreso.setBounds(30, 225, 570, 20);
        lblFechaIngreso.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblFechaIngreso.setForeground(Color.GRAY);
        panelInfoPersonal.add(lblFechaIngreso);
        
        JLabel lblArea = new JLabel("🏭 Área: Producción");
        lblArea.setBounds(30, 250, 570, 20);
        lblArea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblArea.setForeground(Color.GRAY);
        panelInfoPersonal.add(lblArea);
        
        add(panelInfoPersonal);
    }
    
    private void crearPanelHabilidades() {
        JPanel panelHabilidades = new JPanel();
        panelHabilidades.setLayout(null);
        panelHabilidades.setBackground(BLANCO);
        panelHabilidades.setBounds(20, 390, 630, 180);
        panelHabilidades.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
         
        JLabel lblHabilidades = new JLabel("⭐ Habilidades");
        lblHabilidades.setBounds(30, 25, 250, 25);
        lblHabilidades.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        lblHabilidades.setForeground(MARRON_OSCURO);
        panelHabilidades.add(lblHabilidades);
        
        crearBadgeHabilidad(panelHabilidades, "🏠 Carpintería", 30, 70);
        crearBadgeHabilidad(panelHabilidades, "🔨 Lijado", 175, 70);
        crearBadgeHabilidad(panelHabilidades, "🎨 Barnizado", 310, 70);
        crearBadgeHabilidad(panelHabilidades, "🔧 Ensamblaje", 30, 120);
        crearBadgeHabilidad(panelHabilidades, "✂️ Corte Preciso", 175, 120);
        
        add(panelHabilidades);
    }
    
    private void crearPanelRendimiento() {
        JPanel panelRendimiento = new JPanel();
        panelRendimiento.setLayout(null);
        panelRendimiento.setBackground(BLANCO);
        panelRendimiento.setBounds(670, 90, 330, 480);
        panelRendimiento.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblRendimiento = new JLabel("📊 Rendimiento");
        lblRendimiento.setBounds(25, 25, 280, 30);
        lblRendimiento.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        lblRendimiento.setForeground(MARRON_OSCURO);
        panelRendimiento.add(lblRendimiento);
        
        JPanel tarjeta1 = crearTarjetaRendimiento("98%", "Órdenes completadas a tiempo", VERDE_RENDIMIENTO, 25, 80);
        panelRendimiento.add(tarjeta1);
        
        JPanel tarjeta2 = crearTarjetaRendimiento("45", "Órdenes completadas este mes", AZUL_RENDIMIENTO, 25, 200);
        panelRendimiento.add(tarjeta2);
        
        JPanel tarjeta3 = crearTarjetaRendimiento("4.8/5", "Calificación de Calidad", MORADO_RENDIMIENTO, 25, 320);
        panelRendimiento.add(tarjeta3);
        
        add(panelRendimiento);
    }
    
    private void crearBadgeHabilidad(JPanel panel, String texto, int x, int y) {
        JLabel badge = new JLabel(texto);
        badge.setBounds(x, y, 130, 35);
        badge.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));
        badge.setForeground(MARRON_OSCURO);
        badge.setOpaque(true);
        badge.setBackground(new Color(245, 245, 245));
        badge.setHorizontalAlignment(SwingConstants.CENTER);
        badge.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(badge);
    }
    
    private JPanel crearTarjetaRendimiento(String valor, String descripcion, Color color, int x, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BLANCO);
        panel.setBounds(x, y, 280, 100);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        JLabel lblValor = new JLabel(valor);
        lblValor.setBounds(10, 15, 260, 45);
        lblValor.setFont(new Font("Arial", Font.BOLD, 38));
        lblValor.setForeground(color);
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblValor);
        
        JLabel lblDescripcion = new JLabel("<html><center>" + descripcion + "</center></html>");
        lblDescripcion.setBounds(10, 60, 260, 35);
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
        lblDescripcion.setForeground(Color.GRAY);
        lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblDescripcion);
        
        return panel;
    }
}