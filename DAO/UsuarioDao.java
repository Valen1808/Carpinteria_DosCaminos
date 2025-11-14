package Modelo.DAO;

import Modelo.Conexion;
import Modelo.CRUD.Crud;
import Modelo.DatosEmpleados;
import Modelo.Rol;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDao implements Crud<Usuario> {

    Conexion cn = new Conexion();

    @Override
    public List<Usuario> lista() {
        List<Usuario> datos = new ArrayList<>();
        String sql = "SELECT u.*, r.nombre AS nombre_rol "
                + "FROM usuarios u "
                + "INNER JOIN roles r ON u.id_rol = r.id";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario op = new Usuario();
                op.setId(rs.getInt("id"));
                op.setNombre(rs.getString("nombre"));
                op.setApellido(rs.getString("apellido"));
                op.setNum_ident(rs.getString("num_ident"));
                op.setTelefono(rs.getString("telefono"));
                op.setCorreo(rs.getString("correo"));
                op.setPassword(rs.getString("contraseña"));
                op.setDireccion(rs.getString("direccion"));
                op.setEstado(rs.getString("estado"));
                op.setIdCiudad(rs.getInt("id_ciudad"));
                op.setIdTDocumento(rs.getInt("id_TDocumento"));

                Rol rolDelUsuario = new Rol();
                rolDelUsuario.setId(rs.getInt("id_rol"));
                rolDelUsuario.setNombre(rs.getString("nombre_rol"));
                op.setRol(rolDelUsuario);

                datos.add(op);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar usuarios: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, rs);
        }
        return datos;
    }

    public Usuario buscarPorCorreo(String correo) {
        Usuario usuarioExistente = null;
        String sql = "SELECT u.*, r.nombre AS nombre_rol FROM usuarios u INNER JOIN roles r ON u.id_rol = r.id WHERE u.correo = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuarioExistente = new Usuario();
                usuarioExistente.setId(rs.getInt("id"));
                usuarioExistente.setNombre(rs.getString("nombre"));
                usuarioExistente.setApellido(rs.getString("apellido"));
                usuarioExistente.setCorreo(rs.getString("correo"));
                usuarioExistente.setPassword(rs.getString("contraseña"));
                

                Rol rol = new Rol();
                rol.setId(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("nombre_rol"));
                usuarioExistente.setRol(rol);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al buscar por correo" + e);
        }
        
        return usuarioExistente;
    }

    @Override
    public int setAgregar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id_rol, id_ciudad, id_TDocumento, nombre, apellido, num_ident, telefono, correo, contraseña, direccion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, usuario.getRol().getId());
            ps.setInt(2, usuario.getIdCiudad());
            ps.setInt(3, usuario.getIdTDocumento());
            ps.setString(4, usuario.getNombre());
            ps.setString(5, usuario.getApellido());
            ps.setString(6, usuario.getNum_ident());
            ps.setString(7, usuario.getTelefono());
            ps.setString(8, usuario.getCorreo());
            ps.setString(9, usuario.getPassword());
            ps.setString(10, usuario.getDireccion());
            ps.setString(11, usuario.getEstado());

            r = ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR al agregar usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return r;
    }

    @Override
    public int setActualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET id_rol = ?, id_ciudad = ?, id_TDocumento = ?, nombre = ?, apellido = ?, num_ident = ?, telefono = ?, correo = ?, contraseña = ?, direccion = ?, estado = ? WHERE id = ?";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, usuario.getRol().getId());
            ps.setInt(2, usuario.getIdCiudad());
            ps.setInt(3, usuario.getIdTDocumento());
            ps.setString(4, usuario.getNombre());
            ps.setString(5, usuario.getApellido());
            ps.setString(6, usuario.getNum_ident());
            ps.setString(7, usuario.getTelefono());
            ps.setString(8, usuario.getCorreo());
            ps.setString(9, usuario.getPassword());
            ps.setString(10, usuario.getDireccion());
            ps.setString(11, usuario.getEstado());
            ps.setInt(12, usuario.getId()); // WHERE id = ?

            r = ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR en la actualización: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return r;
    }

    @Override
    public int setEliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        int r = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            r = ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR al eliminar usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(con, ps, null);
        }
        return r;
    }

    public int setAgregarEmpleado(Usuario usuario, DatosEmpleados datosEmpleado) {
        String sqlUsuario = "INSERT INTO usuarios (id_rol, id_ciudad, id_TDocumento, nombre, apellido, num_ident, telefono, correo, contraseña, direccion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlEmpleado = "INSERT INTO datos_empleado (id_usuario, salario, fecha_contratacion, eps) VALUES (?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement psUsuario = null;
        PreparedStatement psEmpleado = null;
        ResultSet generatedKeys = null;
        int resultado = 0;
        int nuevoUsuarioId = -1;

        try {
            con = cn.getConnection();
            con.setAutoCommit(false); 

            psUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            psUsuario.setInt(1, usuario.getRol().getId());
            psUsuario.setInt(2, usuario.getIdCiudad());
            psUsuario.setInt(3, usuario.getIdTDocumento());
            psUsuario.setString(4, usuario.getNombre());
            psUsuario.setString(5, usuario.getApellido());
            psUsuario.setString(6, usuario.getNum_ident());
            psUsuario.setString(7, usuario.getTelefono());
            psUsuario.setString(8, usuario.getCorreo());
            psUsuario.setString(9, usuario.getPassword());
            psUsuario.setString(10, usuario.getDireccion());
            psUsuario.setString(11, usuario.getEstado());
            psUsuario.executeUpdate();

            generatedKeys = psUsuario.getGeneratedKeys();
            if (generatedKeys.next()) {
                nuevoUsuarioId = generatedKeys.getInt(1);
            } else {
                con.rollback();
                throw new SQLException("Fallo al crear usuario, no se obtuvo ID.");
            }

            psEmpleado = con.prepareStatement(sqlEmpleado);
            psEmpleado.setInt(1, nuevoUsuarioId);
            psEmpleado.setDouble(2, datosEmpleado.getSalario());
            psEmpleado.setDate(3, new java.sql.Date(datosEmpleado.getFechaContratacion().getTime()));
            psEmpleado.setString(4, datosEmpleado.getEps());
            resultado = psEmpleado.executeUpdate();

            con.commit(); 

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Error en rollback: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Error al registrar empleado: " + e.getMessage());
            e.printStackTrace();
            resultado = 0;
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (psUsuario != null) {
                    psUsuario.close();
                }
                if (psEmpleado != null) {
                    psEmpleado.close();
                }
                if (con != null) {
                    con.setAutoCommit(true); 
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos en setAgregarEmpleado: " + e.getMessage());
            }
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
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
