package carpinteria.Modelo.CRUD;

import java.util.List;

public interface CrudPedidos<T> {

    public List<T> lista();

    public int setAgregar(T ped);

    public int setActualizar(T ped); 

    public int setEliminar(int id);
}