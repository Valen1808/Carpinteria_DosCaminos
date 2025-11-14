package Modelo.DAO;

import Modelo.CRUD.CrudRoles;
import Modelo.Rol; 
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class RolDao implements CrudRoles<Rol> {

    
    Conexion cn = new Conexion();
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    @Override
    public List<Rol> lista() {
        List<Rol> listaRoles = new ArrayList<>();
       
        String sql = "SELECT * FROM roles"; 

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("nombre"));
                listaRoles.add(rol);
            }

        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, "Error al listar roles: " + e.getMessage());
        } finally {
            cerrarConexion(con, ps, rs);
        }
        return listaRoles;
    }

    @Override
    public int setAgregar(Rol rol) {
        String sql = "INSERT INTO roles (nombre) VALUES (?)";
        int r = 0; 

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, rol.getNombre());
            
            r = ps.executeUpdate(); 

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar rol: " + e.getMessage());
        } finally {
            cerrarConexion(con, ps, null);
        }
        return r;
    }

    @Override
    public int setActualizar(Rol rol) {
        String sql = "UPDATE roles SET nombre = ? WHERE id = ?";
        int r = 0;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, rol.getNombre());
            ps.setInt(2, rol.getId());
            
            r = ps.executeUpdate(); 

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar rol: " + e.getMessage());
        } finally {
            cerrarConexion(con, ps, null);
        }
        return r;
    }

    @Override
    public int setEliminar(int id) {
        String sql = "DELETE FROM roles WHERE id = ?";
        int r = 0;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            r = ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar rol: " + e.getMessage());
        } finally {
            cerrarConexion(con, ps, null);
        }
        return r;
    }

    private void cerrarConexion(Connection con, PreparedStatement ps, ResultSet rs) {
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
        } catch (Exception e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}