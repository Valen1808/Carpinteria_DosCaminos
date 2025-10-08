package carpinteria.Modelo;

import java.util.Date;

public class Maquinaria {

    private int id;
    private String nombre;
    private String modelo;
    private String marca;
    private String numero_serie;
    private int id_tipo;
    private int id_estado;
    private Date fecha_compra;
    private double valor_compra;

    public Maquinaria() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_adquisicion) {
        this.fecha_compra = fecha_adquisicion;
    }

    public double getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(double valor_adquisicion) {
        this.valor_compra = valor_adquisicion;
    }
}