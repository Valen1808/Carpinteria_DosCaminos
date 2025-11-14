package Modelo.DAO;

import Modelo.Marca;
import Modelo.CRUD.CrudMarcas;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MarcaDao implements CrudMarcas<Marca> {

    Conexion cn = new Conexion();

    @Override
    public List<Marca> lista() {
        List<Marca> datos = new ArrayList<>();
        String sql = "SELECT * FROM marca";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Marca mar = new Marca();
                mar.setId(rs.getInt("id"));
                mar.setNombre(rs.getString("nombre"));
                datos.add(mar);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar las marcas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, rs);
        }
        return datos;
    }

    @Override
    public int setAgregar(Marca mar) {
        String sql = "INSERT INTO marca (nombre) VALUES (?)";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, mar.getNombre());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar marca: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return r;
    }

    @Override
    public int setActualizar(Marca mar) {
        String sql = "UPDATE marca SET nombre = ? WHERE id = ?";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, mar.getNombre());
            ps.setInt(2, mar.getId());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar marca: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return r;
    }

    @Override
    public int setEliminar(int id) {
        String sql = "DELETE FROM marca WHERE id = ?";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar marca: " + e.getMessage());
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