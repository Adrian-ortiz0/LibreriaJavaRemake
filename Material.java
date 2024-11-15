public abstract class Material {
    
    private String codigo;
    private String titulo;
    private int añoPublicacion;
    private int cantidadDisponible;

    public Material(String codigo, String titulo, int añoPublicacion, int cantidadDisponible) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.añoPublicacion = añoPublicacion;
        this.cantidadDisponible = cantidadDisponible;
    }
    
    public boolean verificarDisponibilidad(){
        if(this.cantidadDisponible < 0){
            return false;
        }
        return true;
    }
    
    public void actualizarStock(int cantidad){
        this.cantidadDisponible += cantidad;
    }
    
    abstract public String obtenerInformacion();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    
}