package VistaInicioSesion.VistaSupervisor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCostos extends JPanel {
    
    // Colores
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color MARRON_TARJETA = new Color(139, 99, 76);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color VERDE_INGRESO = new Color(34, 197, 94);
    private final Color ROJO_EGRESO = new Color(239, 68, 68);
    private final Color GRIS_BORDE = new Color(200, 180, 160);
    
    private JLabel lblMontoIngresos;
    private JLabel lblMontoEgresos;
    private DefaultTableModel modeloTabla;
    private JTable tablaCostos;
    
    private double totalIngresos = 0;
    private double totalEgresos = 0;
    
    private JTextField txtFecha;
    private JTextField txtConcepto;
    private JComboBox<String> cboTipo;
    private JTextField txtMonto;
    
    public PanelCostos() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 1020, 775);
        
        // Header con título y usuario
        JLabel lblTitulo = new JLabel("Costos");
        lblTitulo.setBounds(20, 20, 300, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(MARRON_OSCURO);
        add(lblTitulo);
        
        // Iconos de notificación y usuario
        JLabel lblNotificacion = new JLabel("🔔");
        lblNotificacion.setBounds(900, 25, 35, 35);
        lblNotificacion.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        add(lblNotificacion);
        
        JLabel lblUsuario = new JLabel("👤 Supervisor");
        lblUsuario.setBounds(925, 28, 120, 30);
        lblUsuario.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblUsuario.setForeground(MARRON_OSCURO);
        add(lblUsuario);
        
        crearPanelRegistro();
    }
    
    private void crearPanelRegistro() {
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(null);
        panelRegistro.setBackground(BLANCO);
        panelRegistro.setBounds(20, 90, 980, 665);
        panelRegistro.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        // Título del registro
        JLabel lblRegistro = new JLabel("Registro de Costos");
        lblRegistro.setBounds(25, 25, 500, 35);
        lblRegistro.setFont(new Font("Arial", Font.BOLD, 26));
        lblRegistro.setForeground(MARRON_OSCURO);
        panelRegistro.add(lblRegistro);
        
        // Botón Nuevo Registro
        JButton btnNuevoRegistro = new JButton("Nuevo Registro");
        btnNuevoRegistro.setBounds(835, 25, 130, 35);
        btnNuevoRegistro.setBackground(AMARILLO_BOTON);
        btnNuevoRegistro.setForeground(MARRON_OSCURO);
        btnNuevoRegistro.setFont(new Font("Arial", Font.BOLD, 13));
        btnNuevoRegistro.setFocusPainted(false);
        btnNuevoRegistro.setBorderPainted(false);
        btnNuevoRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNuevoRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDialogoNuevoRegistro();
            }
        });
        panelRegistro.add(btnNuevoRegistro);
        
        // Tarjeta Ingresos del mes
        JPanel tarjetaIngresos = crearTarjetaIngresos();
        panelRegistro.add(tarjetaIngresos);
        
        // Tarjeta Egresos del mes
        JPanel tarjetaEgresos = crearTarjetaEgresos();
        panelRegistro.add(tarjetaEgresos);
        
        // Crear tabla de costos
        crearTablaCostos(panelRegistro);
        
        add(panelRegistro);
    }
    
    private JPanel crearTarjetaIngresos() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(MARRON_TARJETA);
        panel.setBounds(25, 85, 455, 95);
        panel.setBorder(BorderFactory.createLineBorder(MARRON_TARJETA, 2));
        
        // Título de la tarjeta
        JLabel lblTitulo = new JLabel("Ingresos del mes");
        lblTitulo.setBounds(20, 15, 300, 25);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTitulo.setForeground(BLANCO);
        panel.add(lblTitulo);
        
        // Monto dinámico
        lblMontoIngresos = new JLabel("$0");
        lblMontoIngresos.setBounds(20, 40, 400, 30);
        lblMontoIngresos.setFont(new Font("Arial", Font.BOLD, 24));
        lblMontoIngresos.setForeground(VERDE_INGRESO);
        panel.add(lblMontoIngresos);
        
        return panel;
    }
    
    private JPanel crearTarjetaEgresos() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(MARRON_TARJETA);
        panel.setBounds(500, 85, 455, 95);
        panel.setBorder(BorderFactory.createLineBorder(MARRON_TARJETA, 2));
        
        // Título de la tarjeta
        JLabel lblTitulo = new JLabel("Egresos del mes");
        lblTitulo.setBounds(20, 15, 300, 25);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTitulo.setForeground(BLANCO);
        panel.add(lblTitulo);
        
        // Monto dinámico
        lblMontoEgresos = new JLabel("$0");
        lblMontoEgresos.setBounds(20, 40, 400, 30);
        lblMontoEgresos.setFont(new Font("Arial", Font.BOLD, 24));
        lblMontoEgresos.setForeground(ROJO_EGRESO);
        panel.add(lblMontoEgresos);
        
        return panel;
    }
    
    private void crearTablaCostos(JPanel panelRegistro) {
        // Columnas de la tabla
        String[] columnas = {"Fecha", "Concepto", "Tipo", "Monto"};
        
        // Modelo de tabla (no editable)
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Crear tabla
        tablaCostos = new JTable(modeloTabla);
        tablaCostos.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaCostos.setRowHeight(45);
        tablaCostos.setBackground(BLANCO);
        tablaCostos.setSelectionBackground(new Color(245, 240, 230));
        tablaCostos.setSelectionForeground(MARRON_OSCURO);
        tablaCostos.setShowGrid(true);
        tablaCostos.setGridColor(GRIS_BORDE);
        
        // Estilo del header
        JTableHeader header = tablaCostos.getTableHeader();
        header.setBackground(new Color(250, 250, 250));
        header.setForeground(MARRON_OSCURO);
        header.setFont(new Font("Arial", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Renderer para la columna Tipo
        tablaCostos.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setHorizontalAlignment(SwingConstants.LEFT);
                label.setFont(new Font("Arial", Font.PLAIN, 14));
                label.setForeground(Color.GRAY);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return label;
            }
        });
        
        // Renderer para la columna Monto (con colores)
        tablaCostos.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setHorizontalAlignment(SwingConstants.LEFT);
                label.setFont(new Font("Arial", Font.BOLD, 15));
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                String monto = value.toString();
                if (monto.startsWith("+")) {
                    label.setForeground(VERDE_INGRESO);
                } else if (monto.startsWith("-")) {
                    label.setForeground(ROJO_EGRESO);
                } else {
                    label.setForeground(MARRON_OSCURO);
                }
                
                return label;
            }
        });
        
        // Ajustar ancho de columnas
        tablaCostos.getColumnModel().getColumn(0).setPreferredWidth(150);
        tablaCostos.getColumnModel().getColumn(1).setPreferredWidth(300);
        tablaCostos.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablaCostos.getColumnModel().getColumn(3).setPreferredWidth(250);
        
        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaCostos);
        scrollPane.setBounds(25, 200, 930, 440);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        panelRegistro.add(scrollPane);
    }
    
    private void abrirDialogoNuevoRegistro() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Nuevo Registro de Costo", true);
        dialog.setSize(550, 450);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(BEIGE);
        
        JLabel lblTitulo = new JLabel("Nuevo Registro de Costo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(MARRON_OSCURO);
        lblTitulo.setBounds(30, 20, 400, 30);
        dialog.add(lblTitulo);
        
        // Panel del formulario
        JPanel panelForm = new JPanel();
        panelForm.setLayout(null);
        panelForm.setBackground(BLANCO);
        panelForm.setBounds(30, 70, 480, 280);
        panelForm.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        dialog.add(panelForm);
        
        // Campo Fecha
        JLabel lblFecha = new JLabel("Fecha *");
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 13));
        lblFecha.setForeground(MARRON_OSCURO);
        lblFecha.setBounds(20, 20, 200, 20);
        panelForm.add(lblFecha);
        
        txtFecha = new JTextField("dd/mm/aaaa");
        txtFecha.setBounds(20, 45, 200, 36);
        txtFecha.setFont(new Font("Arial", Font.PLAIN, 13));
        txtFecha.setForeground(Color.GRAY);
        txtFecha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panelForm.add(txtFecha);
        
        // Campo Tipo
        JLabel lblTipo = new JLabel("Tipo *");
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTipo.setForeground(MARRON_OSCURO);
        lblTipo.setBounds(260, 20, 200, 20);
        panelForm.add(lblTipo);
        
        cboTipo = new JComboBox<>(new String[]{"Seleccionar tipo", "Ingreso", "Egreso"});
        cboTipo.setFont(new Font("Arial", Font.PLAIN, 13));
        cboTipo.setBounds(260, 45, 200, 36);
        cboTipo.setBackground(BLANCO);
        cboTipo.setForeground(MARRON_OSCURO);
        panelForm.add(cboTipo);
        
        // Campo Concepto
        JLabel lblConcepto = new JLabel("Concepto *");
        lblConcepto.setFont(new Font("Arial", Font.PLAIN, 13));
        lblConcepto.setForeground(MARRON_OSCURO);
        lblConcepto.setBounds(20, 105, 440, 20);
        panelForm.add(lblConcepto);
        
        txtConcepto = new JTextField("");
        txtConcepto.setBounds(20, 130, 440, 36);
        txtConcepto.setFont(new Font("Arial", Font.PLAIN, 13));
        txtConcepto.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panelForm.add(txtConcepto);
        
        // Campo Monto
        JLabel lblMonto = new JLabel("Monto *");
        lblMonto.setFont(new Font("Arial", Font.PLAIN, 13));
        lblMonto.setForeground(MARRON_OSCURO);
        lblMonto.setBounds(20, 190, 440, 20);
        panelForm.add(lblMonto);
        
        txtMonto = new JTextField("");
        txtMonto.setBounds(20, 215, 440, 36);
        txtMonto.setFont(new Font("Arial", Font.PLAIN, 13));
        txtMonto.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panelForm.add(txtMonto);
        
        JLabel lblObligatorio = new JLabel("* Campos obligatorios");
        lblObligatorio.setFont(new Font("Arial", Font.ITALIC, 11));
        lblObligatorio.setForeground(Color.RED);
        lblObligatorio.setBounds(30, 360, 200, 20);
        dialog.add(lblObligatorio);
        
        // Botones
        JButton btnGuardar = new JButton("💾 Guardar");
        btnGuardar.setBounds(260, 380, 120, 35);
        btnGuardar.setBackground(AMARILLO_BOTON);
        btnGuardar.setForeground(MARRON_OSCURO);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 13));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarRegistro(dialog);
            }
        });
        dialog.add(btnGuardar);
        
        JButton btnCancelar = new JButton("✖ Cancelar");
        btnCancelar.setBounds(390, 380, 120, 35);
        btnCancelar.setBackground(new Color(220, 220, 220));
        btnCancelar.setForeground(MARRON_OSCURO);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.add(btnCancelar);
        
        dialog.setVisible(true);
    }
    
    private void guardarRegistro(JDialog dialog) {
        // Obtener valores
        String fecha = txtFecha.getText().trim();
        String concepto = txtConcepto.getText().trim();
        String tipo = (String) cboTipo.getSelectedItem();
        String montoStr = txtMonto.getText().trim();
        
        // Validar campos vacíos
        if (fecha.isEmpty() || fecha.equals("dd/mm/aaaa")) {
            JOptionPane.showMessageDialog(dialog,
                "Por favor ingrese la fecha",
                "Campo Vacío",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (tipo.equals("Seleccionar tipo")) {
            JOptionPane.showMessageDialog(dialog,
                "Por favor seleccione el tipo (Ingreso o Egreso)",
                "Campo Vacío",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (concepto.isEmpty()) {
            JOptionPane.showMessageDialog(dialog,
                "Por favor ingrese el concepto",
                "Campo Vacío",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (montoStr.isEmpty()) {
            JOptionPane.showMessageDialog(dialog,
                "Por favor ingrese el monto",
                "Campo Vacío",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar que el monto sea un número
        try {
            double monto = Double.parseDouble(montoStr.replaceAll("[^0-9.]", ""));
            
            // Formatear monto con símbolo
            String montoFormateado;
            if (tipo.equals("Ingreso")) {
                montoFormateado = String.format("+ $%,.0f", monto);
                totalIngresos += monto;
            } else {
                montoFormateado = String.format("- $%,.0f", monto);
                totalEgresos += monto;
            }
            
            // Reemplazar coma por punto en el formato
            montoFormateado = montoFormateado.replace(",", ".");
            
            // Agregar a la tabla
            modeloTabla.addRow(new Object[]{fecha, concepto, tipo, montoFormateado});
            
            // Actualizar tarjetas de totales
            actualizarTotales();
            
            // Mensaje de éxito
            JOptionPane.showMessageDialog(dialog,
                "¡Registro guardado exitosamente!" +
                "Fecha: " + fecha + "\n" +
                "Concepto: " + concepto + "\n" +
                "Tipo: " + tipo + "\n" +
                "Monto: " + montoFormateado,
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
            
            dialog.dispose();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(dialog,
                "El monto debe ser un número válido",
                "Error de Formato",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarTotales() {
        // Formatear y actualizar ingresos
        String ingresosFormateado = String.format("$%,.0f", totalIngresos);
        ingresosFormateado = ingresosFormateado.replace(",", ".");
        lblMontoIngresos.setText(ingresosFormateado);
        
        // Formatear y actualizar egresos
        String egresosFormateado = String.format("$%,.0f", totalEgresos);
        egresosFormateado = egresosFormateado.replace(",", ".");
        lblMontoEgresos.setText(egresosFormateado);
    }
}