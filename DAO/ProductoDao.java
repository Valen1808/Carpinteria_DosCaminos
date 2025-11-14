package Modelo.DAO;

import Modelo.CRUD.CrudProductos;
import Modelo.Categoria;
import Modelo.Conexion;
import Modelo.Marca;
import Modelo.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoDao implements CrudProductos<Productos> {

    Conexion cn = new Conexion();

    @Override
    public List<Productos> lista() {
        List<Productos> datos = new ArrayList<>();
        String sql = "SELECT p.*, c.nombre AS nombre_categoria, m.nombre AS nombre_marca "
                   + "FROM producto p "
                   + "INNER JOIN categoria c ON p.id_categoria = c.id "
                   + "INNER JOIN marca m ON p.id_marca = m.id";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Productos pr = new Productos();
                pr.setId(rs.getInt("id"));
                pr.setNombre(rs.getString("nombre"));
                pr.setCantidad(rs.getInt("cantidad"));
                pr.setPrecio(rs.getDouble("precio"));

                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("nombre_categoria"));
                pr.setCategoria(cat);

                Marca mar = new Marca();
                mar.setId(rs.getInt("id_marca"));
                mar.setNombre(rs.getString("nombre_marca"));
                pr.setMarca(mar);

                datos.add(pr);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta de productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, rs);
        }
        return datos;
    }

    @Override
    public int setAgregar(Productos pr) {
        String sql = "INSERT INTO producto (id_marca, id_categoria, user_id, nombre, cantidad, fecha_ingreso, precio, estado) VALUES (?, ?, ?, ?, ?, NOW(), ?, ?)";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, pr.getMarca().getId());
            ps.setInt(2, pr.getCategoria().getId());
            ps.setInt(3, pr.getUserId()); 
            ps.setString(4, pr.getNombre());
            ps.setInt(5, pr.getCantidad());
            ps.setDouble(6, pr.getPrecio());
            ps.setString(7, pr.getEstado()); 
            
            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return r;
    }

    @Override
    public int setActualizar(Productos pr) {
        String sql = "UPDATE producto SET id_marca = ?, id_categoria = ?, nombre = ?, cantidad = ?, precio = ?, estado = ? WHERE id = ?";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, pr.getMarca().getId());
            ps.setInt(2, pr.getCategoria().getId());
            ps.setString(3, pr.getNombre());
            ps.setInt(4, pr.getCantidad());
            ps.setDouble(5, pr.getPrecio());
            ps.setString(6, pr.getEstado());
            ps.setInt(7, pr.getId());

            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return r;
    }

    @Override
    public int setEliminar(int id) {
        String sql = "DELETE FROM producto WHERE id = ?";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + e.getMessage());
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