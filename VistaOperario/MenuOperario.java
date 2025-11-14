package VistaInicioSesion.VistaOperario;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MenuOperario extends JFrame {

    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color MARRON_CLARO = new Color(150, 100, 70);
    private final Color BEIGE = new Color(235, 220, 195);

    private JPanel panelContenido;
    private CardLayout cardLayout;

    public MenuOperario() {
        initComponents();
    }

    private void initComponents() {
        setTitle("DosCaminos - Operario");
        setSize(1440, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(BEIGE);

        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(null);
        panelLateral.setBackground(MARRON_OSCURO);
        panelLateral.setBounds(30, 15, 360, 775);

        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(75, 30, 210, 180);
        try {
            BufferedImage img = ImageIO.read(new File("src/imagenes/logo_doscaminos.png"));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(210, 180, Image.SCALE_SMOOTH));
            lblLogo.setIcon(icon);
        } catch (Exception e) {
            lblLogo.setText("DOSCAMINOS");
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
            lblLogo.setForeground(Color.WHITE);
            lblLogo.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22));
        }
        panelLateral.add(lblLogo);

        JButton btnVerificarInsumos = crearBotonMenu("📋  Verificar Insumos", 60, 260, true);
        JButton btnConsultarInventario = crearBotonMenu("🛍️  Consultar Inventario", 60, 340, false);
        JButton btnOrdenesAsignadas = crearBotonMenu("✓  Ordenes Asignadas", 60, 420, false);
        JButton btnReportarProblemas = crearBotonMenu("⚠️  Reportar Problemas", 60, 500, false);
        JButton btnMiPerfil = crearBotonMenu("👤  Mi perfil", 60, 580, false);

        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBounds(400, 15, 1020, 775);
        panelContenido.setBackground(BEIGE);

        panelContenido.add(new PanelVerificarInsumos(), "verificarInsumos");
        panelContenido.add(new PanelConsultarInventario(), "consultarInventario");
        panelContenido.add(new PanelOrdenesAsignadas(), "ordenesAsignadas");
        panelContenido.add(new PanelReportarProblemas(), "reportarProblemas");
        panelContenido.add(new PanelMiPerfilOperario(), "miPerfil");

        btnVerificarInsumos.addActionListener(e -> {
            cardLayout.show(panelContenido, "verificarInsumos");
            actualizarSeleccion(btnVerificarInsumos, btnConsultarInventario, btnOrdenesAsignadas,
                    btnReportarProblemas, btnMiPerfil);
        });

        btnConsultarInventario.addActionListener(e -> {
            cardLayout.show(panelContenido, "consultarInventario");
            actualizarSeleccion(btnConsultarInventario, btnVerificarInsumos, btnOrdenesAsignadas,
                    btnReportarProblemas, btnMiPerfil);
        });

        btnOrdenesAsignadas.addActionListener(e -> {
            cardLayout.show(panelContenido, "ordenesAsignadas");
            actualizarSeleccion(btnOrdenesAsignadas, btnVerificarInsumos, btnConsultarInventario,
                    btnReportarProblemas, btnMiPerfil);
        });

        btnReportarProblemas.addActionListener(e -> {
            cardLayout.show(panelContenido, "reportarProblemas");
            actualizarSeleccion(btnReportarProblemas, btnVerificarInsumos, btnConsultarInventario,
                    btnOrdenesAsignadas, btnMiPerfil);
        });

        btnMiPerfil.addActionListener(e -> {
            cardLayout.show(panelContenido, "miPerfil");
            actualizarSeleccion(btnMiPerfil, btnVerificarInsumos, btnConsultarInventario,
                    btnOrdenesAsignadas, btnReportarProblemas);
        });

        panelLateral.add(btnVerificarInsumos);
        panelLateral.add(btnConsultarInventario);
        panelLateral.add(btnOrdenesAsignadas);
        panelLateral.add(btnReportarProblemas);
        panelLateral.add(btnMiPerfil);

        panelPrincipal.add(panelLateral);
        panelPrincipal.add(panelContenido);

        add(panelPrincipal);
    }

    private JButton crearBotonMenu(String texto, int x, int y, boolean seleccionado) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, y, 240, 60);
        btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (seleccionado) {
            btn.setBackground(MARRON_CLARO);
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(MARRON_OSCURO);
            btn.setForeground(Color.WHITE);
        }

        return btn;
    }

    private void actualizarSeleccion(JButton seleccionado, JButton... otros) {
        seleccionado.setBackground(MARRON_CLARO);
        for (JButton btn : otros) {
            btn.setBackground(MARRON_OSCURO);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuOperario().setVisible(true);
        });
    }
}
