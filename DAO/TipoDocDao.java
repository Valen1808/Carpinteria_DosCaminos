package Modelo.DAO;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Modelo.TipoDoc;
import Modelo.CRUD.CrudTDocumento;

public class TipoDocDao implements CrudTDocumento<TipoDoc> {

    Conexion cn = new Conexion();

    @Override
    public List<TipoDoc> lista() {
        List<TipoDoc> datos = new ArrayList<>();
        String sql = "SELECT * FROM tdocumento";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                TipoDoc td = new TipoDoc();
                td.setId(rs.getInt("id"));
                td.setNombre(rs.getString("nombre"));
                datos.add(td);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar tipos de documento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, rs);
        }
        return datos;
    }

    @Override
    public int setAgregar(TipoDoc documento) {
        int resultado = 0;
        String sql = "INSERT INTO tipo_documento (nombre) VALUES (?)";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, documento.getNombre());

            resultado = ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar tipo de documento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return resultado;
    }

    @Override
    public int setActualizar(TipoDoc documento) {
        int resultado = 0;
        String sql = "UPDATE tipo_documento SET nombre = ? WHERE id = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, documento.getNombre());
            ps.setInt(2, documento.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar tipo de documento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return resultado;
    }

    @Override
    public int setEliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM tipo_documento WHERE id = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            resultado = ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar tipo de documento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return resultado;
    }

    private void cerrarRecursos(Connection con, PreparedStatement ps, ResultSet rs) {
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
            System.err.println("Error al cerrar recursos en TipoDocDao: " + e.getMessage());
        }
    }
}
