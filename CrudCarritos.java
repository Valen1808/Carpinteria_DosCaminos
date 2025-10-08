package carpinteria.Modelo.CRUD;

import java.util.List;

public interface CrudCarritos<T> {

    public List<T> lista();
    
    public int setAgregar(T car); 
   
    public int agregarProductoAlCarrito(int idCarrito, int idProducto, int cantidad);
    
    public int eliminarProductoDelCarrito(int idCarrito, int idProducto);
    
    public T buscarCarritoActivoPorUsuario(int idUsuario);
}