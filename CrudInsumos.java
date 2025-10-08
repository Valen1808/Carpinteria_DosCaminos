package carpinteria.Modelo.CRUD;

import java.util.List;

public interface CrudInsumos<T> {

    public List<T> lista();

    public int setAgregar(T ins);

    public int setActualizar(T ins);

    public int setEliminar(int id);
}