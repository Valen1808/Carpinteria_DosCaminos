/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carpinteria.Modelo;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    String url = "jdbc:mysql://localhost:3306/carpint";
    String pass = "";
    String user = "root";

    public Connection getConection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            // JOptionPane.showMessageDialog(null,"La conexion fue Exitosa", "Conexion
            // Exitosa", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Base de Datos Apagada" + "", JOptionPane.ERROR_MESSAGE);
        }

        return con;
    }

}
