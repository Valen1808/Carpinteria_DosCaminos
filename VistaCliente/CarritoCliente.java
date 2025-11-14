package VistaInicioSesion.VistaCliente;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

public class CarritoCliente extends JFrame {

    private ArrayList<?> productos;
    private DefaultTableModel modeloTabla;
    private JLabel lblSubtotal, lblEnvio, lblTotal;
    private double subtotal = 690000;
    private double envio = 30000;
    private double total = 720000;

    private JPanel panelCamposPago;
    private JRadioButton rbTarjeta, rbEfectivo, rbTransferencia;
    
    private JTextField txtNumeroTarjeta, txtNombreTitular, txtCVV;
    private JComboBox<String> cmbMes, cmbAnio;
    
    private JTextField txtMontoEfectivo;
    private JLabel lblCambio;
    
    private JTextField txtCelularNequi, txtNombreNequi;

    public CarritoCliente(ArrayList<?> productos) {
        this.productos = productos;
        initComponents();
    }

    private void initComponents() {
        setTitle("Mi carrito");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(239, 228, 206));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        JLabel lblTitulo = new JLabel("Mi carrito");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(94, 73, 62));
        panelSuperior.add(lblTitulo);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(new Color(239, 228, 206));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        String[] columnas = {"Producto", "Precio", "Cantidad", "Subtotal", ""};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 4;
            }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.setRowHeight(50);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(40);

        agregarProductosAlCarrito();

        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panelCentral.add(scrollTabla);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(Color.WHITE);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel panelResumen = new JPanel();
        panelResumen.setLayout(new BoxLayout(panelResumen, BoxLayout.Y_AXIS));
        panelResumen.setBackground(Color.WHITE);

        JPanel pSubtotal = new JPanel(new BorderLayout());
        pSubtotal.setBackground(Color.WHITE);
        pSubtotal.add(new JLabel("Subtotal"), BorderLayout.WEST);
        lblSubtotal = new JLabel("$690,000");
        lblSubtotal.setHorizontalAlignment(JLabel.RIGHT);
        pSubtotal.add(lblSubtotal, BorderLayout.EAST);

        JPanel pEnvio = new JPanel(new BorderLayout());
        pEnvio.setBackground(Color.WHITE);
        pEnvio.add(new JLabel("Envío"), BorderLayout.WEST);
        lblEnvio = new JLabel("$30,000");
        lblEnvio.setHorizontalAlignment(JLabel.RIGHT);
        pEnvio.add(lblEnvio, BorderLayout.EAST);

        JPanel pTotal = new JPanel(new BorderLayout());
        pTotal.setBackground(Color.WHITE);
        JLabel lblTotalLabel = new JLabel("Total");
        lblTotalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pTotal.add(lblTotalLabel, BorderLayout.WEST);
        lblTotal = new JLabel("$720,000");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotal.setHorizontalAlignment(JLabel.RIGHT);
        pTotal.add(lblTotal, BorderLayout.EAST);

        panelResumen.add(pSubtotal);
        panelResumen.add(Box.createVerticalStrut(8));
        panelResumen.add(pEnvio);
        panelResumen.add(Box.createVerticalStrut(8));
        panelResumen.add(new JSeparator());
        panelResumen.add(Box.createVerticalStrut(8));
        panelResumen.add(pTotal);

        JPanel panelPago = new JPanel();
        panelPago.setLayout(new BoxLayout(panelPago, BoxLayout.Y_AXIS));
        panelPago.setBackground(Color.WHITE);
        panelPago.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JLabel lblMetodo = new JLabel("Método de Pago");
        lblMetodo.setFont(new Font("Arial", Font.BOLD, 13));
        lblMetodo.setForeground(new Color(94, 73, 62));

        rbTarjeta = new JRadioButton("Tarjeta de Crédito", true);
        rbTarjeta.setFont(new Font("Arial", Font.PLAIN, 12));
        rbTarjeta.setBackground(Color.WHITE);

        rbEfectivo = new JRadioButton("Pago en Efectivo");
        rbEfectivo.setFont(new Font("Arial", Font.PLAIN, 12));
        rbEfectivo.setBackground(Color.WHITE);

        rbTransferencia = new JRadioButton("Transferencia Bancaria (Nequi)");
        rbTransferencia.setFont(new Font("Arial", Font.PLAIN, 12));
        rbTransferencia.setBackground(Color.WHITE);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbTarjeta);
        grupo.add(rbEfectivo);
        grupo.add(rbTransferencia);

        panelCamposPago = new JPanel();
        panelCamposPago.setLayout(new BoxLayout(panelCamposPago, BoxLayout.Y_AXIS));
        panelCamposPago.setBackground(Color.WHITE);
        panelCamposPago.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        rbTarjeta.addActionListener(e -> mostrarCamposTarjeta());
        rbEfectivo.addActionListener(e -> mostrarCamposEfectivo());
        rbTransferencia.addActionListener(e -> mostrarCamposTransferencia());

        panelPago.add(lblMetodo);
        panelPago.add(Box.createVerticalStrut(5));
        panelPago.add(rbTarjeta);
        panelPago.add(rbEfectivo);
        panelPago.add(rbTransferencia);
        panelPago.add(Box.createVerticalStrut(10));
        panelPago.add(panelCamposPago);

        mostrarCamposTarjeta();

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setBackground(Color.WHITE);

        JButton btnContinuar = new JButton("Continuar Comprando");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 13));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBackground(new Color(120, 120, 120));
        btnContinuar.setBorderPainted(false);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnContinuar.addActionListener(e -> dispose());

        JButton btnProcesar = new JButton("Proceder al pago");
        btnProcesar.setFont(new Font("Arial", Font.BOLD, 13));
        btnProcesar.setForeground(Color.WHITE);
        btnProcesar.setBackground(new Color(218, 165, 32));
        btnProcesar.setBorderPainted(false);
        btnProcesar.setFocusPainted(false);
        btnProcesar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnProcesar.addActionListener(e -> procesarPago());

        panelBotones.add(btnContinuar);
        panelBotones.add(btnProcesar);

        panelInferior.add(panelResumen, BorderLayout.NORTH);
        panelInferior.add(panelPago, BorderLayout.CENTER);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        mainPanel.add(panelSuperior, BorderLayout.NORTH);
        mainPanel.add(panelCentral, BorderLayout.CENTER);
        mainPanel.add(panelInferior, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void mostrarCamposTarjeta() {
        panelCamposPago.removeAll();
        
        panelCamposPago.add(new JLabel("Número de Tarjeta *"));
        txtNumeroTarjeta = new JTextField();
        txtNumeroTarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtNumeroTarjeta.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
                if (txtNumeroTarjeta.getText().replaceAll("\\s", "").length() >= 16) {
                    e.consume();
                }
            }
            public void keyReleased(KeyEvent e) {
                String t = txtNumeroTarjeta.getText().replaceAll("\\s", "");
                StringBuilder f = new StringBuilder();
                for (int i = 0; i < t.length(); i++) {
                    if (i > 0 && i % 4 == 0) f.append(" ");
                    f.append(t.charAt(i));
                }
                txtNumeroTarjeta.setText(f.toString());
            }
        });
        panelCamposPago.add(txtNumeroTarjeta);
        panelCamposPago.add(Box.createVerticalStrut(10));
        
        panelCamposPago.add(new JLabel("Nombre del Titular *"));
        txtNombreTitular = new JTextField();
        txtNombreTitular.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtNombreTitular.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isLetter(e.getKeyChar()) && e.getKeyChar() != ' ' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
        panelCamposPago.add(txtNombreTitular);
        panelCamposPago.add(Box.createVerticalStrut(10));
        
        panelCamposPago.add(new JLabel("Fecha de Vencimiento *"));
        JPanel pFecha = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pFecha.setBackground(Color.WHITE);
        pFecha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        String[] meses = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        cmbMes = new JComboBox<>(meses);
        
        int anio = Calendar.getInstance().get(Calendar.YEAR);
        String[] anios = new String[15];
        for (int i = 0; i < 15; i++) anios[i] = String.valueOf(anio + i);
        cmbAnio = new JComboBox<>(anios);
        
        pFecha.add(new JLabel("Mes:"));
        pFecha.add(cmbMes);
        pFecha.add(new JLabel("Año:"));
        pFecha.add(cmbAnio);
        panelCamposPago.add(pFecha);
        panelCamposPago.add(Box.createVerticalStrut(10));
        
        panelCamposPago.add(new JLabel("CVV *"));
        txtCVV = new JTextField();
        txtCVV.setMaximumSize(new Dimension(100, 30));
        txtCVV.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
                if (txtCVV.getText().length() >= 4) {
                    e.consume();
                }
            }
        });
        panelCamposPago.add(txtCVV);
        
        panelCamposPago.revalidate();
        panelCamposPago.repaint();
    }

    private void mostrarCamposEfectivo() {
        panelCamposPago.removeAll();
        
        panelCamposPago.add(new JLabel("Monto con el que paga *"));
        txtMontoEfectivo = new JTextField();
        txtMontoEfectivo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtMontoEfectivo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
            public void keyReleased(KeyEvent e) {
                if (!txtMontoEfectivo.getText().isEmpty()) {
                    try {
                        double m = Double.parseDouble(txtMontoEfectivo.getText());
                        double c = m - total;
                        if (c < 0) {
                            lblCambio.setText("Monto insuficiente");
                            lblCambio.setForeground(Color.RED);
                        } else {
                            lblCambio.setText(String.format("Su cambio será: $%,.0f", c));
                            lblCambio.setForeground(new Color(0, 128, 0));
                        }
                    } catch (Exception ex) {
                        lblCambio.setText("Monto inválido");
                        lblCambio.setForeground(Color.RED);
                    }
                }
            }
        });
        panelCamposPago.add(txtMontoEfectivo);
        panelCamposPago.add(Box.createVerticalStrut(10));
        
        lblCambio = new JLabel("Su cambio será: $0");
        lblCambio.setFont(new Font("Arial", Font.BOLD, 12));
        lblCambio.setForeground(new Color(0, 128, 0));
        panelCamposPago.add(lblCambio);
        
        panelCamposPago.revalidate();
        panelCamposPago.repaint();
    }

    private void mostrarCamposTransferencia() {
        panelCamposPago.removeAll();
        
        panelCamposPago.add(new JLabel("Número de celular Nequi *"));
        txtCelularNequi = new JTextField();
        txtCelularNequi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtCelularNequi.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
                if (txtCelularNequi.getText().length() >= 10) {
                    e.consume();
                }
            }
        });
        panelCamposPago.add(txtCelularNequi);
        panelCamposPago.add(Box.createVerticalStrut(10));
        
        panelCamposPago.add(new JLabel("Nombre del titular *"));
        txtNombreNequi = new JTextField();
        txtNombreNequi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtNombreNequi.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isLetter(e.getKeyChar()) && e.getKeyChar() != ' ' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
        panelCamposPago.add(txtNombreNequi);
        panelCamposPago.add(Box.createVerticalStrut(10));
        
        JLabel info = new JLabel("<html><i>Realice la transferencia del monto total a este número</i></html>");
        info.setFont(new Font("Arial", Font.PLAIN, 10));
        info.setForeground(Color.GRAY);
        panelCamposPago.add(info);
        
        panelCamposPago.revalidate();
        panelCamposPago.repaint();
    }

    private void procesarPago() {
        String error = null;
        
        if (rbTarjeta.isSelected()) {
            error = ValidadorPagos.validarTarjeta(
                txtNumeroTarjeta.getText(),
                txtNombreTitular.getText(),
                Integer.parseInt((String)cmbMes.getSelectedItem()),
                Integer.parseInt((String)cmbAnio.getSelectedItem()),
                txtCVV.getText()
            );
            
            if (error == null) {
                JOptionPane.showMessageDialog(this, "Pago procesado exitosamente\n¡Gracias por su compra!", 
                    "Pago Exitoso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            
        } else if (rbEfectivo.isSelected()) {
            error = ValidadorPagos.validarEfectivo(txtMontoEfectivo.getText(), total);
            
            if (error == null) {
                double cambio = Double.parseDouble(txtMontoEfectivo.getText()) - total;
                JOptionPane.showMessageDialog(this, 
                    String.format("Pago en efectivo registrado\nSu cambio: $%,.0f\n¡Gracias por su compra!", cambio),
                    "Pago Exitoso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            
        } else if (rbTransferencia.isSelected()) {
            error = ValidadorPagos.validarNequi(txtCelularNequi.getText(), txtNombreNequi.getText());
            
            if (error == null) {
                JOptionPane.showMessageDialog(this, 
                    "Transferencia registrada\nProcesaremos su pedido al confirmar el pago\n¡Gracias por su compra!",
                    "Pago Exitoso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
        
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarProductosAlCarrito() {
        modeloTabla.addRow(new Object[]{"Mesa de Comedor Roble", "$450,000", 1, "$450,000", "🗑"});
        modeloTabla.addRow(new Object[]{"Silla Moderna", "$120,000", 2, "$240,000", "🗑"});
    }
}