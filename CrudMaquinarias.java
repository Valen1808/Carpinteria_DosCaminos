package carpinteria.Modelo.CRUD;

import java.util.List;

public interface CrudMaquinarias<T> {

    public List<T> lista();

    public int setAgregar(T maq);

    public int setActualizar(T maq);

    public int setEliminar(int id);
}