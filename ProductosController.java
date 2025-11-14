 package Controlador;

import Modelo.Categoria;
import Modelo.DAO.CategoriaDao;
import Modelo.DAO.MarcaDao;
import Vista.ModuloAdmin.PanelProductos;
import Modelo.DAO.ProductoDao;
import Modelo.Marca;
import Modelo.Productos;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ProductosController {

    private ProductoDao dao;
    private PanelProductos vista;
    private CategoriaDao categoriaDao = new CategoriaDao();
    private MarcaDao marcaDao = new MarcaDao();

    public ProductosController(PanelProductos vista) {
        this.vista = vista;
        this.dao = new ProductoDao();
    }

    public List<Categoria> getCategoriasForm() {
        return categoriaDao.lista();
    }
    
    public List<Marca> getMarcaForm(){
        return marcaDao.lista();
    }   

    public void cargarProductos() {

        vista.limpiarTarjetas();

        List<Productos> productos = dao.lista();

        for (Productos p : productos) {
            vista.agregarTarjeta(p);
        }
    }

    public boolean guardarNuevoProducto(Productos productos) {
        int resultado = dao.setAgregar(productos);

        if (resultado > 0) {
            cargarProductos();
            return true;
        }

        return false;
    }


public boolean procesarProducto(String nombre, String precioStr, String estado, Marca marca, Categoria categoria) {

    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacío.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    int precio;
    try {
        precio = Integer.parseInt(precioStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(vista, "El precio debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        return false; 
    }

    Productos nuevoProducto = new Productos();
    nuevoProducto.setNombre(nombre);
    nuevoProducto.setPrecio(precio); 
    nuevoProducto.setEstado(estado);
    nuevoProducto.setMarca(marca);
    nuevoProducto.setCategoria(categoria);
    
    nuevoProducto.setUserId(1);
    nuevoProducto.setCantidad(50);

    int resultado = dao.setAgregar(nuevoProducto);
    if (resultado > 0) {
        cargarProductos();
        return true;
    }

   
    JOptionPane.showMessageDialog(vista, "No se pudo guardar el producto en la base de datos.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
    return false;
}
}
