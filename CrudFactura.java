package carpinteria.Modelo.CRUD;

import java.util.List;

public interface CrudFactura<T> {

    public List<T> lista();

    public int setAgregar(T fac);

    public int setActualizar(T fac);

    public int setEliminar(int id);
    
    public List<T> listaPorUsuario(int idUsuario);
}