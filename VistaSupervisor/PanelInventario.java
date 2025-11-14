package VistaInicioSesion.VistaSupervisor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInventario extends JPanel {

    // Colores
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(240, 226, 200);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color ROJO_CRITICO = new Color(248, 113, 113);
    private final Color AMARILLO_BAJO = new Color(250, 204, 21);
    private final Color VERDE_NORMAL = new Color(134, 239, 172);
    private final Color GRIS_BORDE = new Color(210, 190, 165);
    private final Color GRIS_TEXTO = new Color(90, 90, 90);

    // Componentes de formulario
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox<String> cboCategoria;
    private JComboBox<String> cboUnidad;
    private JTextField txtStockInicial;
    private JTextField txtStockMinimo;
    private JTextField txtPrecio;
    private JComboBox<String> cboProveedor;
    
    // Tabla
    private DefaultTableModel modeloTabla;
    private JTable tablaMateriales;

    public PanelInventario() {
        setLayout(null);
        setBackground(BEIGE);
        initComponents();
    }

    private void initComponents() {
        // Título
        JLabel lblTitulo = new JLabel("Inventario");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitulo.setForeground(MARRON_OSCURO);
        lblTitulo.setBounds(40, 25, 300, 40);
        add(lblTitulo);

        // Notificación y usuario
        JLabel lblNotificacion = new JLabel("🔔");
        lblNotificacion.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        lblNotificacion.setBounds(880, 28, 40, 40);
        add(lblNotificacion);

        JLabel lblUsuario = new JLabel("👤 Supervisor");
        lblUsuario.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblUsuario.setForeground(MARRON_OSCURO);
        lblUsuario.setBounds(915, 35, 120, 25);
        add(lblUsuario);

        // Panel de Gestión de Inventario
        crearPanelGestion();
    }

    private void crearPanelGestion() {
        JPanel panelGestion = new JPanel();
        panelGestion.setLayout(null);
        panelGestion.setBackground(BLANCO);
        panelGestion.setBounds(40, 90, 940, 650);
        panelGestion.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));

        JLabel lblGestion = new JLabel("Gestión de Inventario");
        lblGestion.setFont(new Font("Arial", Font.BOLD, 22));
        lblGestion.setForeground(MARRON_OSCURO);
        lblGestion.setBounds(25, 20, 400, 30);
        panelGestion.add(lblGestion);

        // Botón Registrar Nuevo Material
        JButton btnNuevoMaterial = crearBoton("Registrar Nuevo Material");
        btnNuevoMaterial.setBounds(680, 20, 230, 35);
        btnNuevoMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDialogoRegistro();
            }
        });
        panelGestion.add(btnNuevoMaterial);

        // Buscador
        JTextField txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Arial", Font.PLAIN, 14));
        txtBuscar.setForeground(Color.GRAY);
        txtBuscar.setText(" Buscar Material ");
        txtBuscar.setBounds(25, 70, 885, 40);
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        panelGestion.add(txtBuscar);

        // Crear tabla de materiales
        crearTablaMateriales(panelGestion);

        add(panelGestion);
    }

    private void crearTablaMateriales(JPanel panelGestion) {
        // Columnas de la tabla
        String[] columnas = {"Código", "Material", "Categoría", "Stock Actual", "Stock Mínimo", "Unidad", "Estado", "Acciones"};

        // Modelo de tabla (no editable)
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Crear tabla
        tablaMateriales = new JTable(modeloTabla);
        tablaMateriales.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaMateriales.setRowHeight(40);
        tablaMateriales.setBackground(BLANCO);
        tablaMateriales.setSelectionBackground(new Color(245, 240, 230));
        tablaMateriales.setSelectionForeground(MARRON_OSCURO);
        tablaMateriales.setShowGrid(true);
        tablaMateriales.setGridColor(GRIS_BORDE);

        // Estilo del header
        JTableHeader header = tablaMateriales.getTableHeader();
        header.setBackground(new Color(250, 250, 250));
        header.setForeground(MARRON_OSCURO);
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        // Renderer para la columna Estado
        tablaMateriales.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setOpaque(true);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 12));
                label.setForeground(MARRON_OSCURO);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                if (value.equals("Crítico")) {
                    label.setBackground(ROJO_CRITICO);
                } else if (value.equals("Bajo")) {
                    label.setBackground(AMARILLO_BAJO);
                } else if (value.equals("Normal")) {
                    label.setBackground(VERDE_NORMAL);
                } else {
                    label.setBackground(Color.WHITE);
                }

                return label;
            }
        });

        // Renderer para la columna Acciones
        tablaMateriales.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
                return label;
            }
        });

        // Ajustar ancho de columnas
        tablaMateriales.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaMateriales.getColumnModel().getColumn(1).setPreferredWidth(180);
        tablaMateriales.getColumnModel().getColumn(2).setPreferredWidth(120);
        tablaMateriales.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaMateriales.getColumnModel().getColumn(4).setPreferredWidth(110);
        tablaMateriales.getColumnModel().getColumn(5).setPreferredWidth(90);
        tablaMateriales.getColumnModel().getColumn(6).setPreferredWidth(100);
        tablaMateriales.getColumnModel().getColumn(7).setPreferredWidth(80);

        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaMateriales);
        scrollPane.setBounds(25, 130, 890, 490);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        panelGestion.add(scrollPane);
    }

    private void abrirDialogoRegistro() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Registrar Nuevo Material", true);
        dialog.setSize(650, 550);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(BEIGE);

        JLabel lblTitulo = new JLabel("Registrar Nuevo Material");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(MARRON_OSCURO);
        lblTitulo.setBounds(30, 20, 400, 30);
        dialog.add(lblTitulo);

        // Panel del formulario
        JPanel panelForm = new JPanel();
        panelForm.setLayout(null);
        panelForm.setBackground(BLANCO);
        panelForm.setBounds(30, 70, 580, 380);
        panelForm.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        dialog.add(panelForm);

        // Campos del formulario
        int y1 = 20;
        txtCodigo = crearCampo(panelForm, "Código *", "Ej: MAD-001", 20, y1, 250);
        txtNombre = crearCampo(panelForm, "Nombre del Material *", "Ej: Madera de Roble", 300, y1, 250);

        int y2 = 100;
        cboCategoria = crearCombo(panelForm, "Categoría *", 
            new String[]{"Seleccionar categoría", "Maderas", "Herrajes", "Pinturas", "Herramientas"}, 20, y2, 250);
        cboUnidad = crearCombo(panelForm, "Unidad de Medida *", 
            new String[]{"Seleccionar unidad", "m²", "m³", "kg", "unidad", "litros"}, 300, y2, 250);

        int y3 = 180;
        txtStockInicial = crearCampo(panelForm, "Stock Inicial *", "", 20, y3, 250);
        txtStockMinimo = crearCampo(panelForm, "Stock Mínimo *", "", 300, y3, 250);

        int y4 = 260;
        txtPrecio = crearCampo(panelForm, "Precio Unitario", "", 20, y4, 250);
        cboProveedor = crearCombo(panelForm, "Proveedor", 
            new String[]{"Seleccionar proveedor", "Maderas del Sur", "Herrajes Modernos", "Pinturas El Sol"}, 300, y4, 250);

        JLabel lblObligatorio = new JLabel("* Campos obligatorios");
        lblObligatorio.setFont(new Font("Arial", Font.ITALIC, 11));
        lblObligatorio.setForeground(Color.RED);
        lblObligatorio.setBounds(20, 340, 200, 20);
        panelForm.add(lblObligatorio);

        // Botones
        JButton btnGuardar = crearBoton("Guardar");
        btnGuardar.setBounds(360, 470, 120, 35);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarMaterial(dialog);
            }
        });
        dialog.add(btnGuardar);

        JButton btnCancelar = crearBoton("Cancelar");
        btnCancelar.setBounds(490, 470, 120, 35);
        btnCancelar.setBackground(new Color(220, 220, 220));
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.add(btnCancelar);

        dialog.setVisible(true);
    }

    private void guardarMaterial(JDialog dialog) {
        // Obtener valores
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String categoria = (String) cboCategoria.getSelectedItem();
        String unidad = (String) cboUnidad.getSelectedItem();
        String stockInicial = txtStockInicial.getText().trim();
        String stockMinimo = txtStockMinimo.getText().trim();

        // Validar campos obligatorios
        if (codigo.isEmpty() || codigo.equals("")) {
            JOptionPane.showMessageDialog(dialog,
                "Por favor ingrese el código del material",
                "Campo Vacío",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nombre.isEmpty() || nombre.equals("")) {
            JOptionPane.showMessageDialog(dialog,
                "Por favor ingrese el nombre del material",
                "Campo Vacío",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (categoria.equals("Seleccionar categoría")) {
            JOptionPane.showMessageDialog(dialog,
                "Por favor seleccione una categoría",
                "Campo Vacío",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (unidad.equals("Seleccionar unidad")) {
            JOptionPane.showMessageDialog(dialog,
                "Por favor seleccione una unidad de medida",
                "Campo Vacío",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (stockInicial.isEmpty()) {
            JOptionPane.showMessageDialog(dialog,
                "Por favor ingrese el stock inicial",
                "Campo Vacío",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (stockMinimo.isEmpty()) {
            JOptionPane.showMessageDialog(dialog,
                "Por favor ingrese el stock mínimo",
                "Campo Vacío",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que sean números
        try {
            int stock = Integer.parseInt(stockInicial);
            int minimo = Integer.parseInt(stockMinimo);

            // Determinar estado
            String estado;
            if (stock <= 0) {
                estado = "Crítico";
            } else if (stock < minimo) {
                estado = "Bajo";
            } else {
                estado = "Estable";
            }

            // Agregar a la tabla
            modeloTabla.addRow(new Object[]{codigo, nombre, categoria, stockInicial, stockMinimo, unidad, estado, "✏️"});

            // Mensaje de éxito
            JOptionPane.showMessageDialog(dialog,
                "¡Material registrado exitosamente!" +
                "Código: " + codigo + "\n" +
                "Material: " + nombre + "\n" +
                "Stock: " + stockInicial + " " + unidad,
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

            dialog.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(dialog,
                "El stock debe ser un número válido",
                "Error de Formato",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(AMARILLO_BOTON);
        btn.setForeground(MARRON_OSCURO);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JTextField crearCampo(JPanel p, String label, String placeholder, int x, int y, int w) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 13));
        lbl.setForeground(MARRON_OSCURO);
        lbl.setBounds(x, y, w, 20);
        p.add(lbl);

        JTextField txt = new JTextField(placeholder);
        txt.setBounds(x, y + 25, w, 36);
        txt.setFont(new Font("Arial", Font.PLAIN, 13));
        txt.setForeground(Color.GRAY);
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        p.add(txt);

        return txt;
    }

    private JComboBox<String> crearCombo(JPanel p, String label, String[] opciones, int x, int y, int w) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 13));
        lbl.setForeground(MARRON_OSCURO);
        lbl.setBounds(x, y, w, 20);
        p.add(lbl);

        JComboBox<String> combo = new JComboBox<>(opciones);
        combo.setFont(new Font("Arial", Font.PLAIN, 13));
        combo.setBounds(x, y + 25, w, 36);
        combo.setBackground(BLANCO);
        combo.setForeground(MARRON_OSCURO);
        p.add(combo);

        return combo;
    }
}