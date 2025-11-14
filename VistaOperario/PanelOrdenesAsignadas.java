package VistaInicioSesion.VistaOperario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOrdenesAsignadas extends JPanel {
    
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color AMARILLO_PROCESO = new Color(254, 240, 138);
    private final Color VERDE_COMPLETADA = new Color(134, 239, 172);
    private final Color GRIS_BORDE = new Color(200, 180, 160);
    
    private class OrdenInfo {
        String titulo, fecha, materiales, tiempo, estado, supervisor, prioridad, notas;
        Color colorEstado;
        
        OrdenInfo(String titulo, String fecha, String materiales, String tiempo, 
                  String estado, Color colorEstado, String supervisor, String prioridad, String notas) {
            this.titulo = titulo;
            this.fecha = fecha;
            this.materiales = materiales;
            this.tiempo = tiempo;
            this.estado = estado;
            this.colorEstado = colorEstado;
            this.supervisor = supervisor;
            this.prioridad = prioridad;
            this.notas = notas;
        }
    }
    
    public PanelOrdenesAsignadas() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 1020, 775);
        
        JLabel lblTitulo = new JLabel("Ordenes Asignadas");
        lblTitulo.setBounds(20, 20, 450, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(MARRON_OSCURO);
        add(lblTitulo);
        
        JLabel lblNotificacion = new JLabel("🔔");
        lblNotificacion.setBounds(875, 25, 35, 35);
        lblNotificacion.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        add(lblNotificacion);
        
        JLabel lblUsuario = new JLabel("👤 Valentina");
        lblUsuario.setBounds(925, 28, 180, 30);
        lblUsuario.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        lblUsuario.setForeground(MARRON_OSCURO);
        add(lblUsuario);
        
        
        JPanel panelOrdenes = new JPanel();
        panelOrdenes.setLayout(null);
        panelOrdenes.setBackground(BLANCO);
        panelOrdenes.setBounds(20, 90, 980, 665);
        panelOrdenes.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblOrdenesAsignadas = new JLabel("Ordenes Asignadas");
        lblOrdenesAsignadas.setBounds(25, 25, 500, 35);
        lblOrdenesAsignadas.setFont(new Font("Arial", Font.BOLD, 26));
        lblOrdenesAsignadas.setForeground(MARRON_OSCURO);
        panelOrdenes.add(lblOrdenesAsignadas);
        
        // Orden #1
        OrdenInfo orden1Info = new OrdenInfo(
            "Orden #1 - Mesa de Comedor",
            "28/07/2025",
            "Madera de Roble, Barniz Transparente",
            "4 días",
            "En proceso",
            AMARILLO_PROCESO,
            "Carlos Mendoza",
            "Alta",
            "Cliente requiere acabado extra fino. Verificar calidad del barniz antes de aplicar."
        );
        JPanel orden1 = crearTarjetaOrden(orden1Info, 80);
        panelOrdenes.add(orden1);
        
        // Orden #2
        OrdenInfo orden2Info = new OrdenInfo(
            "Orden #2 - Juego de Sillas (4)",
            "30/07/2025",
            "Madera de Pino, Barniz Transparente",
            "6 días",
            "En proceso",
            AMARILLO_PROCESO,
            "Carlos Mendoza",
            "Media",
            "Las 4 sillas deben ser idénticas. Medidas: 45cm x 45cm x 95cm."
        );
        JPanel orden2 = crearTarjetaOrden(orden2Info, 220);
        panelOrdenes.add(orden2);
        
        // Orden #3
        OrdenInfo orden3Info = new OrdenInfo(
            "Orden #3 - Armario",
            "24/07/2025",
            "Madera de Cedro, Pintura Blanca",
            "8 días",
            "Completada",
            VERDE_COMPLETADA,
            "Ana Torres",
            "Alta",
            "Armario de 2 puertas con 3 estantes internos. Cliente muy satisfecho con el resultado."
        );
        JPanel orden3 = crearTarjetaOrden(orden3Info, 360);
        panelOrdenes.add(orden3);
        
        add(panelOrdenes);
    }
    
    private JPanel crearTarjetaOrden(OrdenInfo ordenInfo, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BLANCO);
        panel.setBounds(25, y, 930, 120);
        panel.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        
        JLabel lblTitulo = new JLabel(ordenInfo.titulo);
        lblTitulo.setBounds(20, 15, 400, 25);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(MARRON_OSCURO);
        panel.add(lblTitulo);
        
        JLabel lblEstado = new JLabel(ordenInfo.estado);
        lblEstado.setBounds(800, 15, 110, 25);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 12));
        lblEstado.setForeground(MARRON_OSCURO);
        lblEstado.setOpaque(true);
        lblEstado.setBackground(ordenInfo.colorEstado);
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstado.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        panel.add(lblEstado);
        
        JLabel lblFechaLabel = new JLabel("Fecha de entrega:");
        lblFechaLabel.setBounds(20, 45, 150, 18);
        lblFechaLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        lblFechaLabel.setForeground(Color.GRAY);
        panel.add(lblFechaLabel);
        
        JLabel lblFecha = new JLabel(ordenInfo.fecha);
        lblFecha.setBounds(170, 45, 150, 18);
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 13));
        lblFecha.setForeground(Color.GRAY);
        panel.add(lblFecha);
        
        JLabel lblMaterialesLabel = new JLabel("Materiales:");
        lblMaterialesLabel.setBounds(20, 68, 150, 18);
        lblMaterialesLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        lblMaterialesLabel.setForeground(Color.GRAY);
        panel.add(lblMaterialesLabel);
        
        JLabel lblMateriales = new JLabel(ordenInfo.materiales);
        lblMateriales.setBounds(170, 68, 620, 18);
        lblMateriales.setFont(new Font("Arial", Font.PLAIN, 13));
        lblMateriales.setForeground(Color.GRAY);
        panel.add(lblMateriales);
        
        JLabel lblTiempoLabel = new JLabel("Tiempo estimado:");
        lblTiempoLabel.setBounds(20, 91, 150, 18);
        lblTiempoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTiempoLabel.setForeground(Color.GRAY);
        panel.add(lblTiempoLabel);
        
        JLabel lblTiempo = new JLabel(ordenInfo.tiempo);
        lblTiempo.setBounds(170, 91, 150, 18);
        lblTiempo.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTiempo.setForeground(Color.GRAY);
        panel.add(lblTiempo);

        JButton btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.setBounds(800, 75, 110, 35);
        btnVerDetalles.setBackground(AMARILLO_BOTON);
        btnVerDetalles.setForeground(MARRON_OSCURO);
        btnVerDetalles.setFont(new Font("Arial", Font.BOLD, 12));
        btnVerDetalles.setFocusPainted(false);
        btnVerDetalles.setBorderPainted(false);
        btnVerDetalles.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnVerDetalles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDetallesOrden(ordenInfo);
            }
        });
        
        panel.add(btnVerDetalles);
        
        return panel;
    }
    
    private void mostrarDetallesOrden(OrdenInfo orden) {
        JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Detalles de la Orden", true);
        dialogo.setSize(700, 650);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(null);
        dialogo.getContentPane().setBackground(BLANCO);
        
        JLabel lblTituloDialog = new JLabel(orden.titulo);
        lblTituloDialog.setBounds(40, 25, 600, 40);
        lblTituloDialog.setFont(new Font("Arial", Font.BOLD, 26));
        lblTituloDialog.setForeground(MARRON_OSCURO);
        dialogo.add(lblTituloDialog);
        
        JLabel lblEstadoDialog = new JLabel(orden.estado);
        lblEstadoDialog.setBounds(40, 75, 130, 35);
        lblEstadoDialog.setFont(new Font("Arial", Font.BOLD, 14));
        lblEstadoDialog.setForeground(MARRON_OSCURO);
        lblEstadoDialog.setOpaque(true);
        lblEstadoDialog.setBackground(orden.colorEstado);
        lblEstadoDialog.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstadoDialog.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        dialogo.add(lblEstadoDialog);
        
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(null);
        panelInfo.setBackground(new Color(250, 250, 250));
        panelInfo.setPreferredSize(new Dimension(600, 420));
        
        int yPos = 25;
        
        agregarCampoDetallado(panelInfo, "👤 Supervisor que asignó:", orden.supervisor, yPos);
        yPos += 60;
        
        agregarCampoDetallado(panelInfo, "📅 Fecha de entrega:", orden.fecha, yPos);
        yPos += 60;
        
        agregarCampoDetallado(panelInfo, "⏱️ Tiempo estimado:", orden.tiempo, yPos);
        yPos += 60;
        
        agregarCampoDetallado(panelInfo, "⚡ Prioridad:", orden.prioridad, yPos);
        yPos += 60;
        
        JLabel lblMaterialesLabel = new JLabel("🔨 Materiales requeridos:");
        lblMaterialesLabel.setBounds(25, yPos, 550, 25);
        lblMaterialesLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        lblMaterialesLabel.setForeground(MARRON_OSCURO);
        panelInfo.add(lblMaterialesLabel);
        
        JTextArea txtMateriales = new JTextArea(orden.materiales);
        txtMateriales.setBounds(25, yPos + 30, 550, 50);
        txtMateriales.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMateriales.setForeground(Color.GRAY);
        txtMateriales.setBackground(BLANCO);
        txtMateriales.setLineWrap(true);
        txtMateriales.setWrapStyleWord(true);
        txtMateriales.setEditable(false);
        txtMateriales.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        panelInfo.add(txtMateriales);
        yPos += 90;
        
        JLabel lblNotasLabel = new JLabel("📝 Notas adicionales:");
        lblNotasLabel.setBounds(25, yPos, 550, 25);
        lblNotasLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        lblNotasLabel.setForeground(MARRON_OSCURO);
        panelInfo.add(lblNotasLabel);
        
        JTextArea txtNotas = new JTextArea(orden.notas);
        txtNotas.setBounds(25, yPos + 30, 550, 70);
        txtNotas.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNotas.setForeground(Color.GRAY);
        txtNotas.setBackground(BLANCO);
        txtNotas.setLineWrap(true);
        txtNotas.setWrapStyleWord(true);
        txtNotas.setEditable(false);
        txtNotas.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_BORDE, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        panelInfo.add(txtNotas);
        
        JScrollPane scrollPane = new JScrollPane(panelInfo);
        scrollPane.setBounds(40, 130, 620, 440);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRIS_BORDE, 1));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dialogo.add(scrollPane);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(290, 585, 120, 40);
        btnCerrar.setBackground(AMARILLO_BOTON);
        btnCerrar.setForeground(MARRON_OSCURO);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> dialogo.dispose());
        dialogo.add(btnCerrar);
        
        dialogo.setVisible(true);
    }
    
    private void agregarCampoDetallado(JPanel panel, String etiqueta, String valor, int y) {
        JLabel lblEtiqueta = new JLabel(etiqueta);
        lblEtiqueta.setBounds(25, y, 280, 25);
        lblEtiqueta.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        lblEtiqueta.setForeground(MARRON_OSCURO);
        panel.add(lblEtiqueta);
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setBounds(310, y, 270, 25);
        lblValor.setFont(new Font("Arial", Font.PLAIN, 15));
        lblValor.setForeground(Color.GRAY);
        panel.add(lblValor);
    }
}