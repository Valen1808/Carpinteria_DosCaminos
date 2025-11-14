package VistaInicioSesion.VistaOperario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class PanelConsultarInventario extends JPanel {
    
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color GRIS_BORDE = new Color(200, 180, 160);
    private final Color AZUL_MADERAS = new Color(147, 197, 253);
    private final Color NARANJA_HERRAJES = new Color(253, 186, 116);
    private final Color MORADO_ACABADOS = new Color(196, 181, 253);
    private final Color VERDE_HERRAMIENTAS = new Color(134, 239, 172);
    private final Color ROSA_PINTURAS = new Color(251, 207, 232);
    private DefaultTableModel modeloTabla;
    private JTable tablaInventario;
    
    public PanelConsultarInventario() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 1020, 775);
        initComponents();
        cargarDatosInventario();
    }
    
    private void initComponents() {
      
        JLabel lblTitulo = new JLabel("Consultar Inventario");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(MARRON_OSCURO);
        lblTitulo.setBounds(40, 25, 500, 40);
        add(lblTitulo);
        
        JLabel lblNotificacion = new JLabel("🔔");
        lblNotificacion.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        lblNotificacion.setBounds(880, 28, 40, 40);
        add(lblNotificacion);
        
        JLabel lblUsuario = new JLabel("👤 Valentina");
        lblUsuario.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblUsuario.setForeground(MARRON_OSCURO);
        lblUsuario.setBounds(915, 35, 180, 25);
        add(lblUsuario);
        
        crearPanelInventario();
    }
    
    private void crearPanelInventario() {
        JPanel panelInventario = new JPanel();
        panelInventario.setLayout(null);
        panelInventario.setBackground(BLANCO);
        panelInventario.setBounds(40, 90, 940, 650);
        panelInventario.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblInventarioDisponible = new JLabel("Inventario Disponible");
        lblInventarioDisponible.setFont(new Font("Arial", Font.BOLD, 26));
        lblInventarioDisponible.setForeground(MARRON_OSCURO);
        lblInventarioDisponible.setBounds(25, 25, 500, 35);
        panelInventario.add(lblInventarioDisponible);
        
        JTextField txtBuscar = new JTextField("Buscar en inventario");
        txtBuscar.setBounds(25, 80, 890, 45);
        txtBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
        txtBuscar.setForeground(Color.GRAY);
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        panelInventario.add(txtBuscar);
        
        crearTablaInventario(panelInventario);
        
        add(panelInventario);
    }
    
    private void crearTablaInventario(JPanel panelInventario) {
        
        String[] columnas = {"Código", "Material", "Categoría", "Cantidad", "Unidad", "Ubicación"};
         
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaInventario = new JTable(modeloTabla);
        tablaInventario.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaInventario.setRowHeight(50);
        tablaInventario.setBackground(BLANCO);
        tablaInventario.setSelectionBackground(new Color(245, 240, 230));
        tablaInventario.setSelectionForeground(MARRON_OSCURO);
        tablaInventario.setShowGrid(true);
        tablaInventario.setGridColor(GRIS_BORDE);
        
        JTableHeader header = tablaInventario.getTableHeader();
        header.setBackground(MARRON_OSCURO);
        header.setForeground(BLANCO);
        header.setFont(new Font("Arial", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(header.getWidth(), 45));
        
        DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setHorizontalAlignment(SwingConstants.LEFT);
                label.setFont(new Font("Arial", Font.PLAIN, 14));
                label.setForeground(MARRON_OSCURO);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                if (isSelected) {
                    label.setBackground(new Color(245, 240, 230));
                    label.setOpaque(true);
                } else {
                    label.setBackground(BLANCO);
                    label.setOpaque(true);
                }
                
                return label;
            }
        };
        
        tablaInventario.getColumnModel().getColumn(0).setCellRenderer(textRenderer);
        tablaInventario.getColumnModel().getColumn(1).setCellRenderer(textRenderer);
        tablaInventario.getColumnModel().getColumn(3).setCellRenderer(textRenderer);
        tablaInventario.getColumnModel().getColumn(4).setCellRenderer(textRenderer);
        tablaInventario.getColumnModel().getColumn(5).setCellRenderer(textRenderer);
        
        tablaInventario.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 12));
                
                JLabel label = new JLabel(value.toString());
                label.setOpaque(true);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 12));
                label.setForeground(MARRON_OSCURO);
                label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(GRIS_BORDE, 1),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
                
                String categoria = value.toString();
                if (categoria.equals("Maderas")) {
                    label.setBackground(AZUL_MADERAS);
                } else if (categoria.equals("Herrajes")) {
                    label.setBackground(NARANJA_HERRAJES);
                } else if (categoria.equals("Acabados")) {
                    label.setBackground(MORADO_ACABADOS);
                } else if (categoria.equals("Herramientas")) {
                    label.setBackground(VERDE_HERRAMIENTAS);
                } else if (categoria.equals("Pinturas")) {
                    label.setBackground(ROSA_PINTURAS);
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
        
        tablaInventario.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaInventario.getColumnModel().getColumn(1).setPreferredWidth(250);
        tablaInventario.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaInventario.getColumnModel().getColumn(3).setPreferredWidth(120);
        tablaInventario.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaInventario.getColumnModel().getColumn(5).setPreferredWidth(170);
        
        JScrollPane scrollPane = new JScrollPane(tablaInventario);
        scrollPane.setBounds(25, 145, 890, 480);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        panelInventario.add(scrollPane);
    }
    
    private void cargarDatosInventario() {
        Object[][] datos = {
         };
        
        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }
}