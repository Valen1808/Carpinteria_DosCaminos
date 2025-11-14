package VistaInicioSesion.VistaOperario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PanelReportarProblemas extends JPanel {
    
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color GRIS_BORDE = new Color(200, 180, 160);
    private final Color ROJO_URGENCIA = new Color(252, 165, 165);
    private final Color AMARILLO_URGENCIA = new Color(254, 240, 138);
    private final Color VERDE_URGENCIA = new Color(187, 247, 208);
    
    // Componentes del formulario
    private JComboBox<String> cboTipoProblema;
    private JTextField txtEquipoMaterial;
    private JTextArea txtDescripcion;
    private ButtonGroup grupoUrgencia;
    private JRadioButton rbBaja, rbMedia, rbAlta;
    
    private DefaultTableModel modeloTabla;
    private JTable tablaReportes;
    
    public PanelReportarProblemas() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 1020, 775);
        
        JLabel lblTitulo = new JLabel("Reportar problemas");
        lblTitulo.setBounds(20, 20, 450, 40);
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
        
        crearPanelFormulario();
        crearPanelReportesRecientes();
    }
    
    private void crearPanelFormulario() {
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        panelFormulario.setBackground(BLANCO);
        panelFormulario.setBounds(20, 90, 980, 370);
        panelFormulario.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblReportar = new JLabel("Reportar problemas");
        lblReportar.setBounds(25, 25, 500, 35);
        lblReportar.setFont(new Font("Arial", Font.BOLD, 26));
        lblReportar.setForeground(MARRON_OSCURO);
        panelFormulario.add(lblReportar);
        
        JLabel lblTipoProblema = new JLabel("Tipo de problema");
        lblTipoProblema.setBounds(25, 80, 200, 20);
        lblTipoProblema.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTipoProblema.setForeground(MARRON_OSCURO);
        panelFormulario.add(lblTipoProblema);
        
        String[] tiposProblemas = {
            "Seleccionar tipo",
            "Falla en maquinaria",
            "Material defectuoso",
            "Herramienta dañada",
            "Problema de seguridad",
            "Falta de suministros",
            "Otro"
        };
        cboTipoProblema = new JComboBox<>(tiposProblemas);
        cboTipoProblema.setBounds(25, 105, 930, 40);
        cboTipoProblema.setFont(new Font("Arial", Font.PLAIN, 14));
        cboTipoProblema.setBackground(BLANCO);
        panelFormulario.add(cboTipoProblema);
        
        
        JLabel lblEquipoMaterial = new JLabel("Equipo/Material afectado");
        lblEquipoMaterial.setBounds(25, 160, 250, 20);
        lblEquipoMaterial.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEquipoMaterial.setForeground(MARRON_OSCURO);
        panelFormulario.add(lblEquipoMaterial);
        
        txtEquipoMaterial = new JTextField();
        txtEquipoMaterial.setBounds(25, 185, 930, 40);
        txtEquipoMaterial.setFont(new Font("Arial", Font.PLAIN, 14));
        txtEquipoMaterial.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        agregarPlaceholder(txtEquipoMaterial, "Ej: Sierra Circular, Madera de Roble, etc.");
        panelFormulario.add(txtEquipoMaterial);
        
        JLabel lblDescripcion = new JLabel("Descripción del problema");
        lblDescripcion.setBounds(25, 240, 250, 20);
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDescripcion.setForeground(MARRON_OSCURO);
        panelFormulario.add(lblDescripcion);
        
        txtDescripcion = new JTextArea();
        txtDescripcion.setBounds(25, 265, 630, 90);
        txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        agregarPlaceholderTextArea(txtDescripcion, "Describe el problema con el mayor detalle posible...");
        panelFormulario.add(txtDescripcion);
        
        JLabel lblUrgencia = new JLabel("Urgencia");
        lblUrgencia.setBounds(680, 240, 100, 20);
        lblUrgencia.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUrgencia.setForeground(MARRON_OSCURO);
        panelFormulario.add(lblUrgencia);
        
        grupoUrgencia = new ButtonGroup();
        
        rbBaja = new JRadioButton("Baja");
        rbBaja.setBounds(680, 270, 80, 25);
        rbBaja.setFont(new Font("Arial", Font.PLAIN, 13));
        rbBaja.setForeground(MARRON_OSCURO);
        rbBaja.setBackground(BLANCO);
        rbBaja.setFocusPainted(false);
        grupoUrgencia.add(rbBaja);
        panelFormulario.add(rbBaja);
        
        rbMedia = new JRadioButton("Media");
        rbMedia.setBounds(770, 270, 80, 25);
        rbMedia.setFont(new Font("Arial", Font.PLAIN, 13));
        rbMedia.setForeground(MARRON_OSCURO);
        rbMedia.setBackground(BLANCO);
        rbMedia.setFocusPainted(false);
        rbMedia.setSelected(true); 
        grupoUrgencia.add(rbMedia);
        panelFormulario.add(rbMedia);
        
        rbAlta = new JRadioButton("Alta");
        rbAlta.setBounds(865, 270, 80, 25);
        rbAlta.setFont(new Font("Arial", Font.PLAIN, 13));
        rbAlta.setForeground(MARRON_OSCURO);
        rbAlta.setBackground(BLANCO);
        rbAlta.setFocusPainted(false);
        grupoUrgencia.add(rbAlta);
        panelFormulario.add(rbAlta);
        
        JButton btnEnviarReporte = new JButton("Enviar Reporte");
        btnEnviarReporte.setBounds(680, 315, 275, 40);
        btnEnviarReporte.setBackground(AMARILLO_BOTON);
        btnEnviarReporte.setForeground(MARRON_OSCURO);
        btnEnviarReporte.setFont(new Font("Arial", Font.BOLD, 14));
        btnEnviarReporte.setFocusPainted(false);
        btnEnviarReporte.setBorderPainted(false);
        btnEnviarReporte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEnviarReporte.addActionListener(e -> enviarReporte());
        panelFormulario.add(btnEnviarReporte);
        
        add(panelFormulario);
    }
    
    private void crearPanelReportesRecientes() {
        JPanel panelReportesRecientes = new JPanel();
        panelReportesRecientes.setLayout(null);
        panelReportesRecientes.setBackground(BLANCO);
        panelReportesRecientes.setBounds(20, 480, 980, 275);
        panelReportesRecientes.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblReportesRecientes = new JLabel("Mis Reportes Recientes");
        lblReportesRecientes.setBounds(25, 25, 500, 35);
        lblReportesRecientes.setFont(new Font("Arial", Font.BOLD, 26));
        lblReportesRecientes.setForeground(MARRON_OSCURO);
        panelReportesRecientes.add(lblReportesRecientes);
        
        crearTablaReportes(panelReportesRecientes);
        
        add(panelReportesRecientes);
    }
    
    private void crearTablaReportes(JPanel panel) {
     
        String[] columnas = {"Fecha", "Tipo", "Equipo/Material", "Descripción", "Urgencia"};
        
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaReportes = new JTable(modeloTabla);
        tablaReportes.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaReportes.setRowHeight(45);
        tablaReportes.setBackground(BLANCO);
        tablaReportes.setSelectionBackground(new Color(245, 240, 230));
        tablaReportes.setSelectionForeground(MARRON_OSCURO);
        tablaReportes.setShowGrid(true);
        tablaReportes.setGridColor(GRIS_BORDE);
        
        JTableHeader header = tablaReportes.getTableHeader();
        header.setBackground(MARRON_OSCURO);
        header.setForeground(BLANCO);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value != null ? value.toString() : "");
                label.setHorizontalAlignment(SwingConstants.LEFT);
                label.setFont(new Font("Arial", Font.PLAIN, 13));
                label.setForeground(Color.GRAY);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                label.setOpaque(true);
                
                if (isSelected) {
                    label.setBackground(new Color(245, 240, 230));
                } else {
                    label.setBackground(BLANCO);
                }
                
                return label;
            }
        };
        
        for (int i = 0; i < 4; i++) {
            tablaReportes.getColumnModel().getColumn(i).setCellRenderer(textRenderer);
        }
        
        tablaReportes.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
                
                JLabel label = new JLabel(value != null ? value.toString() : "");
                label.setOpaque(true);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 12));
                label.setForeground(MARRON_OSCURO);
                label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(GRIS_BORDE, 1),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
                
                String urgencia = value != null ? value.toString() : "";
                if (urgencia.equals("Alta")) {
                    label.setBackground(ROJO_URGENCIA);
                } else if (urgencia.equals("Media")) {
                    label.setBackground(AMARILLO_URGENCIA);
                } else if (urgencia.equals("Baja")) {
                    label.setBackground(VERDE_URGENCIA);
                } else {
                    label.setBackground(Color.WHITE);
                }
                
                panel.add(label);
                panel.setBackground(BLANCO);
                
                if (isSelected) {
                    panel.setBackground(new Color(245, 240, 230));
                }
                
                return panel;
            }
        });
        
        tablaReportes.getColumnModel().getColumn(0).setPreferredWidth(100);  // Fecha
        tablaReportes.getColumnModel().getColumn(1).setPreferredWidth(150);  // Tipo
        tablaReportes.getColumnModel().getColumn(2).setPreferredWidth(180);  // Equipo/Material
        tablaReportes.getColumnModel().getColumn(3).setPreferredWidth(350);  // Descripción
        tablaReportes.getColumnModel().getColumn(4).setPreferredWidth(100);  // Urgencia
        
        JScrollPane scrollPane = new JScrollPane(tablaReportes);
        scrollPane.setBounds(25, 75, 930, 180);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        panel.add(scrollPane);
    }
    
    private void enviarReporte() {
        if (cboTipoProblema.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona un tipo de problema", 
                "Campo requerido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String equipo = txtEquipoMaterial.getText().trim();
        if (equipo.isEmpty() || equipo.equals("Ej: Sierra Circular, Madera de Roble, etc.")) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingresa el equipo o material afectado", 
                "Campo requerido", 
                JOptionPane.WARNING_MESSAGE);
            txtEquipoMaterial.requestFocus();
            return;
        }
        
        String descripcion = txtDescripcion.getText().trim();
        if (descripcion.isEmpty() || descripcion.equals("Describe el problema con el mayor detalle posible...")) {
            JOptionPane.showMessageDialog(this, 
                "Por favor describe el problema", 
                "Campo requerido", 
                JOptionPane.WARNING_MESSAGE);
            txtDescripcion.requestFocus();
            return;
        }
        
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        String tipo = cboTipoProblema.getSelectedItem().toString();
        String urgencia = rbAlta.isSelected() ? "Alta" : 
                         rbMedia.isSelected() ? "Media" : "Baja";
        
        modeloTabla.addRow(new Object[]{fecha, tipo, equipo, descripcion, urgencia});
        
        JOptionPane.showMessageDialog(this, 
            "¡Reporte enviado exitosamente!\n\nTu reporte ha sido registrado y será atendido pronto.", 
            "Reporte Enviado", 
            JOptionPane.INFORMATION_MESSAGE);
        
        limpiarFormulario();
    }
    
    private void limpiarFormulario() {
        cboTipoProblema.setSelectedIndex(0);
        txtEquipoMaterial.setText("");
        agregarPlaceholder(txtEquipoMaterial, "Ej: Sierra Circular, Madera de Roble, etc.");
        txtDescripcion.setText("");
        agregarPlaceholderTextArea(txtDescripcion, "Describe el problema con el mayor detalle posible...");
        rbMedia.setSelected(true);
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
    
    private void agregarPlaceholderTextArea(JTextArea textArea, String placeholder) {
        textArea.setForeground(Color.GRAY);
        textArea.setText(placeholder);
        
        textArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals(placeholder)) {
                    textArea.setText("");
                    textArea.setForeground(MARRON_OSCURO);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (textArea.getText().isEmpty()) {
                    textArea.setForeground(Color.GRAY);
                    textArea.setText(placeholder);
                }
            }
        });
    }
}