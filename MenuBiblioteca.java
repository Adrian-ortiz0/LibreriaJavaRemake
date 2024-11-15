import java.util.Scanner;
import java.util.List;
import java.text.SimpleDateFormat;

public class MenuBiblioteca {
    private static SistemaBiblioteca sistema = new SistemaBiblioteca();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void ui(){
        // Datos de prueba
        inicializarDatosPrueba();

        while (true) {
            mostrarMenuPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarMateriales();
                    break;
                case 2:
                    buscarMaterial();
                    break;
                case 3:
                    realizarPrestamo();
                    break;
                case 4:
                    realizarDevolucion();
                    break;
                case 5:
                    mostrarPrestamosActivos();
                    break;
                case 6:
                    mostrarReportePrestamos();
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema!");
                    return;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
        System.out.println("1. Ver todos los materiales");
        System.out.println("2. Buscar material");
        System.out.println("3. Realizar prestamo");
        System.out.println("4. Realizar devolucion");
        System.out.println("5. Ver prestamos activos");
        System.out.println("6. Generar reporte de prestamos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static void mostrarMateriales() {
        System.out.println("\n=== MATERIALES DISPONIBLES ===");
        for (Material material : sistema.getMateriales()) {
            System.out.println(material.obtenerInformacion());
        }
    }

    private static void buscarMaterial() {
        System.out.print("\nIngrese criterio de busqueda: ");
        String criterio = scanner.nextLine();
        
        List<Material> resultados = sistema.buscarMaterial(criterio);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron materiales.");
            return;
        }

        System.out.println("\n=== RESULTADOS DE BÚSQUEDA ===");
        for (Material material : resultados) {
            System.out.println(material.obtenerInformacion());
        }
    }

    private static void realizarPrestamo() {
        System.out.println("\n=== REALIZAR PRÉSTAMO ===");
        
        // Mostrar usuarios
        System.out.println("\nUsuarios disponibles:");
        for (Usuario usuario : sistema.getUsuarios()) {
            System.out.println(usuario.getId() + " - " + usuario.getNombre());
        }
        
        System.out.print("Ingrese ID del usuario: ");
        String userId = scanner.nextLine();
        
        Usuario usuario = buscarUsuario(userId);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        // Verificar si el usuario puede realizar más préstamos
        if (!usuario.puedePrestarMas()) {
            System.out.println("El usuario ha alcanzado su limite de prestamos.");
            return;
        }

        // Mostrar materiales disponibles
        System.out.println("\nMateriales disponibles:");
        for (Material material : sistema.getMateriales()) {
            if (material.verificarDisponibilidad()) {
                System.out.println(material.getCodigo() + " - " + material.getTitulo());
            }
        }

        System.out.print("Ingrese código del material: ");
        String codigo = scanner.nextLine();
        
        Material material = buscarMaterial(codigo);
        if (material == null) {
            System.out.println("Material no encontrado.");
            return;
        }

        Prestamo prestamo = sistema.realizarPrestamo(usuario, material);
        if (prestamo != null) {
            System.out.println("Prestamo realizado exitosamente.");
            System.out.println("Código de prestamo: " + prestamo.getCodigoPrestamo());
            System.out.println("Fecha de devolucion: " + dateFormat.format(prestamo.getFechaDevolucion()));
        } else {
            System.out.println("No se pudo realizar el prestamo.");
        }
    }

    private static void realizarDevolucion() {
        System.out.println("\n=== REALIZAR DEVOLUCIÓN ===");
        
        // Mostrar préstamos activos
        System.out.println("\nPrestamos activos:");
        boolean hayPrestamosActivos = false;
        
        for (Prestamo prestamo : sistema.getPrestamos()) {
            if (prestamo.isEstado()) {
                System.out.println(prestamo.getCodigoPrestamo() + " - " + 
                                 prestamo.getMaterial().getTitulo() + " - " +
                                 prestamo.getUsuario().getNombre());
                hayPrestamosActivos = true;
            }
        }

        if (!hayPrestamosActivos) {
            System.out.println("No hay prestamos activos.");
            return;
        }

        System.out.print("Ingrese código de prestamo: ");
        String codigo = scanner.nextLine();
        
        Prestamo prestamo = buscarPrestamo(codigo);
        if (prestamo == null || !prestamo.isEstado()) {
            System.out.println("Prestamo no encontrado o ya devuelto.");
            return;
        }

        double multa = prestamo.calcularMulta();
        if (multa > 0) {
            System.out.println("Multa por retraso: $" + multa);
        }

        if (sistema.procesarDevolucion(prestamo)) {
            System.out.println("Devolucion realizada exitosamente.");
        } else {
            System.out.println("Error al procesar la devolucion.");
        }
    }

    private static void mostrarPrestamosActivos() {
        System.out.println("\n=== PRESTAMOS ACTIVOS ===");
        boolean hayPrestamos = false;
        
        for (Prestamo prestamo : sistema.getPrestamos()) {
            if (prestamo.isEstado()) {
                System.out.println("\nCodigo: " + prestamo.getCodigoPrestamo());
                System.out.println("Usuario: " + prestamo.getUsuario().getNombre());
                System.out.println("Material: " + prestamo.getMaterial().getTitulo());
                System.out.println("Fecha prestamo: " + dateFormat.format(prestamo.getFechaPrestamo()));
                System.out.println("Fecha devolucion: " + dateFormat.format(prestamo.getFechaDevolucion()));
                if (prestamo.estaVencido()) {
                    System.out.println("Estado: VENCIDO - Multa: $" + prestamo.calcularMulta());
                } else {
                    System.out.println("Estado: Al dia");
                }
                hayPrestamos = true;
            }
        }

        if (!hayPrestamos) {
            System.out.println("No hay prestamos activos.");
        }
    }

    private static void mostrarReportePrestamos() {
        System.out.println(sistema.generarReportePrestamos());
    }

    private static Usuario buscarUsuario(String id) {
        for (Usuario usuario : sistema.getUsuarios()) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    private static Material buscarMaterial(String codigo) {
        for (Material material : sistema.getMateriales()) {
            if (material.getCodigo().equals(codigo)) {
                return material;
            }
        }
        return null;
    }

    private static Prestamo buscarPrestamo(String codigo) {
        for (Prestamo prestamo : sistema.getPrestamos()) {
            if (prestamo.getCodigoPrestamo().equals(codigo)) {
                return prestamo;
            }
        }
        return null;
    }

    private static void inicializarDatosPrueba() {
        // Agregar algunos libros
        sistema.agregarMaterial(new Libro("L001", "El Señor de los Anillos", 1954, 2,
                                        "J.R.R. Tolkien", "Fantasia", "978-0544003415"));
        sistema.agregarMaterial(new Libro("L002", "Cien años de soledad", 1967, 3,
                                        "Gabriel Garcia Marquez", "Literatura", "978-0307474728"));

        // Agregar algunas revistas
        sistema.agregarMaterial(new Revista("R001", "National Geographic", 2023, 5,
                                          "123", "Enero", "National Geographic Society"));
        sistema.agregarMaterial(new Revista("R002", "Scientific American", 2023, 4,
                                          "423", "Febrero", "Springer Nature"));

        // Agregar usuarios
        sistema.agregarUsuario(new Estudiante("E001", "Juan Perez", "juan@email.com",
                                            "Ingenieria", 5));
        sistema.agregarUsuario(new Estudiante("E002", "Ana Garcia", "ana@email.com",
                                            "Medicina", 3));
        sistema.agregarUsuario(new Profesor("P001", "Carlos Lopez", "carlos@email.com",
                                          "Ciencias", "OF-101"));
    }
}