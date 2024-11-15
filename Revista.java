public class Revista extends Material{
    
    private String numeroEdicion;
    private String mes;
    private String editorial;

    public Revista(String codigo, String titulo, int añoPublicacion, int cantidadDisponible, String numeroEdicion, String mes, String editorial) {
        super(codigo, titulo, añoPublicacion, cantidadDisponible);
        this.editorial = editorial;
        this.mes = mes;
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public String obtenerInformacion() {
        return "Titulo: " + super.getTitulo() + " Editorial: " + this.editorial + " Numero de edicion: " + this.numeroEdicion;
    }

    public String getNumeroEdicion() {
        return numeroEdicion;
    }

    public String getMes() {
        return mes;
    }

    public String getEditorial() {
        return editorial;
    }
    
}