package VistaInicioSesion;

import Vista.ModuloAdmin.InicioSesion;
import javax.swing.SwingUtilities;


public class Index {

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InicioSesion().setVisible(true);
            
        });
    }
}