import java.util.ArrayList;

/**
 *
 * @author Danie
 */
public abstract class Usuario {
    private String id;
    private String nombre;
    private String email;
    private ArrayList<Prestamo> prestamos;

    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.prestamos = new ArrayList<>();
    }
    
    public boolean puedePrestarMas(){
        return prestamos.size() < obtenerLimitePrestamos();
    }
    
    abstract public int obtenerLimitePrestamos();
    abstract public int obtenerDiasPrestamo();

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }
    
    
}