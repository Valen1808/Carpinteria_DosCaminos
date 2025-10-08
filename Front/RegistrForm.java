package Front;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class RegistrForm extends JFrame {

    // Componentes
    private JTextField txtTipoDocumento;
    private JTextField txtNumeroDocumento;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmarContrasena;
    private JComboBox<String> cboDia;
    private JComboBox<String> cboMes;
    private JComboBox<String> cboAnio;
    private JButton btnRegistrar;
    private JLabel lblFondo;
    private JLabel lblLogo;

    public RegistrForm() {
        initComponents();
    }

    private void initComponents() {
        // Configuración del JFrame
        setTitle("DosCaminos - Registro");
        setSize(950, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con layout null para posicionamiento absoluto
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);

        // Cargar imagen de fondo
        lblFondo = new JLabel();
        lblFondo.setBounds(0, 0, 950, 550);
        try {
            BufferedImage img = ImageIO.read(new File("src/imagenes/fondo_madera.jpg"));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(950, 550, Image.SCALE_SMOOTH));
            lblFondo.setIcon(icon);
        } catch (Exception e) {
            lblFondo.setBackground(new Color(139, 90, 60));
            lblFondo.setOpaque(true);
        }

        // Panel central beige
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        panelFormulario.setBackground(new Color(235, 220, 195));
        panelFormulario.setBounds(320, 130, 310, 430);
        panelFormulario.setBorder(BorderFactory.createLineBorder(new Color(180, 150, 120), 2));

        // Logo superior
        lblLogo = new JLabel();
        lblLogo.setBounds(400, 2, 150, 135);
        try {
            BufferedImage img = ImageIO.read(new File("src/imagenes/logo_doscaminos.png"));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            lblLogo.setIcon(icon);
        } catch (Exception e) {
            lblLogo.setText("DOSCAMINOS");
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
            lblLogo.setFont(new Font("Arial", Font.BOLD, 18));
        }

        // Título "¡Regístrate!"
        JLabel lblTitulo = new JLabel("¡Regístrate!");
        lblTitulo.setBounds(80, 10, 150, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(100, 70, 50));
        panelFormulario.add(lblTitulo);

        // Labels y campos - Primera fila
        JLabel lblTipoDoc = new JLabel("Tipo de Documento");
        lblTipoDoc.setBounds(20, 50, 130, 20);
        lblTipoDoc.setFont(new Font("Arial", Font.PLAIN, 11));
        panelFormulario.add(lblTipoDoc);

        txtTipoDocumento = new JTextField();
        txtTipoDocumento.setBounds(20, 70, 130, 25);
        panelFormulario.add(txtTipoDocumento);

        JLabel lblNumDoc = new JLabel("N° de Documento");
        lblNumDoc.setBounds(160, 50, 130, 20);
        lblNumDoc.setFont(new Font("Arial", Font.PLAIN, 11));
        panelFormulario.add(lblNumDoc);

        txtNumeroDocumento = new JTextField();
        txtNumeroDocumento.setBounds(160, 70, 130, 25);
        panelFormulario.add(txtNumeroDocumento);

        // Segunda fila
        JLabel lblNombres = new JLabel("Nombres");
        lblNombres.setBounds(20, 105, 130, 20);
        lblNombres.setFont(new Font("Arial", Font.PLAIN, 11));
        panelFormulario.add(lblNombres);

        txtNombres = new JTextField();
        txtNombres.setBounds(20, 125, 130, 25);
        panelFormulario.add(txtNombres);

        JLabel lblApellidos = new JLabel("Apellidos");
        lblApellidos.setBounds(160, 105, 130, 20);
        lblApellidos.setFont(new Font("Arial", Font.PLAIN, 11));
        panelFormulario.add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(160, 125, 130, 25);
        panelFormulario.add(txtApellidos);

        // Tercera fila
        JLabel lblCorreo = new JLabel("Correo Electrónico");
        lblCorreo.setBounds(20, 160, 130, 20);
        lblCorreo.setFont(new Font("Arial", Font.PLAIN, 11));
        panelFormulario.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(20, 180, 130, 25);
        panelFormulario.add(txtCorreo);

        JLabel lblTelefono = new JLabel("Teléfono");
        lblTelefono.setBounds(160, 160, 130, 20);
        lblTelefono.setFont(new Font("Arial", Font.PLAIN, 11));
        panelFormulario.add(lblTelefono);

        JLabel lblCodigo = new JLabel("+57");
        lblCodigo.setBounds(160, 180, 25, 25);
        lblCodigo.setFont(new Font("Arial", Font.PLAIN, 11));
        panelFormulario.add(lblCodigo);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(185, 180, 105, 25);
        panelFormulario.add(txtTelefono);

        // Cuarta fila
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setBounds(20, 215, 130, 20);
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 11));
        panelFormulario.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(20, 235, 130, 25);
        panelFormulario.add(txtContrasena);

        JLabel lblConfirmar = new JLabel("Confirmar Contraseña");
        lblConfirmar.setBounds(160, 215, 130, 20);
        lblConfirmar.setFont(new Font("Arial", Font.PLAIN, 11));
        panelFormulario.add(lblConfirmar);

        txtConfirmarContrasena = new JPasswordField();
        txtConfirmarContrasena.setBounds(160, 235, 130, 25);
        panelFormulario.add(txtConfirmarContrasena);

        // Texto bajo contraseña
        JLabel lblMinCaracteres = new JLabel("Al menos 8 Caracteres");
        lblMinCaracteres.setBounds(20, 260, 130, 15);
        lblMinCaracteres.setFont(new Font("Arial", Font.PLAIN, 9));
        lblMinCaracteres.setForeground(Color.GRAY);
        panelFormulario.add(lblMinCaracteres);

        // Fecha de nacimiento
        JLabel lblFechaNac = new JLabel("Fecha de nacimiento");
        lblFechaNac.setBounds(20, 280, 150, 20);
        lblFechaNac.setFont(new Font("Arial", Font.PLAIN, 11));
        panelFormulario.add(lblFechaNac);

        // ComboBox para día
        String[] dias = new String[32];
        dias[0] = "Día";
        for (int i = 1; i <= 31; i++) {
            dias[i] = String.valueOf(i);
        }
        cboDia = new JComboBox<>(dias);
        cboDia.setBounds(20, 300, 70, 25);
        panelFormulario.add(cboDia);

        // ComboBox para mes
        String[] meses = {"Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        cboMes = new JComboBox<>(meses);
        cboMes.setBounds(100, 300, 90, 25);
        panelFormulario.add(cboMes);

        // ComboBox para año
        String[] anios = new String[101];
        anios[0] = "Año";
        int anioActual = 2025;
        for (int i = 1; i <= 100; i++) {
            anios[i] = String.valueOf(anioActual - i);
        }
        cboAnio = new JComboBox<>(anios);
        cboAnio.setBounds(200, 300, 90, 25);
        panelFormulario.add(cboAnio);

        // Botón Registrarme
        btnRegistrar = new JButton("Registrarme");
        btnRegistrar.setBounds(80, 340, 150, 35);
        btnRegistrar.setBackground(new Color(180, 140, 80));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorder(BorderFactory.createLineBorder(new Color(150, 110, 60), 2));
        panelFormulario.add(btnRegistrar);

        // Link "¿Ya tienes una cuenta?"
        JLabel lblYaTienesCuenta = new JLabel("¿Ya tienes una cuenta?");
        lblYaTienesCuenta.setBounds(50, 380, 130, 20);
        lblYaTienesCuenta.setFont(new Font("Arial", Font.PLAIN, 11));
        lblYaTienesCuenta.setForeground(Color.BLACK);
        panelFormulario.add(lblYaTienesCuenta);

        JLabel lblIniciarSesion = new JLabel("¡Inicia sesión aquí!");
        lblIniciarSesion.setBounds(175, 380, 110, 20);
        lblIniciarSesion.setFont(new Font("Arial", Font.PLAIN, 11));
        lblIniciarSesion.setForeground(new Color(0, 100, 200));
        lblIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFormulario.add(lblIniciarSesion);

        // Agregar componentes al panel principal
        panelPrincipal.add(lblLogo);
        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(lblFondo);

        add(panelPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistrForm().setVisible(true);
        });
    }
}
