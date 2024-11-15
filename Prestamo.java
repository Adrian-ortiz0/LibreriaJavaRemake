import java.util.Date;

public class Prestamo {
    private String codigoPrestamo;
    private Usuario usuario;
    private Material material;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private boolean estado;

    public Prestamo(String codigoPrestamo, Usuario usuario, Material material) {
        this.codigoPrestamo = codigoPrestamo;
        this.usuario = usuario;
        this.material = material;
        this.fechaPrestamo = new Date();
        long diasPrestamo = usuario.obtenerDiasPrestamo() * 24 * 60 * 60 * 1000L;
        this.fechaDevolucion = new Date(fechaPrestamo.getTime() + diasPrestamo);
        this.estado = true;
    }
    
    public double calcularMulta(){
        if(!estado || !estaVencido()){
            return 0;
        }
        long diferencia = new Date().getTime() - fechaDevolucion.getTime();
        long diasRetraso = diferencia / (24 * 60 * 60 * 1000);
        return diasRetraso * 1000;
    }
    
    public boolean realizarDevolucion(){
        if(!estado){
            return false;
        }
        estado = false;
        material.actualizarStock(1);
        return true;
    }
    
    public boolean estaVencido() {
        return estado && new Date().after(fechaDevolucion);
    }

    public String getCodigoPrestamo() {
        return codigoPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public boolean isEstado() {
        return estado;
    }
    
    
}