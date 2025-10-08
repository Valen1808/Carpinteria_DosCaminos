
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package carpinteria.Modelo.CRUD;

import java.util.List;

/**
 *
 * @author usuario
 */
public interface Crud<Lista> {

    public List<Lista> lista();

    public int setAgregar(Lista tr);

    public int setActualizar(Lista tr);

    public int setEliminar(int id);
}
