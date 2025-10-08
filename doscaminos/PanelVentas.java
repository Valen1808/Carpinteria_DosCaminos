package doscaminos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PanelVentas extends JPanel {
    
    // Colores
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color MARRON_TABLA = new Color(100, 70, 50);
    private final Color BEIGE_CLARO = new Color(245, 235, 220);
    private final Color VERDE_COMPLETADA = new Color(134, 239, 172);
    private final Color AMARILLO_PROCESO = new Color(254, 240, 138);
    private final Color AZUL_PENDIENTE = new Color(191, 219, 254);
    
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;
    
    public PanelVentas() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 920, 675);
        
        // Header con título y usuario
        JLabel lblTitulo = new JLabel("Ventas");
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
        
        // Panel de registro
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(null);
        panelRegistro.setBackground(BLANCO);
        panelRegistro.setBounds(20, 90, 880, 560);
        panelRegistro.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 160), 1));
        
        // Título del panel
        JLabel lblRegistroVentas = new JLabel("Registro de ventas");
        lblRegistroVentas.setBounds(20, 20, 400, 30);
        lblRegistroVentas.setFont(new Font("Arial", Font.BOLD, 24));
        lblRegistroVentas.setForeground(MARRON_OSCURO);
        panelRegistro.add(lblRegistroVentas);
        
        // Crear tabla
        String[] columnas = {"ID", "Cliente", "Fecha", "Productos", "Estado", ""};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Agregar datos de ejemplo
        Object[][] datos = {
            {"V001", "María González", "15/07/2025", "Mesa de Comedor (1)", "Completada", "👁️ 💬"},
            {"V002", "Carlos Rodriguez", "18/07/2025", "Estantería Modular (2)", "En proceso", "👁️ 💬"},
            {"V003", "Ana Martínez", "20/07/2025", "Puerta de Madera (1)", "Pendiente", "👁️ 💬"}
        };
        
        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
        
        tablaVentas = new JTable(modeloTabla);
        tablaVentas.setRowHeight(40);
        tablaVentas.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaVentas.setGridColor(new Color(200, 180, 160));
        tablaVentas.setSelectionBackground(BEIGE_CLARO);
        tablaVentas.setSelectionForeground(Color.BLACK);
        tablaVentas.setShowVerticalLines(true);
        tablaVentas.setShowHorizontalLines(true);
        
        // Configurar anchos de columnas
        tablaVentas.getColumnModel().getColumn(0).setPreferredWidth(60);  // ID
        tablaVentas.getColumnModel().getColumn(1).setPreferredWidth(150); // Cliente
        tablaVentas.getColumnModel().getColumn(2).setPreferredWidth(100); // Fecha
        tablaVentas.getColumnModel().getColumn(3).setPreferredWidth(200); // Productos
        tablaVentas.getColumnModel().getColumn(4).setPreferredWidth(120); // Estado
        tablaVentas.getColumnModel().getColumn(5).setPreferredWidth(60);  // Acciones
        
        // Configurar header de la tabla
        JTableHeader header = tablaVentas.getTableHeader();
        header.setBackground(MARRON_TABLA);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 45));
        
        // Renderizador personalizado
        tablaVentas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Alternar colores de fila
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(BEIGE_CLARO);
                    }
                }
                
                // Columna de estado con badge de color
                if (column == 4) {
                    JLabel label = new JLabel(value.toString());
                    label.setOpaque(true);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setFont(new Font("Arial", Font.BOLD, 11));
                    label.setForeground(MARRON_OSCURO);
                    label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                    
                    if (value.equals("Completada")) {
                        label.setBackground(VERDE_COMPLETADA);
                    } else if (value.equals("En proceso")) {
                        label.setBackground(AMARILLO_PROCESO);
                    } else if (value.equals("Pendiente")) {
                        label.setBackground(AZUL_PENDIENTE);
                    }
                    
                    if (!isSelected) {
                        if (row % 2 == 0) {
                            label.setBackground(label.getBackground());
                        } else {
                            label.setBackground(label.getBackground());
                        }
                    }
                    
                    return label;
                }
                
                // Columna de acciones
                if (column == 5) {
                    setHorizontalAlignment(SwingConstants.CENTER);
                    setFont(new Font("Arial", Font.PLAIN, 16));
                } else if (column == 4) {
                    setHorizontalAlignment(SwingConstants.CENTER);
                } else {
                    setHorizontalAlignment(SwingConstants.LEFT);
                }
                
                return c;
            }
        });
        
        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaVentas);
        scrollPane.setBounds(20, 70, 840, 470);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 160), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);
        panelRegistro.add(scrollPane);
        
        add(panelRegistro);
    }
}