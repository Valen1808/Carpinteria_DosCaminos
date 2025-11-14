package VistaInicioSesion.VistaCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelContacto extends JPanel {
    
    private final Color BEIGE = new Color(239, 228, 206);
    private final Color BLANCO = Color.WHITE;
    private final Color MARRON_OSCURO = new Color(94, 73, 62);
    private final Color GRIS_TEXTO = new Color(120, 120, 120);
    private final Color AMARILLO_BOTON = new Color(218, 165, 32);
    private final Color BORDE = new Color(200, 200, 200);
    
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JComboBox<String> cboAsunto;
    private JTextArea txtMensaje;
    
    public PanelContacto() {
        setLayout(new BorderLayout());
        setBackground(BEIGE);
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        JPanel panelContenido = new JPanel(new BorderLayout(0, 25));
        panelContenido.setBackground(BEIGE);

        panelContenido.add(crearTitulo(), BorderLayout.NORTH);
        
        panelContenido.add(crearContenidoCentral(), BorderLayout.CENTER);
        
        add(panelContenido, BorderLayout.CENTER);
    }
    
    
    private JPanel crearTitulo() {
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.setBackground(BEIGE);
        
        JLabel lblTitulo = new JLabel("📞 Atención al Cliente");
        lblTitulo.setFont(new Font("Segoe UI Emoji", Font.BOLD, 32));
        lblTitulo.setForeground(MARRON_OSCURO);
        
        panelTitulo.add(lblTitulo);
        
        return panelTitulo;
    }
    
    private JPanel crearContenidoCentral() {
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 30, 0));
        panelCentral.setBackground(BEIGE);
        
        JPanel panelFormulario = crearFormulario();
        
        JPanel panelInfo = crearInformacionContacto();
        
        panelCentral.add(panelFormulario);
        panelCentral.add(panelInfo);
        
        return panelCentral;
    }
    
    private JPanel crearFormulario() {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBackground(BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE, 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        JLabel lblFormulario = new JLabel("✉️ Envíanos un mensaje");
        lblFormulario.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        lblFormulario.setForeground(MARRON_OSCURO);
        
        // Campos del formulario
        JPanel panelCampos = crearCamposFormulario();
        
        panel.add(lblFormulario, BorderLayout.NORTH);
        panel.add(panelCampos, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearCamposFormulario() {
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));
        panelCampos.setBackground(BLANCO);
        
        // Campo Nombre
        panelCampos.add(crearCampoTexto("Nombre *", true));
        panelCampos.add(Box.createVerticalStrut(18));
        
        // Campo Correo
        panelCampos.add(crearCampoTexto("Correo Electrónico *", false));
        panelCampos.add(Box.createVerticalStrut(18));
        
        // Campo Asunto
        panelCampos.add(crearCampoAsunto());
        panelCampos.add(Box.createVerticalStrut(18));
        
        // Campo Mensaje
        panelCampos.add(crearCampoMensaje());
        panelCampos.add(Box.createVerticalStrut(25));
        
        // Botón Enviar
        panelCampos.add(crearBotonEnviar());
        
        return panelCampos;
    }
    
    private JPanel crearCampoTexto(String etiqueta, boolean esNombre) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BLANCO);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        lbl.setForeground(MARRON_OSCURO);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextField txt = new JTextField();
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        if (esNombre) {
            txtNombre = txt;
        } else {
            txtCorreo = txt;
        }
        
        panel.add(lbl);
        panel.add(Box.createVerticalStrut(6));
        panel.add(txt);
        
        return panel;
    }
    
    private JPanel crearCampoAsunto() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BLANCO);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lbl = new JLabel("Asunto *");
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        lbl.setForeground(MARRON_OSCURO);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        cboAsunto = new JComboBox<>(new String[]{
            "Seleccionar asunto",
            "Consulta General",
            "Información de Producto",
            "Pedido",
            "Devolución",
            "Garantía",
            "Sugerencia",
            "Otro"
        });
        cboAsunto.setFont(new Font("Arial", Font.PLAIN, 14));
        cboAsunto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cboAsunto.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(lbl);
        panel.add(Box.createVerticalStrut(6));
        panel.add(cboAsunto);
        
        return panel;
    }
    
    private JPanel crearCampoMensaje() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BLANCO);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lbl = new JLabel("Mensaje *");
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        lbl.setForeground(MARRON_OSCURO);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        txtMensaje = new JTextArea(5, 30);
        txtMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        JScrollPane scrollMensaje = new JScrollPane(txtMensaje);
        scrollMensaje.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
        scrollMensaje.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollMensaje.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));
        
        panel.add(lbl);
        panel.add(Box.createVerticalStrut(6));
        panel.add(scrollMensaje);
        
        return panel;
    }
    
    private JButton crearBotonEnviar() {
        JButton btnEnviar = new JButton("📧 Enviar Mensaje");
        btnEnviar.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        btnEnviar.setForeground(BLANCO);
        btnEnviar.setBackground(AMARILLO_BOTON);
        btnEnviar.setBorderPainted(false);
        btnEnviar.setFocusPainted(false);
        btnEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEnviar.setMaximumSize(new Dimension(220, 45));
        btnEnviar.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Efecto hover
        btnEnviar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEnviar.setBackground(new Color(200, 150, 25));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btnEnviar.setBackground(AMARILLO_BOTON);
            }
        });
        
        btnEnviar.addActionListener(e -> enviarMensaje());
        
        return btnEnviar;
    }
    
    private JPanel crearInformacionContacto() {
        JPanel panel = new JPanel(new BorderLayout(0, 25));
        panel.setBackground(BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE, 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        JLabel lblTitulo = new JLabel("Información de Contacto");
        lblTitulo.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        lblTitulo.setForeground(MARRON_OSCURO);
        
        JPanel panelDatos = crearDatosContacto();
        
        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(panelDatos, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearDatosContacto() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BLANCO);
        
        panel.add(crearSeccionContacto(
            "📱 Teléfono",
            new String[]{"+57 314 567 8940"}
        ));
        panel.add(Box.createVerticalStrut(25));
        
        panel.add(crearSeccionContacto(
            "💬 WhatsApp",
            new String[]{"+57 314 567 8940"}
        ));
        panel.add(Box.createVerticalStrut(25));
        
        panel.add(crearSeccionContacto(
            "✉️ Correo Electrónico",
            new String[]{
                "admin@doscaminos.com",
                "tienda@doscaminos.com"
            }
        ));
        panel.add(Box.createVerticalStrut(25));
        
        panel.add(crearSeccionContacto(
            "🕒 Horario de Atención",
            new String[]{
                "Lunes a Viernes: 8:00 AM - 6:00 PM",
                "Sábados: 9:00 AM - 2:00 PM",
                "Domingos: Cerrado"
            }
        ));
        panel.add(Box.createVerticalStrut(25));
        
        panel.add(crearSeccionContacto(
            "📍 Dirección",
            new String[]{
                "Calle Principal #45-67",
                "La Ceja, Antioquia",
                "Colombia"
            }
        ));
        
        return panel;
    }
    
    private JPanel crearSeccionContacto(String titulo, String[] datos) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BLANCO);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        lblTitulo.setForeground(MARRON_OSCURO);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(8));
        
        for (String dato : datos) {
            JLabel lblDato = new JLabel(dato);
            lblDato.setFont(new Font("Arial", Font.PLAIN, 14));
            lblDato.setForeground(GRIS_TEXTO);
            lblDato.setBorder(BorderFactory.createEmptyBorder(0, 25, 3, 0));
            lblDato.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(lblDato);
        }
        
        return panel;
    }
    
    
    private void enviarMensaje() {
      
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        int asuntoIndex = cboAsunto.getSelectedIndex();
        String mensaje = txtMensaje.getText().trim();
        
        if (nombre.isEmpty()) {
            mostrarError("Por favor, ingrese su nombre");
            txtNombre.requestFocus();
            return;
        }
        
        if (correo.isEmpty()) {
            mostrarError("Por favor, ingrese su correo electrónico");
            txtCorreo.requestFocus();
            return;
        }
        
        if (!validarCorreo(correo)) {
            mostrarError("Por favor, ingrese un correo electrónico válido");
            txtCorreo.requestFocus();
            return;
        }
        
        if (asuntoIndex == 0) {
            mostrarError("Por favor, seleccione un asunto");
            cboAsunto.requestFocus();
            return;
        }
        
        if (mensaje.isEmpty()) {
            mostrarError("Por favor, escriba su mensaje");
            txtMensaje.requestFocus();
            return;
        }
        
        if (mensaje.length() < 10) {
            mostrarError("El mensaje debe tener al menos 10 caracteres");
            txtMensaje.requestFocus();
            return;
        }
        
        enviarMensajeAlAdmin(nombre, correo, cboAsunto.getSelectedItem().toString(), mensaje);
    }
    
    private boolean validarCorreo(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return correo.matches(regex);
    }
    
    private void enviarMensajeAlAdmin(String nombre, String correo, String asunto, String mensaje) {
       
        
        String mensajeCompleto = String.format(
            "NUEVO MENSAJE DE CONTACTO\n\n" +
            "De: %s\n" +
            "Correo: %s\n" +
            "Asunto: %s\n\n" +
            "Mensaje:\n%s\n\n" +
            "-------------------------------\n" +
            "Este mensaje ha sido enviado al administrador.",
            nombre, correo, asunto, mensaje
        );
        
        JOptionPane.showMessageDialog(
            this,
            "¡Mensaje enviado correctamente!\n\n" +
            "Hemos recibido tu mensaje.\n" +
            "Nos pondremos en contacto contigo pronto.",
            "Mensaje Enviado",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        System.out.println("\n" + mensajeCompleto);
        
        limpiarFormulario();
    }
    
    private void limpiarFormulario() {
        txtNombre.setText("");
        txtCorreo.setText("");
        cboAsunto.setSelectedIndex(0);
        txtMensaje.setText("");
        txtNombre.requestFocus();
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "⚠️ Campo Requerido",
            JOptionPane.WARNING_MESSAGE
        );
    }
}