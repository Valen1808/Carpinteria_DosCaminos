package Modelo.DAO;

import Modelo.Conexion;
import Modelo.Ciudad;
import Modelo.CRUD.CrudCiudad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CiudadDao implements CrudCiudad<Ciudad> {

    Conexion cn = new Conexion();

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<Ciudad> listarCiudad() {
        List<Ciudad> listaCiudades = new ArrayList<>();

        String sql = "SELECT id, nombre FROM ciudad ORDER BY nombre ASC";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Ciudad ciudad = new Ciudad();
                ciudad.setId(rs.getInt("id"));
                ciudad.setNombre(rs.getString("nombre"));

                listaCiudades.add(ciudad);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar ciudades: " + e.getMessage());
        } finally {
            
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos en listarCiudad: " + e.getMessage());
            }
        }
        return listaCiudades;
    }

    @Override
    public int setAgregar() {
        JOptionPane.showMessageDialog(null, "Método setAgregar para Ciudad no implementado completamente.");
        return 0;
        
    }

    @Override
    public int setActualizar() {
        JOptionPane.showMessageDialog(null, "Método setActualizar para Ciudad no implementado.");
        return 0;
    }

    @Override
    public int setEliminar() {
        JOptionPane.showMessageDialog(null, "Método setEliminar para Ciudad no implementado.");
        return 0;
         
    }
}
