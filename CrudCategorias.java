package carpinteria.Modelo.CRUD;

import java.util.List;

public interface CrudCategorias<T> {

    public List<T> lista();

    public int setAgregar(T cat);

    public int setActualizar(T cat);

    public int setEliminar(int id);
}