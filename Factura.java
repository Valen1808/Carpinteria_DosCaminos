package carpinteria.Modelo;

import java.util.Date;
import java.util.List;

public class Factura {

    private int id;
    private int id_usuario;
    private int id_met_pago;
    private Date fecha;
    private double total;
    private String estado;
    private int id_pedido;
    private List<DetalleFactura> detalles; // Para guardar los detalles

    public Factura() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_met_pago() {
        return id_met_pago;
    }

    public void setId_met_pago(int id_met_pago) {
        this.id_met_pago = id_met_pago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
    
    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}