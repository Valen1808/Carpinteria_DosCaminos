package doscaminos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MenuPrincipalConUsuarios extends JFrame {

    private final Color MARRON_OSCURO = new Color(90, 60, 40);
    private final Color MARRON_CLARO = new Color(150, 100, 70);
    private final Color BEIGE = new Color(235, 220, 195);

    private JPanel panelContenido;
    private CardLayout cardLayout;

    public MenuPrincipalConUsuarios() {
        initComponents();
    }

    private void initComponents() {
        setTitle("DosCaminos - Sistema");
        setSize(1280, 720);
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
        panelLateral.setBounds(25, 15, 315, 675);

        // Logo
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(50, 30, 215, 150);
        try {
            BufferedImage img = ImageIO.read(new File("src/imagenes/logo_doscaminos.png"));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(215, 150, Image.SCALE_SMOOTH));
            lblLogo.setIcon(icon);
        } catch (Exception e) {
            lblLogo.setText("DOSCAMINOS");
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
            lblLogo.setForeground(Color.WHITE);
            lblLogo.setFont(new Font("Arial", Font.BOLD, 20));
        }
        panelLateral.add(lblLogo);

        // Botones del menú
        JButton btnMenuPrincipal = crearBotonMenu("Menú Principal", 50, 240, true);
        JButton btnUsuarios = crearBotonMenu("Usuarios", 50, 310, false);
        JButton btnProductos = crearBotonMenu("Productos", 50, 380, false);
        JButton btnVentas = crearBotonMenu("Ventas", 50, 450, false);
        JButton btnReportes = crearBotonMenu("Reportes", 50, 520, false);
        JButton btnMiPerfil = crearBotonMenu("Mi perfil", 50, 590, false);

        // Panel de contenido con CardLayout
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBounds(340, 15, 920, 675);
        panelContenido.setBackground(BEIGE);

        // Agregar paneles
        panelContenido.add(new PanelMenuPrincipal(), "menu");
        panelContenido.add(new PanelUsuarios(), "usuarios");
        panelContenido.add(new PanelProductos(), "productos");
        panelContenido.add(new PanelVentas(), "ventas");
        panelContenido.add(new PanelReportes(), "reportes");
        panelContenido.add(new PanelMiPerfil(), "miperfil");

        //Events de botones panel izquierdo
        btnMenuPrincipal.addActionListener(e -> {
            cardLayout.show(panelContenido, "menu");
            actualizarSeleccion(btnMenuPrincipal, btnUsuarios, btnProductos, btnVentas, btnReportes, btnMiPerfil);
        });

        btnUsuarios.addActionListener(e -> {
            cardLayout.show(panelContenido, "usuarios");
            actualizarSeleccion(btnUsuarios, btnMenuPrincipal, btnProductos, btnVentas, btnReportes, btnMiPerfil);
        });

        btnProductos.addActionListener(e -> {
            cardLayout.show(panelContenido, "productos");
            actualizarSeleccion(btnProductos, btnMenuPrincipal, btnUsuarios, btnVentas, btnReportes, btnMiPerfil);
        });

        btnVentas.addActionListener(e -> {
            cardLayout.show(panelContenido, "ventas");
            actualizarSeleccion(btnVentas, btnMenuPrincipal, btnUsuarios, btnProductos, btnReportes, btnMiPerfil);
        });

        btnReportes.addActionListener(e -> {
            cardLayout.show(panelContenido, "reportes");
            actualizarSeleccion(btnReportes, btnMenuPrincipal, btnUsuarios, btnProductos, btnVentas, btnMiPerfil);
        });

        btnMiPerfil.addActionListener(e -> {
            cardLayout.show(panelContenido, "miperfil");
            actualizarSeleccion(btnMiPerfil, btnMenuPrincipal, btnUsuarios, btnProductos, btnVentas, btnReportes);
        });

        panelLateral.add(btnMenuPrincipal);
        panelLateral.add(btnUsuarios);
        panelLateral.add(btnProductos);
        panelLateral.add(btnVentas);
        panelLateral.add(btnReportes);
        panelLateral.add(btnMiPerfil);

        panelPrincipal.add(panelLateral);
        panelPrincipal.add(panelContenido);

        add(panelPrincipal);
    }

    private JButton crearBotonMenu(String texto, int x, int y, boolean seleccionado) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, y, 215, 50);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
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
            new MenuPrincipalConUsuarios().setVisible(true);
        });
    }
}
