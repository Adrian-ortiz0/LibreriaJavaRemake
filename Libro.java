public class Libro extends Material{
    
    private String autor;
    private String genero;
    private String isbn;

    public Libro(String codigo, String titulo, int añoPublicacion, int cantidadDisponible, String autor, String genero, String isbn) {
        super(codigo, titulo, añoPublicacion, cantidadDisponible);
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
    }

    @Override
    public String obtenerInformacion() {
        return "Titulo: " + super.getTitulo() + " Año: " + super.getAñoPublicacion() + " Autor: " + this.autor + " Genero: " + this.genero;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getIsbn() {
        return isbn;
    }
    
}
