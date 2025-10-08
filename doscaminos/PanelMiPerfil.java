package doscaminos;

import javax.swing.*;
import java.awt.*;

public class PanelMiPerfil extends JPanel {
    
    // Colores
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color GRIS_CLARO = new Color(245, 245, 245);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color GRIS_ICONO = new Color(180, 180, 180);
    
    public PanelMiPerfil() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 920, 675);
        
        //Header con título y usuario
        JLabel lblTitulo = new JLabel("Mi perfil");
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
        
        // Panel contenedor principal
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(null);
        panelContenedor.setBackground(GRIS_CLARO);
        panelContenedor.setBounds(20, 90, 880, 560);
        panelContenedor.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 160), 1));
        
        // Panel de perfil (izquierda superior)
        JPanel panelPerfil = new JPanel();
        panelPerfil.setLayout(null);
        panelPerfil.setBackground(BLANCO);
        panelPerfil.setBounds(20, 20, 300, 340);
        panelPerfil.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        
        // Avatar circular
        JPanel avatarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Círculo gris de fondo
                g2d.setColor(GRIS_ICONO);
                g2d.fillOval(0, 0, 120, 120);
                
                // Icono de usuario simplificado
                g2d.setColor(Color.WHITE);
                g2d.fillOval(35, 25, 50, 50); // Cabeza
                g2d.fillOval(15, 75, 90, 70); // Cuerpo
            }
        };
        avatarPanel.setBounds(90, 30, 120, 120);
        avatarPanel.setOpaque(false);
        panelPerfil.add(avatarPanel);
        
        // Nombre del admin
        JLabel lblNombreAdmin = new JLabel("Admin DosCaminos");
        lblNombreAdmin.setBounds(0, 160, 300, 25);
        lblNombreAdmin.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombreAdmin.setForeground(MARRON_OSCURO);
        lblNombreAdmin.setHorizontalAlignment(SwingConstants.CENTER);
        panelPerfil.add(lblNombreAdmin);
        
        // Rol
        JLabel lblRol = new JLabel("Administrador de Sistema");
        lblRol.setBounds(0, 185, 300, 20);
        lblRol.setFont(new Font("Arial", Font.PLAIN, 12));
        lblRol.setForeground(Color.GRAY);
        lblRol.setHorizontalAlignment(SwingConstants.CENTER);
        panelPerfil.add(lblRol);
        
        // Botón Cambiar Foto
        JButton btnCambiarFoto = new JButton("Cambiar Foto");
        btnCambiarFoto.setBounds(85, 220, 130, 35);
        btnCambiarFoto.setBackground(AMARILLO_BOTON);
        btnCambiarFoto.setForeground(MARRON_OSCURO);
        btnCambiarFoto.setFont(new Font("Arial", Font.BOLD, 12));
        btnCambiarFoto.setFocusPainted(false);
        btnCambiarFoto.setBorderPainted(false);
        btnCambiarFoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelPerfil.add(btnCambiarFoto);
        
        // Información de contacto
        JLabel lblInfoContacto = new JLabel("Información de Contacto");
        lblInfoContacto.setBounds(15, 270, 270, 20);
        lblInfoContacto.setFont(new Font("Arial", Font.BOLD, 13));
        lblInfoContacto.setForeground(MARRON_OSCURO);
        panelPerfil.add(lblInfoContacto);
        
        JLabel lblEmail = new JLabel("✉️ admin@doscaminos.com");
        lblEmail.setBounds(15, 295, 270, 18);
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 11));
        lblEmail.setForeground(Color.GRAY);
        panelPerfil.add(lblEmail);
        
        JLabel lblTelefono = new JLabel("📞 +57 3145678940");
        lblTelefono.setBounds(15, 313, 270, 18);
        lblTelefono.setFont(new Font("Arial", Font.PLAIN, 11));
        lblTelefono.setForeground(Color.GRAY);
        panelPerfil.add(lblTelefono);
        
        JLabel lblUbicacion = new JLabel("📍 Cali, Colombia");
        lblUbicacion.setBounds(15, 331, 270, 18);
        lblUbicacion.setFont(new Font("Arial", Font.PLAIN, 11));
        lblUbicacion.setForeground(Color.GRAY);
        panelPerfil.add(lblUbicacion);
        
        // Link Cerrar sesión
        JLabel lblCerrarSesion = new JLabel("🔓 Cerrar sesión");
        lblCerrarSesion.setBounds(200, 356, 100, 18);
        lblCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 11));
        lblCerrarSesion.setForeground(Color.GRAY);
        lblCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelPerfil.add(lblCerrarSesion);
        
        panelContenedor.add(panelPerfil);
        
        // Panel de Configuración de Cuenta (derecha superior)
        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(null);
        panelConfiguracion.setBackground(BLANCO);
        panelConfiguracion.setBounds(340, 20, 520, 340);
        panelConfiguracion.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        
        JLabel lblConfiguracion = new JLabel("Configuración de Cuenta");
        lblConfiguracion.setBounds(20, 15, 300, 25);
        lblConfiguracion.setFont(new Font("Arial", Font.BOLD, 16));
        lblConfiguracion.setForeground(MARRON_OSCURO);
        panelConfiguracion.add(lblConfiguracion);
        
        // Botón Guardar Cambios
        JButton btnGuardarCambios = new JButton("Guardar Cambios");
        btnGuardarCambios.setBounds(360, 15, 140, 30);
        btnGuardarCambios.setBackground(AMARILLO_BOTON);
        btnGuardarCambios.setForeground(MARRON_OSCURO);
        btnGuardarCambios.setFont(new Font("Arial", Font.BOLD, 11));
        btnGuardarCambios.setFocusPainted(false);
        btnGuardarCambios.setBorderPainted(false);
        btnGuardarCambios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelConfiguracion.add(btnGuardarCambios);
        
        // Campos de configuración
        agregarCampo(panelConfiguracion, "Nombre", "Carlos Julio", 20, 55);
        agregarCampo(panelConfiguracion, "Correo Electrónico", "Carlosjulio@gmail.com", 20, 125);
        agregarCampo(panelConfiguracion, "Teléfono", "+57 314678940", 20, 195);
        agregarCampo(panelConfiguracion, "Dirección", "Kr 28c T14-34", 20, 265);
        
        panelContenedor.add(panelConfiguracion);
        
        // Panel de Cambiar Contraseña (izquierda inferior)
        JPanel panelContrasena = new JPanel();
        panelContrasena.setLayout(null);
        panelContrasena.setBackground(BLANCO);
        panelContrasena.setBounds(20, 380, 400, 160);
        panelContrasena.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        
        JLabel lblCambiarContrasena = new JLabel("Cambiar Contraseña");
        lblCambiarContrasena.setBounds(20, 15, 250, 25);
        lblCambiarContrasena.setFont(new Font("Arial", Font.BOLD, 16));
        lblCambiarContrasena.setForeground(MARRON_OSCURO);
        panelContrasena.add(lblCambiarContrasena);
        
        agregarCampoPassword(panelContrasena, "Contraseña actual", 20, 50);
        agregarCampoPassword(panelContrasena, "Nueva Contraseña", 20, 95);
        agregarCampoPassword(panelContrasena, "Confirmar Nueva Contraseña", 200, 95);
        
        // Botón Actualizar Contraseña
        JButton btnActualizarContrasena = new JButton("Actualizar Contraseña");
        btnActualizarContrasena.setBounds(230, 128, 150, 30);
        btnActualizarContrasena.setBackground(AMARILLO_BOTON);
        btnActualizarContrasena.setForeground(MARRON_OSCURO);
        btnActualizarContrasena.setFont(new Font("Arial", Font.BOLD, 11));
        btnActualizarContrasena.setFocusPainted(false);
        btnActualizarContrasena.setBorderPainted(false);
        btnActualizarContrasena.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelContrasena.add(btnActualizarContrasena);
        
        panelContenedor.add(panelContrasena);
        
        // Panel de Agregar otra cuenta (derecha inferior)
        JPanel panelAgregarCuenta = new JPanel();
        panelAgregarCuenta.setLayout(null);
        panelAgregarCuenta.setBackground(BLANCO);
        panelAgregarCuenta.setBounds(440, 380, 420, 160);
        panelAgregarCuenta.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        
        JLabel lblAgregarCuenta = new JLabel("Agregar otra cuenta");
        lblAgregarCuenta.setBounds(20, 15, 250, 25);
        lblAgregarCuenta.setFont(new Font("Arial", Font.BOLD, 16));
        lblAgregarCuenta.setForeground(MARRON_OSCURO);
        panelAgregarCuenta.add(lblAgregarCuenta);
        
        agregarCampo(panelAgregarCuenta, "Correo Electrónico", "", 20, 50);
        agregarCampo(panelAgregarCuenta, "Nombre", "", 20, 95);
        agregarCampoCorto(panelAgregarCuenta, "Teléfono", "", 220, 95);
        
        // Botón Agregar cuenta
        JButton btnAgregarCuenta = new JButton("Agregar cuenta");
        btnAgregarCuenta.setBounds(270, 128, 130, 30);
        btnAgregarCuenta.setBackground(AMARILLO_BOTON);
        btnAgregarCuenta.setForeground(MARRON_OSCURO);
        btnAgregarCuenta.setFont(new Font("Arial", Font.BOLD, 11));
        btnAgregarCuenta.setFocusPainted(false);
        btnAgregarCuenta.setBorderPainted(false);
        btnAgregarCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAgregarCuenta.add(btnAgregarCuenta);
        
        panelContenedor.add(panelAgregarCuenta);
        
        add(panelContenedor);
    }
    
    private void agregarCampo(JPanel panel, String etiqueta, String valorInicial, int x, int y) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setBounds(x, y, 200, 18);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl.setForeground(Color.GRAY);
        panel.add(lbl);
        
        JTextField txt = new JTextField(valorInicial);
        txt.setBounds(x, y + 20, 480, 35);
        txt.setFont(new Font("Arial", Font.PLAIN, 13));
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(txt);
    }
    
    private void agregarCampoCorto(JPanel panel, String etiqueta, String valorInicial, int x, int y) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setBounds(x, y, 180, 18);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl.setForeground(Color.GRAY);
        panel.add(lbl);
        
        JTextField txt = new JTextField(valorInicial);
        txt.setBounds(x, y + 20, 180, 35);
        txt.setFont(new Font("Arial", Font.PLAIN, 13));
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(txt);
    }
    
    private void agregarCampoPassword(JPanel panel, String etiqueta, int x, int y) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setBounds(x, y, 180, 18);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl.setForeground(Color.GRAY);
        panel.add(lbl);
        
        JPasswordField txt = new JPasswordField();
        txt.setBounds(x, y + 20, 160, 35);
        txt.setFont(new Font("Arial", Font.PLAIN, 13));
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(txt);
    }
}