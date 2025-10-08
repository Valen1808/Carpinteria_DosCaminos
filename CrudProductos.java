/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package carpinteria.Modelo.CRUD;

import java.util.List;

public interface CrudProductos<ListaPro> {

    public List<ListaPro> lista();

    public int setAgregar(ListaPro pr);

    public int setEliminar(int id);

    public int setActualizar(ListaPro pr);
}
