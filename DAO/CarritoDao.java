/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.CRUD.CrudCarritos;
import Modelo.Carrito;
import com.sun.source.tree.TryTree;
import java.util.ArrayList;
import java.util.List;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CarritoDao implements CrudCarritos<Carrito> {

    Conexion cn = new Conexion();
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List<Carrito> lista() {
        List infoCarro = new ArrayList();
        String sql = "";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Carrito car = new Carrito();
                car.setId(rs.getInt("id"));
                car.setId_usuario(rs.getInt("id_usuario"));
                car.setFecha_registro(rs.getTimestamp("fecha_registro"));
                car.setTotal(rs.getDouble("total"));
                car.setEstado(rs.getString("estado"));
                infoCarro.add(car);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                cerrarConexion(con, ps, rs);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return infoCarro;
    }

    @Override
    public int eliminarProductoDelCarrito(int idCarrito, int idProducto) {
        String sql = "DELETE FROM detallecarrito WHERE id_carrito = ? AND id_producto = ?";
        int r = 0;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCarrito);
            ps.setInt(2, idProducto);
            r = ps.executeUpdate();

            if (r > 0) {
                actualizarTotalCarrito(idCarrito, con);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar producto del carrito: " + e.getMessage());
        } finally {
            cerrarConexion(con, ps, null);
        }
        return r;
    }
@Override
    public Carrito buscarCarritoActivoPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM carrito WHERE id_usuario = ? AND estado = 'Activo'";
        Carrito carrito = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
          
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                carrito = new Carrito();
                carrito.setId(rs.getInt("id"));
                carrito.setId_usuario(rs.getInt("id_usuario"));
                carrito.setFecha_registro(rs.getTimestamp("fecha_registro"));
                carrito.setTotal(rs.getDouble("total"));
                carrito.setEstado(rs.getString("estado"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en la consulta" + e);
        }
        return carrito;
    }

    private void actualizarTotalCarrito(int idCarrito, Connection con) throws SQLException {
        String sqlTotal = "SELECT SUM(subtotal) AS total_calculado FROM detallecarrito WHERE id_carrito = ?";
        String sqlUpdate = "UPDATE carrito SET total = ? WHERE id = ?";
        PreparedStatement psTotal = null;
        PreparedStatement psUpdate = null;
        ResultSet rsTotal = null;
        try {

            psTotal = con.prepareStatement(sqlTotal);
            psTotal.setInt(1, idCarrito);
            rsTotal = psTotal.executeQuery();
            double nuevoTotal = 0;
            if (rsTotal.next()) {
                nuevoTotal = rsTotal.getDouble("total_calculado");
            }

            psUpdate = con.prepareStatement(sqlUpdate);
            psUpdate.setDouble(1, nuevoTotal);
            psUpdate.setInt(2, idCarrito);
            psUpdate.executeUpdate();

        } finally {
            cerrarConexion(con, ps, rs);
        }
    }

    private double obtenerPrecioProducto(int idProducto) throws SQLException {
        String sql = "SELECT precio FROM producto WHERE id = ?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        double precio = 0;
        try {
            ps.setInt(1, idProducto);
            if (rs.next()) {
                precio = rs.getDouble("precio");
            }
        } finally {

            try {
                cerrarConexion(con, ps, rs);
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Ha ocurrido un error" + e);
            }

        }
        return precio;
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

  

    @Override
    public int setAgregar(Carrito car) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int agregarProductoAlCarrito(int idCarrito, int idProducto, int cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
