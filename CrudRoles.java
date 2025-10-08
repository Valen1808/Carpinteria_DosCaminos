package carpinteria.Modelo.CRUD;

import java.util.List;

public interface CrudRoles<T> {

    public List<T> lista();

    public int setAgregar(T rol);

    public int setActualizar(T rol);

    public int setEliminar(int id);
}