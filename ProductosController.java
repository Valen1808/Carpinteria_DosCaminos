package Controlador;

import Vista.PanelProductos;
import carpinteria.Modelo.DAO.ProductoDao;
import carpinteria.Modelo.Productos;
import java.util.List;

public class ProductosController {

    private ProductoDao dao;
    private PanelProductos vista;

    public ProductosController(PanelProductos vista) {
        this.vista = vista;
        this.dao = new ProductoDao();
    }

    // --- CAMBIO: El método ahora llama a los métodos de la vista para crear tarjetas ---
    public void cargarProductos() {
        // 1. Limpia la vista de cualquier tarjeta de ejemplo.
        vista.limpiarTarjetas();
        
        // 2. Pide la lista de productos al DAO.
        List<Productos> productos = dao.lista();
        
        // 3. Por cada producto, le ordena a la vista que cree y agregue una nueva tarjeta.
        for (Productos p : productos) {
            vista.agregarTarjetaProducto(p);
        }
    }
}