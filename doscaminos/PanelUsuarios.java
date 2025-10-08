package doscaminos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PanelUsuarios extends JPanel {
    
    // Colores
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color MARRON_TABLA = new Color(100, 70, 50);
    private final Color BEIGE_CLARO = new Color(245, 235, 220);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    
    public PanelUsuarios() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 920, 675);
        
        // Header con título y usuario
        JLabel lblTitulo = new JLabel("Usuarios");
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
        
        // Panel de gestión
        JPanel panelGestion = new JPanel();
        panelGestion.setLayout(null);
        panelGestion.setBackground(BLANCO);
        panelGestion.setBounds(20, 90, 880, 560);
        panelGestion.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 160), 1));
        
        // Título del panel
        JLabel lblGestionUsuarios = new JLabel("Gestión de usuarios");
        lblGestionUsuarios.setBounds(20, 20, 400, 30);
        lblGestionUsuarios.setFont(new Font("Arial", Font.BOLD, 24));
        lblGestionUsuarios.setForeground(MARRON_OSCURO);
        panelGestion.add(lblGestionUsuarios);
        
        // Botón Nuevo Usuario
        JButton btnNuevoUsuario = new JButton("+ Nuevo Usuario");
        btnNuevoUsuario.setBounds(700, 20, 160, 35);
        btnNuevoUsuario.setBackground(AMARILLO_BOTON);
        btnNuevoUsuario.setForeground(MARRON_OSCURO);
        btnNuevoUsuario.setFont(new Font("Arial", Font.BOLD, 13));
        btnNuevoUsuario.setFocusPainted(false);
        btnNuevoUsuario.setBorderPainted(false);
        btnNuevoUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelGestion.add(btnNuevoUsuario);
        
        // Crear tabla
        String[] columnas = {"ID", "Nombre", "Apellido", "Correo", "Teléfono", "Tipo de rol", "Acciones"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6; // Solo editable rol y acciones
            }
        };
        
        // Agregar datos de ejemplo
        Object[][] datos = {
            {"01", "Juan", "Perez", "juanip@example.com", "+57 3235678902", "Operario", ""},
            {"02", "María", "Gonzales", "mar_ll12@gmail.com", "+57 3235678902", "Cliente", ""},
            {"03", "Carlos", "Rodriguez", "carls@example.com", "+57 3235678902", "Supervisor", ""}
        };
        
        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
        
        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setRowHeight(35);
        tablaUsuarios.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaUsuarios.setGridColor(new Color(200, 180, 160));
        tablaUsuarios.setSelectionBackground(BEIGE_CLARO);
        tablaUsuarios.setSelectionForeground(Color.BLACK);
        
        // Configurar header de la tabla
        JTableHeader header = tablaUsuarios.getTableHeader();
        header.setBackground(MARRON_TABLA);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setReorderingAllowed(false);
        
        // Alternar colores de filas
        tablaUsuarios.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(BEIGE_CLARO);
                    }
                }
                
                // Renderizar columna de acciones
                if (column == 6) {
                    setHorizontalAlignment(SwingConstants.CENTER);
                    setText("✏️ 🗑️");
                } else if (column == 5) {
                    setHorizontalAlignment(SwingConstants.LEFT);
                } else {
                    setHorizontalAlignment(SwingConstants.LEFT);
                }
                
                return c;
            }
        });
        
        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBounds(20, 70, 840, 420);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 160), 1));
        panelGestion.add(scrollPane);
        
        // Panel de paginación
        JPanel panelPaginacion = new JPanel();
        panelPaginacion.setLayout(null);
        panelPaginacion.setBackground(BLANCO);
        panelPaginacion.setBounds(20, 500, 840, 40);
        
        JLabel lblMostrando = new JLabel("Mostrando 1-3 de 25 usuarios");
        lblMostrando.setBounds(0, 10, 200, 20);
        lblMostrando.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMostrando.setForeground(Color.GRAY);
        panelPaginacion.add(lblMostrando);
        
        // Botones de paginación
        JButton btnAnterior = new JButton("Anterior");
        btnAnterior.setBounds(600, 5, 80, 30);
        btnAnterior.setFont(new Font("Arial", Font.PLAIN, 11));
        btnAnterior.setFocusPainted(false);
        btnAnterior.setBackground(Color.WHITE);
        btnAnterior.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        btnAnterior.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelPaginacion.add(btnAnterior);
        
        JButton btn1 = crearBotonPagina("1", 690, true);
        JButton btn2 = crearBotonPagina("2", 720, false);
        JButton btn3 = crearBotonPagina("3", 750, false);
        panelPaginacion.add(btn1);
        panelPaginacion.add(btn2);
        panelPaginacion.add(btn3);
        
        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBounds(790, 5, 80, 30);
        btnSiguiente.setFont(new Font("Arial", Font.PLAIN, 11));
        btnSiguiente.setFocusPainted(false);
        btnSiguiente.setBackground(Color.WHITE);
        btnSiguiente.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        btnSiguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelPaginacion.add(btnSiguiente);
        
        panelGestion.add(panelPaginacion);
        
        add(panelGestion);
    }
    
    private JButton crearBotonPagina(String texto, int x, boolean seleccionado) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, 5, 30, 30);
        btn.setFont(new Font("Arial", Font.PLAIN, 11));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (seleccionado) {
            btn.setBackground(new Color(100, 70, 50));
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createLineBorder(new Color(100, 70, 50), 1));
        } else {
            btn.setBackground(Color.WHITE);
            btn.setForeground(Color.BLACK);
            btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        }
        
        return btn;
    }
}