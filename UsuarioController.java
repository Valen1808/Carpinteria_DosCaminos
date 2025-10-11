package Controlador; // O el paquete donde tengas los controladores


import carpinteria.Modelo.Usuario;
import carpinteria.Modelo.DAO.UsuarioDao;
import Vista.PanelUsuarios; // Importa la vista
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class UsuarioController {
    
    private UsuarioDao dao;
    private PanelUsuarios vista;

    public UsuarioController(PanelUsuarios vista) {
        this.vista = vista;
        this.dao = new UsuarioDao();
    }

    public void cargarUsuariosEnTabla() {
        
        DefaultTableModel modeloTabla = vista.getModeloTabla();
        modeloTabla.setRowCount(0); // Limpia la tabla
        
        List<Usuario> usuarios = dao.lista();
        
// En UsuarioController.java, dentro de cargarUsuariosEnTabla()

// En UsuarioController.java, dentro del método cargarUsuariosEnTabla()

for (Usuario u : usuarios) {
    Object[] fila = {
        u.getId(),              // Columna 0: ID
        u.getNombre(),          // Columna 1: Nombre
        u.getApellido(),        // Columna 2: Apellido (¡Asegúrate de que aquí sea getApellido()!)
        u.getCorreo(),          // Columna 3: Correo
        u.getTelefono(),        // Columna 4: Teléfono
        u.getRol().getNombre(), // Columna 5: Nombre del Rol
        "✏️ 🗑️"                 // Columna 6: Acciones
    };
    modeloTabla.addRow(fila);
}
    }
}