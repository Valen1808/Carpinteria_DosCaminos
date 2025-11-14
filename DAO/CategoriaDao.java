package Modelo.DAO;

import Modelo.Categoria;
import Modelo.CRUD.CrudCategorias;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaDao implements CrudCategorias<Categoria> {

    Conexion cn = new Conexion();

    @Override
    public List<Categoria> lista() {
        List<Categoria> datos = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id"));
                cat.setNombre(rs.getString("nombre"));
                datos.add(cat);
            }
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, "Error al listar las categorías: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, rs);
        }
        return datos;
    }

    @Override
    public int setAgregar(Categoria cat) {
        String sql = "INSERT INTO categoria (nombre) VALUES (?)";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cat.getNombre());
            r = ps.executeUpdate();
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, "Error al agregar categoría: " + e.getMessage());
            e.printStackTrace();
        } finally {
            
            cerrarRecursos(con, ps, null);
        }
        return r;
    }

    @Override
    public int setActualizar(Categoria cat) {
        String sql = "UPDATE categoria SET nombre = ? WHERE id = ?";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cat.getNombre());
            ps.setInt(2, cat.getId());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar categoría: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return r;
    }

    @Override
    public int setEliminar(int id) {
        String sql = "DELETE FROM categoria WHERE id = ?";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar categoría: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return r;
    }

    private void cerrarRecursos(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}