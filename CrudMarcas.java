package carpinteria.Modelo.CRUD;

import java.util.List;

public interface CrudMarcas<T> {

    public List<T> lista();

    public int setAgregar(T mar);

    public int setActualizar(T mar);

    public int setEliminar(int id);
}