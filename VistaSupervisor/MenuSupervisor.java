package VistaInicioSesion.VistaSupervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MenuSupervisor extends JFrame {

    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color MARRON_CLARO = new Color(150, 100, 70);
    private final Color BEIGE = new Color(235, 220, 195);

    private JPanel panelContenido;
    private CardLayout cardLayout;

    public MenuSupervisor() {
        initComponents();
    }

    private void initComponents() {
        setTitle("DosCaminos - Supervisor");
        setSize(1440, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(BEIGE);

        // Panel lateral izquierdo
        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(null);
        panelLateral.setBackground(MARRON_OSCURO);
        panelLateral.setBounds(30, 15, 360, 775);

        // Logo
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
            lblLogo.setFont(new Font("Arial", Font.BOLD, 22));
        }
        panelLateral.add(lblLogo);

        // Botones del menú
        JButton btnProveedores = crearBotonMenu("⇄  Proveedores", 60, 260, true);
        JButton btnPedidos = crearBotonMenu("🛒  Pedidos", 60, 340, false);
        JButton btnInventario = crearBotonMenu("📦  Inventario", 60, 420, false);
        JButton btnMantenimiento = crearBotonMenu("⚙  Mantenimiento", 60, 500, false);
        JButton btnCostos = crearBotonMenu("💰  Costos", 60, 580, false);

        // Panel de contenido con CardLayout
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBounds(400, 15, 1020, 775);
        panelContenido.setBackground(BEIGE);

        // Agregar panel de proveedores
        panelContenido.add(new PanelProveedores(), "proveedores");
        panelContenido.add(new PanelPedidos(), "pedidos");
        panelContenido.add(new PanelInventario(), "inventario");
        panelContenido.add(new PanelMantenimiento(), "mantenimiento");
        panelContenido.add(new PanelCostos(), "costos");

        // Eventos de los botones
        btnProveedores.addActionListener(e -> {
            cardLayout.show(panelContenido, "proveedores");
            actualizarSeleccion(btnProveedores, btnPedidos, btnInventario, btnMantenimiento, btnCostos);
        });

        btnPedidos.addActionListener(e -> {
            cardLayout.show(panelContenido, "pedidos");
            actualizarSeleccion(btnPedidos, btnProveedores, btnInventario, btnMantenimiento, btnCostos);
        });

        btnInventario.addActionListener(e -> {
            cardLayout.show(panelContenido, "inventario");
            actualizarSeleccion(btnInventario, btnProveedores, btnPedidos, btnMantenimiento, btnCostos);
        });

        btnMantenimiento.addActionListener(e -> {
            cardLayout.show(panelContenido, "mantenimiento");
            actualizarSeleccion(btnMantenimiento, btnProveedores, btnPedidos, btnInventario, btnCostos);
        });

        btnCostos.addActionListener(e -> {
            cardLayout.show(panelContenido, "costos");
            actualizarSeleccion(btnCostos, btnProveedores, btnPedidos, btnInventario, btnMantenimiento);
        });

        panelLateral.add(btnProveedores);
        panelLateral.add(btnPedidos);
        panelLateral.add(btnInventario);
        panelLateral.add(btnMantenimiento);
        panelLateral.add(btnCostos);
        

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
            new MenuSupervisor().setVisible(true);
        });
    }
}
