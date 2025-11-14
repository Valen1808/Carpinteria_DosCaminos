package VistaInicioSesion.VistaSupervisor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelMantenimiento extends JPanel {
  
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color AMARILLO_PENDIENTE = new Color(254, 240, 138);
    private final Color VERDE_APROBADO = new Color(134, 239, 172);
    private final Color GRIS_BORDE = new Color(200, 180, 160);
    
   
    private JPanel panelCalendario;
    private JPanel panelContenidoFilas;
    private List<Mantenimiento> listaMantenimientos;
    private int siguienteY = 145; 
    
    private class Mantenimiento {
        String equipo, tipo, fecha, tecnico, estado;
        
        Mantenimiento(String equipo, String tipo, String fecha, String tecnico, String estado) {
            this.equipo = equipo;
            this.tipo = tipo;
            this.fecha = fecha;
            this.tecnico = tecnico;
            this.estado = estado;
        }
    }
    
    public PanelMantenimiento() {
        listaMantenimientos = new ArrayList<>();
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 1020, 775);
        
        JLabel lblTitulo = new JLabel("Mantenimiento");
        lblTitulo.setBounds(20, 20, 400, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(MARRON_OSCURO);
        add(lblTitulo);
        
        JLabel lblNotificacion = new JLabel("🔔");
        lblNotificacion.setBounds(900, 25, 35, 35);
        lblNotificacion.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        add(lblNotificacion);
        
        JLabel lblUsuario = new JLabel("👤 Supervisor");
        lblUsuario.setBounds(930, 28, 120, 30);
        lblUsuario.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblUsuario.setForeground(MARRON_OSCURO);
        add(lblUsuario);
        
        panelCalendario = new JPanel();
        panelCalendario.setLayout(null);
        panelCalendario.setBackground(BLANCO);
        panelCalendario.setBounds(20, 90, 980, 665);
        panelCalendario.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblCalendario = new JLabel("Calendario de Mantenimiento");
        lblCalendario.setBounds(25, 25, 500, 35);
        lblCalendario.setFont(new Font("Arial", Font.BOLD, 26));
        lblCalendario.setForeground(MARRON_OSCURO);
        panelCalendario.add(lblCalendario);
        
        JButton btnNuevoMantenimiento = new JButton("Nuevo Mantenimiento");
        btnNuevoMantenimiento.setBounds(780, 25, 180, 40);
        btnNuevoMantenimiento.setBackground(AMARILLO_BOTON);
        btnNuevoMantenimiento.setForeground(MARRON_OSCURO);
        btnNuevoMantenimiento.setFont(new Font("Arial", Font.BOLD, 13));
        btnNuevoMantenimiento.setFocusPainted(false);
        btnNuevoMantenimiento.setBorderPainted(false);
        btnNuevoMantenimiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelCalendario.add(btnNuevoMantenimiento);
        
        btnNuevoMantenimiento.addActionListener(e -> mostrarFormulario());

        int headerY = 100;
        crearHeader(panelCalendario, "Equipo", 25, headerY, 200);
        crearHeader(panelCalendario, "Tipo", 235, headerY, 120);
        crearHeader(panelCalendario, "Fecha programada", 365, headerY, 160);
        crearHeader(panelCalendario, "Técnico", 535, headerY, 150);
        crearHeader(panelCalendario, "Estado", 695, headerY, 130);
        crearHeader(panelCalendario, "Acciones", 835, headerY, 110);
        
        panelContenidoFilas = new JPanel();
        panelContenidoFilas.setLayout(null);
        panelContenidoFilas.setBackground(BLANCO);
        
        JScrollPane scrollPane = new JScrollPane(panelContenidoFilas);
        scrollPane.setBounds(25, 130, 930, 520);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panelCalendario.add(scrollPane);
        
        agregarMantenimiento("Cierra circular industrial", "Preventivo", "25/07/2025", "Jancel Aleman", "Pendiente");
        agregarMantenimiento("Lijadora industrial", "Correctivo", "27/07/2025", "Jose Luis", "Aprobado");
        
        add(panelCalendario);
    }
    
    private void agregarMantenimiento(String equipo, String tipo, String fecha, String tecnico, String estado) {
        Mantenimiento m = new Mantenimiento(equipo, tipo, fecha, tecnico, estado);
        listaMantenimientos.add(m);
        actualizarListaVisual();
    }
    
    private void actualizarListaVisual() {

        panelContenidoFilas.removeAll();
        
        // Calcular altura necesaria
        int alturaTotal = listaMantenimientos.size() * 50 + 50;
        panelContenidoFilas.setPreferredSize(new Dimension(900, alturaTotal));
        
        // Recrear todas las filas
        int yPos = 15;
        for (int i = 0; i < listaMantenimientos.size(); i++) {
            Mantenimiento m = listaMantenimientos.get(i);
            Color colorEstado = m.estado.equals("Pendiente") ? AMARILLO_PENDIENTE : VERDE_APROBADO;
            crearFilaMantenimiento(panelContenidoFilas, m.equipo, m.tipo, m.fecha, m.tecnico, m.estado, colorEstado, yPos, i);
            yPos += 50;
        }
        
        panelContenidoFilas.revalidate();
        panelContenidoFilas.repaint();
    }
    
    private void eliminarMantenimiento(int indice) {
        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de eliminar este mantenimiento?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            listaMantenimientos.remove(indice);
            actualizarListaVisual();
            JOptionPane.showMessageDialog(this, "Mantenimiento eliminado correctamente");
        }
    }
    
    private void mostrarFormulario() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Nuevo Mantenimiento", true);
        dialog.setSize(400, 450);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(BEIGE);

        JLabel lblTitulo = new JLabel("Registrar Mantenimiento");
        lblTitulo.setBounds(60, 20, 300, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(MARRON_OSCURO);
        dialog.add(lblTitulo);

        JLabel lblEquipo = new JLabel("Equipo:");
        lblEquipo.setBounds(50, 80, 120, 25);
        dialog.add(lblEquipo);
        JTextField txtEquipo = new JTextField();
        txtEquipo.setBounds(160, 80, 180, 25);
        dialog.add(txtEquipo);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(50, 120, 120, 25);
        dialog.add(lblTipo);
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Preventivo", "Correctivo"});
        comboTipo.setBounds(160, 120, 180, 25);
        dialog.add(comboTipo);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(50, 160, 120, 25);
        dialog.add(lblFecha);
        JTextField txtFecha = new JTextField("dd/mm/yyyy");
        txtFecha.setBounds(160, 160, 180, 25);
        dialog.add(txtFecha);

        JLabel lblTecnico = new JLabel("Técnico:");
        lblTecnico.setBounds(50, 200, 120, 25);
        dialog.add(lblTecnico);
        JTextField txtTecnico = new JTextField();
        txtTecnico.setBounds(160, 200, 180, 25);
        dialog.add(txtTecnico);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(50, 240, 120, 25);
        dialog.add(lblEstado);
        JComboBox<String> comboEstado = new JComboBox<>(new String[]{"Pendiente", "Aprobado"});
        comboEstado.setBounds(160, 240, 180, 25);
        dialog.add(comboEstado);

        JButton btnGuardar = new JButton("💾 Guardar");
        btnGuardar.setBounds(80, 320, 110, 35);
        btnGuardar.setBackground(AMARILLO_BOTON);
        btnGuardar.setForeground(MARRON_OSCURO);
        btnGuardar.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        btnGuardar.setFocusPainted(false);
        dialog.add(btnGuardar);

        JButton btnCancelar = new JButton("❌ Cancelar");
        btnCancelar.setBounds(210, 320, 110, 35);
        btnCancelar.setBackground(Color.LIGHT_GRAY);
        btnCancelar.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        btnCancelar.addActionListener(e -> dialog.dispose());
        dialog.add(btnCancelar);

        btnGuardar.addActionListener(e -> {
            String equipo = txtEquipo.getText().trim();
            String tipo = (String) comboTipo.getSelectedItem();
            String fecha = txtFecha.getText().trim();
            String tecnico = txtTecnico.getText().trim();
            String estado = (String) comboEstado.getSelectedItem();
            
            if (equipo.isEmpty() || fecha.isEmpty() || tecnico.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, 
                    "Por favor complete todos los campos", 
                    "Campos vacíos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            agregarMantenimiento(equipo, tipo, fecha, tecnico, estado);
            
            JOptionPane.showMessageDialog(dialog, 
                "Mantenimiento agregado correctamente:\n\n" +
                "Equipo: " + equipo + "\n" +
                "Tipo: " + tipo + "\n" +
                "Fecha: " + fecha + "\n" +
                "Técnico: " + tecnico + "\n" +
                "Estado: " + estado);
            
            dialog.dispose();
        });

        dialog.setVisible(true);
    }
    
    private void crearHeader(JPanel panel, String texto, int x, int y, int width) {
        JLabel lbl = new JLabel(texto);
        lbl.setBounds(x, y, width, 25);
        lbl.setFont(new Font("Arial", Font.BOLD, 15));
        lbl.setForeground(MARRON_OSCURO);
        panel.add(lbl);
    }
    
    private void crearFilaMantenimiento(JPanel panel, String equipo, String tipo, 
        String fecha, String tecnico, String estado, 
        Color colorEstado, int y, int indice) {

        JLabel lblEquipo = new JLabel(equipo);
        lblEquipo.setBounds(0, y, 200, 25);
        lblEquipo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEquipo.setForeground(Color.GRAY);
        panel.add(lblEquipo);
        
        JLabel lblTipo = new JLabel(tipo);
        lblTipo.setBounds(210, y, 120, 25);
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTipo.setForeground(Color.GRAY);
        panel.add(lblTipo);
        
        JLabel lblFecha = new JLabel(fecha);
        lblFecha.setBounds(340, y, 160, 25);
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 14));
        lblFecha.setForeground(Color.GRAY);
        panel.add(lblFecha);
        
        JLabel lblTecnico = new JLabel(tecnico);
        lblTecnico.setBounds(510, y, 150, 25);
        lblTecnico.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTecnico.setForeground(Color.GRAY);
        panel.add(lblTecnico);
        
        JLabel lblEstado = new JLabel(estado);
        lblEstado.setBounds(670, y, 110, 25);
        lblEstado.setFont(new Font("Segoe UI Emoji", Font.BOLD, 12));
        lblEstado.setForeground(MARRON_OSCURO);
        lblEstado.setOpaque(true);
        lblEstado.setBackground(colorEstado);
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstado.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        panel.add(lblEstado);
        
        JLabel lblEliminar = new JLabel("🗑️");
        lblEliminar.setBounds(810, y, 30, 25);
        lblEliminar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        lblEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarMantenimiento(indice);
            }
        });
        panel.add(lblEliminar);
    }
}