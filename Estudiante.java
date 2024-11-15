public class Estudiante extends Usuario{
    
    private String carrera;
    private int semestre;

    public Estudiante(String id, String nombre, String email, String carrera, int semestre) {
        super(id, nombre, email);
        this.carrera = carrera;
        this.semestre = semestre;
    }   

    @Override
    public int obtenerLimitePrestamos() {
        return 3;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    @Override
    public int obtenerDiasPrestamo() {
        return 15;
    }
    
    
    
}
