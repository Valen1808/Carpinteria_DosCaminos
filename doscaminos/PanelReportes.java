package doscaminos;

import javax.swing.*;
import java.awt.*;

public class PanelReportes extends JPanel {
    
    // Colores
    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color BEIGE = new Color(235, 220, 195);
    private final Color BLANCO = new Color(255, 255, 255);
    private final Color AMARILLO_BOTON = new Color(230, 190, 80);
    private final Color VERDE_BOTON = new Color(34, 197, 94);
    private final Color VERDE_CLARO = new Color(134, 239, 172);
    private final Color AZUL_CLARO = new Color(147, 197, 253);
    private final Color MORADO_CLARO = new Color(196, 181, 253);
    private final Color VERDE_BARRA = new Color(132, 204, 22);
    private final Color BEIGE_BARRA = new Color(229, 217, 182);
    
    public PanelReportes() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);
        setBackground(BEIGE);
        setBounds(0, 0, 920, 675);
        
        // Header con título y usuario
        JLabel lblTitulo = new JLabel("Reportes");
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
        
        // Panel principal de reportes
        JPanel panelReportes = new JPanel();
        panelReportes.setLayout(null);
        panelReportes.setBackground(BLANCO);
        panelReportes.setBounds(20, 90, 880, 560);
        panelReportes.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 160), 1));
        
        // Título del panel
        JLabel lblReportesEstadisticas = new JLabel("Reportes y Estadísticas");
        lblReportesEstadisticas.setBounds(20, 20, 400, 30);
        lblReportesEstadisticas.setFont(new Font("Arial", Font.BOLD, 24));
        lblReportesEstadisticas.setForeground(MARRON_OSCURO);
        panelReportes.add(lblReportesEstadisticas);
        
        // Botones de exportar
        JButton btnDescargarPDF = new JButton("Descargar PDF");
        btnDescargarPDF.setBounds(630, 20, 120, 35);
        btnDescargarPDF.setBackground(AMARILLO_BOTON);
        btnDescargarPDF.setForeground(MARRON_OSCURO);
        btnDescargarPDF.setFont(new Font("Arial", Font.BOLD, 12));
        btnDescargarPDF.setFocusPainted(false);
        btnDescargarPDF.setBorderPainted(false);
        btnDescargarPDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelReportes.add(btnDescargarPDF);
        
        JButton btnExportarExcel = new JButton("Exportar Excel");
        btnExportarExcel.setBounds(760, 20, 120, 35);
        btnExportarExcel.setBackground(VERDE_BOTON);
        btnExportarExcel.setForeground(Color.WHITE);
        btnExportarExcel.setFont(new Font("Arial", Font.BOLD, 12));
        btnExportarExcel.setFocusPainted(false);
        btnExportarExcel.setBorderPainted(false);
        btnExportarExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelReportes.add(btnExportarExcel);
        
        // Panel de Ventas Mensuales
        JPanel panelVentasMensuales = new JPanel();
        panelVentasMensuales.setLayout(null);
        panelVentasMensuales.setBackground(BEIGE);
        panelVentasMensuales.setBounds(20, 70, 470, 280);
        panelVentasMensuales.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 160), 1));
        
        JLabel lblVentasMensuales = new JLabel("Ventas Mensuales");
        lblVentasMensuales.setBounds(15, 10, 200, 25);
        lblVentasMensuales.setFont(new Font("Arial", Font.BOLD, 16));
        lblVentasMensuales.setForeground(MARRON_OSCURO);
        panelVentasMensuales.add(lblVentasMensuales);
        
        // Gráfico de barras
        GraficoBarras grafico = new GraficoBarras();
        grafico.setBounds(30, 50, 420, 210);
        panelVentasMensuales.add(grafico);
        
        panelReportes.add(panelVentasMensuales);
        
        // Panel de Productos más vendidos
        JPanel panelProductosVendidos = new JPanel();
        panelProductosVendidos.setLayout(null);
        panelProductosVendidos.setBackground(BEIGE);
        panelProductosVendidos.setBounds(510, 70, 350, 280);
        panelProductosVendidos.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 160), 1));
        
        JLabel lblProductosVendidos = new JLabel("Productos más vendidos");
        lblProductosVendidos.setBounds(15, 10, 250, 25);
        lblProductosVendidos.setFont(new Font("Arial", Font.BOLD, 16));
        lblProductosVendidos.setForeground(MARRON_OSCURO);
        panelProductosVendidos.add(lblProductosVendidos);
        
        // Barras de progreso de productos
        crearBarraProducto(panelProductosVendidos, "Mesa de comedor", 65, 50);
        crearBarraProducto(panelProductosVendidos, "Estantería Modular", 45, 120);
        crearBarraProducto(panelProductosVendidos, "Puerta de Madera", 30, 190);
        
        panelReportes.add(panelProductosVendidos);
        
        // Panel de Resumen Financiero
        JLabel lblResumenFinanciero = new JLabel("Resumen Financiero");
        lblResumenFinanciero.setBounds(20, 365, 250, 25);
        lblResumenFinanciero.setFont(new Font("Arial", Font.BOLD, 18));
        lblResumenFinanciero.setForeground(MARRON_OSCURO);
        panelReportes.add(lblResumenFinanciero);
        
        // Tarjetas financieras
        JPanel tarjetaIngresos = crearTarjetaFinanciera("Ingresos Totales", "$24,580", VERDE_CLARO, 20, 400);
        panelReportes.add(tarjetaIngresos);
        
        JPanel tarjetaGastos = crearTarjetaFinanciera("Gastos", "$12,340", AZUL_CLARO, 310, 400);
        panelReportes.add(tarjetaGastos);
        
        JPanel tarjetaBeneficio = crearTarjetaFinanciera("Beneficio Neto", "$12,240", MORADO_CLARO, 600, 400);
        panelReportes.add(tarjetaBeneficio);
        
        add(panelReportes);
    }
    
    private void crearBarraProducto(JPanel panel, String producto, int porcentaje, int y) {
        JLabel lblProducto = new JLabel(producto);
        lblProducto.setBounds(15, y, 200, 20);
        lblProducto.setFont(new Font("Arial", Font.PLAIN, 13));
        lblProducto.setForeground(MARRON_OSCURO);
        panel.add(lblProducto);
        
        JLabel lblPorcentaje = new JLabel(porcentaje + "%");
        lblPorcentaje.setBounds(290, y, 50, 20);
        lblPorcentaje.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPorcentaje.setForeground(Color.GRAY);
        panel.add(lblPorcentaje);
        
        // Barra de progreso
        JPanel barraFondo = new JPanel();
        barraFondo.setBounds(15, y + 25, 320, 25);
        barraFondo.setBackground(BEIGE_BARRA);
        barraFondo.setLayout(null);
        
        JPanel barraProgreso = new JPanel();
        barraProgreso.setBounds(0, 0, (int)(320 * porcentaje / 100.0), 25);
        barraProgreso.setBackground(MARRON_OSCURO);
        barraFondo.add(barraProgreso);
        
        panel.add(barraFondo);
    }
    
    private JPanel crearTarjetaFinanciera(String titulo, String valor, Color colorFondo, int x, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(colorFondo);
        panel.setBounds(x, y, 270, 120);
        panel.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 160), 1));
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setBounds(20, 20, 230, 25);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        
        if (titulo.contains("Ingresos")) {
            lblTitulo.setForeground(new Color(22, 101, 52));
        } else if (titulo.contains("Gastos")) {
            lblTitulo.setForeground(new Color(30, 58, 138));
        } else {
            lblTitulo.setForeground(new Color(88, 28, 135));
        }
        
        panel.add(lblTitulo);
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setBounds(20, 50, 230, 40);
        lblValor.setFont(new Font("Arial", Font.BOLD, 32));
        
        if (titulo.contains("Ingresos")) {
            lblValor.setForeground(new Color(22, 101, 52));
        } else if (titulo.contains("Gastos")) {
            lblValor.setForeground(new Color(30, 58, 138));
        } else {
            lblValor.setForeground(new Color(88, 28, 135));
        }
        
        panel.add(lblValor);
        
        return panel;
    }
    
    // Clase interna para el gráfico de barras
    class GraficoBarras extends JPanel {
        private final String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo"};
        private final int[] valores = {70, 55, 85, 65, 95}; // Porcentajes de altura
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int ancho = getWidth();
            int alto = getHeight();
            int anchoBarra = 50;
            int espaciado = (ancho - (anchoBarra * 5)) / 6;
            int alturaMaxima = alto - 40;
            
            // Dibujar barras
            for (int i = 0; i < meses.length; i++) {
                int x = espaciado + (i * (anchoBarra + espaciado));
                int alturaBarra = (int) (alturaMaxima * valores[i] / 100.0);
                int y = alturaMaxima - alturaBarra;
                
                // Barra verde
                g2d.setColor(VERDE_BARRA);
                g2d.fillRoundRect(x, y, anchoBarra, alturaBarra, 8, 8);
                
                // Nombre del mes
                g2d.setColor(MARRON_OSCURO);
                g2d.setFont(new Font("Arial", Font.PLAIN, 11));
                FontMetrics fm = g2d.getFontMetrics();
                int anchoTexto = fm.stringWidth(meses[i]);
                g2d.drawString(meses[i], x + (anchoBarra - anchoTexto) / 2, alto - 10);
            }
        }
    }
}