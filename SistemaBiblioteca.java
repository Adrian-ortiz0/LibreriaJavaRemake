import java.util.ArrayList;

/**
 *
 * @author Danie
 */
public class SistemaBiblioteca {
    private ArrayList<Material> materiales;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;

    public SistemaBiblioteca() {
        this.materiales = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }
    
    public Prestamo realizarPrestamo(Usuario usuario, Material material) {
        if (!material.verificarDisponibilidad() || !usuario.puedePrestarMas()) {
            return null;
        }
        String codigoPrestamo = generarCodigoPrestamo();
        Prestamo prestamo = new Prestamo(codigoPrestamo, usuario, material);
        
        material.actualizarStock(-1);
        usuario.getPrestamos().add(prestamo);
        prestamos.add(prestamo);
        
        return prestamo;
    }
    
    public boolean procesarDevolucion(Prestamo prestamo) {
        if (prestamo.realizarDevolucion()) {
            prestamo.getUsuario().getPrestamos().remove(prestamo);
            return true;
        }
        return false;
    }
    public ArrayList<Material> buscarMaterial(String criterio) {
        ArrayList<Material> resultados = new ArrayList<>();
        String criterioBusqueda = criterio.toLowerCase();
        
        for (Material material : materiales) {
            if (material.getTitulo().toLowerCase().contains(criterioBusqueda) ||
                material.getCodigo().toLowerCase().contains(criterioBusqueda)) {
                resultados.add(material);
            }
        }
        return resultados;
    }
     public double calcularMulta(Prestamo prestamo) {
        return prestamo.calcularMulta();
    }
     public String generarReportePrestamos() {
        StringBuilder reporte = new StringBuilder("=== REPORTE DE PRESTAMOS ===\n");
        
        for (Prestamo prestamo : prestamos) {
            reporte.append(String.format(
                "Préstamo: %s\nUsuario: %s\nMaterial: %s\nFecha Préstamo: %s\nFecha Devolucion: %s\n" +
                "Estado: %s\nMulta: $%.2f\n\n",
                prestamo.getCodigoPrestamo(),
                prestamo.getUsuario().getNombre(),
                prestamo.getMaterial().getTitulo(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion(),
                prestamo.isEstado() ? "Activo" : "Devuelto",
                prestamo.calcularMulta()
            ));
        }
        
        return reporte.toString();
    }
     private String generarCodigoPrestamo() {
        return "PREST-" + System.currentTimeMillis();
    }

    public void agregarMaterial(Material material) {
        materiales.add(material);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public ArrayList<Material> getMateriales() {
        return materiales;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }
    
    

}