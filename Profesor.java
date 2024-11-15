public class Profesor extends Usuario{
    
    private String departamento;
    private String oficina;

    public Profesor(String id, String nombre, String email, String departamento, String oficina) {
        super(id, nombre, email);
        this.departamento = departamento;
        this.oficina = oficina;
    }

    @Override
    public int obtenerLimitePrestamos() {
        return 5;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    @Override
    public int obtenerDiasPrestamo() {
        return 30;
    }
    
}
