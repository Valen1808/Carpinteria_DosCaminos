package Controlador;

import Modelo.DAO.MarcaDao;
import Modelo.DAO.RolDao;
import Modelo.TipoDoc;
import Modelo.DAO.TipoDocDao;

import Modelo.Usuario;
import Modelo.DAO.UsuarioDao;
import Vista.ModuloAdmin.PanelUsuarios;
import Modelo.DatosEmpleados;
import Modelo.Marca;
import Modelo.Rol;
import Modelo.Ciudad;
import Modelo.DAO.CiudadDao;


import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UsuarioController {

    private UsuarioDao dao;
    private PanelUsuarios vista;
    private MarcaDao marcaDao = new MarcaDao();
    private RolDao rolDao = new RolDao();
    private TipoDocDao tipoDocDao = new TipoDocDao();
    private CiudadDao ciudadao = new CiudadDao();

    public UsuarioController(PanelUsuarios vista) {
        this.vista = vista;
        this.dao = new UsuarioDao();
    }

    public List<Marca> getMarcaForm() {
        return marcaDao.lista();
    }

    public List<Rol> getRolForm() {
        return rolDao.lista();
    }

    public List<TipoDoc> getTipoDocForm() {
        return tipoDocDao.lista();
    }
    
    public List<Ciudad> getCiudadForm(){
        return ciudadao.listarCiudad();
    }

    public void cargarUsuariosEnTabla() {

        DefaultTableModel modeloTabla = vista.getModeloTabla();
        modeloTabla.setRowCount(0); 

        List<Usuario> usuarios = dao.lista();

        for (Usuario u : usuarios) {
            Object[] fila = {
                u.getId(),
                u.getNombre(),
                u.getApellido(),
                u.getCorreo(),
                u.getTelefono(),
                u.getRol().getNombre(),
                "✏️ 🗑️"
            };
            modeloTabla.addRow(fila);
        }
    }

    public boolean procesarNuevoEmpleado(String nombre, String apellido, String correo, String password,
            String numIdent, String telefono, String direccion, String estado,
            int idRol, int idCiudad, int idTDocumento,
            double salario, Date fechaContratacion, String eps) {
       
        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Los campos básicos (nombre, apellido, correo, contraseña) son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Usuario usuarioNew = new Usuario();

        usuarioNew.setNombre(nombre);
        usuarioNew.setApellido(apellido);
        usuarioNew.setCorreo(correo);
        usuarioNew.setPassword(password);
        usuarioNew.setNum_ident(numIdent);
        usuarioNew.setTelefono(telefono);
        usuarioNew.setDireccion(direccion);
        usuarioNew.setEstado(estado);

        Rol rolEmpleado = new Rol(); 
        rolEmpleado.setId(idRol);
        usuarioNew.setRol(rolEmpleado);
        //usuarioNew.setIdCiudad(idCiudad);
        //usuarioNew.setIdTDocumento(idTDocumento);
        
        TipoDoc tipoDoc = new TipoDoc();
        tipoDoc.setId(idTDocumento);
        
        Ciudad ciudad =  new Ciudad();
        ciudad.setId(idCiudad);

        DatosEmpleados datos = new DatosEmpleados();
        datos.setSalario(salario);
        datos.setFechaContratacion(fechaContratacion);
        datos.setEps(eps);
 
        int resultado = dao.setAgregarEmpleado(usuarioNew, datos);
        if (resultado > 0) {
            cargarUsuariosEnTabla();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
            return false;
        }
    }

    public boolean registrarNuevoCliente(String numIdent, String nombre, String apellido, String correo,
            String password, String telefono, String direccion, int idTDocumento) {

        
        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty() || numIdent.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos básicos son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        
        Usuario usuarioNew = new Usuario();
        usuarioNew.setNombre(nombre);
        usuarioNew.setApellido(apellido);
        usuarioNew.setCorreo(correo);
        usuarioNew.setPassword(password);
        usuarioNew.setNum_ident(numIdent);
        usuarioNew.setTelefono(telefono);
        usuarioNew.setDireccion(direccion);
        usuarioNew.setEstado("Activo"); 

        
        Rol rolCliente = new Rol();
        rolCliente.setId(3); 
        usuarioNew.setRol(rolCliente);

        TipoDoc tDoc = new TipoDoc();
        tDoc.setId(idTDocumento);
        usuarioNew.setTDocumento(tDoc); 
        int resultado = dao.setAgregar(usuarioNew);

        if (resultado > 0) {
            JOptionPane.showMessageDialog(null, "¡Registro exitoso! Ya puedes iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
