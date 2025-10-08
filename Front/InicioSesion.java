package Front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InicioSesion extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIniciar;
    private JLabel lblRegistro;

    public InicioSesion() {
        setTitle("Carpintería Artesanal Dos Caminos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 230, 200));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        // ----- Título -----
        JLabel lblTitulo = new JLabel("CARPINTERÍA ARTESANAL", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);

        // ----- Logo -----
        ImageIcon icono = new ImageIcon("src/imagenes/logo_doscaminos.png");
        Image imagenEscalada = icono.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(imagenEscalada));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblLogo);

        // ----- Subtítulo -----
        JLabel lblInicia = new JLabel("Inicia Sesión", JLabel.CENTER);
        lblInicia.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblInicia.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblInicia);
        panel.add(Box.createVerticalStrut(15));

        // ----- Panel de campos -----
        JPanel campos = new JPanel(new GridLayout(4, 1, 0, 8));
        campos.setBackground(new Color(240, 230, 200));

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();
        txtUsuario.setPreferredSize(new Dimension(200, 28));

        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField();
        txtContrasena.setPreferredSize(new Dimension(200, 28));

        campos.add(lblUsuario);
        campos.add(txtUsuario);
        campos.add(lblContrasena);
        campos.add(txtContrasena);

        panel.add(campos);
        panel.add(Box.createVerticalStrut(10));

        // ----- Enlace recuperar -----
        JLabel lblRecuperar = new JLabel("¿Olvidaste tu contraseña? Recupérala", JLabel.CENTER);
        lblRecuperar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblRecuperar.setForeground(new Color(80, 60, 30));
        lblRecuperar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblRecuperar);
        panel.add(Box.createVerticalStrut(15));

        // ----- Botón -----
        btnIniciar = new JButton("Iniciar Sesión");
        btnIniciar.setBackground(new Color(240, 190, 80));
        btnIniciar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIniciar.setFocusPainted(false);
        btnIniciar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnIniciar);

        panel.add(Box.createVerticalStrut(15));

        // ----- Enlace registro -----
        lblRegistro = new JLabel("¿No tienes una cuenta? ¡Regístrate!", JLabel.CENTER);
        lblRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblRegistro.setForeground(new Color(100, 60, 20));
        lblRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblRegistro);

        // ----- Acción del botón -----
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());

                if (usuario.equals("Sebastian") && contrasena.equals("juansebastian777")) {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + usuario + "!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InicioSesion().setVisible(true));
    }
}
