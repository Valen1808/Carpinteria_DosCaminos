package VistaInicioSesion.VistaOperario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelVerificarInsumos extends JPanel {
    
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color ROJO_CRITICO = new Color(248, 113, 113);
    private final Color AMARILLO_BAJO = new Color(250, 204, 21);
    private final Color VERDE_OPTIMO = new Color(134, 239, 172);
    private final Color GRIS_BORDE = new Color(200, 180, 160);
    
    private DefaultTableModel modeloTabla;
    private JTable tablaInsumos;
    
    public PanelVerificarInsumos() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 1020, 775);
        
        JLabel lblTitulo = new JLabel("Verificar Insumos");
        lblTitulo.setBounds(20, 20, 400, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(MARRON_OSCURO);
        add(lblTitulo);
        
        JLabel lblNotificacion = new JLabel("🔔");
        lblNotificacion.setBounds(900, 25, 35, 35);
        lblNotificacion.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        add(lblNotificacion);
        
        JLabel lblUsuario = new JLabel("👤 Valentina");
        lblUsuario.setBounds(925, 28, 180, 30);
        lblUsuario.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblUsuario.setForeground(MARRON_OSCURO);
        add(lblUsuario);
        
        crearPanelInsumos();
    }
    
    private void crearPanelInsumos() {
        JPanel panelInsumos = new JPanel();
        panelInsumos.setLayout(null);
        panelInsumos.setBackground(BLANCO);
        panelInsumos.setBounds(20, 90, 980, 665);
        panelInsumos.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblInsumosHoy = new JLabel("Insumos para Verificar Hoy");
        lblInsumosHoy.setBounds(25, 25, 500, 35);
        lblInsumosHoy.setFont(new Font("Arial", Font.BOLD, 26));
        lblInsumosHoy.setForeground(MARRON_OSCURO);
        panelInsumos.add(lblInsumosHoy);

        JTextField txtBuscar = new JTextField("Buscar Insumo");
        txtBuscar.setBounds(25, 80, 930, 45);
        txtBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
        txtBuscar.setForeground(Color.GRAY);
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        panelInsumos.add(txtBuscar);
        
        crearTablaInsumos(panelInsumos);
        
        add(panelInsumos);
    }
    
    private void crearTablaInsumos(JPanel panelInsumos) {
        String[] columnas = {"Insumo", "Cantidad Actual", "Cantidad Mínima", "Estado", "Acción"};
        
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo la columna de Acción es editable (para el botón)
            }
        };
        
        tablaInsumos = new JTable(modeloTabla);
        tablaInsumos.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaInsumos.setRowHeight(50);
        tablaInsumos.setBackground(BLANCO);
        tablaInsumos.setSelectionBackground(new Color(245, 240, 230));
        tablaInsumos.setSelectionForeground(MARRON_OSCURO);
        tablaInsumos.setShowGrid(true);
        tablaInsumos.setGridColor(GRIS_BORDE);
        
        JTableHeader header = tablaInsumos.getTableHeader();
        header.setBackground(new Color(250, 250, 250));
        header.setForeground(MARRON_OSCURO);
        header.setFont(new Font("Arial", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer() {
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
        };
        
        tablaInsumos.getColumnModel().getColumn(0).setCellRenderer(textRenderer);
        tablaInsumos.getColumnModel().getColumn(1).setCellRenderer(textRenderer);
        tablaInsumos.getColumnModel().getColumn(2).setCellRenderer(textRenderer);
        
        tablaInsumos.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
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
                } else if (value.equals("Óptimo")) {
                    label.setBackground(VERDE_OPTIMO);
                } else {
                    label.setBackground(Color.WHITE);
                }
                
                return label;
            }
        });
        
        tablaInsumos.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        tablaInsumos.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        
        tablaInsumos.getColumnModel().getColumn(0).setPreferredWidth(250);
        tablaInsumos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tablaInsumos.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaInsumos.getColumnModel().getColumn(3).setPreferredWidth(130);
        tablaInsumos.getColumnModel().getColumn(4).setPreferredWidth(200);
        
        JScrollPane scrollPane = new JScrollPane(tablaInsumos);
        scrollPane.setBounds(25, 145, 930, 490);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        panelInsumos.add(scrollPane);
    }
    
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Reportar");
            setBackground(AMARILLO_BOTON);
            setForeground(MARRON_OSCURO);
            setFont(new Font("Arial", Font.BOLD, 13));
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            return this;
        }
    }
    
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean clicked;
        private int row;
        
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(AMARILLO_BOTON);
            button.setForeground(MARRON_OSCURO);
            button.setFont(new Font("Arial", Font.BOLD, 13));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.row = row;
            label = "Reportar";
            button.setText(label);
            clicked = true;
            return button;
        }
        
        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                String insumo = (String) modeloTabla.getValueAt(row, 0);
                String cantidadActual = (String) modeloTabla.getValueAt(row, 1);
                String estado = (String) modeloTabla.getValueAt(row, 3);
                
                JOptionPane.showMessageDialog(button,
                    "Reporte de Insumo\n\n" +
                    "Insumo: " + insumo + "\n" +
                    "Cantidad Actual: " + cantidadActual + "\n" +
                    "Estado: " + estado + "\n\n" +
                    "¿Desea solicitar reposición?",
                    "Reportar Insumo",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            clicked = false;
            return label;
        }
        
        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }
}