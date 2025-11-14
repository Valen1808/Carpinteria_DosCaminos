package VistaInicioSesion.VistaSupervisor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPedidos extends JPanel {
    
    // Colores
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color AMARILLO_PENDIENTE = new Color(254, 240, 138);
    private final Color VERDE_APROBADO = new Color(134, 239, 172);
    private final Color GRIS_BORDE = new Color(200, 180, 160);
    
    // Componentes del formulario
    private JPanel panelNuevoPedido;
    private JComboBox<String> cboProveedor;
    private JTextField txtFecha;
    private JComboBox<String> cboProducto;
    private JComboBox<String> cboCantidad;
    private JTextField txtPrecio;
    private JTextField txtTotalProducto;
    private JLabel lblTotalPedido;
    
    // Tabla de pedidos
    private DefaultTableModel modeloTabla;
    private JTable tablaPedidos;
    
    public PanelPedidos() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 1020, 775);
        
        // Header con título y usuario
        JLabel lblTitulo = new JLabel("Pedidos");
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
        lblUsuario.setBounds(930, 28, 120, 30);
        lblUsuario.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblUsuario.setForeground(MARRON_OSCURO);
        add(lblUsuario);
        
        // Panel de Gestión de Pedidos con tabla
        crearPanelGestionPedidos();
        
        // Panel de Nuevo Pedido 
        crearPanelNuevoPedido();
    }
    
    private void crearPanelGestionPedidos() {
        JPanel panelGestion = new JPanel();
        panelGestion.setLayout(null);
        panelGestion.setBackground(BLANCO);
        panelGestion.setBounds(20, 90, 980, 650);
        panelGestion.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblGestion = new JLabel("Gestión de Pedidos");
        lblGestion.setBounds(25, 20, 400, 30);
        lblGestion.setFont(new Font("Arial", Font.BOLD, 24));
        lblGestion.setForeground(MARRON_OSCURO);
        panelGestion.add(lblGestion);
        
        // Botón Hacer Pedido con ActionListener
        JButton btnHacerPedido = new JButton("Hacer Pedido");
        btnHacerPedido.setBounds(820, 20, 140, 35);
        btnHacerPedido.setBackground(AMARILLO_BOTON);
        btnHacerPedido.setForeground(MARRON_OSCURO);
        btnHacerPedido.setFont(new Font("Arial", Font.BOLD, 13));
        btnHacerPedido.setFocusPainted(false);
        btnHacerPedido.setBorderPainted(false);
        btnHacerPedido.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // ActionListener para mostrar/ocultar formulario
        btnHacerPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioNuevoPedido();
            }
        });
        
        panelGestion.add(btnHacerPedido);
        
        // Crear tabla de pedidos
        crearTablaPedidos(panelGestion);
        
        add(panelGestion);
    }
    
    private void crearTablaPedidos(JPanel panelGestion) {
        // Columnas de la tabla
        String[] columnas = {"Proveedor", "Fecha", "Total", "Estado", "Acciones"};
        
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaPedidos = new JTable(modeloTabla);
        tablaPedidos.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaPedidos.setRowHeight(45);
        tablaPedidos.setBackground(BLANCO);
        tablaPedidos.setSelectionBackground(new Color(245, 240, 230));
        tablaPedidos.setSelectionForeground(MARRON_OSCURO);
        tablaPedidos.setShowGrid(true);
        tablaPedidos.setGridColor(GRIS_BORDE);
        
        JTableHeader header = tablaPedidos.getTableHeader();
        header.setBackground(new Color(250, 250, 250));
        header.setForeground(MARRON_OSCURO);
        header.setFont(new Font("Arial", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        tablaPedidos.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setOpaque(true);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 12));
                label.setForeground(MARRON_OSCURO);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                if (value.equals("Pendiente")) {
                    label.setBackground(AMARILLO_PENDIENTE);
                } else if (value.equals("Aprobado")) {
                    label.setBackground(VERDE_APROBADO);
                } else {
                    label.setBackground(Color.WHITE);
                }
                
                return label;
            }
        });
        
        tablaPedidos.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
                return label;
            }
        });
        
        tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(250);
        tablaPedidos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tablaPedidos.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaPedidos.getColumnModel().getColumn(3).setPreferredWidth(150);
        tablaPedidos.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        scrollPane.setBounds(25, 75, 930, 550);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        panelGestion.add(scrollPane);
    }
    
    private void crearPanelNuevoPedido() {
        panelNuevoPedido = new JPanel();
        panelNuevoPedido.setLayout(null);
        panelNuevoPedido.setBackground(BLANCO);
        panelNuevoPedido.setBounds(20, 90, 980, 650);
        panelNuevoPedido.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        panelNuevoPedido.setVisible(false); 
        
        JLabel lblCrearPedido = new JLabel("Crear Nuevo Pedido");
        lblCrearPedido.setBounds(25, 20, 400, 30);
        lblCrearPedido.setFont(new Font("Arial", Font.BOLD, 24));
        lblCrearPedido.setForeground(MARRON_OSCURO);
        panelNuevoPedido.add(lblCrearPedido);
        
        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(690, 20, 120, 35);
        btnCancelar.setBackground(new Color(220, 220, 220));
        btnCancelar.setForeground(MARRON_OSCURO);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocultarFormularioNuevoPedido();
            }
        });
        panelNuevoPedido.add(btnCancelar);
        
        // Campo Proveedor
        JLabel lblProveedorSelect = new JLabel("Proveedor");
        lblProveedorSelect.setBounds(25, 80, 150, 20);
        lblProveedorSelect.setFont(new Font("Arial", Font.PLAIN, 13));
        lblProveedorSelect.setForeground(MARRON_OSCURO);
        panelNuevoPedido.add(lblProveedorSelect);
        
        cboProveedor = new JComboBox<>(new String[]{"Seleccionar Proveedor", "Maderas del Sur", "Herrajes Modernos", "Pinturas El Sol"});
        cboProveedor.setBounds(25, 105, 400, 35);
        cboProveedor.setFont(new Font("Arial", Font.PLAIN, 13));
        panelNuevoPedido.add(cboProveedor);
        
        // Campo Fecha
        JLabel lblFechaEntrega = new JLabel("Fecha de Entrega");
        lblFechaEntrega.setBounds(540, 80, 150, 20);
        lblFechaEntrega.setFont(new Font("Arial", Font.PLAIN, 13));
        lblFechaEntrega.setForeground(MARRON_OSCURO);
        panelNuevoPedido.add(lblFechaEntrega);
        
        txtFecha = new JTextField("dd/mm/aaaa");
        txtFecha.setBounds(540, 105, 400, 35);
        txtFecha.setFont(new Font("Arial", Font.PLAIN, 13));
        txtFecha.setForeground(Color.GRAY);
        txtFecha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panelNuevoPedido.add(txtFecha);
        
        // Sección Productos
        JLabel lblProductos = new JLabel("Productos");
        lblProductos.setBounds(25, 165, 150, 20);
        lblProductos.setFont(new Font("Arial", Font.PLAIN, 13));
        lblProductos.setForeground(MARRON_OSCURO);
        panelNuevoPedido.add(lblProductos);
        
        // Header de productos
        JPanel panelHeaderProductos = new JPanel();
        panelHeaderProductos.setLayout(null);
        panelHeaderProductos.setBackground(new Color(250, 250, 250));
        panelHeaderProductos.setBounds(25, 190, 915, 35);
        panelHeaderProductos.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblProductoHeader = new JLabel("Producto");
        lblProductoHeader.setBounds(15, 8, 150, 20);
        lblProductoHeader.setFont(new Font("Arial", Font.BOLD, 13));
        lblProductoHeader.setForeground(MARRON_OSCURO);
        panelHeaderProductos.add(lblProductoHeader);
        
        JLabel lblCantidadHeader = new JLabel("Cantidad");
        lblCantidadHeader.setBounds(360, 8, 150, 20);
        lblCantidadHeader.setFont(new Font("Arial", Font.BOLD, 13));
        lblCantidadHeader.setForeground(MARRON_OSCURO);
        panelHeaderProductos.add(lblCantidadHeader);
        
        JLabel lblPrecioHeader = new JLabel("Precio");
        lblPrecioHeader.setBounds(550, 8, 150, 20);
        lblPrecioHeader.setFont(new Font("Arial", Font.BOLD, 13));
        lblPrecioHeader.setForeground(MARRON_OSCURO);
        panelHeaderProductos.add(lblPrecioHeader);
        
        JLabel lblTotalHeader = new JLabel("Total");
        lblTotalHeader.setBounds(740, 8, 150, 20);
        lblTotalHeader.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotalHeader.setForeground(MARRON_OSCURO);
        panelHeaderProductos.add(lblTotalHeader);
        
        panelNuevoPedido.add(panelHeaderProductos);
        
        // Campos de producto
        cboProducto = new JComboBox<>(new String[]{"Seleccionar Producto", "Madera Pino 2x4", "Tornillos 1/2", "Pintura Blanca"});
        cboProducto.setBounds(40, 235, 300, 35);
        cboProducto.setFont(new Font("Arial", Font.PLAIN, 12));
        panelNuevoPedido.add(cboProducto);
        
        cboCantidad = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "10", "20", "50"});
        cboCantidad.setBounds(375, 235, 160, 35);
        cboCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
        
        cboCantidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotales();
            }
        });
        
        panelNuevoPedido.add(cboCantidad);
        
        txtPrecio = new JTextField("");
        txtPrecio.setBounds(565, 235, 150, 35);
        txtPrecio.setFont(new Font("Arial", Font.PLAIN, 13));
        txtPrecio.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                calcularTotales();
            }
        });
        
        panelNuevoPedido.add(txtPrecio);
        
        txtTotalProducto = new JTextField("$0");
        txtTotalProducto.setBounds(745, 235, 150, 35);
        txtTotalProducto.setFont(new Font("Arial", Font.PLAIN, 13));
        txtTotalProducto.setEditable(false);
        txtTotalProducto.setBackground(new Color(245, 245, 245));
        txtTotalProducto.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panelNuevoPedido.add(txtTotalProducto);
        
        lblTotalPedido = new JLabel("Total: $0");
        lblTotalPedido.setBounds(25, 580, 300, 30);
        lblTotalPedido.setFont(new Font("Arial", Font.BOLD, 20));
        lblTotalPedido.setForeground(MARRON_OSCURO);
        panelNuevoPedido.add(lblTotalPedido);

        JButton btnEnviarPedido = new JButton("Enviar Pedido");
        btnEnviarPedido.setBounds(810, 580, 130, 35);
        btnEnviarPedido.setBackground(AMARILLO_BOTON);
        btnEnviarPedido.setForeground(MARRON_OSCURO);
        btnEnviarPedido.setFont(new Font("Arial", Font.BOLD, 13));
        btnEnviarPedido.setFocusPainted(false);
        btnEnviarPedido.setBorderPainted(false);
        btnEnviarPedido.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnEnviarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarPedido();
            }
        });
        
        panelNuevoPedido.add(btnEnviarPedido);
        
        add(panelNuevoPedido);
    }
    
    private void mostrarFormularioNuevoPedido() {
        // Ocultar tabla y mostrar formulario
        Component[] componentes = getComponents();
        for (Component comp : componentes) {
            if (comp instanceof JPanel && comp != panelNuevoPedido) {
                comp.setVisible(false);
            }
        }
        panelNuevoPedido.setVisible(true);
        limpiarFormulario();
    }
    
    private void ocultarFormularioNuevoPedido() {
        // Mostrar tabla y ocultar formulario
        panelNuevoPedido.setVisible(false);
        Component[] componentes = getComponents();
        for (Component comp : componentes) {
            if (comp instanceof JPanel && comp != panelNuevoPedido) {
                comp.setVisible(true);
            }
        }
    }
    
    private void enviarPedido() {
        // Validar campos
        String proveedor = (String) cboProveedor.getSelectedItem();
        String fecha = txtFecha.getText();
        String producto = (String) cboProducto.getSelectedItem();
        String precio = txtPrecio.getText();
        
        // Verificar si hay campos vacíos
        if (proveedor.equals("Seleccionar Proveedor")) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un proveedor", 
                "Campo Vacío", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (fecha.equals("dd/mm/aaaa") || fecha.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese la fecha de entrega", 
                "Campo Vacío", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (producto.equals("Seleccionar Producto")) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un producto", 
                "Campo Vacío", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (precio.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese el precio", 
                "Campo Vacío", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String cantidad = (String) cboCantidad.getSelectedItem();
        String total = txtTotalProducto.getText();
        
        modeloTabla.addRow(new Object[]{proveedor, fecha, total, "Pendiente", "👁️  ✏️"});
        
        // Mensaje de éxito
        JOptionPane.showMessageDialog(this, 
            "¡Pedido enviado exitosamente!\n\nProveedor: " + proveedor + "\nFecha: " + fecha + "\nTotal: " + total, 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
        
        ocultarFormularioNuevoPedido();
    }
    
    private void limpiarFormulario() {
        cboProveedor.setSelectedIndex(0);
        txtFecha.setText("dd/mm/aaaa");
        txtFecha.setForeground(Color.GRAY);
        cboProducto.setSelectedIndex(0);
        cboCantidad.setSelectedIndex(0);
        txtPrecio.setText("");
        txtTotalProducto.setText("$0");
        lblTotalPedido.setText("Total: $0");
    }
    
    private void calcularTotales() {
        try {
            String cantidadStr = (String) cboCantidad.getSelectedItem();
            int cantidad = Integer.parseInt(cantidadStr);
            
            String precioStr = txtPrecio.getText().replaceAll("[^0-9]", "");
            
            if (precioStr.isEmpty()) {
                txtTotalProducto.setText("$0");
                lblTotalPedido.setText("Total: $0");
                return;
            }
            
            int precio = Integer.parseInt(precioStr);
            
            int totalProducto = cantidad * precio;
            
            String totalFormateado = String.format("$%,d", totalProducto);
            totalFormateado = totalFormateado.replace(",", ".");
            
            txtTotalProducto.setText(totalFormateado);
            lblTotalPedido.setText("Total: " + totalFormateado);
            
        } catch (NumberFormatException e) {
            txtTotalProducto.setText("$0");
            lblTotalPedido.setText("Total: $0");
        }
    }
}