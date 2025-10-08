package carpinteria.Modelo.CRUD;

import java.util.List;

public interface CrudProveedores<T> {

    public List<T> lista();

    public int setAgregar(T prov);

    public int setActualizar(T prov);

    public int setEliminar(int id);
}