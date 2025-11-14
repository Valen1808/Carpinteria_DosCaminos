package Controlador;

import Modelo.DAO.UsuarioDao;
import Vista.ModuloAdmin.InicioSesion;
import Modelo.Usuario;
import Vista.ModuloAdmin.VentanaPrincipal;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private InicioSesion vistaLogin;
    private UsuarioDao usuarioDao;
    
    // Intentos fallidos por correo (3 intentos únicamente)
    private static Map<String, Integer> intentosFallidos = new HashMap<>();
    private static final int MAX_INTENTOS = 3;

    public LoginController(InicioSesion vista) {
        this.vistaLogin = vista;
        this.usuarioDao = new UsuarioDao();
    }

    public void validarLogin(String correo, String password) {
        //Campos vacíos
        String correoLimpio = correo.trim();
        String passwordLimpio = password.trim();
        
        if (correoLimpio.isEmpty() || passwordLimpio.isEmpty()) {
            JOptionPane.showMessageDialog(vistaLogin, 
                "Los campos de correo y contraseña no deben estar vacíos", 
                "Campos Vacíos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Formato de correo
        if (!esCorreoValido(correoLimpio)) {
            JOptionPane.showMessageDialog(vistaLogin, 
                "El formato del correo electrónico no es válido\n" +
                "Ejemplo: usuario@ejemplo.com", 
                "Correo Inválido", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Contraseña corta
        if (passwordLimpio.length() < 6) {
            JOptionPane.showMessageDialog(vistaLogin, 
                "Tu contraseña tiene al menos 6 caracteres", 
                "Contraseña Incorrecta", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Intentos fallidos, este tiene un límite de 3 intentos
        if (estaBloqueado(correoLimpio)) {
            JOptionPane.showMessageDialog(vistaLogin, 
                "Ha excedido el número máximo de intentos (3).\n" +
                "Cuenta bloqueada permanentemente.\n" +
                "Contacte al administrador.", 
                "Cuenta Bloqueada", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar usuario y verificar la contraseña
        Usuario usuario = usuarioDao.buscarPorCorreo(correoLimpio);
        
        if (usuario != null && usuario.getPassword().equals(passwordLimpio)) {
            //Aquí si es lgin exitoso
            reiniciarIntentos(correoLimpio);
            
            JOptionPane.showMessageDialog(vistaLogin, 
                "¡Bienvenido, " + usuario.getNombre() + "!", 
                "Acceso Concedido", 
                JOptionPane.INFORMATION_MESSAGE);
            
            vistaLogin.dispose();
            
            String nombreRol = usuario.getRol().getNombre().toLowerCase();
            
            SwingUtilities.invokeLater(() -> {
                switch (nombreRol) {
                    case "admin":
                        new VentanaPrincipal().setVisible(true);
                        break;
                    case "operario":
                        JOptionPane.showMessageDialog(null, 
                            "Login Operario OK (Crea su ventana aquí)");
                        break;
                    case "cliente":
                        JOptionPane.showMessageDialog(null, 
                            "Login Cliente OK (Crea su ventana aquí)");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, 
                            "Rol de usuario desconocido: " + nombreRol, 
                            "Error de Rol", 
                            JOptionPane.ERROR_MESSAGE);
                        new InicioSesion().setVisible(true);
                }
            });
        } else {
            //Usuario no existe o contraseña incorrecta
            registrarIntentoFallido(correoLimpio);
            mostrarMensajeCredencialesIncorrectas(correoLimpio);
        }
    }

    

    //Esto valida el formato del correo
    private boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return correo.matches(regex);
    }

    //Esto verifica si una cuenta está bloqueada
    private boolean estaBloqueado(String correo) {
        return intentosFallidos.getOrDefault(correo, 0) >= MAX_INTENTOS;
    }

    //Esto va a registrar los intentos fallidos
    private void registrarIntentoFallido(String correo) {
        int intentos = intentosFallidos.getOrDefault(correo, 0) + 1;
        intentosFallidos.put(correo, intentos);
    }

    //Esto va a reiniciar los intentos fallidos si se looguea correctamente
    private void reiniciarIntentos(String correo) {
        intentosFallidos.remove(correo);
    }

    //Esto muestra un mensaje de error con intentos restantes para loguear
    private void mostrarMensajeCredencialesIncorrectas(String correo) {
        int intentos = intentosFallidos.getOrDefault(correo, 0);
        int intentosRestantes = MAX_INTENTOS - intentos;
        
        String mensaje;
        if (intentosRestantes > 0) {
            mensaje = "Usuario o contraseña incorrectos.\n" +
                     "Intentos restantes: " + intentosRestantes;
        } else {
            mensaje = "Demasiados intentos fallidos.\n" +
                     "Su cuenta ha sido bloqueada permanentemente.\n" +
                     "Contacte al administrador.";
        }
        
        JOptionPane.showMessageDialog(vistaLogin, 
            mensaje, 
            "Error de Acceso", 
            JOptionPane.ERROR_MESSAGE);
    }
}